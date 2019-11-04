/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payload;

import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class pHTMLi {
    public ArrayList<String> HTMLinjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("<h1>Test</h1>");
        list.add("%3Ch1%3ETest%3C/h1%3E");
        return list;
    }
}
