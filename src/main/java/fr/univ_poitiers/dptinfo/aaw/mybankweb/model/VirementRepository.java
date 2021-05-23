package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VirementRepository extends JpaRepository<Virement, Integer> {
    List<Virement> findByIdCompteVers(Integer integer);
    List<Virement> findByIdCompteDepuis(Integer integer);
}
