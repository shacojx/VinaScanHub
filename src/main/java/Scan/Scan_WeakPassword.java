/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import function.encodeValue;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

/**
 *
 * @author toanvv1
 */
public class Scan_WeakPassword {

    public void bruteForce(String sURL, String[][] userPass) throws IOException {
        WebRequest requestSettings;
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        List<NameValuePair> params;
        String action = "";
        encodeValue encodeValue = new encodeValue();

        for (String[] obj : userPass) {
            String user = obj[0];
            String pass = obj[1];
            params = new ArrayList<>();

//            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            requestSettings = new WebRequest(new URL(sURL), HttpMethod.GET);
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
                } catch (MalformedURLException ex) {
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
                        params.add(new NameValuePair(xname, encodeValue.encode(xvalue)));

                    } else {
                        if (xvalue.length() != 0) {
                            params.add(new NameValuePair(xname, encodeValue.encode(xvalue)));

                        } else {
                            if (xtype.contains("password")) {
                                params.add(new NameValuePair(xname, encodeValue.encode(pass)));

                            } else {
                                params.add(new NameValuePair(xname, encodeValue.encode(user)));

                            }
                        }
                    }
                }
            }

            requestSettings = new WebRequest(new URL(action), HttpMethod.POST);
            requestSettings.setRequestParameters(params);
            page = client.getPage(requestSettings);
            String tempBody = page.asXml();
            System.out.println(page.getUrl().toString());

            List<HtmlForm> formCheck1 = page.getForms();
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;
            for (HtmlForm f : formCheck1) {
                if (page.getFullyQualifiedUrl(f.getActionAttribute()).toString().contains(action)) {
                    checkLogin1 = false;
                    break;
                }
            }

            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + user + " ---- Password: " + pass);
                break;
            }
        }
    }

}
