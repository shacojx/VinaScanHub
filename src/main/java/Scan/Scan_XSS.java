/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import function.AttackGet;
import function.AttackPost;
import function.LoadData;
import java.io.IOException;
import org.jsoup.nodes.Element;

/**
 *
 * @author toanvv1
 */
public class Scan_XSS {

    public void ScanXSS(Element element, String urlAction) throws IOException {
        AttackGet ag = new AttackGet();
        AttackPost ap = new AttackPost();
        LoadData data = new LoadData();
        String vuln = "XSS";
        ag.attackGet(urlAction, data.pay_XSS, data.sig_XSS, vuln);
        ap.attackPost(element, urlAction, data.pay_XSS, data.sig_XSS, vuln);
    }
}
