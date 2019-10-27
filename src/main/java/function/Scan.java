/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import Pay_Sig.P_S;
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
import payload.A1p;
import payload.A3p;
import payload.A3p_1;
import payload.A3p_2;
import payload.A3p_3;
import payload.A3p_4;
import payload.A3p_5;
import payload.A3p_6;
import payload.A3p_7;
import payload.A3p_8;
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

    public void BlindSQLinjection(String urlAction) throws IOException {
        try {
            Document doc = Jsoup.connect(urlAction).get();
            Document doc1 = Jsoup.connect(urlAction + " and 1=1").get();
            Document doc2 = Jsoup.connect(urlAction + " and 1=2").get();
            Document doc3 = Jsoup.connect(urlAction + "' and 1=2").get();
            Document doc4 = Jsoup.connect(urlAction + "' and 1=2").get();
//            Document doc5 = Jsoup.connect(urlAction + "\" and 1=2").get();
//            Document doc6 = Jsoup.connect(urlAction + "\" and 1=2").get();
            if (doc.toString().equals(doc1.toString()) == true && doc.toString().equals(doc2.toString()) == false) {
                if (list_vuln.size() != 0) {
                    for (String x : list_vuln) {
                        if (!x.contains(urlAction.split("=")[0])) {
                            list_vuln.add("Get Blind SQL injection: " + urlAction);
                        }
                    }
                } else {
                    list_vuln.add("Get Blind SQL injection: " + urlAction);
                }
            }
        } catch (Exception e) {
            System.out.println("Error Get Blind SQL injection: " + urlAction + "Ex: " + e);
        }

    }

    public void attackGetPost(Element element, String urlAction, Map<String, ArrayList> paySig, String vulnName) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        Elements ele = null;
        boolean checkVuln = false;

        for (Map.Entry<String, ArrayList> entry : paySig.entrySet()) {
            String keyPay = entry.getKey();
            ArrayList<String> valueSig = entry.getValue();

            try {
                map = new HashMap<String, String>();
                ele = element.getElementsByAttribute("name");
                for (Element e1 : ele) {
                    if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                        map.put(e1.attr("name"), keyPay);
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

                for (String sSig : valueSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
                        System.out.println("Pay = " + keyPay);
                        System.out.println("Sig = " + sSig);
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

    public void attackGet(String urlAction, Map<String, ArrayList> paySig, String vulnName) throws IOException {

        String fURL = urlAction.split("\\?")[0];
        String lURL = urlAction.split("\\?")[1];

        Map<String, String> mapParemeter;
        boolean checkVuln = false;

        for (Map.Entry<String, ArrayList> entry : paySig.entrySet()) {
            String keyPay = entry.getKey();
            ArrayList<String> valueSig = entry.getValue();

            try {
                mapParemeter = new HashMap<>();
                for (String s : lURL.split("&")) {
                    String key = s.split("\\=")[0];
                    String value = "";
                    try {
                        value = s.split("\\=")[1] + keyPay;
                    } catch (Exception e) {
                        System.out.println("Error attackGet: " + e);
                    }
                    mapParemeter.put(key, value);
                }

                Document doc = Jsoup.connect(fURL).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();

                for (String sSig : valueSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println("GET " + vulnName + " : " + urlAction);
                        System.out.println("Pay = " + keyPay);
                        System.out.println("Sig = " + sSig);
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
        for (String sURL : listURL) {
            this.scanVuln(sURL);
        }
        System.out.println("Scan end!");
    }

    public void scanVuln(String sURL) throws IOException {
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

    public void bruteForce(String sURL) throws IOException {
        P_S ps = new P_S();

        for (Map.Entry<String, String> entry : ps.userPass().entrySet()) {
            String keyUser = entry.getKey();
            String valuePass = entry.getValue();

            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            Document doc = resp.parse();
            Map<String, String> cookie = new HashMap<>();
            cookie = resp.cookies();

            Elements linksOnPage = doc.select("form");
            Element ele = null;
            String name = "";
            String action = "";
            for (Element element : linksOnPage) {
                try {
                    action = element.attr("abs:action");
                    name = element.attr("abs:name");
                } catch (Exception e) {
                }

                if (name.contains("login") || action.contains("login") || name.contains("dangnhap") || action.contains("dangnhap")) {
                    ele = element;
                    break;
                }
            }

            Map<String, String> map = new HashMap<String, String>();
            Elements eles = ele.getElementsByAttribute("name");

            for (Element ele1 : eles) {
                String xname = ele1.attr("name");
                String xvalue = ele1.attr("value");
                String xtype = ele1.attr("type");
                if (xname.length() != 0) {
                    if (xtype.contains("submit") || xtype.contains("button")) {
                        map.put(xname, this.encodeValue(xvalue));

                    } else {
                        if (xvalue.length() != 0) {
                            map.put(xname, this.encodeValue(xvalue));

                        } else {
                            if (xtype.contains("password")) {
                                map.put(xname, this.encodeValue(valuePass));

                            } else {
                                map.put(xname, this.encodeValue(keyUser));

                            }
                        }
                    }
                }
            }

            resp = Jsoup.connect(sURL).cookies(cookie).data(map).method(Connection.Method.POST).execute();

            Document document = resp.parse();
            Elements formCheck = document.select("form");
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;

            for (Element e : formCheck) {
                if (e.attr("abs:action").equals(action)) {
                    checkLogin1 = false;
                    break;
                }
            }
            formCheck = document.getElementsByAttribute("href");
            for (Element e : formCheck) {
                if (e.attr("abs:href").equals(action)) {
                    checkLogin2 = false;
                    break;
                }
            }
            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + keyUser + " ---- Password: " + valuePass);
                break;
            }
        }
    }

    // Method to encode a string value using `UTF-8` encoding scheme
    public String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public void scanMethodGet(String urlAction) throws IOException {
        P_S ps = new P_S();

        this.attackGet(urlAction, ps.HTMLinjection(), "HTML injection");
        this.attackGet(urlAction, ps.IFrameInjection(), "IFrame injection");
        this.attackGet(urlAction, ps.SQLinjection(), "SQL injection");
        this.attackGet(urlAction, ps.XSS(), "XSS");
        this.attackGet(urlAction, ps.XMLXPathInjection(), "XML/XPath injection");
        BlindSQLinjection(urlAction);
    }

    public void scanMethodGetPost(Element element, String urlAction) throws IOException {
        P_S ps = new P_S();

        this.attackGetPost(element, urlAction, ps.HTMLinjection(), "HTML injection");
        this.attackGetPost(element, urlAction, ps.IFrameInjection(), "IFrame injection");
        this.attackGetPost(element, urlAction, ps.SQLinjection(), "SQL injection");
        this.attackGetPost(element, urlAction, ps.XSS(), "XSS");
        this.attackGetPost(element, urlAction, ps.XMLXPathInjection(), "XML/XPath injection");
    }

}
