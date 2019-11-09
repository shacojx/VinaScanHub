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
public class MS15_034 {
    private static String[] arrSigCVE;
    private static String[] arrPayCVE;

    public void loadDataMS() {

        arrSigCVE = new String[]{
            "welcome.png",
           };

        arrPayCVE = new String[]{
            "<title>IIS",
           };
    }

    public String[] getArrSigMS() {
        return arrSigCVE;
    }

    public String[] getArrPayMS() {
        return arrPayCVE;
    }
}
