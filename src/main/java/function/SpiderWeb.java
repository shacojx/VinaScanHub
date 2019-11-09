/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import View.VSH;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static void getPageLinks(String URL, int depth, String root_url) {
        if ((!links.contains(URL) && (depth < VSH.dept))
                && URL.contains(root_url)) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            VSH.LOG_CONSOLE.append(">> Depth: " + depth + " [" + URL + "]" + "\n");
            VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
            try {
                links.add(URL);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    Param.EXECUTOR_SERVICE.submit(new thread(page.attr("abs:href"), depth, root_url));
                }

                String docString = document.body().toString();
                String regex = "[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(docString);

                while (matcher.find()) {
                    String email = matcher.group();
                    if (Param.listEmail.add(email)) {
                        System.out.println("Found 1 email: " + matcher.group());
                        VSH.LOG_CONSOLE.append("Found 1 email: " + matcher.group() + "\n");
                        VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                    }
                    // Get the group matched using group() method 

                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }

    public static class thread implements Runnable {

        String url;
        int dept;
        String root_url;

        public thread(String url, int dept, String root_url) {
            this.url = url;
            this.dept = dept;
            this.root_url = root_url;
        }

        @Override
        public void run() {
            try {
                getPageLinks(url, dept, root_url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
