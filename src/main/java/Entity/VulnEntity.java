/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Shaco JX
 */
import java.io.Serializable; 
import javax.persistence.*;

@Entity
@Table(name = "VulnEntity")
public class VulnEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private Long historyId;
    @Column(nullable = false)
    private String vuln_name;
    @Column(nullable = false)
    private String link_vuln;
    @Column(nullable = false)
    private String payload;
    @Column(nullable = false)
    private String signature;

    public VulnEntity() {
    }

    public VulnEntity(String vuln_name, String link_vuln, String payload, String signature) {
        this.vuln_name = vuln_name;
        this.link_vuln = link_vuln;
        this.payload = payload;
        this.signature = signature;
    }
 
    public String getVuln_name() {
        return vuln_name;
    }

    public void setVuln_name(String vuln_name) {
        this.vuln_name = vuln_name;
    }

    public String getLink_vuln() {
        return link_vuln;
    }

    public void setLink_vuln(String link_vuln) {
        this.link_vuln = link_vuln;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    } 

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }
    
    
}
