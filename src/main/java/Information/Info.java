/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Information;

import Scan.Scan_WeakPassword;
import View.VSH;
import com.gargoylesoftware.htmlunit.CookieManager;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author toanvv1
 */
public class Info {

    public void info(String url, CookieManager cooki) throws IOException {
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
        requestSettings = new WebRequest(new URL(url), HttpMethod.GET);
        HtmlPage page = client.getPage(requestSettings);
        WebResponse response = page.getWebResponse();
        List<NameValuePair> li = response.getResponseHeaders();
        System.out.println("URL: " + url);
        VSH.Info.append("URL: " + url + "\n");
        int status = response.getStatusCode();
        System.out.println("Status: " + status);
        VSH.Info.append("Status: " + status + "\n");
        String title = page.getTitleText();
        System.out.println("Title: " + title);
        VSH.Info.append("Title: " + title + "\n");
        System.out.println("Set-Cookie: " + client.getCookieManager().getCookies().toString());
        VSH.Info.append("Set-Cookie: " + client.getCookieManager().getCookies().toString() + "\n");
        for (NameValuePair header : li) {
            System.out.println(header.getName() + " = " + header.getValue());
            VSH.Info.append(header.getName() + " = " + header.getValue() + "\n");
        }

//        Connection.Response resp = (Connection.Response) Jsoup.connect(url).execute();
//        Document doc = resp.parse();
//        Map<String, String> Header = resp.headers();
//        System.out.println("Status: " + resp.statusCode());
//        System.out.println("Title: " + doc.title());
//        System.out.println("WebServer: " + Header.get("Server"));
//        System.out.println("X-Powered-By: " + Header.get("X-Powered-By"));
//        System.out.println("Content-Type: " + Header.get("Content-Type"));
//        System.out.println("Date: " + Header.get("Date"));
//        System.out.println("Set-Cookie: " + Header.get("Set-Cookie"));
//        VSH.Info.append("Status: " + resp.statusCode() + "\n");
//        VSH.Info.append("Title: " + doc.title() + "\n");
//        VSH.Info.append(url + "\n");
//        VSH.Info.append("WebServer: " + Header.get("Server") + "\n");
//        VSH.Info.append("X-Powered-By: " + Header.get("X-Powered-By") + "\n");
//        VSH.Info.append("Content-Type: " + Header.get("Content-Type") + "\n");
//        VSH.Info.append("Date: " + Header.get("Date") + "\n");
//        VSH.Info.append("Set-Cookie: " + Header.get("Set-Cookie") + "\n");
    }

}
