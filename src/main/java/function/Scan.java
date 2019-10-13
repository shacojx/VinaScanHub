/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import payload.A1p;
import payload.A3p;
import signature.A1;
import signature.A3;

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
        System.out.println("Total link: " + wc.links.size());

        for (String s : wc.links) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------------------------------------");
        this.scanVuln(wc.links);
    }

    public void attackGetPost(Element element, String urlAction, ArrayList<String> listPay, ArrayList<String> listSig, String vulnName) throws IOException {
       
        Map<String, String> map = new HashMap<String, String>();
        Elements ele = null;
        boolean checkVuln = false;

        for (String sPay : listPay) {
            try {
                map = new HashMap<String, String>();
                ele = element.getElementsByAttribute("name");
                for (Element e1 : ele) {
                    if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                        map.put(e1.attr("name"), sPay);
                    } else {
                        if (e1.attr("name").length() != 0) {
                            map.put(e1.attr("name"), e1.attr("value"));
                        }
                    }
                }

                Document doc = null;
                if (element.attr("method").contains("post")) {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).post();
                } else {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).get();
                }

                for (String sSig : listSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
                        list_vuln.add(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
                        break;
                    }
                }
                if (checkVuln) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error attackGetPost: " + e);
            }
        }
    }

    public void attackGet(String urlAction, ArrayList<String> listPay, ArrayList<String> listSig, String vulnName) throws IOException {

        String fURL = urlAction.split("\\?")[0];
        String lURL = urlAction.split("\\?")[1];

        Map<String, String> mapParemeter;
        boolean checkVuln = false;
        for (String sPay : listPay) {
            try {
                mapParemeter = new HashMap<>();
                for (String s : lURL.split("&")) {
                    String key = s.split("\\=")[0];
                    String value = "";
                    try {
                        value = s.split("\\=")[1] + sPay;
                    } catch (Exception e) {
                        System.out.println("Error attackGet: " + e);
                    }
                    mapParemeter.put(key, value);
                }


                Document doc = Jsoup.connect(fURL).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();

                for (String sSig : listSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println("GET " + vulnName + " : " + urlAction);
                        list_vuln.add("GET " + vulnName + " : " + urlAction);
                        break;
                    }
                }
                if (checkVuln) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error attackGet: " + urlAction + " ||| " + e);
            }
        }
    }

    public void scanVuln(HashSet<String> listURL) throws IOException {
        System.out.println("[ Checking Vuln Top 10 OWASP ]");
        System.out.println("Please waiting... Checking ...");

        for (String sURL : listURL) {
            if (!sURL.contains("jpg")) {
                if (sURL.contains("?")) {
                    String temp = sURL.split("\\?")[0];
                    if (!hashSet.contains(temp)) {
                        hashSet.add(temp);
                        this.scanMethodGet(sURL);
                    }
                } else {
                    try {
                        Document document = Jsoup.connect(sURL).userAgent("Mozilla").followRedirects(false).get();
                        Elements linksOnPage = document.select("form");
                        for (Element element : linksOnPage) {
                            String temp = "";
                            try {
                                temp = element.attr("abs:action");
                            } catch (Exception e) {
                            }
                            if (temp.length() != 0) {
                                if (!hashSet.contains(temp)) {
                                    hashSet.add(temp);
//                                    System.out.println("Hashset : " + temp);
                                    if (temp.contains("?")) {
                                        this.scanMethodGet(temp);
                                    }
                                    this.scanMethodGetPost(element, temp);
                                }
                            } else {
                                this.scanMethodGetPost(element, sURL);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Error scanVuln: " + sURL + " ||| " + e);
                    }
                }
            }
        }
        System.out.println("Scan End!");
    }

    public void scanMethodGet(String urlAction) throws IOException {
        A1 sigA1 = new A1();
        A3 sigA3 = new A3();
        A1p payA1 = new A1p();
        A3p payA3 = new A3p();

        this.attackGet(urlAction, payA1.HTMLinjection(), sigA1.HTMLinjection(), "HTML injection");
        this.attackGet(urlAction, payA1.IFrameInjection(), sigA1.IFrameInjection(), "IFrame injection");
        this.attackGet(urlAction, payA1.SQLinjection(), sigA1.SQLinjection(), "SQL injection");
        this.attackGet(urlAction, payA3.XSS(), sigA3.XSS(), "XSS");
        this.attackGet(urlAction, payA1.XMLXPathInjection(), sigA1.XMLXPathInjection(), "XML/XPath injection");
    }

    public void scanMethodGetPost(Element element, String urlAction) throws IOException {
        A1 sigA1 = new A1();
        A3 sigA3 = new A3();
        A1p payA1 = new A1p();
        A3p payA3 = new A3p();

        this.attackGetPost(element, urlAction, payA1.HTMLinjection(), sigA1.HTMLinjection(), "HTML injection");
        this.attackGetPost(element, urlAction, payA1.IFrameInjection(), sigA1.IFrameInjection(), "IFrame injection");
        this.attackGetPost(element, urlAction, payA1.SQLinjection(), sigA1.SQLinjection(), "SQL injection");
        this.attackGetPost(element, urlAction, payA3.XSS(), sigA3.XSS(), "XSS");
        this.attackGetPost(element, urlAction, payA1.XMLXPathInjection(), sigA1.XMLXPathInjection(), "XML/XPath injection");
    }

}
