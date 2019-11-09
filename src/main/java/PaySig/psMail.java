/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaySig;

/**
 *
 * @author Shaco JX
 */
public class psMail {
      private static String[] arrSigMail;

    public void loadDataMail() {

        arrSigMail = new String[]{
            "@",
            ".",
            };

    }

    public String[] getArrSigMail() {
        return arrSigMail;
    }

    
}
