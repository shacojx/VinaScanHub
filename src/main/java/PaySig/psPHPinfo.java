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
public class psPHPinfo {
    private static String[] arrSigPHPinfo;

    public psPHPinfo() {
        arrSigPHPinfo = new String[]{
            "<title>phpinfo()</title>",
            };

    }


    public String[] getArrSigPHPinfo() {
        return arrSigPHPinfo;
    }
}
