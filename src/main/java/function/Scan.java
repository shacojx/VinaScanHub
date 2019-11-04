/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import Scan.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import payload.*;
import signature.*;

/**
 *
 * @author Shaco JX
 */
public class Scan {

    private static HashSet<String> hashSet = new HashSet<>();
    public ArrayList<String> list_vuln = new ArrayList<>();

    public void Scan(String url) throws IOException {

        WebCrawlerWithDepth wc = new WebCrawlerWithDepth();
        System.out.println("Spider level: " + wc.MAX_DEPTH);
        wc.getPageLinks(url, 0, url);
        System.out.println("==========================================");
        System.out.println("Total link: " + wc.links.size());

        for (String s : wc.links) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------------------------------------");
        this.scanVuln(wc.links);
    }

    public void scanVuln(HashSet<String> listURL) throws IOException {
        System.out.println("Scan SQLi");
        Scan_SQLi scansqli = new Scan_SQLi();
        LoadData data = new LoadData();
        data.LoadPayload();
        data.LoadSignature();
        System.out.println("Load data done "+listURL.size());
        for (String sURL : listURL) {
            System.out.println("Foring "+ sURL);
            if(sURL.contains("?") && sURL.contains("=") && !sURL.contains("jpg") && !sURL.contains("png")){
                System.out.println("if "+sURL);
                System.out.println("pay "+data.pay_SQLi.size());
                System.out.println("sig "+data.sig_SQLi.size());
                for(String pay : data.pay_SQLi){
                    if(sURL.contains("#")){
                        sURL = sURL.split("#")[0];
                    }
                    System.out.println("Checking "+sURL);
                    Document doc = Jsoup.connect(sURL+pay).get();
                    System.out.println("checking "+sURL+"pay"+pay);
                    for(String sig : data.sig_SQLi){
                        if(doc.body().toString().contains(sig)){
                            System.out.println("Get SQL injection: "+sURL+" Payload: "+pay+ "Sig: "+sig);
                            
                        }
                    }
                }
            }
        }
        System.out.println("Scan end!");
        
      
    }

 

}
