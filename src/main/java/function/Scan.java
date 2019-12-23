/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import Information.Info;
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
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import paramstatic.Param;
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
    Scan_CVE_2017_9248 scan_telerik = new Scan_CVE_2017_9248();
    Scan_FileUpload sFileUp = new Scan_FileUpload();
    Scan_Unknow sUnknow = new Scan_Unknow();

    psSQLi psSQLin = new psSQLi();
    psXMLXpatchi psXMLXpatchin = new psXMLXpatchi();
    psXSS psXSS = new psXSS();
    psCodeInjection psCI = new psCodeInjection();
    psLFI psLFI = new psLFI();
    psCMDInjection psCMDi = new psCMDInjection();
    psUserPass psUP = new psUserPass();
    psUploadFile psUpFile = new psUploadFile();

    public static boolean checkC = true;

    public void Scan(String url) throws IOException, InterruptedException, InterruptedException {
        Scan_WeakPassword sw = new Scan_WeakPassword();
        if (VSH.cbAuthen.isSelected()) {
            sw.setCheckLogin(true);
            String user = VSH.tfUser.getText();
            String pass = VSH.tfPassword.getText();
            String[][] ps = {
                {user, pass},};
            sw.bruteForce(url, ps, null);
        }
        CookieManager cookieM = sw.getCookieManager();
        String urlTemp = url;
        try {
            if (sw.getUrlS().length() != 0) {
                urlTemp = sw.getUrlS();
            }
            System.out.println("Cookie Test : " + cookieM.getCookies().toString());
        } catch (Exception e) {
        }
        final String urlScan = urlTemp;

        SpiderWeb spider = new SpiderWeb();
        Info in = new Info();
        in.info(urlScan, cookieM);
        scan_telerik.scan(urlScan, cookieM);
        VSH.Action.setText("Scanning Vuln!!!");
        VSH.Loading.setText("");
        System.out.println("Spider level: " + VSH.dept);
        VSH.LOG_CONSOLE.append("Spider level: " + VSH.dept + "\n");
        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
        EXECUTOR_SERVICE = Executors.newFixedThreadPool(VSH.numberOfThreads);

        String baseUrl = urlScan.split("/")[0] + "/" + urlScan.split("/")[1] + "/" + urlScan.split("/")[2] + "/";

        EXECUTOR_SERVICE.execute(new SpiderWeb.thread(urlScan, 0, baseUrl, cookieM));
//        EXECUTOR_SERVICE.execute(new SpiderWeb.thread(url, 0, url));
        new Thread(() -> {
            try {
                EXECUTOR_SERVICE.awaitTermination(60, TimeUnit.SECONDS);
                EXECUTOR_SERVICE.shutdown();
                System.out.println("==========================================");
                VSH.LOG_CONSOLE.append("Total link: " + spider.links.size() + "\n");
                VSH.LOG_CONSOLE.append("=========================================" + "\n");
                VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                System.out.println("Total link: " + spider.links.size());
                for (String s : spider.links) {
                    System.out.println(s);
                }
//                spider.links.forEach((s) -> {
//                    System.out.println(s);
//                });
                System.out.println("---------------------------------------------------------------------------------");
                if (checkC) {
                    CheckSiteAdmin checkSite = new CheckSiteAdmin();
                    checkSite.checkSiteAdmin(urlScan, cookieM);
                    this.scanVuln(spider.links, cookieM);
                    for (String xxx : Param.listAdmin) {
                        spider.links.add(xxx);
                    }
                    this.BruteForce(spider.links, cookieM);
                }
                checkC = true;
            } catch (Exception ex) {
                System.out.println("ERROR Scan!!!");
                ex.printStackTrace();
            }
        }).start();
    }
    public static ExecutorService service;

    public void scanVuln(HashSet<String> listURL, CookieManager cooki) throws IOException {
        /* turn off annoying htmlunit warnings */
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        Scan scan = new Scan();
        service = Executors.newFixedThreadPool(VSH.numberOfThreads);
        for (String sURL : listURL) {
            if (!sURL.contains("png")
                    && !sURL.contains("jpg")
                    && !sURL.contains("pdf")) {

                if (sURL.contains("/vulnerabilities/weak_id/")
                        || sURL.contains("/vulnerabilities/csrf/")
                        || sURL.contains("logout.php")
                        || sURL.contains("security.php")) {
                    continue;
                }

                if (sURL.contains("#")) {
                    sURL = sURL.split("#")[0];
                }
                if (sURL.contains("?") && sURL.contains("=")) {
                    if (!checkURLGET.contains(sURL.split("\\?")[0])) {
                        scan.checkURLGET.add(sURL.split("\\?")[0]);
                        this.scanMethodGet(sURL, cooki);
                    }
                }
                try {
                    WebRequest requestSettings;
                    WebClient client = new WebClient();
                    client.getOptions().setCssEnabled(false);
                    client.getOptions().setJavaScriptEnabled(false);
                    client.getOptions().setThrowExceptionOnFailingStatusCode(false);
                    List<NameValuePair> params;
                    if (cooki != null) {
                        client.setCookieManager(cooki);
                    }

                    requestSettings = new WebRequest(new URL(sURL), HttpMethod.GET);
                    HtmlPage page = client.getPage(requestSettings);
                    List<HtmlForm> htmlForm = page.getForms();

//                    Document document = Jsoup.connect(sURL).userAgent("Mozilla").followRedirects(false).get();
//                    Elements linksOnPage = document.select("form");
                    for (HtmlForm form : htmlForm) {
//                    for (Element element : linksOnPage) {
                        String temp = sURL;
                        try {
                            temp = page.getFullyQualifiedUrl(form.getActionAttribute()).toString();
                            temp = temp.replaceAll("#", "");
//                            temp = element.attr("abs:action");
                        } catch (Exception e) {
                        }

                        if (temp.length() == 0) {
                            temp = sURL;
                        }

                        if (temp.contains("?") && sURL.contains("=") && !checkURLGET.contains(temp.split("\\?")[0])) {
                            scan.checkURLGET.add(temp.split("\\?")[0]);
                            this.scanMethodGet(temp, cooki);
                        }
                        String method = "get";
                        try {
                            method = form.getMethodAttribute().toLowerCase();
                        } catch (Exception e) {
                        }
//                        String method = element.attr("method").toLowerCase();
                        Document doc = Jsoup.parse(form.asXml());
                        Element element = doc;
                        if (method.contains("get") && !checkURLGET.contains(temp)) {
                            scan.checkURLGET.add(temp);
                            this.scanMethodGetPost(element, temp, method, cooki);
                        } else {
                            if (method.contains("post") && !checkURLPOST.contains(temp)) {
                                scan.checkURLPOST.add(temp);
                                this.scanMethodGetPost(element, temp, method, cooki);
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("ERROR scanVuln: " + sURL);
                    e.printStackTrace();
                }
            }
        }
        service.shutdown();
        System.out.println("Scan end!");
        VSH.LOG_CONSOLE.append("Scan end!" + "\n");
        VSH.Loading.setText("Loading |===================>|");
        VSH.Action.setText("Do Nothing");
        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
        VSH.setAllViewEnable(true);
        VSH.btnScan.setEnabled(true);
        VSH.setViewAuthenEnable(VSH.cbAuthen.isSelected());
        shutdownAndAwaitTermination(service);
    }

    public void BruteForce(HashSet<String> listURL, CookieManager cooki) throws IOException {
        for (String url : listURL) {
            if (url.contains("login") || url.contains("sigin") || url.contains("admin")
                    || url.contains("dang") || url.contains("nhap")) {
                sWeakPass.bruteForce(url, psUP.getUserPass(), cooki);
            }
        }
    }

    public void scanMethodGet(String urlAction, CookieManager cooki) throws IOException {
        String method = "get";
        service.execute(() -> {
            try {
                this.sFileUp.uploadfile(urlAction, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sSQLi.scanSQLin(null, urlAction, this.psSQLin.getArrPaySQLin(), method, cooki);
            } catch (Exception ex) {
                System.out.println("ERROR scanMethodGET SQLi!!!");
                ex.printStackTrace();
//                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sXMLXpatchi.scanXMLXpatchin(null, urlAction, this.psXMLXpatchin.getArrPayXMLXPathin(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sXSS.scanXSS(null, urlAction, this.psXSS.getArrPayXSS(), method, cooki);
            } catch (Exception ex) {
                System.out.println("ERROR scanMethodGET XSS!!!");
                ex.printStackTrace();
//                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.scan_telerik.scan(urlAction, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sLFI.scanLFI(null, urlAction, this.psLFI.getArrPayLFI(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sCI.scanCI(null, urlAction, this.psCI.getArrPayCI(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sCMDi.scanCMDi(null, urlAction, this.psCMDi.getArrPayCMDi(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sBlinkSQLi.scanBlindSQLin(null, urlAction, method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sUnknow.scan_Unknow(null, urlAction, View.VSH.PayloadAdd, method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void scanMethodGetPost(Element element, String urlAction, String method, CookieManager cooki) throws IOException {
        service.execute(() -> {
            try {
                this.sFileUp.uploadfile(urlAction, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sSQLi.scanSQLin(element, urlAction, this.psSQLin.getArrPaySQLin(), method, cooki);
            } catch (Exception ex) {
                System.out.println("ERROR scanMethodGETPOST SQLi!!!");
                ex.printStackTrace();
//                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sXMLXpatchi.scanXMLXpatchin(element, urlAction, this.psXMLXpatchin.getArrPayXMLXPathin(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sXSS.scanXSS(element, urlAction, this.psXSS.getArrPayXSS(), method, cooki);
            } catch (Exception ex) {
                System.out.println("ERROR scanMethodGET XSS!!!");
                ex.printStackTrace();
//                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.scan_telerik.scan(urlAction, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sLFI.scanLFI(element, urlAction, this.psLFI.getArrPayLFI(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sCI.scanCI(element, urlAction, this.psCI.getArrPayCI(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sCMDi.scanCMDi(element, urlAction, this.psCMDi.getArrPayCMDi(), method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        service.execute(() -> {
            try {
                this.sBlinkSQLi.scanBlindSQLin(element, urlAction, method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.execute(() -> {
            try {
                this.sUnknow.scan_Unknow(element, urlAction, View.VSH.PayloadAdd, method, cooki);
            } catch (IOException ex) {
                Logger.getLogger(Scan.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.err.println("Pool did not terminate");
                }
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
