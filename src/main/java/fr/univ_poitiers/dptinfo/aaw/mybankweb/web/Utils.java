package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AuthToken;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AuthTokenRepository;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Date;
import java.util.List;

public class Utils {

    //pas utilisé
    public static boolean isTokenExpired(AuthTokenRepository authTokenRepository, Integer userId) {

        List<AuthToken> tokens = authTokenRepository.findByUserId(userId);
        Date currentDate = new Date(System.currentTimeMillis());
        boolean isTokenExpired = true;
        int counter = 0;
        while (isTokenExpired || counter < tokens.size()) {
            if (currentDate.before(tokens.get(counter).getExpiredDate())) {
                isTokenExpired = false;
            }
            counter++;
        }
        return isTokenExpired;
    }
}
