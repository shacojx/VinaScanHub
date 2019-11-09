/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

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

    public void getPageLinks(String URL, int depth, String root_url) {
        if ((!links.contains(URL) && (depth < MAX_DEPTH))
                && URL.contains(root_url)) {
            System.out.println(">> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);

                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");

                depth++;
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth, root_url);
                }
                String docString = document.body().toString();
                String regex = "[a-zA-Z0-9-_.]+@[a-zA-Z0-9-_.]+";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(docString);

                while (matcher.find()) {
                    String email = matcher.group();
                    if(Param.listEmail.add(email)) {
                        System.out.println("Found 1 email: "+matcher.group());
                    }
                    // Get the group matched using group() method 
                    
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}
