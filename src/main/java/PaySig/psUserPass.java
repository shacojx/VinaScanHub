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
public class psUserPass {

    private static String[][] userPass;

    public void loadDataUserPass() {
        userPass = new String[][]{
            {"admin", "password"},
            {"bee", "bug"},
            {"test", "test"},
            {"laxbaortrung", "vangta0fa"}};
    }

    public String[][] getUserPass() {
        return userPass;
    }
}
