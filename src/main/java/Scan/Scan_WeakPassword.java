/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import function.encodeValue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author toanvv1
 */
public class Scan_WeakPassword {

    encodeValue e = new encodeValue();
    
    public Map<String, String> userPass() {
        Map<String, String> map = new HashMap<String, String>();
        //dvwa
        map.put("admin", "password");
        //bwapp
        map.put("bee", "bug");
        //http://testphp.vulnweb.com
        map.put("test", "test");
        map.put("admin", "12341234");
        map.put("admin", "123456");
        map.put("admin", "123456789");
        map.put("admin", "admin");
        map.put("admin", "qwerty");

        return map;
    }

    public void bruteForce(String sURL) throws IOException {

        for (Map.Entry<String, String> entry : this.userPass().entrySet()) {
            String keyUser = entry.getKey();
            String valuePass = entry.getValue();

            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            Document doc = resp.parse();
            Map<String, String> cookie = new HashMap<>();
            cookie = resp.cookies();

            Elements linksOnPage = doc.select("form");
            Element ele = null;
            String name = "";
            String action = "";
            for (Element element : linksOnPage) {
                try {
                    action = element.attr("abs:action");
                    name = element.attr("abs:name");
                } catch (Exception e) {
                }

                if (name.contains("login") || action.contains("login") || name.contains("dangnhap") || action.contains("dangnhap")) {
                    ele = element;
                    break;
                }
            }

            Map<String, String> map = new HashMap<String, String>();
            Elements eles = ele.getElementsByAttribute("name");

            for (Element ele1 : eles) {
                String xname = ele1.attr("name");
                String xvalue = ele1.attr("value");
                String xtype = ele1.attr("type");
                if (xname.length() != 0) {
                    if (xtype.contains("submit") || xtype.contains("button")) {
                        map.put(xname, e.encodeValue(xvalue));

                    } else {
                        if (xvalue.length() != 0) {
                            map.put(xname, e.encodeValue(xvalue));

                        } else {
                            if (xtype.contains("password")) {
                                map.put(xname, e.encodeValue(valuePass));

                            } else {
                                map.put(xname, e.encodeValue(keyUser));

                            }
                        }
                    }
                }
            }

            resp = Jsoup.connect(sURL).cookies(cookie).data(map).method(Connection.Method.POST).execute();

            Document document = resp.parse();
            Elements formCheck = document.select("form");
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;

            for (Element e : formCheck) {
                if (e.attr("abs:action").equals(action)) {
                    checkLogin1 = false;
                    break;
                }
            }
            formCheck = document.getElementsByAttribute("href");
            for (Element e : formCheck) {
                if (e.attr("abs:href").equals(action)) {
                    checkLogin2 = false;
                    break;
                }
            }
            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + keyUser + " ---- Password: " + valuePass);
                break;
            }
        }
    }
    
    public void bruteForceJSOUP(String sURL, String[][] userPass) throws IOException {
        encodeValue en = new encodeValue();
        for (String[] obj : userPass) {
            String user = obj[0];
            String pass = obj[1];

            Connection.Response resp = Jsoup.connect(sURL).userAgent("Mozilla").method(Connection.Method.GET).execute();
            Document doc = resp.parse();
            Map<String, String> cookie = new HashMap<>();
            cookie = resp.cookies();

            Elements linksOnPage = doc.select("form");
            Element ele = null;
            String name = "";
            String action = "";
            String id = "";
            for (Element element : linksOnPage) {
                try {
                    action = element.attr("abs:action");
                    name = element.attr("name");
                } catch (Exception e) {
                }
                try {
                    id = element.attr("id");
                } catch (Exception e) {
                }

                if (name.toLowerCase().contains("login")
                        || action.toLowerCase().contains("login")
                        || id.toLowerCase().contains("login")
                        || name.toLowerCase().contains("dangnhap")
                        || action.toLowerCase().contains("dangnhap")
                        || id.toLowerCase().contains("dangnhap")) {
                    ele = element;
                    break;
                }
            }

            Map<String, String> map = new HashMap<String, String>();
            Elements eles = ele.getElementsByAttribute("name");

            for (Element ele1 : eles) {
                String xname = ele1.attr("name");
                String xvalue = ele1.attr("value");
                String xtype = ele1.attr("type");
                if (xname.length() != 0 && !name.contains(xname)) {
                    if (xtype.contains("submit") || xtype.contains("button")) {
                        map.put(xname, en.encodeValue(xvalue));

                    } else {
                        if (xvalue.length() != 0) {
                            map.put(xname, en.encodeValue(xvalue));

                        } else {
                            if (xtype.contains("password")) {
                                map.put(xname, en.encodeValue(pass));

                            } else {
                                map.put(xname, en.encodeValue(user));

                            }
                        }
                    }
                }
            }
            System.out.println("Map: " + map);
            resp = Jsoup.connect(action).cookies(cookie).data(map).method(Connection.Method.POST).execute();
            System.out.println("Action: " + action);
            System.out.println("URL: " + resp.url().toString());
            System.out.println("Map: " + map);

            Document document = resp.parse();
            Elements formCheck = document.select("form");
            boolean checkLogin1 = true;
            boolean checkLogin2 = true;

            for (Element e : formCheck) {
                if (e.attr("abs:action").contains(action)) {
                    checkLogin1 = false;
                    break;
                }
            }
//            formCheck = document.getElementsByAttribute("href");
//            for (Element e : formCheck) {
//                if (e.attr("abs:href").contains(sURL)) {
//                    checkLogin2 = false;
//                    break;
//                }
//            }
            System.out.println("Check1: " + checkLogin1);
            System.out.println("Check2: " + checkLogin2);

            if (checkLogin1 && checkLogin2) {
                System.out.println("Login Thanh Cong : " + sURL);
                System.out.println("User: " + user + " ---- Password: " + pass);
                break;
            }
        }
    }
    
}
