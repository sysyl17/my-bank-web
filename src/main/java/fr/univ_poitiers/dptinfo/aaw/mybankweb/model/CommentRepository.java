package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}