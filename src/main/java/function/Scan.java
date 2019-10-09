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

    public void Scan(String url) throws IOException {
//        System.out.println("[ Checking Vuln Top 10 OWASP ]");
//        System.out.println("Please waiting... Checking ...");
//        A1 sigA1 = new A1();
//        A3 sigA3 = new A3();
//        A1p payA1 = new A1p();
//        A3p payA3 = new A3p();
//        checkVuln(payA1.SQLinjection(), sigA1.SQLinjection(), "SQL injection", list);
//        checkVuln(payA1.HTMLinjection(), sigA1.HTMLinjection(), "HTML injection", list);
//        checkVuln(payA1.XMLXPathInjection(), sigA1.XMLXPathInjection(), "XML/XPath injection", list);
//        checkVuln(payA1.IFrameInjection(), sigA1.IFrameInjection(), "IFrame injection", list);
//        checkVuln(payA3.XSS(), sigA3.XSS(), "XSS", list);
//        System.out.println("=== STOPED ===");

        WebCrawlerWithDepth wc = new WebCrawlerWithDepth();
        System.out.println("Spider level: "+wc.MAX_DEPTH);
        wc.getPageLinks(url, 0, url);
        System.out.println("Total link: " + wc.links.size());

        for (String s : wc.links) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------------------------------------");
        this.scanVuln(wc.links);
    }

    public void checkVuln(ArrayList<String> listPay, ArrayList<String> listSig, String vulnName, HashSet<String> list) throws IOException {
        String vuln = "";
        removeDup remove = new removeDup();
        ArrayList<String> list_vuln = new ArrayList<>();
        ArrayList<String> list_tmp = new ArrayList<>();
        for (String x : list) {
            if (x.contains("?") && x.contains("=") && !x.contains("jpg")) {
                list_tmp.add(x.split("=")[0]);
            }
        }
        ArrayList<String> list_point = remove.removeDuplicates(list_tmp);
        for (String x : list_point) {
            vuln = "";
            for (String y : listPay) {
                for (String z : listSig) {
//                        System.out.println("check " + x + y + z);
                    Connection.Response respx = (Connection.Response) Jsoup.connect(x+"=1" + y).execute();
                    Document docx = respx.parse();
                    String bodyx = docx.body().toString();
                    if (bodyx.contains(z)) {
                        list_vuln.add(x+"=");
                        vuln = "vuln";
                        break;
                    }
                }
                if (!vuln.equals("")) {
                    break;
                }
            }

        }

        ArrayList<String> list_entrypoint = remove.removeDuplicates(list_vuln);

        for (String x : list_entrypoint) {
            System.out.println(vulnName + " : " + x);
        }

    }
    

    public void attackGetPost(Element element, String urlAction, ArrayList<String> listPay, ArrayList<String> listSig, String vulnName) throws IOException {
//        System.out.println("2222222222222222222222222222222222222");
//        System.out.println(urlAction);
        Map<String, String> map = new HashMap<String, String>();
//        System.out.println(element);
        Elements ele = element.getElementsByAttribute("name");
        boolean checkVuln = false;

        for (String sPay : listPay) {
            try {
                map = new HashMap<String, String>();
                for (Element e1 : ele) {
                    if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                        map.put(e1.attr("name"), sPay);
                    } else {
                        if (e1.attr("name").length() != 0) {
                            map.put(e1.attr("name"), e1.attr("value"));
                        }
                    }
                }
                //        System.out.println(map);
                Document doc = null;
                if (element.attr("method").contains("get") && !hashSet.contains(element.attr("abs:action"))) {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).get();
                } else {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).post();
                }
//        System.out.println(doc.toString());
//        System.out.println(doc.location());
                for (String sSig : listSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
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
//        System.out.println("3333333333333333333333333333333333");
//        System.out.println(urlAction);
        String fURL = urlAction.split("\\?")[0];
        String lURL = urlAction.split("\\?")[1];
//        System.out.println(fURL);
//        System.out.println(lURL);
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
//        System.out.println(mapParemeter);

                Document doc = Jsoup.connect(fURL).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();
//        System.out.println(doc.toString());
//        System.out.println(doc.location());
                for (String sSig : listSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println("GET " + vulnName + " : " + urlAction);
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
                        Elements linksOnPage = document.select("form[action]");
                        for (Element element : linksOnPage) {
                            String temp = element.attr("abs:action");
                            if (!hashSet.contains(temp)) {
                                hashSet.add(temp);
                                System.out.println("Hashset : "+temp);
                                if(temp.contains("?")){
                                    this.scanMethodGet(temp);
                                }
                                this.scanMethodGetPost(element, temp);
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
        this.attackGet(urlAction, payA1.XMLXPathInjection(), sigA1.XMLXPathInjection(), "XML/XPath injection");
        this.attackGet(urlAction, payA3.XSS(), sigA3.XSS(), "XSS");
    }
    
    public void scanMethodGetPost(Element element, String urlAction) throws IOException {
        A1 sigA1 = new A1();
        A3 sigA3 = new A3();
        A1p payA1 = new A1p();
        A3p payA3 = new A3p();

        this.attackGetPost(element, urlAction, payA1.HTMLinjection(), sigA1.HTMLinjection(), "HTML injection");
        this.attackGetPost(element, urlAction, payA1.IFrameInjection(), sigA1.IFrameInjection(), "IFrame injection");
        this.attackGetPost(element, urlAction, payA1.SQLinjection(), sigA1.SQLinjection(), "SQL injection");
        this.attackGetPost(element, urlAction, payA1.XMLXPathInjection(), sigA1.XMLXPathInjection(), "XML/XPath injection");
        this.attackGetPost(element, urlAction, payA3.XSS(), sigA3.XSS(), "XSS");
    }

}
