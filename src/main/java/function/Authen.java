/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import PaySig.psSQLi;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import paramstatic.Param;

/**
 *
 * @author Shaco JX
 */
public class Authen {

    private static String[][] userPass;

    public void Authen() throws IOException {
        
        userPass = new String[][]{{"admin", "password"}};
        WebClient ee = bruteForce("http://10.1.30.123:8080/dvwa/login.php", userPass);
        Param.COOKIE = ee.getCookieManager().getCookies().toString().replace("[", "").replace("]", "");
        System.out.println(Param.COOKIE);
    }

    public WebClient bruteForce(String sURL, String[][] userPass) throws IOException {
        WebRequest requestSettings;
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
//        client.getOptions().setJavaScriptEnabled(false);
        List<NameValuePair> params;
        String action = "";
        encodeValue encode = new encodeValue();

        for (String[] obj : userPass) {
            String user = obj[0];
            String pass = obj[1];
            params = new ArrayList<>();

//            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            requestSettings = new WebRequest(new URL(sURL), HttpMethod.GET);
            requestSettings.setAdditionalHeader("User-Agent", Param.USERAL);
            requestSettings.setAdditionalHeader("Cookie", Param.COOKIE);
            HtmlPage page = client.getPage(requestSettings);
            List<HtmlForm> htmlForm = page.getForms();
            Document doc = null;

            for (HtmlForm html : htmlForm) {
                doc = Jsoup.parse(html.asXml(), "", Parser.xmlParser());
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
            Elements eles = e.getElementsByAttribute("name");
            for (Element ele1 : eles) {
                String xname = ele1.attr("name");
                String xvalue = ele1.attr("value");
                String xtype = ele1.attr("type");
//                if (xname.length() != 0 && !name.contains(xname)) {
                if (xname.length() != 0) {
                    if (xtype.contains("submit") || xtype.contains("button")) {
                        params.add(new NameValuePair(xname, encode.encode(xvalue)));

                    } else {
                        if (xvalue.length() != 0) {
                            params.add(new NameValuePair(xname, encode.encode(xvalue)));

                        } else {
                            if (xtype.contains("password")) {
                                params.add(new NameValuePair(xname, encode.encode(pass)));

                            } else {
                                params.add(new NameValuePair(xname, encode.encode(user)));

                            }
                        }
                    }
                }
            }

            requestSettings = new WebRequest(new URL(action), HttpMethod.POST);
            requestSettings.setRequestParameters(params);
            page = client.getPage(requestSettings);
            String tempBody = page.asXml();
//            System.out.println("-----------------");
//            System.out.println("Action: " + action);
//            System.out.println("Params: " + params);
//            System.out.println("URL: " + page.getUrl());

            List<HtmlForm> formCheck1 = page.getForms();
            boolean checkLogin1 = false;
            boolean checkLogin2 = true;
            System.out.println("Count: " + formCheck1.size());
            boolean checkLogin3 = false;
            if (action.contains(sURL)) {
                checkLogin3 = true;
            }
            for (HtmlForm f : formCheck1) {
//                System.out.println("HTML Action: " + page.getFullyQualifiedUrl(f.getActionAttribute()));
//                System.out.println("action: " + action);

                System.out.println("Form: " + f.getActionAttribute());
                if (checkLogin3) {
                    checkLogin1 = true;
                    if (page.getFullyQualifiedUrl(f.getActionAttribute()).toString().contains(action)) {
                        checkLogin1 = false;
                        break;
                    }
                } else {
                    if (page.getUrl().toString().contains(action)) {
                        checkLogin1 = true;
                        break;
                    }
                }
            }
            if (formCheck1.size() == 0) {
                checkLogin1 = true;
                checkLogin2 = true;
            }

            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + user + " ---- Password: " + pass);
                return client;
            }
        }
        return null;
    }
    
    public static void scanSQLinTest(Element element, String urlAction, String[] payload, WebClient wc) throws IOException {
           WebClient client = null;
            if(wc == null){
                client = new WebClient();
            }else{
                client = wc;
            }
        String vulnName = "SQL Injection";
        String urlAttack = urlAction;
        boolean checkVuln = false;
        WebRequest requestSettings;
//        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        List<NameValuePair> params;
        psSQLi psSQLi = new psSQLi();
 
        for (String sPay : payload) {
            params = new ArrayList<>();
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
                function.Scan scan = new Scan();
                if (method.toLowerCase().contains("post")) {
                    requestSettings = new WebRequest(new URL(urlAction), HttpMethod.POST);
                    method = "|POST|";
                    scan.checkURLPOST.add(urlAction);
                } else {
                    requestSettings = new WebRequest(new URL(urlAttack), HttpMethod.GET);
                    method = "|GET|";
                    scan.checkURLGET.add(urlAttack);
                }
                requestSettings.setRequestParameters(params);
                HtmlPage page = client.getPage(requestSettings);
                for (String sSig : psSQLi.getArrSigSQLin()) {
                    if (page.asXml().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(method + vulnName + " : " + urlAction);
                        System.out.println("        " + params.toString());
                        scan.list_vuln.add(method + vulnName + " : " + urlAction);
                        break;
                    }
                }
            } catch (IOException | RuntimeException e) {
                System.out.println("Error attackVulnSQLin: " + urlAction + " ||| " + e);
            }
            if (checkVuln) {
                break;
            }
        }
    }

}
