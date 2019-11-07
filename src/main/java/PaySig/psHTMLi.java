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
public class psHTMLi {

    private static String[] arrPayHTMLin;

    public void loadDataHTMLinjection() {
        /*List payload-signature html injection*/
        arrPayHTMLin = new String[]{
            //<h1>Test12345</h1>
            "%3Ch1%3ETest12345%3C%2Fh1%3E",
            //<h1>Low12345</h1>
            "%3Ch1%3ELow12345%3C%2Fh1%3E"
        };
    }

    public String[] getArrPayHTMLin() {
        return arrPayHTMLin;
    }
}
