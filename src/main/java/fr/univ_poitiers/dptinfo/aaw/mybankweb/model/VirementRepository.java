package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VirementRepository extends JpaRepository<Virement, Integer> {
    Optional<Virement> findByIdCompteVers(Integer integer);
    Optional<Virement> findByIdCompteDepuis(Integer integer);
}
