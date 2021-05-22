package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Account;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    Collection<Account> getUserAccounts(@PathVariable("id") Integer id) {
        List<Account> all = accountRepository.findByUserId(id);
        return all;
    }



}
