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
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import paramstatic.Param;

/**
 *
 * @author Shaco JX
 */
public class SpiderWeb {

    public static int MAX_DEPTH = 5;
    public static HashSet<String> links = new HashSet<>();

    public SpiderWeb() {
    }

    public SpiderWeb(HashSet<String> links) {
        this.links = links;
    }

    public static void getPageLinks(String URL, int depth, String root_url, CookieManager cooki) {
        /* turn off annoying htmlunit warnings */
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF);
        if ((!links.contains(URL)
                && (depth < VSH.dept))
                && URL.contains(root_url)
                && !URL.contains("logout")
                && !URL.contains("dangxuat")
                && !URL.contains("thoat")) {

            if (URL.toLowerCase().contains(".jpg")
                    || URL.toLowerCase().contains(".pdf")
                    || URL.toLowerCase().contains(".png")) {
                return;
            }
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            VSH.LOG_CONSOLE.append(">> Depth: " + depth + " [" + URL + "]" + "\n");
            VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());

            WebRequest requestSettings;
            WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            client.getOptions().setThrowExceptionOnFailingStatusCode(false);
            if (cooki != null) {
                client.setCookieManager(cooki);
            }

            try {
                links.add(URL);

                requestSettings = new WebRequest(new URL(URL), HttpMethod.GET);
                HtmlPage htmlPage = client.getPage(requestSettings);
                List<HtmlAnchor> htmlAnchor = htmlPage.getAnchors();
                DefaultTableModel dtm = (DefaultTableModel) View.VSH.LinkResult.getModel();
                dtm.addRow(new Object[]{URL, htmlPage.getWebResponse().getStatusCode()});

//                Document document = Jsoup.connect(URL).get();
//                Connection.Response resp = (Connection.Response) Jsoup.connect(URL).execute();
//                Document document = resp.parse();
//                DefaultTableModel dtm = (DefaultTableModel) View.VSH.LinkResult.getModel();
//                dtm.addRow(new Object[]{URL, resp.statusCode()});
//                Elements linksOnPage = document.select("a[href]");
                depth++;
                for (HtmlAnchor htmlAnchor1 : htmlAnchor) {
//                for (Element page : linksOnPage) {
                    String tUrl = htmlPage.getFullyQualifiedUrl(htmlAnchor1.getAttribute("href")).toString();
                    Param.EXECUTOR_SERVICE.submit(new thread(tUrl, depth, root_url, cooki));
//                    Param.EXECUTOR_SERVICE.submit(new thread(page.attr("abs:href"), depth, root_url));
                }

                String docString = htmlPage.asXml();
//                String docString = document.body().toString();
                String regex = "[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(docString);

                while (matcher.find()) {
                    String email = matcher.group();
                    if (Param.listEmail.add(email)) {
                        System.out.println("Found 1 email: " + matcher.group());
                        DefaultTableModel dtmz = (DefaultTableModel) View.VSH.OtherResult.getModel();
                        dtmz.addRow(new Object[]{"Found 1 email: " + matcher.group()});
                        VSH.LOG_CONSOLE.append("Found 1 email: " + matcher.group() + "\n");
                        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                    }
                    // Get the group matched using group() method 

                }
            } catch (Exception e) {
                System.err.println("ERROR spiderWeb : " + URL + " || " + e.toString());
//                e.printStackTrace();
            }
        }
    }

    public static class thread implements Runnable {

        String url;
        int dept;
        String root_url;
        CookieManager cooki;

        public thread(String url, int dept, String root_url, CookieManager cooki) {
            this.url = url;
            this.dept = dept;
            this.root_url = root_url;
            this.cooki = cooki;
        }

        @Override
        public void run() {
            try {
                getPageLinks(url, dept, root_url, cooki);                
            } catch (Exception e) {
                System.out.println("ERROR thread spider!!!");
                e.printStackTrace();
            }
        }

    }
}
