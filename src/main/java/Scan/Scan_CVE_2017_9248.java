/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import PaySig.CVE_2017_9248;
import View.VSH;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author toanvv1
 */
public class Scan_CVE_2017_9248 {

    public void scan(String url) throws IOException {
        CVE_2017_9248 telerik = new CVE_2017_9248();
        for (String pay : telerik.getArrPayCVE()) {
            try {
                Document doc = Jsoup.connect(url + pay).get();
                for (String sig : telerik.getArrSigCVE()) {
                    if (doc.body().toString().contains(sig)) {
                        System.out.println("CVE-2017-9248: " + url + pay + " sig: " + sig);
                        DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                        dtm.addRow(new Object[]{"CVE-2017-9248", url, pay, sig});
                        VSH.LOG_CONSOLE.append("CVE-2017-9248: " + url + "\n");
                        VSH.LOG_CONSOLE.append("        " + pay +" "+sig+ "\n");
                        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                    }
                }
            } catch (Exception e) {
            }

        }
    }

}
