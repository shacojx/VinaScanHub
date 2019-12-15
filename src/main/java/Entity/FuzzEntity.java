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
public class FuzzEntity {

    private String link;
    private String vuln;
    private String payload;
    private String response;

    public FuzzEntity() {
    }

    public FuzzEntity(String link, String vuln, String payload, String response) {
        this.link = link;
        this.vuln = vuln;
        this.payload = payload;
        this.response = response;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getVuln() {
        return vuln;
    }

    public void setVuln(String vuln) {
        this.vuln = vuln;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "FuzzEntity{" + "link=" + link + ", vuln=" + vuln + ", payload=" + payload + ", response=" + response + '}';
    }

}
