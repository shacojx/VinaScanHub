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

        psHTMLi psHTMLin = new psHTMLi();
        psHTMLin.loadDataHTMLinjection();

        psXSS psXSS = new psXSS();
        psXSS.loadDataXSS();

        psIFramei psIFramein = new psIFramei();
        psIFramein.loadDataIFramein();
    }
}
