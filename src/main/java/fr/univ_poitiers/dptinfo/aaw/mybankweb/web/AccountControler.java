package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Account;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AccountRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AuthTokenRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static fr.univ_poitiers.dptinfo.aaw.mybankweb.web.Utils.isTokenExpired;

@RestController
@RequestMapping("/api/accounts")
public class AccountControler {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Value("${fr.univ_poitiers.dptinfo.aaw.auth.expired}")
    private int expiredTime;

    public AccountControler(AccountRepository accountRepository, AuthTokenRepository authTokenRepository) {
        this.accountRepository = accountRepository;
        this.authTokenRepository = authTokenRepository;
    }

    @GetMapping
    Collection<Account> getAccounts() {
        List<Account> all = accountRepository.findAll();
        return all;
    }


    @GetMapping("/{id}")
    Collection<Account> getUserAccounts(@PathVariable("id") Integer id) {
        List<Account> all = accountRepository.findByUserId(id);
        return all;
    }

    @PostMapping
    void save(@RequestBody Account account) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        /*Date expiredDate = new Date(System.currentTimeMillis() + expiredTime);
        authTokenRepository.findByUserId(principal.getId());*/

        account.setUserId(principal.getId());
        System.out.println(account);
        accountRepository.save(account);
    }

}
