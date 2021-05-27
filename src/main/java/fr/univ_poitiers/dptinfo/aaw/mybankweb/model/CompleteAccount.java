package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe utilistaire qui décrire une ligne de virement afin que l'affichage soit plus facile
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteAccount {
    private Integer id;
    private String nomCompteDepuis;
    private String nomCompteVers;
    private String nameUserCompteDepuis;
    private String nameUserCompteVers;
    private double montant;
    private String motif;


}
