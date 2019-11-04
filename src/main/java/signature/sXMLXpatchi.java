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
public class sXMLXpatchi {
    public ArrayList<String> XMLXPathInjection(){
        ArrayList<String> list = new ArrayList<>();
        list.add("SimpleXMLElement::xpath()");
        list.add("Invalid predicate in");
        list.add("xmlXPathEval: evaluation failed in");
        return list;
    }
}
