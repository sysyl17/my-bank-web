package fr.univ_poitiers.dptinfo.aaw.mybankweb.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class AuthToken {

    @Id
    private String token;
    private Integer userId;
    private Date expiredDate;

    public AuthToken(String token, Integer userId, Date expiredDate) {
        this.token = token;
        this.userId = userId;
        this.expiredDate = expiredDate;
    }

    public AuthToken() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }


}
