/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import Scan.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import PaySig.*;
import View.VSH;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import static paramstatic.Param.EXECUTOR_SERVICE;

/**
 *
 * @author Shaco JX
 */
public class Scan {

    public Scan() {
    }

    public static ArrayList<String> list_vuln = new ArrayList<>();
    public static HashSet<String> checkURLGET = new HashSet<>();
    public static HashSet<String> checkURLPOST = new HashSet<>();

    Scan_BlindSQLi sBlinkSQLi = new Scan_BlindSQLi();
    Scan_SQLi sSQLi = new Scan_SQLi();
    Scan_XMLXpatchi sXMLXpatchi = new Scan_XMLXpatchi();
    Scan_XSS sXSS = new Scan_XSS();
    Scan_CodeInjection sCI = new Scan_CodeInjection();
    Scan_LFI sLFI = new Scan_LFI();
    Scan_CMDinjection sCMDi = new Scan_CMDinjection();
    Scan_WeakPassword sWeakPass = new Scan_WeakPassword();
    Scan_DirList sDirList = new Scan_DirList();

    psSQLi psSQLin = new psSQLi();
    psXMLXpatchi psXMLXpatchin = new psXMLXpatchi();
    psXSS psXSS = new psXSS();
    psCodeInjection psCI = new psCodeInjection();
    psLFI psLFI = new psLFI();
    psCMDInjection psCMDi = new psCMDInjection();
    psUserPass psUP = new psUserPass();

    public void Scan(String url) throws IOException, InterruptedException, InterruptedException {
        SpiderWeb spider = new SpiderWeb();
        System.out.println("Spider level: " + VSH.dept);
        VSH.LOG_CONSOLE.append("Spider level: " + VSH.dept + "\n");
        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
        EXECUTOR_SERVICE = Executors.newFixedThreadPool(VSH.numberOfThreads);
        EXECUTOR_SERVICE.execute(new SpiderWeb.thread(url, 0, url));
        new Thread(() -> {
            try {
                EXECUTOR_SERVICE.awaitTermination(20, TimeUnit.SECONDS);
                EXECUTOR_SERVICE.shutdown();
                System.out.println("==========================================");
                VSH.LOG_CONSOLE.append("Total link: " + spider.links.size() + "\n");
                VSH.LOG_CONSOLE.append("=========================================" + "\n");
                VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                System.out.println("Total link: " + spider.links.size());
                spider.links.forEach((s) -> {
                    System.out.println(s);
                });
                System.out.println("---------------------------------------------------------------------------------");

                this.scanVuln(spider.links);

//        for(String xxx : Param.listAdmin){
//            spider.links.add(xxx);
//        }
                this.BruteForce(spider.links);
            } catch (InterruptedException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }

        }).start();

    }
    ExecutorService service;

    public void scanVuln(HashSet<String> listURL) throws IOException {
        Scan scan = new Scan();
        service = Executors.newFixedThreadPool(VSH.numberOfThreads);
        for (String sURL : listURL) {
            if (!sURL.contains("png") && !sURL.contains("jpg")) {
                if (sURL.contains("#")) {
                    sURL = sURL.split("#")[0];
                }
                if (sURL.contains("?") && sURL.contains("=")) {
                    if (!checkURLGET.contains(sURL.split("\\?")[0])) {
                        scan.checkURLGET.add(sURL.split("\\?")[0]);
                        this.scanMethodGet(sURL);
                    }
                }
                try {
                    Document document = Jsoup.connect(sURL).userAgent("Mozilla").followRedirects(false).get();
                    Elements linksOnPage = document.select("form");
                    for (Element element : linksOnPage) {
                        String temp = "";
                        try {
                            temp = element.attr("abs:action");
                        } catch (Exception e) {
                        }

                        if (temp.length() == 0) {
                            temp = sURL;
                        }

                        if (temp.contains("?") && sURL.contains("=") && !checkURLGET.contains(temp.split("\\?")[0])) {
                            scan.checkURLGET.add(temp.split("\\?")[0]);
                            this.scanMethodGet(temp);
                        }
                        String method = element.attr("method").toLowerCase();
                        if (method.contains("get") && !checkURLGET.contains(temp)) {
                            scan.checkURLGET.add(temp);
                                this.scanMethodGetPost(element, temp);
                        } else {
                            if (method.contains("post") && !checkURLPOST.contains(temp)) {
                                scan.checkURLPOST.add(temp);
                                    this.scanMethodGetPost(element, temp);                                
                            }
                        }

                    }
                } catch (IOException e) {
                    //System.out.println("Error scanVuln: " + sURL + " ||| " + e);
                }

            }
        }
        service.shutdown();
        System.out.println("Scan end!");
    }

    public void BruteForce(HashSet<String> listURL) throws IOException {
        for (String url : listURL) {
            if (url.contains("login") || url.contains("sigin") || url.contains("admin")
                    || url.contains("dang") || url.contains("nhap")) {
                sWeakPass.bruteForce(url, psUP.getUserPass());
            }
        }
    }

    public void scanMethodGet(String urlAction) throws IOException {
        service.execute(() -> {
            try {
                this.sSQLi.scanSQLin(null, urlAction, this.psSQLin.getArrPaySQLin());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sXMLXpatchi.scanXMLXpatchin(null, urlAction, this.psXMLXpatchin.getArrPayXMLXPathin());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sXSS.scanXSS(null, urlAction, this.psXSS.getArrPayXSS());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sLFI.scanLFI(null, urlAction, this.psLFI.getArrPayLFI());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sCI.scanCI(null, urlAction, this.psCI.getArrPayCI());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sCMDi.scanCMDi(null, urlAction, this.psCMDi.getArrPayCMDi());
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

//        BlindSQLinjection(urlAction);
    }

    public void scanMethodGetPost(Element element, String urlAction) throws IOException {
        service.execute(() -> {
            try {
                this.sSQLi.scanSQLin(element, urlAction, this.psSQLin.getArrPaySQLin());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sXMLXpatchi.scanXMLXpatchin(element, urlAction, this.psXMLXpatchin.getArrPayXMLXPathin());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sXSS.scanXSS(element, urlAction, this.psXSS.getArrPayXSS());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sLFI.scanLFI(element, urlAction, this.psLFI.getArrPayLFI());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sCI.scanCI(element, urlAction, this.psCI.getArrPayCI());

            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sCMDi.scanCMDi(element, urlAction, this.psCMDi.getArrPayCMDi());
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
