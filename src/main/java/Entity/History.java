/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author hunternight
 */
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "History")
public class History implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private Integer toal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getToal() {
        return toal;
    }

    public void setToal(Integer toal) {
        this.toal = toal;
    }

    public History() {
    }

    public History(Long id, Date date, String url, Integer toal) {
        this.id = id;
        this.date = date;
        this.url = url;
        this.toal = toal;
    }

    @Override
    public String toString() {
        return "History{" + "id=" + id + ", date=" + date + ", url=" + url + ", toal=" + toal + '}';
    }

}
