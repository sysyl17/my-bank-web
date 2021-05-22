package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findById(Integer id);

    List<Account> findByUserId(Integer user);
}
