/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signature;

import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class sXSS {
    public ArrayList<String> XSS() {
        ArrayList<String> list = new ArrayList<>();
        String payload = "<script>alert(123);</script>\n";
        list.add("<marquee onstart='javascript:alert('1');'>=(◕_◕)=");
        list.add("<script>alert(1);</script>");
        return list;
    }
}
