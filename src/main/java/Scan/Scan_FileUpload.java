/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import Entity.VulnEntity;
import PaySig.psUploadFile;
import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import function.Scan;
import java.io.IOException;
import java.net.URL;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Shaco JX
 */
public class Scan_FileUpload {

    public void uploadfile(String url, CookieManager cooki) throws IOException {
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

        psUploadFile psUp = new psUploadFile();
        Scan s = new Scan();
        requestSettings = new WebRequest(new URL(url), HttpMethod.GET);
        HtmlPage page = client.getPage(requestSettings);
        String tempBody = page.asXml();
//        Document doc = Jsoup.connect(url).get();
        for (String x : psUp.getArrSigUploadFile()) {
            if (tempBody.contains(x)) {
//            if (doc.body().toString().contains(x)) {
                s.list_vuln.add("Upload File: " + url);
                DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                dtm.addRow(new Object[]{"Upload File ", url, "", x});
                VulnEntity v = new VulnEntity("Upload File ", url, "", x);
                View.VSH.ve.add(v);
                VSH.LOG_CONSOLE.append("Upload File: " + url + "\n");
                VSH.LOG_CONSOLE.append("        " + x + "\n");
                VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
            }
        }

    }
}
