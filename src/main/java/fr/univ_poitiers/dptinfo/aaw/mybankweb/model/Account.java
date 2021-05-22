package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private Integer id;
    private Integer userId;
    private String name;
    private double balance;


}
