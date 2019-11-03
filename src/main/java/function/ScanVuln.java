/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import Pay_Sig.PaySigVuln;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author laxba
 */
public class ScanVuln {

    private static HashSet<String> checkURLGET = new HashSet<>();
    private static HashSet<String> checkURLPOST = new HashSet<>();

    public ArrayList<String> list_vuln = new ArrayList<>();
    private PaySigVuln ps = new PaySigVuln();

    public void Scan(String url) throws IOException {
        WebCrawlerWithDepth wc = new WebCrawlerWithDepth();
        System.out.println("Spider level: " + wc.MAX_DEPTH);
        wc.getPageLinks(url, 0, url);
        System.out.println("Total link: " + wc.links.size());

        for (String s : wc.links) {
            System.out.println(s);
        }
        System.out.println("---------------------------------------------------------------------------------");
        //Data load 1 lan khi mo ung dung
        ps.loadData();
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);

        //Scan can lam moi khi moi lan chay 1 url moi
        checkURLGET = new HashSet<>();
        checkURLPOST = new HashSet<>();
        this.checkVuln(wc.links);
//        attackVulnSQLinWithHTMLUNIT(null, url, ps.getArrPaySQLin());
//        this.bruteForceHTMLUNIT(url, ps.getUserPass());
//        this.bruteForceJSOUP(url, ps.getUserPass());

    }

    public void checkVuln(HashSet<String> listURL) throws IOException {
        for (String sURL : listURL) {
            if (!sURL.contains("jpg")) {
                if (sURL.contains("?")) {
                    if (!checkURLGET.contains(sURL.split("\\?")[0])) {
//                        System.out.println("Attack: " + sURL);
//                        System.out.println("1");
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
//                        if (temp.length() != 0) {
//                            if (!checkURLPOST.contains(temp)) {
////                                checkURLPOST.add(temp);
////                                    System.out.println("Hashset : " + temp);
//                                if (temp.contains("?") && !checkURLGET.contains(temp)) {
////                                    checkURLGET.add(temp);
//                                    System.out.println("2");
//                                    this.scanMethodGet(temp);
//                                }
//                                System.out.println("3");
//                                this.scanMethodGetPost(element, temp);
//                            }
//                        } else {
//                            if (!checkURLPOST.contains(sURL)) {
////                                checkURLPOST.add(sURL);
//                                System.out.println("4");
//                                this.scanMethodGetPost(element, sURL);
//                            }
//                        }

                        if (temp.length() == 0) {
                            temp = sURL;
                        }

                        if (temp.contains("?") && !checkURLGET.contains(temp.split("\\?")[0])) {
//                            System.out.println("2");
                            this.scanMethodGet(temp);
                        }
                        if (!checkURLGET.contains(temp) && !checkURLPOST.contains(temp)) {
//                            System.out.println("Attack: " + temp);
//                            System.out.println("3");
                            this.scanMethodGetPost(element, temp);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error scanVuln: " + sURL + " ||| " + e);
                }

            }
        }
        System.out.println("Scan end!");
    }

    public void scanMethodGet(String urlAction) throws IOException {
//        this.attackVulnSQLinWithJSOUP(null, urlAction, ps.getArrPaySQLin());
        this.attackVulnSQLinWithHTMLUNIT(null, urlAction, ps.getArrPaySQLin(), ps.getArrSigSQLin());
        this.attackVulnSQLinWithHTMLUNIT(null, urlAction, ps.getArrPayXMLXPathin(), ps.getArrSigXMLXPathin());
//        System.out.println("HTML1 SCAN");
        this.attackVulnXSSHTMLUNIT(null, urlAction, "HTMLin", ps.getArrPayHTMLin());
        this.attackVulnXSSHTMLUNIT(null, urlAction, "XSS", ps.getArrPayXSS());
        this.attackVulnXSSHTMLUNIT(null, urlAction, "IFramein", ps.getArrPayIFramein());
        BlindSQLinjection(urlAction);
    }

    public void scanMethodGetPost(Element element, String urlAction) throws IOException {
//        this.attackVulnSQLinWithJSOUP(element, urlAction, ps.getArrPaySQLin());
        this.attackVulnSQLinWithHTMLUNIT(element, urlAction, ps.getArrPaySQLin(), ps.getArrSigSQLin());
        this.attackVulnSQLinWithHTMLUNIT(element, urlAction, ps.getArrPayXMLXPathin(), ps.getArrSigXMLXPathin());
//        System.out.println("HTML2 SCAN");
        this.attackVulnXSSHTMLUNIT(element, urlAction, "HTMLin", ps.getArrPayHTMLin());
        this.attackVulnXSSHTMLUNIT(element, urlAction, "XSS", ps.getArrPayXSS());
        this.attackVulnXSSHTMLUNIT(element, urlAction, "IFramein", ps.getArrPayIFramein());
    }

    private void attackVulnSQLinWithJSOUP(Element element, String urlAction, String[] payload, String[] Sig) throws IOException {
        String vulnName = "SQLin";
        String urlAttack = urlAction;
        Map<String, String> mapParemeter;
        Document doc;
        boolean checkVuln = false;

        for (String sPay : payload) {
            mapParemeter = new HashMap<>();
            try {
                if (element == null) {
                    try {
                        String fURL = urlAction.split("\\?")[0];
                        String lURL = urlAction.split("\\?")[1];
                        urlAttack = fURL;

                        for (String s : lURL.split("&")) {
                            String key = s.split("\\=")[0];
                            String value = "";
                            try {
                                value = s.split("\\=")[1] + sPay;
                            } catch (Exception e) {
                                System.out.println("Error Value attackVulnSQLin: " + e);
                            }
                            mapParemeter.put(key, value);
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR Case 1: " + e);
                    }
                } else {
                    Elements ele = element.getElementsByAttribute("name");
                    for (Element e1 : ele) {
                        if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                            mapParemeter.put(e1.attr("name"), sPay);
                        } else {
                            if (e1.attr("name").length() != 0) {
                                mapParemeter.put(e1.attr("name"), e1.attr("value"));
                            }
                        }
                    }
                }
                String method = "";
                try {
                    method = element.attr("method");
                } catch (Exception e) {
                }
                if (method.toLowerCase().contains("post")) {
                    doc = Jsoup.connect(urlAction).data(mapParemeter).userAgent("Mozilla").followRedirects(false).post();
                    method = "|POST|";
                } else {
                    doc = Jsoup.connect(urlAttack).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();
                    method = "|GET|";
                }

                for (String sSig : Sig) {
                    if (doc.body().toString().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(method + vulnName + " : " + urlAction);
                        System.out.println(mapParemeter.toString());
                        list_vuln.add(method + vulnName + " : " + urlAction);
                        break;
                    }
                }
                if (checkVuln) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error attackVulnSQLin: " + urlAction + " ||| " + e);
            }
        }
    }

    private void attackVulnSQLinWithHTMLUNIT(Element element, String urlAction, String[] payload, String[] Sig) throws IOException {
        String vulnName = "SQLin";
        String urlAttack = urlAction;
        boolean checkVuln = false;
        WebRequest requestSettings;
        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
//        client.getOptions().setRedirectEnabled(false);
        List<NameValuePair> params;

        for (String sPay : payload) {
            params = new ArrayList<NameValuePair>();
            try {
                if (element == null) {
                    try {
                        String fURL = urlAction.split("\\?")[0];
                        String lURL = urlAction.split("\\?")[1];
                        urlAttack = fURL;

                        for (String s : lURL.split("&")) {
                            String key = s.split("\\=")[0];
                            String value = "";
                            try {
                                value = s.split("\\=")[1] + sPay;
                            } catch (Exception e) {
                                System.out.println("Error Value attackVulnSQLin: " + e);
                            }
                            params.add(new NameValuePair(key, value));
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR Case 1: " + e);
                    }
                } else {
                    Elements ele = element.getElementsByAttribute("name");
                    for (Element e1 : ele) {
                        if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                            params.add(new NameValuePair(e1.attr("name"), sPay));
                        } else {
                            if (e1.attr("name").length() != 0) {
                                params.add(new NameValuePair(e1.attr("name"), e1.attr("value")));
                            }
                        }
                    }
                }
                String method = "";
                try {
                    method = element.attr("method");
                } catch (Exception e) {
                }
                if (method.toLowerCase().contains("post")) {
                    requestSettings = new WebRequest(new URL(urlAction), HttpMethod.POST);
                    method = "|POST|";
                    checkURLPOST.add(urlAction);
                } else {
                    requestSettings = new WebRequest(new URL(urlAttack), HttpMethod.GET);
                    method = "|GET|";
                    checkURLGET.add(urlAttack);
                }
                requestSettings.setRequestParameters(params);
                HtmlPage page = client.getPage(requestSettings);
                for (String sSig : Sig) {
                    if (page.asXml().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(method + vulnName + " : " + urlAction);
                        System.out.println(params.toString());
                        list_vuln.add(method + vulnName + " : " + urlAction);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error attackVulnSQLin: " + urlAction + " ||| " + e);
            }
            if (checkVuln) {
                break;
            }
        }
    }

    public void attackVulnXSSHTMLUNIT(Element element, String urlAction, String vulnName, String[] payload) throws IOException {
        String urlAttack = urlAction;
        boolean checkVuln = false;
        WebRequest requestSettings;
        WebClient client = new WebClient();
//        client.getOptions().setCssEnabled(true);
        client.getOptions().setJavaScriptEnabled(false);
        List<NameValuePair> params;

        for (String sPay : payload) {
            String[] listPay = new String[]{
                ps.decodeValue(sPay),
                sPay,
                ps.encodeValue(sPay)
            };
            for (String lPay : listPay) {
                params = new ArrayList<NameValuePair>();
                try {
                    if (element == null) {
                        try {
                            String fURL = urlAction.split("\\?")[0];
                            String lURL = urlAction.split("\\?")[1];
                            urlAttack = fURL;

                            for (String s : lURL.split("&")) {
                                String key = s.split("\\=")[0];
                                String value = "";
                                try {
                                    value = s.split("\\=")[1] + lPay;
                                } catch (Exception e) {
                                    System.out.println("Error Value attackVulnXSS: " + e);
                                }
                                params.add(new NameValuePair(key, value));
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR Case 1: " + e);
                        }
                    } else {
                        Elements ele = element.getElementsByAttribute("name");
                        for (Element e1 : ele) {
                            if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                                params.add(new NameValuePair(e1.attr("name"), lPay));
                            } else {
                                if (e1.attr("name").length() != 0) {
                                    params.add(new NameValuePair(e1.attr("name"), e1.attr("value")));
                                }
                            }
                        }
                    }
                    String method = "";
                    try {
                        method = element.attr("method");
                    } catch (Exception e) {
                    }
                    if (method.toLowerCase().contains("post")) {
                        requestSettings = new WebRequest(new URL(urlAction), HttpMethod.POST);
                        method = "POST ";
                        checkURLPOST.add(urlAction);
                    } else {
                        requestSettings = new WebRequest(new URL(urlAttack), HttpMethod.GET);
                        method = "GET ";
                        checkURLGET.add(urlAttack);
                    }
//                    System.out.println("-----------------------------XYZZZZZZZZZZZZZ");
//                    System.out.println("Method: " + method);
//                    System.out.println("Params: " + params);
                    requestSettings.setRequestParameters(params);
//                    if (vulnName.contains("XSS")) {
//                        System.out.println("2.2");
//                        System.out.println("params: " + params);
//                        if (method.contains("GET")) {
//                            System.out.println("URL: " + urlAttack);
//                        }
//                        if (method.contains("POST")) {
//                            System.out.println("URL: " + urlAction);
//                        }
//                    }
                    HtmlPage page = client.getPage(requestSettings);
                    String tempBody = page.asXml();
//                    if (method.contains("POST")) {
//                        System.out.println(tempBody);
//                    }

                    if (tempBody.replaceAll("\\s+", "").contains(ps.decodeValue(sPay))) {
                        boolean tempC = true;
                        for (String s : ps.getArrSigSQLin()) {
                            if (tempBody.contains(s)) {
                                tempC = false;
                                break;
                            }
                        }
                        if (tempC) {
                            checkVuln = true;
                            System.out.println(method + vulnName + " : " + urlAction);
                            System.out.println(params.toString());
                            list_vuln.add(method + vulnName + " : " + urlAction);
                            break;
                        }
                    }
//                    if (checkVuln) {
//                        break;
//                    }
                } catch (Exception e) {
                    System.out.println("Error attackVulnXSS: " + urlAction + " ||| " + e);
                }
            }
            if (checkVuln) {
                break;
            }
        }
    }

    private void attackVulnXSSJSOUP(Element element, String urlAction, String vulnName, String[] payload) throws IOException {
        String urlAttack = urlAction;
        Map<String, String> mapParemeter;
        Document doc;
        boolean checkVuln = false;

        for (String sPay : payload) {
            String[] listPay = new String[]{
                sPay,
                ps.encodeValue(sPay)
            };
            for (String lPay : listPay) {
                mapParemeter = new HashMap<>();
                try {
                    if (element == null) {
                        try {
                            String fURL = urlAction.split("\\?")[0];
                            String lURL = urlAction.split("\\?")[1];
                            urlAttack = fURL;

                            for (String s : lURL.split("&")) {
                                String key = s.split("\\=")[0];
                                String value = "";
                                try {
                                    value = s.split("\\=")[1] + lPay;
                                } catch (Exception e) {
                                    System.out.println("Error Value attackVulnXSS: " + e);
                                }
                                mapParemeter.put(key, value);
                            }
                        } catch (Exception e) {
                            System.out.println("ERROR Case 1: " + e);
                        }
                    } else {
                        Elements ele = element.getElementsByAttribute("name");
                        for (Element e1 : ele) {
                            if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                                mapParemeter.put(e1.attr("name"), lPay);
                            } else {
                                if (e1.attr("name").length() != 0) {
                                    mapParemeter.put(e1.attr("name"), e1.attr("value"));
                                }
                            }
                        }
                    }
                    String method = "";
                    try {
                        method = element.attr("method");
                    } catch (Exception e) {
                    }
                    if (method.toLowerCase().contains("post")) {
                        doc = Jsoup.connect(urlAction).data(mapParemeter).userAgent("Mozilla").followRedirects(false).post();
                        method = "POST ";
                    } else {
                        doc = Jsoup.connect(urlAttack).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();
                        method = "GET ";
                    }

                    if (doc.body().toString().contains(ps.decodeValue(sPay))) {
                        boolean tempC = true;
                        for (String s : ps.getArrSigSQLin()) {
                            if (doc.body().toString().contains(s)) {
                                tempC = false;
                                break;
                            }
                        }
                        if (tempC) {
                            checkVuln = true;
                            System.out.println(method + vulnName + " : " + urlAction);
                            System.out.println(mapParemeter.toString());
                            list_vuln.add(method + vulnName + " : " + urlAction);
                            break;
                        }
                    }
                    if (checkVuln) {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println("Error attackVulnXSS: " + urlAction + " ||| " + e);
                }
                if (checkVuln) {
                    break;
                }
            }
        }
    }

    private void bruteForceJSOUP(String sURL, String[][] userPass) throws IOException {
        for (String[] obj : userPass) {
            String user = obj[0];
            String pass = obj[1];

            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            Document doc = resp.parse();
            Map<String, String> cookie = new HashMap<>();
            cookie = resp.cookies();

            Elements linksOnPage = doc.select("form");
            Element ele = null;
            String name = "";
            String action = "";
            String id = "";
            for (Element element : linksOnPage) {
                try {
                    action = element.attr("abs:action");
                    name = element.attr("name");
                } catch (Exception e) {
                }
                try {
                    id = element.attr("id");
                } catch (Exception e) {
                }

                if (name.toLowerCase().contains("login")
                        || action.toLowerCase().contains("login")
                        || id.toLowerCase().contains("login")
                        || name.toLowerCase().contains("dangnhap")
                        || action.toLowerCase().contains("dangnhap")
                        || id.toLowerCase().contains("dangnhap")) {
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
                if (xname.length() != 0 && !name.contains(xname)) {
                    if (xtype.contains("submit") || xtype.contains("button")) {
                        map.put(xname, ps.encodeValue(xvalue));

                    } else {
                        if (xvalue.length() != 0) {
                            map.put(xname, ps.encodeValue(xvalue));

                        } else {
                            if (xtype.contains("password")) {
                                map.put(xname, ps.encodeValue(pass));

                            } else {
                                map.put(xname, ps.encodeValue(user));

                            }
                        }
                    }
                }
            }
            System.out.println("Map: " + map);
            resp = Jsoup.connect(action).cookies(cookie).data(map).method(Connection.Method.POST).execute();
            System.out.println("Action: " + action);
            System.out.println("URL: " + resp.url().toString());
            System.out.println("Map: " + map);

            Document document = resp.parse();
            Elements formCheck = document.select("form");
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;

            for (Element e : formCheck) {
                if (e.attr("abs:action").contains(action)) {
                    checkLogin1 = false;
                    break;
                }
            }
//            formCheck = document.getElementsByAttribute("href");
//            for (Element e : formCheck) {
//                if (e.attr("abs:href").contains(sURL)) {
//                    checkLogin2 = false;
//                    break;
//                }
//            }
            System.out.println("Check1: " + checkLogin1);
            System.out.println("Check2: " + checkLogin2);

            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + user + " ---- Password: " + pass);
                break;
            }
        }
    }

    private void bruteForceHTMLUNIT(String sURL, String[][] userPass) throws IOException {
        WebRequest requestSettings;
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        List<NameValuePair> params;
        String action = "";
        for (String[] obj : userPass) {
            String user = obj[0];
            String pass = obj[1];
            params = new ArrayList<NameValuePair>();

//            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            requestSettings = new WebRequest(new URL(sURL), HttpMethod.GET);
            HtmlPage page = client.getPage(requestSettings);
            List<HtmlForm> htmlForm = page.getForms();
            Document doc = null;

            for (HtmlForm html : htmlForm) {
                doc = Jsoup.parse(html.asXml(), "", Parser.xmlParser());
                Element e = doc;
                String name = "";
                String id = "";

                try {
                    action = page.getFullyQualifiedUrl(html.getActionAttribute()).toString();
                    name = html.getAttribute("name");
                } catch (Exception ex) {
                }
                try {
                    id = html.getAttribute("id");
                } catch (Exception ex) {
                }

                if (name.toLowerCase().contains("login")
                        || action.toLowerCase().contains("login")
                        || id.toLowerCase().contains("login")
                        || name.toLowerCase().contains("dangnhap")
                        || action.toLowerCase().contains("dangnhap")
                        || id.toLowerCase().contains("dangnhap")) {
                    doc = Jsoup.parse(html.asXml(), "", Parser.xmlParser());
                    break;
                }
            }

            Element e = doc;
//                System.out.println(e);
            Elements eles = e.getElementsByAttribute("name");
//                System.out.println("DOCCCCC: "+e.attr("action"));
            for (Element ele1 : eles) {
                String xname = ele1.attr("name");
                String xvalue = ele1.attr("value");
                String xtype = ele1.attr("type");
//                if (xname.length() != 0 && !name.contains(xname)) {
                if (xname.length() != 0) {
                    if (xtype.contains("submit") || xtype.contains("button")) {
                        params.add(new NameValuePair(xname, ps.encodeValue(xvalue)));

                    } else {
                        if (xvalue.length() != 0) {
                            params.add(new NameValuePair(xname, ps.encodeValue(xvalue)));

                        } else {
                            if (xtype.contains("password")) {
                                params.add(new NameValuePair(xname, ps.encodeValue(pass)));

                            } else {
                                params.add(new NameValuePair(xname, ps.encodeValue(user)));

                            }
                        }
                    }
                }
            }

//            System.out.println("Params: " + params);
            requestSettings = new WebRequest(new URL(action), HttpMethod.POST);
            requestSettings.setRequestParameters(params);
            page = client.getPage(requestSettings);
            String tempBody = page.asXml();
            System.out.println(page.getUrl().toString());

            List<HtmlForm> formCheck1 = page.getForms();
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;
            for (HtmlForm f : formCheck1) {
//                System.out.println("HTML Action: " + page.getFullyQualifiedUrl(f.getActionAttribute()));
//                System.out.println("action: " + action);
                if (page.getFullyQualifiedUrl(f.getActionAttribute()).toString().contains(action)) {
                    checkLogin1 = false;
                    break;
                }
            }
//            List<HtmlAnchor> formCheck2 = page.getByXPath(".//a[@href]");
//            for (HtmlAnchor a : formCheck2) {
//                if (page.getFullyQualifiedUrl(a.getAttribute("href")).toString().contains(sURL)) {
//                    checkLogin2 = false;
//                    break;
//                }
//            }
//            System.out.println("Check1: " + checkLogin1);
//            System.out.println("Check2: " + checkLogin2);

            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + user + " ---- Password: " + pass);
                break;
            }
        }
    }

    private void BlindSQLinjection(String urlAction) throws IOException {
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

}
