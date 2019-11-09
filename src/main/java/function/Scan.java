/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import Scan.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import PaySig.*;

/**
 *
 * @author Shaco JX
 */
public class Scan {

    public static ArrayList<String> list_vuln = new ArrayList<>();
    public static HashSet<String> checkURLGET = new HashSet<>();
    public static HashSet<String> checkURLPOST = new HashSet<>();

    Scan_BlindSQLi sBlinkSQLi = new Scan_BlindSQLi();
    Scan_SQLi sSQLi = new Scan_SQLi();
    Scan_XMLXpatchi sXMLXpatchi = new Scan_XMLXpatchi();
    Scan_XSS sXSS = new Scan_XSS();
    Scan_CodeInjection sCI = new Scan_CodeInjection();
    Scan_LFI sLFI = new Scan_LFI();

    psSQLi psSQLin = new psSQLi();
    psXMLXpatchi psXMLXpatchin = new psXMLXpatchi();
    psXSS psXSS = new psXSS();
    psCodeInjection psCI = new psCodeInjection();
    psLFI psLFI = new psLFI();

    public void Scan(String url) throws IOException {
//        WebCrawlerWithDepth wc = new WebCrawlerWithDepth();
//        WebCrawlerWithDepth spider = new WebCrawlerWithDepth();
//        System.out.println("Spider level: " + spider.MAX_DEPTH);
//        spider.getPageLinks(url, 0, url);
//        System.out.println("==========================================");
//        System.out.println("Total link: " + spider.links.size());
//
//        spider.links.forEach((s) -> {
//            System.out.println(s);
//        });
//        System.out.println("---------------------------------------------------------------------------------");
//        this.scanVuln(spider.links);

        SpiderWeb spider = new SpiderWeb();
        System.out.println("Spider level: " + spider.MAX_DEPTH);
        spider.getPageLinks(url, 0, url);
        System.out.println("==========================================");
        System.out.println("Total link: " + spider.links.size());

        spider.links.forEach((s) -> {
            System.out.println(s);
        });
        System.out.println("---------------------------------------------------------------------------------");
        this.scanVuln(spider.links);
    }

    public void scanVuln(HashSet<String> listURL) throws IOException {
        for (String sURL : listURL) {
            if (!sURL.contains("png") && !sURL.contains("jpg")) {
                if (sURL.contains("#")) {
                    sURL = sURL.split("#")[0];
                }
                if (sURL.contains("?") && sURL.contains("=")) {
                    if (!checkURLGET.contains(sURL.split("\\?")[0])) {
                        this.scanMethodGet(sURL);
                    }
                }
                try {
                    Document document = Jsoup.connect(sURL).userAgent("Mozilla").followRedirects(false).get();
                    Elements linksOnPage = document.select("form");
                    for (Element element : linksOnPage) {
                        String temp = "";
                        try {
                            temp = element.attr("abs:action");
                        } catch (Exception e) {
                        }

                        if (temp.length() == 0) {
                            temp = sURL;
                        }

                        if (temp.contains("?") && sURL.contains("=") && !checkURLGET.contains(temp.split("\\?")[0])) {
                            this.scanMethodGet(temp);
                        }
                        String method = element.attr("method").toLowerCase();
                        if (method.contains("get")) {
                            if (!checkURLGET.contains(temp)) {
                                this.scanMethodGetPost(element, temp);
                            }
                        } else {
                            if (method.contains("post")) {
                                if (!checkURLPOST.contains(temp)) {
                                    this.scanMethodGetPost(element, temp);
                                }
                            }
                        }
//                        if (!checkURLGET.contains(temp) && !checkURLPOST.contains(temp)) {
//                            this.scanMethodGetPost(element, temp);
//                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error scanVuln: " + sURL + " ||| " + e);
                }

            }
        }
        System.out.println("Scan end!");
    }

    public void scanMethodGet(String urlAction) throws IOException {
        this.sSQLi.scanSQLin(null, urlAction, this.psSQLin.getArrPaySQLin());
        this.sXMLXpatchi.scanXMLXpatchin(null, urlAction, this.psXMLXpatchin.getArrPayXMLXPathin());
        this.sXSS.scanXSS(null, urlAction, this.psXSS.getArrPayXSS());
        this.sCI.scanCI(null, urlAction, this.psCI.getArrPayCI());
        this.sLFI.scanLFI(null, urlAction, this.psLFI.getArrPayLFI());
//        BlindSQLinjection(urlAction);
    }

    public void scanMethodGetPost(Element element, String urlAction) throws IOException {
        this.sSQLi.scanSQLin(element, urlAction, this.psSQLin.getArrPaySQLin());
        this.sXMLXpatchi.scanXMLXpatchin(element, urlAction, this.psXMLXpatchin.getArrPayXMLXPathin());
        this.sXSS.scanXSS(element, urlAction, this.psXSS.getArrPayXSS());
        this.sCI.scanCI(element, urlAction, this.psCI.getArrPayCI());
        this.sLFI.scanLFI(element, urlAction, this.psLFI.getArrPayLFI());
//        BlindSQLinjection(urlAction);
    }
}
