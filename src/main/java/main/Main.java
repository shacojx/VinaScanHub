/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Information.ScanPort;
import function.Authen;
import function.CheckSiteAdmin;
import function.Scan;
import java.io.IOException;
import java.util.Scanner;
import function.LoadData;
import function.SpiderWeb;

/**
 *
 * @author Shaco JX
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String url;
        int dept = 5;
        Scan s = new Scan();
        ScanPort scanport = new ScanPort();
        SpiderWeb spider = new SpiderWeb();
        LoadData loadDB = new LoadData();
        loadDB.loadData();
        System.out.println("Load Data payload done!");
        System.out.println("Load Data signature done!");
        System.out.println("-----------------------------");
        System.out.println("================================");
        System.out.println("=        Vina Scan Hub         =");
        System.out.println("=   Code by Eyes Of God team   =");
        System.out.println("=            Shaco JX          =");
        System.out.println("=             Mr.Lax           =");
        System.out.println("=             Lyser            =");
        System.out.println("================================");
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("Enter url: ");
            url = scan.nextLine();
        } while (!url.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)"));

        System.out.print("Enter level spider: ");
        dept = scan.nextInt();

        scan.nextLine();
        Authen au = new Authen();
        au.Authen();
        spider.MAX_DEPTH = dept;
        scanport.url = url;
        System.err.println("Scanning Port");
        scanport.ScanPort();
        System.out.println("Scan port end");
//        CheckSiteAdmin checkSite = new CheckSiteAdmin();
//        checkSite.checkSiteAdmin(url);
        s.Scan(url);
    }

}
