/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import PaySig.*;

/**
 *
 * @author toanvv1
 */
public class LoadData {

    public void loadData() {
        psSQLi psSQLin = new psSQLi();
        psSQLin.loadDataSQLinjection();

        psXMLXpatchi psXMLXpatchin = new psXMLXpatchi();
        psXMLXpatchin.loadDataXMLXPathin();

        psXSS psXSS = new psXSS();
        psXSS.loadDataXSS();

        psUserPass psUserPass = new psUserPass();
        psUserPass.loadDataUserPass();
        
        psCodeInjection psCI = new psCodeInjection();
        psCI.loadDataCI();
        
        psLFI psLFI = new psLFI();
        psLFI.loadDataLFI();
        
        psCMDInjection psCMDi = new psCMDInjection();
        psCMDi.loadDataCMDinjection();
        
        psDirectorylisting psDirList = new psDirectorylisting() ;
        psDirList.loadDataDL();
        
    }
}
