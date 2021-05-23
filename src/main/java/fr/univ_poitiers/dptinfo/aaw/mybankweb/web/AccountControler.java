package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Account;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AccountRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountControler {

    private AccountRepository accountRepository;

    public AccountControler(AccountRepository repo) {
        this.accountRepository = repo;
    }

    @GetMapping
    Collection<Account> getAccounts() {
        List<Account> all = accountRepository.findAll();
        return all;
    }

    @PostMapping
    void save(@RequestBody Account account) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        account.setUserId(principal.getId());
        System.out.println(account);
        accountRepository.save(account);
    }

    @GetMapping("/{id}")
    Collection<Account> getUserAccounts(@PathVariable("id") Integer id) {
        List<Account> all = accountRepository.findByUserId(id);
        return all;
    }



}
