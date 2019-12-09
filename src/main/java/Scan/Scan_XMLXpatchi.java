/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import PaySig.psXMLXpatchi;
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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author laxba
 */
public class Scan_XMLXpatchi {

    public Scan_XMLXpatchi() {
    }

    public void scanXMLXpatchin(Element element, String urlAction, String[] payload, String method, CookieManager cooki) throws IOException {
        /* turn off annoying htmlunit warnings */
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        String vulnName = "XMLXpatch Injection";
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
        psXMLXpatchi psXMLXpatchi = new psXMLXpatchi();

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
                                //System.out.println("Error Value attackVulnXMLXpatchin: " + e);
                            }
                            params.add(new NameValuePair(key, value));
                        }
                    } catch (Exception e) {
                        //System.out.println("ERROR Case 1: " + e);
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
//                String method = "";
//                try {
//                    method = element.attr("method");
//                } catch (Exception e) {
//                }
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
                for (String sSig : psXMLXpatchi.getArrSigXMLXPathin()) {
                    if (page.asXml().contains(sSig)) {
                        checkVuln = true;
                        System.out.println(method + vulnName + " : " + urlAction);
                        System.out.println("        " + params.toString());
                        DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                        dtm.addRow(new Object[]{method + vulnName, urlAction, params.toString(), sSig});
                        VSH.LOG_CONSOLE.append(method + vulnName + " : " + urlAction + "\n");
                        VSH.LOG_CONSOLE.append("        " + params.toString() + "\n");
                        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                        scan.list_vuln.add(method + vulnName + " : " + urlAction);
                        break;
                    } else {
                        DefaultTableModel dtmz = (DefaultTableModel) View.VSH.FuzzResult.getModel();
                        dtmz.addRow(new Object[]{urlAction, params.toString()});
//                        View.FuzzResult.FuzzOutput.append(page.asXml());
                    }
                }
            } catch (IOException | RuntimeException e) {
                //System.out.println("Error attackVulnXMLXpatchin: " + urlAction + " ||| " + e);
            }
            if (checkVuln) {
                break;
            }
        }
    }
}
