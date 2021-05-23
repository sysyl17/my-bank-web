package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Account;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.AccountRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Virement;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.VirementRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/virement")
public class VirementControler {
    private VirementRepository virementRepository;
    private AccountRepository accountRepository;

    public VirementControler(VirementRepository virementRepository, AccountRepository accountRepository) {
        this.virementRepository = virementRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    Collection<Virement> getVirements() {
        List<Virement> all = virementRepository.findAll();
        return all;
    }

    @GetMapping("/recu/{id}")
    Collection<Virement> getVirementRecu(@PathVariable("id") Integer idUser) {
        List<Account> accountUser = accountRepository.findByUserId(idUser);
        System.out.println(accountUser);
        List<Virement> all = new ArrayList<>();
        for (Account account : accountUser) {
            Optional<Virement> virement = virementRepository.findByIdCompteVers(account.getId());
            if (virement.isPresent()) {
                all.add(virement.get());
            }
        }
        return all;
    }

    @GetMapping("/effectue/{id}")
    Collection<Virement> getVirementEffectue(@PathVariable("id") Integer idUser) {
        List<Account> accountUser = accountRepository.findByUserId(idUser);
        List<Virement> all = new ArrayList<>();
        for (Account account : accountUser) {
            Optional<Virement> virement = virementRepository.findByIdCompteDepuis(account.getId());
            if (virement.isPresent()) {
                all.add(virement.get());
            }
        }

        return all;
    }

}
