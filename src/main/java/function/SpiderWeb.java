/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import View.VSH;
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

    public static void getPageLinks(String URL, int depth, String root_url, WebClient cClient) {
        if ((!links.contains(URL) && (depth < VSH.dept))
                && URL.contains(root_url)) {
            WebRequest requestSettings = null;
            WebClient client = new WebClient();
            client.getOptions().setCssEnabled(false);
            client.getOptions().setJavaScriptEnabled(false);
            client.getOptions().setThrowExceptionOnFailingStatusCode(false);
            if (cClient != null) {
                client = cClient;
                System.out.println("Khac NULLLLLLLLLLLLLLLLLLLLLLLLL");
            }

            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            VSH.LOG_CONSOLE.append(">> Depth: " + depth + " [" + URL + "]" + "\n");
            VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
            try {
                links.add(URL);
                requestSettings = new WebRequest(new URL(URL), HttpMethod.GET);
                HtmlPage htmlPage = client.getPage(requestSettings);
                List<HtmlAnchor> htmlAnchor = htmlPage.getAnchors();
//                System.out.println("SIZE:::::::::::::::::::::::::::"+htmlAnchor.size());
//                for (HtmlAnchor htmlAnchor1 : htmlAnchor) {
//                    System.out.println("1");
//                    System.out.println(htmlPage.getFullyQualifiedUrl(htmlAnchor1.getAttribute("href")).toString());
//                    System.out.println("2");
//                }

//                Document document = Jsoup.connect(URL).get();
//                Connection.Response resp = (Connection.Response) Jsoup.connect(URL).execute();
//                Document document = resp.parse();
//                DefaultTableModel dtm = (DefaultTableModel) View.VSH.LinkResult.getModel();
//                dtm.addRow(new Object[]{URL, resp.statusCode()});
//                Elements linksOnPage = document.select("a[href]");
                depth++;
//                for (Element page : linksOnPage) {
//                    Param.EXECUTOR_SERVICE.submit(new thread(page.attr("abs:href"), depth, root_url, null));
//                }
                DefaultTableModel dtm = (DefaultTableModel) View.VSH.LinkResult.getModel();
                dtm.addRow(new Object[]{URL, htmlPage.getWebResponse().getStatusCode()});
                for (HtmlAnchor htmlAnchor1 : htmlAnchor) {
//                    System.out.println("1");
                    String tUrl = htmlPage.getFullyQualifiedUrl(htmlAnchor1.getAttribute("href")).toString();
                    Param.EXECUTOR_SERVICE.submit(new thread(tUrl, depth, root_url, null));
//                    System.out.println("2");
                }

//                String docString = document.body().toString();
                String docString = htmlPage.asXml();
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
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public static class thread implements Runnable {

        String url;
        int dept;
        String root_url;
        WebClient client;

        public thread(String url, int dept, String root_url, WebClient cClient) {
            this.url = url;
            this.dept = dept;
            this.root_url = root_url;
            this.client = cClient;
        }

        @Override
        public void run() {
            try {
                getPageLinks(url, dept, root_url, client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
