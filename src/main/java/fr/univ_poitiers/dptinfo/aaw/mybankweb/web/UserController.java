package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;


import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AuthToken;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AuthTokenRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.User;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api/user")
class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${fr.univ_poitiers.dptinfo.aaw.auth.token}")
    private String authToken;

    @Value("${fr.univ_poitiers.dptinfo.aaw.auth.expired}")
    private int expiredTime;


    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/current")
    ResponseEntity<User> getUserConnected(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserConnected(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id).orElse(new User());
        return ResponseEntity.ok().body(user);
    }

    //permet de réinitialiser la date d'expiration du token
    @GetMapping("/tokenRefresh")
    ResponseEntity<User> refreshTokenExpiration(HttpServletRequest request) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Date expiredDate = new Date(System.currentTimeMillis() + expiredTime);

        Cookie token = WebUtils.getCookie(request, authToken);
        if (token != null) {
            Optional<AuthToken> byUserId = authTokenRepository.findById(token.getValue());
            byUserId.ifPresent((authToken) -> {
                authToken.setExpiredDate(expiredDate);
                authTokenRepository.save(authToken);
                log.info("réinitialisation de la date d'expiration du token : {}", authToken.getExpiredDate());
            });
        }

        return ResponseEntity.ok().body(principal);
    }

    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) throws IOException {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            password
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final User user = (User) authentication.getPrincipal();
            String sessionId = UUID.randomUUID().toString();
            Date expiredDate = new Date(System.currentTimeMillis() + expiredTime);
            AuthToken token = new AuthToken(sessionId, user.getId(), expiredDate);
            authTokenRepository.save(token);
            log.info("new session : {} expired in {} user {}", token.getToken(), token.getExpiredDate().toString(), user.getUsername());

            Cookie tokenCookie = new Cookie(authToken, token.getToken());
            tokenCookie.setPath("/");
            tokenCookie.setHttpOnly(true);
            tokenCookie.setMaxAge(expiredTime);
            response.addCookie(tokenCookie);
            response.sendRedirect("/espacePerso");
        } catch (Exception e) {
            log.info("Couple login/password incorrect");
            response.sendError(HttpStatus.LOCKED.value());

        }
    }
}
