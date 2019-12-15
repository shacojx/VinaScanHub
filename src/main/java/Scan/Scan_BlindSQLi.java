/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import Entity.FuzzEntity;
import Entity.VulnEntity;
import PaySig.psSQLi;
import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import function.Scan;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author toanvv1
 */
public class Scan_BlindSQLi {

    public Scan_BlindSQLi() {
    }

    public void scanBlindSQLin(Element element, String urlAction, String method, CookieManager cooki) throws IOException {
        /* turn off annoying htmlunit warnings */
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        boolean check_blind = false;
        String vulnName = "Blind SQL Injection";
        String urlAttack = urlAction;
        boolean checkVuln = false;
        WebRequest requestSettings;
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        if (cooki != null) {
            client.setCookieManager(cooki);
        }
        List<NameValuePair> params;
        List<NameValuePair> params1;
        List<NameValuePair> params2;
        List<NameValuePair> params3;
        List<NameValuePair> params4;
        psSQLi psSQLi = new psSQLi();

        params = new ArrayList<>();
        params1 = new ArrayList<>();
        params2 = new ArrayList<>();
        params3 = new ArrayList<>();
        params4 = new ArrayList<>();
        try {
            if (element == null) {
                try {
                    String fURL = urlAction.split("\\?")[0];
                    String lURL = urlAction.split("\\?")[1];
                    urlAttack = fURL;

                    for (String s : lURL.split("&")) {
                        String key = s.split("\\=")[0];
                        String value = "";
                        String value1 = "";
                        String value2 = "";
                        String value3 = "";
                        String value4 = "";
                        try {
                            value = s.split("\\=")[1] + "";
                            value1 = s.split("\\=")[1] + " and 1=1#";
                            value2 = s.split("\\=")[1] + " and 1=2#";
                            value3 = s.split("\\=")[1] + "' and 1=1#";
                            value4 = s.split("\\=")[1] + "' and 1=2#";
                        } catch (Exception e) {
//                                System.out.println("Error Value attackVulnSQLin: " + e);
                        }
                        params.add(new NameValuePair(key, value));
                        params1.add(new NameValuePair(key, value1));
                        params2.add(new NameValuePair(key, value2));
                        params3.add(new NameValuePair(key, value3));
                        params4.add(new NameValuePair(key, value4));
                    }
                } catch (Exception e) {
                    //System.out.println("ERROR Case 1: " + e);
                }
            } else {
                Elements ele = element.getElementsByAttribute("name");
                for (Element e1 : ele) {
                    if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                        params.add(new NameValuePair(e1.attr("name"), "1"));
                        params1.add(new NameValuePair(e1.attr("name"), "1 and 1=1#"));
                        params2.add(new NameValuePair(e1.attr("name"), "1 and 1=2#"));
                        params3.add(new NameValuePair(e1.attr("name"), "1' and 1=1#"));
                        params4.add(new NameValuePair(e1.attr("name"), "1' and 1=2#"));
                    } else {
                        if (e1.attr("name").length() != 0) {
                            params.add(new NameValuePair(e1.attr("name"), e1.attr("value")));
                            params1.add(new NameValuePair(e1.attr("name"), e1.attr("value1")));
                            params2.add(new NameValuePair(e1.attr("name"), e1.attr("value2")));
                            params3.add(new NameValuePair(e1.attr("name"), e1.attr("value3")));
                            params4.add(new NameValuePair(e1.attr("name"), e1.attr("value4")));
                        }
                    }
                }
            }
//            String method = "";
//            try {
//                method = element.attr("method");
//            } catch (Exception e) {
//            }
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
            requestSettings.setRequestParameters(params1);
            HtmlPage page1 = client.getPage(requestSettings);
            requestSettings.setRequestParameters(params2);
            HtmlPage page2 = client.getPage(requestSettings);
            requestSettings.setRequestParameters(params3);
            HtmlPage page3 = client.getPage(requestSettings);
            requestSettings.setRequestParameters(params4);
            HtmlPage page4 = client.getPage(requestSettings);

            requestSettings.setRequestParameters(params);
            HtmlPage page = client.getPage(requestSettings);

            if ((page.asXml().equals(page1.asXml()) == true && page.asXml().equals(page2.asXml()) == false)
                    || (page.asXml().equals(page3.asXml()) == true && page.asXml().equals(page4.asXml()) == false)) {
                check_blind = true;
            }

            for (String sSig : psSQLi.getArrSigSQLin()) {

                if (page.asXml().contains(sSig) == false && check_blind == true) {

                    System.out.println(method + vulnName + " : " + urlAction);
                    System.out.println("        " + params.toString());
                    DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                    dtm.addRow(new Object[]{method + vulnName, urlAction, params.toString(), sSig});
                    VulnEntity v = new VulnEntity( method + vulnName, urlAction, params.toString(), sSig);
                    View.VSH.ve.add(v);
                    VSH.LOG_CONSOLE.append(method + vulnName + " : " + urlAction + "\n");
                    VSH.LOG_CONSOLE.append("        " + params.toString() + "\n");
                    VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                    scan.list_vuln.add(method + vulnName + " : " + urlAction);
                    break;
                } else {
//                    DefaultTableModel dtmz = (DefaultTableModel) View.VSH.FuzzResult.getModel();
//                    dtmz.addRow(new Object[]{urlAction, params.toString()});
                    FuzzEntity f = new FuzzEntity(urlAction, vulnName, params.toString(), page.asXml());
                    View.VSH.fu.add(f);
                }
            }
        } catch (IOException | RuntimeException e) {
//                System.out.println("Error attackVulnSQLin: " + urlAction + " ||| " + e);
        }
//            if (checkVuln) {
//                break;
//            }

    }
}
