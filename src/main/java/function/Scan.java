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
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Shaco JX
 */
public class Scan {

    private static HashSet<String> hashSet = new HashSet<>();

    public void Scan(String url) throws IOException {
        System.out.println("[ Checking Vuln Top 10 OWASP ]");
        System.out.println("Please waiting... Checking ...");

//        WebCrawlerWithDepth wc = new WebCrawlerWithDepth();
//        System.out.println("Spider level: " + wc.MAX_DEPTH);
//        wc.getPageLinks(url, 0, url);
//        System.out.println("Total link: " + wc.links.size());
//
//        for (String s : wc.links) {
//            System.out.println(s);
//        }
//
//        System.out.println("---------------------------------------------------------------------------------");
//
//        this.scanVuln(wc.links);
        this.bruteForce(url);

        System.out.println("Scan End!");
    }

//    public void checkVuln(ArrayList<String> listPay, ArrayList<String> listSig, String vulnName, HashSet<String> list) throws IOException {
//        String vuln = "";
//        removeDup remove = new removeDup();
//        ArrayList<String> list_vuln = new ArrayList<>();
//        ArrayList<String> list_tmp = new ArrayList<>();
//        for (String x : list) {
//            if (x.contains("?") && x.contains("=") && !x.contains("jpg")) {
//                list_tmp.add(x.split("=")[0]);
//            }
//        }
//        ArrayList<String> list_point = remove.removeDuplicates(list_tmp);
//        for (String x : list_point) {
//            vuln = "";
//            for (String y : listPay) {
//                for (String z : listSig) {
////                        System.out.println("check " + x + y + z);
//                    Connection.Response respx = (Connection.Response) Jsoup.connect(x+"=1" + y).execute();
//                    Document docx = respx.parse();
//                    String bodyx = docx.body().toString();
//                    if (bodyx.contains(z)) {
//                        list_vuln.add(x+"=");
//                        vuln = "vuln";
//                        break;
//                    }
//                }
//                if (!vuln.equals("")) {
//                    break;
//                }
//            }
//
//        }
//
//        ArrayList<String> list_entrypoint = remove.removeDuplicates(list_vuln);
//
//        for (String x : list_entrypoint) {
//            System.out.println(vulnName + " : " + x);
//        }
//
//    }
    public void attackGetPost(Element element, String urlAction, Map<String, ArrayList> paySig, String vulnName) throws IOException {
//        System.out.println("2222222222222222222222222222222222222");
//        System.out.println(urlAction);
        Map<String, String> map = new HashMap<String, String>();
//        System.out.println(element);
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
                //        System.out.println(map);
                Document doc = null;
                if (element.attr("method").contains("post")) {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).post();
                } else {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).get();
                }
//        System.out.println(doc.toString());
//        System.out.println(doc.location());
                for (String sSig : valueSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
                        System.out.println("Pay = " + keyPay);
                        System.out.println("Sig = " + sSig);
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
//        System.out.println("3333333333333333333333333333333333");
//        System.out.println(urlAction);
        String fURL = urlAction.split("\\?")[0];
        String lURL = urlAction.split("\\?")[1];
//        System.out.println(fURL);
//        System.out.println(lURL);
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
//        System.out.println(mapParemeter);

                Document doc = Jsoup.connect(fURL).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();
//        System.out.println(doc.toString());
//        System.out.println(doc.location());
                for (String sSig : valueSig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println("GET " + vulnName + " : " + urlAction);
                        System.out.println("Pay = " + keyPay);
                        System.out.println("Sig = " + sSig);
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
//        Document document = Jsoup.connect(sURL).userAgent("Mozilla").get();

        for (Map.Entry<String, String> entry : ps.userPass().entrySet()) {
            String keyUser = entry.getKey();
            String valuePass = entry.getValue();

            Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
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
//                        System.out.println("1" + xname);
                    } else {
                        if (xvalue.length() != 0) {
                            map.put(xname, this.encodeValue(xvalue));
//                            System.out.println("2" + xname);
                        } else {
                            if (xtype.contains("password")) {
                                map.put(xname, this.encodeValue(valuePass));
//                                System.out.println("3" + xname);
                            } else {
                                map.put(xname, this.encodeValue(keyUser));
//                                System.out.println("4" + xname);
                            }
                        }
                    }
                }
            }
//            System.out.println(map);

            resp = Jsoup.connect(sURL).cookies(cookie).data(map).method(Connection.Method.POST).execute();
//            System.out.println(resp.url());

            Document document = resp.parse();
            Elements formCheck = document.select("form");
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;
            
            for (Element e : formCheck) {
                if(e.attr("abs:action").equals(action)){
                    checkLogin1 = false;
                    break;
                }
            }
            formCheck = document.getElementsByAttribute("href");
            for (Element e : formCheck) {
                if(e.attr("abs:href").equals(action)){
                    checkLogin2 = false;
                    break;
                }
            }                       
            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : "+ sURL);
                System.out.println("User: "+keyUser+" ---- Password: "+valuePass);
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
