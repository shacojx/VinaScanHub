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
public class psIFramei {

    private static String[] arrPayIFramein;

    public void loadDataIFramein() {
        arrPayIFramein = new String[]{
            "%3Ciframe%3E%3Ch1%3ETest12345%3C%2Fh1%3E%3C%2Fiframe%3E",
            "%22%3E%3Ciframe%3E%3Ch1%3ELow%3C%2Fh1%3E%3C%2Fiframe%3E"};
    }

    public String[] getArrPayIFramein() {
        return arrPayIFramein;
    }
}
