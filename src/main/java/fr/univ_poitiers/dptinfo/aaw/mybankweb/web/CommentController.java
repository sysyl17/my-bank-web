package fr.univ_poitiers.dptinfo.aaw.mybankweb.web;


import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.Comment;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.CommentRepository;
import fr.univ_poitiers.dptinfo.aaw.mybankweb.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentRepository commentRepository;

    public CommentController(CommentRepository repo){
        this.commentRepository = repo;
    }

    @GetMapping
    Collection<Comment> users() {
        List<Comment> all = commentRepository.findAll();
        all.forEach(comment->{
            comment.getUser().setPassword(null);
        });
        return all;
    }

    @PostMapping
    void save(@RequestBody Comment comment) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUser(principal);
        commentRepository.save(comment);
    }

    @DeleteMapping
    void delete(@RequestParam Long id) {
        commentRepository.delete(new Comment(id, null, null));
    }
}
