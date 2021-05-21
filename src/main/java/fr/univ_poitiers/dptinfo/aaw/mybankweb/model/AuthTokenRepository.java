package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {

    List<AuthToken> findByUserId(Integer user);
}