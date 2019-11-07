/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import static function.WebCrawlerWithDepth.MAX_DEPTH;
import java.io.IOException;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author laxba
 */
public class SpiderWeb {

    public final int MAX_DEPTH = 5;
    public HashSet<String> links = new HashSet<>();

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
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}
