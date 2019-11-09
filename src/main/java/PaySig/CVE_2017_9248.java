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
public class CVE_2017_9248 {
    private static String[] arrSigCVE;
    private static String[] arrPayCVE;

    public void loadDataCVE() {

        arrSigCVE = new String[]{
            "Telerik.Web.UI.DialogHandler.aspx",
           };

        arrPayCVE = new String[]{
            "Loading the dialog...",
           };
    }

    public String[] getArrSigCVE() {
        return arrSigCVE;
    }

    public String[] getArrPayCVE() {
        return arrPayCVE;
    }
}
