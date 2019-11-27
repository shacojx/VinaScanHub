/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Information;

import View.VSH;
import java.io.IOException;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author toanvv1
 */
public class Info {

    public void info(String url) throws IOException {
        Connection.Response resp = (Connection.Response) Jsoup.connect(url).execute();
        Document doc = resp.parse();
        Map<String,String> Header = resp.headers();
        System.out.println("Status: "+resp.statusCode());
        System.out.println("Title: "+doc.title());
        System.out.println("WebServer: "+Header.get("Server"));
        System.out.println("X-Powered-By: "+Header.get("X-Powered-By"));
        System.out.println("Content-Type: "+Header.get("Content-Type"));
        System.out.println("Date: "+Header.get("Date"));
        System.out.println("Set-Cookie: "+Header.get("Set-Cookie"));
        VSH.Info.append("Status: "+resp.statusCode()+"\n");
        VSH.Info.append("Title: "+doc.title()+"\n");
        VSH.Info.append(url+"\n");
        VSH.Info.append("WebServer: "+Header.get("Server")+"\n");
        VSH.Info.append("X-Powered-By: "+Header.get("X-Powered-By")+"\n");
        VSH.Info.append("Content-Type: "+Header.get("Content-Type")+"\n");
        VSH.Info.append("Date: "+Header.get("Date")+"\n");
        VSH.Info.append("Set-Cookie: "+Header.get("Set-Cookie")+"\n");
        
    }
   
}
