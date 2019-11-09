/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Information.ScanPort;
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
        String url1 = null;
        int dept;
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
        System.out.print("Enter url: ");
        url = scan.nextLine();
        System.out.print("Enter level spider: ");
        dept = scan.nextInt();
        scan.nextLine();
        spider.MAX_DEPTH = dept;
        if(url.contains("//")){
            if(url.split("//")[1].contains("/")){
                url1 = url.split("//")[1].split("/")[0];
            }
        }else if(url.contains("/")){
             url1 = url.split("/")[0];
        }else{
            url1 = url;
        }
        scanport.url = url1;
        System.err.println("Scanning Port");
        scanport.ScanPort();
        System.out.println("Scan port end");
        s.Scan(url);
    }

}
