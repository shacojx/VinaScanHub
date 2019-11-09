/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;
 
import java.io.File;
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
 
    public void checkSiteAdmin() {
        String data = ReadFileIO.readFile(System.getProperty("user.dir") + File.separator + "admin.txt");
        String[] urls = data.split("\\s+");
        ExecutorService excutor = Executors.newFixedThreadPool(100);
        for (String url : urls) {
            excutor.execute(new thread("http://testphp.vulnweb.com/" + url));
        }
        excutor.shutdown();
    }
 
    class thread implements Runnable {
 
        private String url;
 
        public thread(String url) {
            this.url = url;
        }
 
        @Override
        public void run() {
//                System.out.println(url);
                try {
                    Connection.Response response = Jsoup.connect(url).timeout(5000).execute();
                    if (response.statusCode() != 404) {
                        System.err.println("Found url[" + url + "] CODE: " + response.statusCode());
                        Param.listAdmin.add(url);
                    }
                } catch (Exception ex) {
                   
                }
            }
        }
 
        public static void main(String[] args) {
            CheckSiteAdmin checkSite = new CheckSiteAdmin();
            checkSite.checkSiteAdmin();
 
        }
    }