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
public class psUploadFile {

    private static String[] arrSigUploadFile;

    public psUploadFile() {
        arrSigUploadFile = new String[]{
            "<input type=\"file\"",};
    }

    public String[] getArrSigUploadFile() {
        return arrSigUploadFile;
    }
}
