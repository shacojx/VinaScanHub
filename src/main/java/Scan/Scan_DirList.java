/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import PaySig.psDirectorylisting;
import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import function.Scan;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Shaco JX
 */
public class Scan_DirList {

    public Scan_DirList() {
    }

    public void Scan_DirList(HashSet<String> listurl, CookieManager cooki) throws IOException {
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

        psDirectorylisting psDirList = new psDirectorylisting();
        Scan s = new Scan();
        for (String url : listurl) {
            requestSettings = new WebRequest(new URL(url), HttpMethod.GET);
            HtmlPage page = client.getPage(requestSettings);
            String tempBody = page.asXml();
//            Document doc = Jsoup.connect(url).get();
            for (String sig : psDirList.getArrSigDL()) {
                if (tempBody.contains(sig)) {
//                if (doc.body().toString().contains(sig)) {
                    s.list_vuln.add("Dir List: " + url);
                    DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                    dtm.addRow(new Object[]{"Dir List ", url, "", sig});
                    VSH.LOG_CONSOLE.append("Dir List: " + url + "\n");
                    VSH.LOG_CONSOLE.append("        " + sig + "\n");
                    VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                }
            }
        }
    }

}
