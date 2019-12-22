/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import paramstatic.Param;

/**
 *
 * @author phulhd
 */
public class CheckSiteAdmin {

    public void checkSiteAdmin(String link, CookieManager cooki) {
        try {
            String data = ReadFileIO.readFile(System.getProperty("user.dir") + File.separator + "admin.txt");
            String[] urls = data.split("\\s+");
            ExecutorService excutor = Executors.newFixedThreadPool(VSH.numberOfThreads);
            for (String url : urls) {
                excutor.execute(new thread(link + url, cooki));
            }
            excutor.shutdown();
        } catch (Exception e) {
            System.out.println("ERROR checkSiteAdmin!!!");
            e.printStackTrace();
        }
    }

    class thread implements Runnable {

        private String url;
        CookieManager cooki;

        public thread(String url, CookieManager cooki) {
            this.url = url;
            this.cooki = cooki;
        }

        @Override
        public void run() {
//                System.out.println(url);
            try {
                /* turn off annoying htmlunit warnings */
                java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
                WebRequest requestSettings;
                WebClient client = new WebClient();
                client.getOptions().setCssEnabled(false);
                client.getOptions().setJavaScriptEnabled(false);
                client.getOptions().setThrowExceptionOnFailingStatusCode(false);
                if (cooki != null) {
                    client.setCookieManager(cooki);
                }
                client.getOptions().setTimeout(5000);
                requestSettings = new WebRequest(new URL(url), HttpMethod.GET);
                HtmlPage page = client.getPage(requestSettings);
                WebResponse response = page.getWebResponse();
                String tempBody = page.asXml();
//                Connection.Response response = Jsoup.connect(url).timeout(5000).execute();
                if (response.getStatusCode() != 404 && tempBody.contains("form")) {
                    System.err.println("Found url[" + url + "] CODE: " + response.getStatusCode());
                    Param.listAdmin.add(url);
                }
            } catch (Exception ex) {
                System.out.println("ERROR Thread checkSiteAdmin!!!");
                ex.printStackTrace();
            }
        }
    }

}
