/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author toanvv1
 */
public class LinkEntity {
    private String link;
    private String header_response;
    private String response;

    public LinkEntity(String link, String header_response, String response) {
        this.link = link;
        this.header_response = header_response;
        this.response = response;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHeader_response() {
        return header_response;
    }

    public void setHeader_response(String header_response) {
        this.header_response = header_response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "LinkEntity{" + "link=" + link + ", header_response=" + header_response + ", response=" + response + '}';
    }
    
    
    
}
