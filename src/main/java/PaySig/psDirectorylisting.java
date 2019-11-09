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
public class psDirectorylisting {
    private static String[] arrSigCI;

    public psDirectorylisting() {
        arrSigCI = new String[]{
            "<title>Index of /",
            "<h1>Index of /",
            "Index of /",
            };
    }


    public String[] getArrSigDL() {
        return arrSigCI;
    }

}
