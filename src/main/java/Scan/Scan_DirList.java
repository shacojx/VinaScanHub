/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import PaySig.psDirectorylisting;
import function.Scan;
import java.io.IOException;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Shaco JX
 */
public class Scan_DirList {

    public Scan_DirList() {
    }
    
    public void Scan_DirList(HashSet<String> listurl) throws IOException{
        psDirectorylisting psDirList = new psDirectorylisting();
        Scan s = new Scan();
        for(String url : listurl){
            Document doc = Jsoup.connect(url).get();
            for(String sig : psDirList.getArrSigDL()){
                if(doc.body().toString().contains(sig)){
                    s.list_vuln.add("Dir List: "+url);
                }
            }
        }
    }
    
}
