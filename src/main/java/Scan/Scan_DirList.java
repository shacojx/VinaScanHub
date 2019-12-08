/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import PaySig.psDirectorylisting;
import View.VSH;
import function.Scan;
import java.io.IOException;
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

    public void Scan_DirList(HashSet<String> listurl) throws IOException {
        psDirectorylisting psDirList = new psDirectorylisting();
        Scan s = new Scan();
        for (String url : listurl) {
            Document doc = Jsoup.connect(url).get();
            for (String sig : psDirList.getArrSigDL()) {
                if (doc.body().toString().contains(sig)) {
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
