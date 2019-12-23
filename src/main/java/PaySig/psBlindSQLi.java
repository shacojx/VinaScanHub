/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaySig;

/**
 *
 * @author toanvv1
 */
public class psBlindSQLi {
    
    private static String[] arrPayBlindSQLin;

    public psBlindSQLi() {
        

        /*List Payload SQL Injection*/
        arrPayBlindSQLin = new String[]{
            "1 and 1=1#",
           "1 and 1=2#",
           "1' and 1=1#",
           "1' and 1=2#"
        };
    }

    public String[] getArrPaySQLin() {
        return arrPayBlindSQLin;
    }
}
