package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;

import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/virement")
public class VirementControler {

    @Autowired
    private VirementRepository virementRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping
    Collection<Virement> getVirements() {
        return virementRepository.findAll();
    }

    /**
     * Permet de d'obtenir via la BD la liste des virements recu.
     * @param idUser Un identifiant d'utilisateur
     * @return Une collection de CompleteAccount qui permet de décrire une ligne de virement pour faciliter l'affichage
     * @see CompleteAccount
     */
    @GetMapping("/recu/{id}")
    Collection<CompleteAccount> getVirementRecu(@PathVariable("id") Integer idUser) {
        List<CompleteAccount> all = new ArrayList<>();
        List<Virement> virements = virementRepository.findAll();
        for (Virement virement : virements) {
            Account accVers = accountRepository.findById(virement.getIdCompteVers()).orElseThrow(IllegalArgumentException::new);
            if (accVers.getUserId().equals(idUser)) {
                CompleteAccount cpAccount = new CompleteAccount();
                cpAccount.setId(virement.getId());
                cpAccount.setMotif(virement.getMotif());
                cpAccount.setMontant(virement.getMontant());
                cpAccount.setDate(formatDate(virement.getDate()));

                cpAccount.setNomCompteVers(accVers.getName());
                cpAccount.setNameUserCompteVers(userRepository.findById(idUser).orElseThrow(IllegalArgumentException::new).getName());

                Account accDepuis = accountRepository.findById(virement.getIdCompteDepuis()).orElseThrow(IllegalArgumentException::new);
                cpAccount.setNomCompteDepuis(accDepuis.getName());
                cpAccount.setNameUserCompteDepuis(userRepository.findById(accDepuis.getUserId()).orElseThrow(IllegalArgumentException::new).getName());
                all.add(cpAccount);
            }
        }
        return all;
    }

    /**
     * Permet de d'obtenir via la BD la liste des virements envoyé par le client.
     * @param idUser Un identifiant d'utilisateur
     * @return Une collection de CompleteAccount qui permet de décrire une ligne de virement pour faciliter l'affichage
     * @see CompleteAccount
     */
    @GetMapping("/effectue/{id}")
    Collection<CompleteAccount> getVirementEffectue(@PathVariable("id") Integer idUser) {
        List<CompleteAccount> all = new ArrayList<>();
        List<Virement> virements = virementRepository.findAll();
        for (Virement virement : virements) {
            Account accDepuis = accountRepository.findById(virement.getIdCompteDepuis()).orElseThrow(IllegalArgumentException::new);
            if (accDepuis.getUserId().equals(idUser)) {
                CompleteAccount cpAccount = new CompleteAccount();
                cpAccount.setId(virement.getId());
                cpAccount.setMotif(virement.getMotif());
                cpAccount.setMontant(virement.getMontant());
                cpAccount.setDate(formatDate(virement.getDate()));

                cpAccount.setNomCompteDepuis(accDepuis.getName());
                cpAccount.setNameUserCompteDepuis(userRepository.findById(accDepuis.getUserId()).orElseThrow(IllegalArgumentException::new).getName());

                Account accVers = accountRepository.findById(virement.getIdCompteVers()).orElseThrow(IllegalArgumentException::new);
                cpAccount.setNomCompteVers(accVers.getName());
                cpAccount.setNameUserCompteVers(userRepository.findById(accVers.getUserId()).orElseThrow(IllegalArgumentException::new).getName());

                all.add(cpAccount);
            }
        }
        return all;
    }

    public String formatDate(Date date){
        DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
                DateFormat.SHORT,
                DateFormat.SHORT);

        return shortDateFormat.format(date);
    }

}
