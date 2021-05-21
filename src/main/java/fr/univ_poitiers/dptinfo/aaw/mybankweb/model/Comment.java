package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;



import javax.persistence.*;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 4000)
    private String text;

    @OneToOne
    private User user;

    public Comment(Long id, String text, User user) {
        this.id = id;
        this.text = text;
        this.user = user;
    }

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
