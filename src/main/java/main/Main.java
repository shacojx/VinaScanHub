/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import function.Scan;
import function.WebCrawlerWithDepth;
import function.removeDup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import signature.A1;
import payload.A1p;
import payload.A3p;
import signature.A3;

/**
 *
 * @author Shaco JX
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String url;
        Scan s = new Scan();
        System.out.println("================================");
        System.out.println("=        Vina Scan Hub v0.2    =");
        System.out.println("=   Code by Eyes Of God team   =");
        System.out.println("=    Leader: Shaco JX          =");
        System.out.println("=  Member: TrungLB,HuyVT,DaiL  =");
        System.out.println("================================");
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter url: ");
        url = scan.nextLine();
        WebCrawlerWithDepth wc = new WebCrawlerWithDepth();
        System.out.println("Spider level: "+wc.MAX_DEPTH);
        wc.getPageLinks(url, 0, url);
        System.out.println("Total link: "+wc.links.size());
        s.Scan(wc.links);
        
        
    }

}
