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
public class pSQLi {

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
}
