/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import Entity.FuzzEntity;
import Entity.VulnEntity;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import function.encodeValue;
import PaySig.psSQLi;
import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.WebResponse;
import function.Scan;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author toanvv1
 */
public class Scan_XSS {

    public Scan_XSS() {
    }

    public void scanXSS(Element element, String urlAction, String[] payload, String method, CookieManager cooki) throws IOException {
//        if (urlAction.contains("/vulnerabilities/xss_r/")) {
//            System.out.println("VAO: " + urlAction);
//        }
        /* turn off annoying htmlunit warnings */
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        String vulnName = "XSS";
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
        List<NameValuePair> paramButton;
        encodeValue encodeValue = new encodeValue();
        psSQLi psSQLi = new psSQLi();
        for (String sPay : payload) {
            try {
                String[] listPay = new String[]{
                    encodeValue.decode(sPay),
                    sPay,
                    encodeValue.encode(sPay)
                };
                for (String lPay : listPay) {
                    params = new ArrayList<>();
                    paramButton = new ArrayList<>();
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
                                        System.out.println("ERROR GET XSS!!!");
                                        e.printStackTrace();
                                    }
                                    params.add(new NameValuePair(key, value));
                                }
                            } catch (Exception e) {
                                //System.out.println("ERROR Case 1: " + e);
                                System.out.println("ERROR GET XSS!!!");
                                e.printStackTrace();
                            }
                        } else {
                            Elements ele = element.select("input, select, textarea");
//                            Elements ele = element.getElementsByAttribute("name");
                            for (Element e1 : ele) {
                                if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                                    params.add(new NameValuePair(e1.attr("name"), lPay));
                                } else {
                                    if (e1.attr("name").length() != 0) {
                                        paramButton.add(new NameValuePair(e1.attr("name"), encodeValue.encode(e1.attr("value"))));
                                    }
                                }
                            }
                        }
//                        if (urlAction.contains("/vulnerabilities/xss_r/")) {
//                            System.out.println("VAO3: " + urlAction);
//                            System.out.println("pa = " + params.toString());
//                            System.out.println("bu = " + paramButton.toString());
//
//                        }
                        if (paramButton.size() == 0) {
                            paramButton.add(new NameValuePair(" ", " "));
                        }
                        for (NameValuePair nameValuePair : paramButton) {
                            params.add(nameValuePair);
                            try {
//                                String method = "";
//                                try {
//                                    method = element.attr("method");
//                                } catch (Exception e) {
//                                    System.out.println("ERROR Param XSS!!!");
//                                    e.printStackTrace();
//                                }
                                Scan scan = new Scan();
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
                                String tempBody = page.asXml();
//                                if (urlAction.contains("/vulnerabilities/xss_r")) {
//                                    System.out.println("~~~~~~~~~~~~~~~~~~~");
//                                    System.out.println("Method: " + method);
//                                    System.out.println("Param: " + params.toString());
//                                    System.out.println("URL_Action: " + urlAction);
//                                    System.out.println("URL_Attack: " + urlAttack);
//                                    System.out.println("Page: " + page.getUrl().toString());
//                                    System.out.println(tempBody.replaceAll("\\s+", "").replace("//<![CDATA[", "").replace("//]]>", ""));
//                                }

                                if (tempBody.replaceAll("\\s+", "").replace("//<![CDATA[", "").replace("//]]>", "").contains(encodeValue.decode(sPay))) {
                                    boolean tempC = true;
                                    for (String s : psSQLi.getArrSigSQLin()) {
                                        if (tempBody.contains(s)) {
                                            tempC = false;
                                            break;
                                        }
                                    }
                                    if (tempC) {
                                        checkVuln = true;
                                        System.out.println(method + vulnName + " : " + urlAction);
                                        System.out.println("        " + params.toString());
                                        DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                                        dtm.addRow(new Object[]{method + vulnName, urlAction, params.toString(), params.toString(), "https://www.owasp.org/index.php/Cross-site_Scripting_(XSS)"});
                                        VulnEntity v = new VulnEntity(method + vulnName, urlAction, params.toString(), params.toString());
                                        View.VSH.ve.add(v);
                                        VSH.LOG_CONSOLE.append(method + vulnName + " : " + urlAction + "\n");
                                        VSH.LOG_CONSOLE.append("        " + params.toString() + "\n");
                                        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                                        scan.list_vuln.add(method + vulnName + " : " + urlAction + " : " + params.toString() + " : " + params.toString());
                                        break;
                                    } else {
//                                        DefaultTableModel dtmz = (DefaultTableModel) View.VSH.FuzzResult.getModel();
//                                        dtmz.addRow(new Object[]{urlAction, params.toString()});
                                        WebResponse response = page.getWebResponse();
                                        List<NameValuePair> li = response.getResponseHeaders();
                                        FuzzEntity f = new FuzzEntity(urlAction, vulnName, params.toString(), page.asXml(), li.toString());
                                        View.VSH.fu.add(f);
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("ERROR paramButton Scan XSS!!!");
                                e.printStackTrace();
                            }
                            if (checkVuln) {
                                break;
                            }
                            params.remove(nameValuePair);
                        }
                    } catch (Exception e) {
                        //System.out.println("Error attackVulnXSS: " + urlAction + " ||| " + e);
                        System.out.println("ERROR lPay Scan XSS!!!");
                        e.printStackTrace();
                    }
                    if (checkVuln) {
                        break;
                    }
                }
                if (checkVuln) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("ERROR Scan XSS");
                e.printStackTrace();
            }
        }
    }
}
