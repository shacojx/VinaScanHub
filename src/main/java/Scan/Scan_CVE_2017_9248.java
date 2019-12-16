/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import Entity.VulnEntity;
import PaySig.CVE_2017_9248;
import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.net.URL;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author toanvv1
 */
public class Scan_CVE_2017_9248 {

    public void scan(String url, CookieManager cooki) throws IOException {
        /* turn off annoying htmlunit warnings */
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        WebRequest requestSettings;
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setThrowExceptionOnFailingStatusCode(false);
        if (cooki != null) {
            client.setCookieManager(cooki);
        }

        CVE_2017_9248 telerik = new CVE_2017_9248();
        for (String pay : telerik.getArrPayCVE()) {
            try {
                requestSettings = new WebRequest(new URL(url + pay), HttpMethod.GET);
                HtmlPage page = client.getPage(requestSettings);
//                Document doc = Jsoup.connect(url + pay).get();
                String tempBody = page.asXml();
                for (String sig : telerik.getArrSigCVE()) {
                    if (tempBody.contains(sig)) {
//                    if (doc.body().toString().contains(sig)) {
                        System.out.println("CVE-2017-9248: " + url + pay + " sig: " + sig);
                        DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                        dtm.addRow(new Object[]{"CVE-2017-9248", url, pay, sig});
                        VulnEntity v = new VulnEntity("CVE-2017-9248", url, pay, sig);
                        View.VSH.ve.add(v);
                        VSH.LOG_CONSOLE.append("CVE-2017-9248: " + url + "\n");
                        VSH.LOG_CONSOLE.append("        " + pay + " " + sig + "\n");
                        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR Scan_CVE_2017_9248!!!");
                e.printStackTrace();
            }

        }
    }

}
