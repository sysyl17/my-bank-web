package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Account;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AccountRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/accounts")
public class AccountControler {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AccountRepository accountRepository;


    @GetMapping
    Collection<Account> getAccounts() {
        return accountRepository.findAll();
    }


    @GetMapping("/{id}")
    Collection<Account> getUserAccounts(@PathVariable("id") Integer id) {
        return accountRepository.findByUserId(id);
    }

    @PostMapping
    void save(@RequestBody Account account) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        account.setUserId(principal.getId());
        System.out.println(account);
        accountRepository.save(account);
        log.info("Sauvegarde d'un nouveau compte : {}", account.getName());
    }

}
