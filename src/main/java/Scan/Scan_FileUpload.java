/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import PaySig.psUploadFile;
import View.VSH;
import function.Scan;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Shaco JX
 */
public class Scan_FileUpload {

    public void uploadfile(String url) throws IOException {
        psUploadFile psUp = new psUploadFile();
        Scan s = new Scan();
        Document doc = Jsoup.connect(url).get();
        for (String x : psUp.getArrSigUploadFile()) {
            if (doc.body().toString().contains(x)) {
                s.list_vuln.add("Upload File: " + url);
                DefaultTableModel dtm = (DefaultTableModel) View.VSH.VulnResult.getModel();
                dtm.addRow(new Object[]{"Upload File ", url, "", x});
                VSH.LOG_CONSOLE.append("Upload File: " + url + "\n");
                VSH.LOG_CONSOLE.append("        " + x + "\n");
                VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
            }
        }

    }
}
