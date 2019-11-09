/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

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
        int dept;
        Scan s = new Scan();
        SpiderWeb spider = new SpiderWeb();
        LoadData loadDB = new LoadData();
        loadDB.loadData();
        System.out.println("Load Data payload done!");
        System.out.println("Load Data signature done!");
        System.out.println("-----------------------------");
        System.out.println("================================");
        System.out.println("=        Vina Scan Hub v0.5    =");
        System.out.println("=   Code by Eyes Of God team   =");
        System.out.println("=            Shaco JX          =");
        System.out.println("=             Mr.Lax           =");
        System.out.println("================================");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter url: ");
        url = scan.nextLine();
        System.out.print("Enter level spider: ");
        dept = scan.nextInt();
        scan.nextLine();
        spider.MAX_DEPTH = dept;
        s.Scan(url);
    }

}
