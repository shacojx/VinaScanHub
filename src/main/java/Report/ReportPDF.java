/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import com.lowagie.text.DocumentException;
import java.io.*;
import java.util.ArrayList;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 *
 * @author Shaco JX
 */
public class ReportPDF {

    public void generatePDF(String inputHtmlPath, String outputPdfPath) {
        try {
            String url = new File(inputHtmlPath).toURI().toURL().toString();
            System.out.println("URL: " + url);

            OutputStream out = new FileOutputStream(outputPdfPath);

            //Flying Saucer part
            ITextRenderer renderer = new ITextRenderer();

            renderer.setDocument(url);
            renderer.layout();
            renderer.createPDF(out);

            out.close();
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void genFileHTML() throws IOException {
//        String vuln = "";
//        String vulnx = "";
//        for (String x : list) {
//            if (x.split(": ")[1].contains("&")) {
//                x.split(": ")[1].split("&");
//                vulnx = x.split(": ")[1].split("&")[0] + "&amp;" + x.split(": ")[1].split("&")[1];
//            } else {
//                vulnx = x.split(": ")[1];
//            }
//
//            vuln = vuln + "<tr>\n"
//                    + "<td style=\"padding-right: 100px;\"><b>" + x.split(": ")[0] + "</b></td>\n"
//                    + "<td><b>" + vulnx + "</b></td>\n"
//                    + " </tr>\n";
//        }
//        String str = "<body>\n"
//                + "    <div style=\"\n"
//                + "    text-align: center;\n"
//                + "    background-color: green;\n"
//                + "    color: red;\">\n"
//                + "        <h1>Vina Scan Hub</h1>\n"
//                + "        <h3>Report Scan Result</h3>\n"
//                + "    </div>\n"
//                + "    <div style=\"\n"
//                + "    text-align: center;\n"
//                + "    margin-left: 40%;\">\n"
//                + "        <table>\n"
//                + "            <tr>\n"
//                + "            <th style=\"padding-right: 40px;\">Vulnerability</th>\n"
//                + "            <th>Link</th>\n"
//                + "            </tr>\n"
//                + vuln
//                + "        </table>\n"
//                + "    </div>\n"
//                + "</body>";

        StringBuilder db = new StringBuilder();
        db.append("<!DOCTYPE html>");
        db.append("<html>");
        db.append("<head>");
        db.append("<title></title>");
        db.append("<meta charset=\"utf-8\">");
        db.append("<meta name=\"viewport\" content=\"width=device-width\"initial-scale=1\">");
        db.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">");
        db.append("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>");
        db.append("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>");
        db.append("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>");
        db.append("<link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.0/css/all.css\" integrity=\"sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ\" crossorigin=\"anonymous\">");
        db.append("<style type=\"text/css\">");
        db.append("body{");
        db.append("font-family: sans-serif;");
        db.append("margin: 0 ;");
        db.append("padding : 0;");
        db.append("box-sizing: border-box;");
        db.append("}");
        db.append(".title-bottom-web{");
        db.append("font-size: 10px;");
        db.append("}");
        db.append("#Items{");
        db.append("background-image: url(Hinh1.jpg);");
        db.append("background-repeat: no-repeat;");
        db.append("background-size: cover;");
        db.append("background-position: 50% 50%;");
        db.append("}");
        db.append("div#Scan {");
        db.append("border-bottom: 3px solid grey;");
        db.append("}");
        db.append("table\" th td {");
        db.append("border: 1px solid black;");
        db.append("border-collapse: collapse;");
        db.append("}");
        db.append("th\" td {");
        db.append("padding: 5px;");
        db.append("text-align: left;    ");
        db.append("}");
        db.append(".text1 {");
        db.append("border: 1px solid black;");
        db.append("font-family: monospace;");
        db.append("font-size: 14px;");
        db.append("}");
        db.append(".text2 {");
        db.append("font-family: monospace;");
        db.append("font-size: 15px;");
        db.append("}");
        db.append("</style>");
        db.append("</head>");
        db.append("<body>");
        db.append("<div>");
        db.append("<div class=\"container\">");
        db.append("<div>");
        db.append("<img src=\"Hinh23.png\">");
        db.append("</div>");
        db.append("<br>");
        db.append("<div class=\"text-center text-muted\" id=\"Items\">");
        db.append("<h1>Affected Items <br>Report</h1>");
        db.append("<p class=\"pt-5\">Acunetix website audit</p>");
        db.append("<p class=\"pt-5\">12 December 2019</p>");
        db.append("</div>");
        db.append("<div class=\"pt-5 text-center\">");
        db.append("<div>");
        db.append("<h6>WEB APPLICATION SECURITY</h6>");
        db.append("<hr>");
        db.append("<p class=\"title-bottom-web\">Generated by Acunetix Reporter</p>");
        db.append("</div>");
        db.append("</div>");
        db.append("</div>");
        db.append("</div>");
        db.append("<br>");
        db.append("<br>");
        db.append("<br>");
        db.append("");
        db.append("<div class=\"container\">");
        db.append("<div class=\"text-center\" id=\"Scan\"><h4><strong>Scan of https://soyte.hanoi.gov.vn/</strong></h4></div>");
        db.append("<div class=\"pt-3\"><h5><strong>Scan Details</strong></h5></div>");
        db.append("</div>");
        db.append("<div class=\"container pt-1\">");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th colspan=\"2\">Scan information </th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Start time</td>");
        db.append("<td>10/12/2019, 23:35:52 </td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Start url</td>");
        db.append("<td>https://soyte.hanoi.gov .vn/</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Host</td>");
        db.append("<td>https://soyte.hanoi.gov .vn/</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Scan time</td>");
        db.append("<td>792 minutes, 4 seconds</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Profile</td>");
        db.append("<td>Full Scan</td>");
        db.append("</tr>");
        db.append("</table>");
        db.append("</div>");
        db.append("<br>");
        db.append("<div class=\"container\">");
        db.append("<div class=\"text-center\"><h4><strong>Threat level</strong></h4></div>");
        db.append("<hr>");
        db.append("<div class=\"pt-3\"><h5><strong>Acunetix Threat Level 2</strong></h5></div>");
        db.append("</div>");
        db.append("<div class=\"container\">");
        db.append("<p class=\"pt-3 pb-3\">One or more medium-severity type vulnerabilities have been discovered by the scanner. You should investigate each of these vulnerabilities to ensure they will not escalate to more severe problems.");
        db.append("</p>");
        db.append("<div><h5><strong>Alerts distribution</strong></h5></div>");
        db.append("<hr>");
        db.append("</div>");
        db.append("<div class=\"container pt-3\">");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th>Total alerts found</th>");
        db.append("<th>95</th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><i class=\"fas fa-exclamation-circle text-danger\"></i>&ensp;<span>Hight</span></td>");
        db.append("<td>0</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><i class=\"fas fa-exclamation-circle text-warning\"></i>&ensp;<span>Medium</span></td>");
        db.append("<td>3</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><i class=\"fas fa-exclamation-circle text-primary\"></i>&ensp;<span>Low</span></td>");
        db.append("<td>1</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><i class=\"fas fa-exclamation-circle text-info\"></i>&ensp;<span>Informational</span></td>");
        db.append("<td>91</td>");
        db.append("</tr>");
        db.append("</table>");
        db.append("<div class=\"pt-5\"><h5><strong>Alerts distribution</strong></h5></div>");
        db.append("<hr>");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th>/combo</th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><b>Alert group</b></td>");
        db.append("<td><b>Application error message </b></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Severity</td>");
        db.append("<td>Medium</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Description</td>");
        db.append("<td>");
        db.append("<div class=\"text\">This page contains an error/warning message that may disclose sensitive information. The<br> message can also contain the location of the file that produced the unhandled exception.");
        db.append("</div>");
        db.append("<br>");
        db.append("<div class=\"text\">This may be a false positive if the error message is found in documentation pages</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Recommendations</td>");
        db.append("<td>Review the source code for this script.</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Alert variants");
        db.append("</td>");
        db.append("<td></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Details</td>");
        db.append("<td class=\"pb-3\">");
        db.append("<div>URL encoded GET input was set to </div>");
        db.append("<br>");
        db.append("<div>Pattern found: </div>");
        db.append("<br>");
        db.append("<div class=\"text1\">Internal Server Error");
        db.append("</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td colspan=\"2\">");
        db.append("<div class=\"text2\">");
        db.append("GET /combo/?&b=6205&browserId=other&languageId=vi_VN&minifierType=&t=1574996606000 HTTP/1.1<br> Referer: https://soyte.hanoi.gov.vn/<br> Cookie: JSESSIONID=4czRoZ5p4CbmuyzDHgMj6u7+; COOKIE_SUPPORT=true; <br>BNES_JSESSIONID=Z6gh0MpWyPbc1KxsruoXTUW/6Z0vzIqNS/Xz0fYf6rM/3m8b8UfpRYjMT2pa4IggwXAr7TcUVqsP<br> JgwPYZunwMbXOT58FXcq;<br> BNES_COOKIE_SUPPORT=gBPBZj99nz+5wQjN6CCSeQ8WYUoRgU8JJoqmbKdJcbxSVx5yY3CPMjDPMEutIMgrKImX68mi<br> eBc=; GUEST_LANGUAGE_ID=vi_VN;<br> BNES_GUEST_LANGUAGE_ID=fN5CS5gAu0fJyumSWNi4w49QYy6QknMsrVFQfWKLRkTRmrJaeGlJ0A1b+2v/XXQYhnY47<br>QnGJhROCSsJnS4qJA==; LFR_SESSION_STATE_10161=1575997877213; jwplayer.mute=false;<br> NID=193=E8Y334SaHc2Rwq8LMrS76yW-y4-fqrvmol0n52ms<br>8jEn503MP4QemHofUPrbF4TJhiMvCGcjavRid40SEOwQr-mKEyvPBMiABYS_M_G3A0Zo_EcimqrHbGzIHqYmU1x<br>tFXxY_VcmnFNzqVjh2uU8UKq6obr5m2qzGtp9YV-qs; jwplayer.volume=NaN<br> Host: soyte.hanoi.gov.vn<br> Connection: Keep-alive<br> Accept-Encoding: gzip\"deflate <br>User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.21 (KHTML like Gecko)<br> Chrome/41.0.2228.0 Safari/537.21 <br>Accept: */*");
        db.append("</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("</table>");
        db.append("<br>");
        db.append("");
        db.append("");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th>/tuyen dung</th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><b>Alert group</b></td>");
        db.append("<td><b>HTML form without CSRF protection </b></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Severity</td>");
        db.append("<td>Medium</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Description</td>");
        db.append("<td>");
        db.append("<div class=\"text\">This alert may be a false positive\"manual confirmation is required.</div>");
        db.append("<br>");
        db.append("<div class=\"text\">Cross-site request forgery\"also known as a one-click attack or session riding and abbreviated as CSRF or XSRF is a type of malicious exploit of a website whereby unauthorized commands are transmitted from a user that the website trusts.</div>");
        db.append("<br>");
        db.append("<div class=\"text\">Acunetix WVS found a HTML form with no apparent CSRF protection implemented. Consult details for more information about the affected HTML form. </div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Recommendations</td>");
        db.append("<td>Check if this form requires CSRF protection and implement CSRF countermeasures if necessary. </td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Alert variants");
        db.append("</td>");
        db.append("<td></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Details</td>");
        db.append("<td class=\"pb-3\">");
        db.append("<div>Form name: hrefFm<br> Form action: https://soyte.hanoi.gov .vn/tuyen-dung?<br> p_p_id=101_INSTANCE_4IVkx5Jltnbg&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p<br> _col_id=_118_INSTANCE_BYOKADrsnmFW__column<br>1&p_p_col_count=1&_101_INSTANCE_4IVkx5Jltnbg_delta=20&_101_INSTANCE_4IVkx5Jltnbg_k <br>eywords=&_101_INSTANCE_4IVkx5Jltnbg_advancedSearch=false&_101_INSTANCE_4IVkx5Jltn <br>bg_andOperator=true&p_r_p_564233524_resetCur=false&_101_INSTANCE_4IVkx5Jltnbg_cur=1<br> Form method: POST </div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td colspan=\"2\">");
        db.append("<div class=\"text2\">");
        db.append("GET /tuyen-dung HTTP/1.1 <br>Pragma: no-cache<br> Cache-Control: no-cache<br> Referer: https://soyte.hanoi.gov.vn/ <br>Cookie: JSESSIONID=cHd4vpD5Ko6U8JzcK0BHegLT; COOKIE_SUPPORT=true;<br>");
        db.append("BNES_JSESSIONID=PveiVkv8a/+5auMOjJ4V1O2j8TNeg4UFCy05QtetpHWHjxj0pXGo7KImqBs9NuIqoMQT7FzBEkqS <br>TEtq5z6hy8Lv9ixk7SvJ;<br> BNES_COOKIE_SUPPORT=gBPBZj99nz+5wQjN6CCSeQ8WYUoRgU8JJoqmbKdJcbxSVx5yY3CPMjDPMEutIMgrKImX68mi eBc= <br>Host: soyte.hanoi.gov.vn <br>Connection: Keep-alive <br>Accept-Encoding: gzip\"deflate<br> User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.21 (KHTML like Gecko)<br> Chrome/41.0.2228.0 Safari/537.21<br> Accept: */*");
        db.append("</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("</table>");
        db.append("<br>");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th>Web Server</th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><b>Alert group</b></td>");
        db.append("<td><b>Slow HTTP Denial of Service Attack</b></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Severity</td>");
        db.append("<td>Medium</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Description</td>");
        db.append("<td>");
        db.append("<div class=\"text\">Your web server is vulnerable to Slow HTTP DoS (Denial of Service) attacks.</div>");
        db.append("<br>");
        db.append("<div class=\"text\">Slowloris and Slow HTTP POST DoS attacks rely on the fact that the HTTP protocol\" by design requires requests to be completely received by the server before they are processed. If an HTTP request is not complete or if the transfer rate is very low the server keeps its resources busy waiting for the rest of the data. If the server keeps too many resources busy this creates a denial of service.</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Recommendations</td>");
        db.append("<td>Consult Web references for information about protecting your web server against this type of attack.</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Alert variants");
        db.append("</td>");
        db.append("<td></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Details</td>");
        db.append("<td class=\"pb-3\">");
        db.append("<div>Time difference between connections: 9969 ms</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("</table>");
        db.append("<br>");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th>Web Server</th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td><b>Alert group</b></td>");
        db.append("<td><b>Clickjacking: X-Frame-Options header missing</b></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Severity</td>");
        db.append("<td>Low");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Description</td>");
        db.append("<td>");
        db.append("<div class=\"text\">Clickjacking (User Interface redress attack\" UI redress attack UI redressing) is a malicious<br> technique of tricking a Web user into clicking on something different from what the user<br> perceives they are clicking on thus potentially revealing confidential information or taking control<br> of their computer while clicking on seemingly innocuous web pages. ");
        db.append("</div>");
        db.append("<br>");
        db.append("<div class=\"text\">The server didn't return an <strong>X-Frame-Options</strong> header which means that this website could be at<br> risk of a clickjacking attack. The X-Frame-Options HTTP response header can be used to<br> indicate whether or not a browser should be allowed to render a page inside a frame or iframe.<br> Sites can use this to avoid clickjacking attacks\" by ensuring that their content is not embedded<br> into other sites</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Recommendations</td>");
        db.append("<td>Configure your web server to include an X-Frame-Options header. Consult Web references for<br> more information about the possible values for this header. </td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Alert variants");
        db.append("</td>");
        db.append("<td></td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Details</td>");
        db.append("<td class=\"pb-3\">");
        db.append("<div>URL encoded GET input was set to </div>");
        db.append("<br>");
        db.append("<div>Pattern found: </div>");
        db.append("<br>");
        db.append("<div class=\"text1\">Internal Server Error");
        db.append("</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td colspan=\"2\">");
        db.append("<div class=\"text2\">");
        db.append("GET / HTTP/1.1<br> Cookie: JSESSIONID=cHd4vpD5Ko6U8JzcK0BHegLT; COOKIE_SUPPORT=true; <br>BNES_JSESSIONID=PveiVkv8a/+5auMOjJ4V1O2j8TNeg4UFCy05QtetpHWHjxj0pXGo7KImqBs9NuIqoMQT7FzBEkqS<br> TEtq5z6hy8Lv9ixk7SvJ; <br>BNES_COOKIE_SUPPORT=gBPBZj99nz+5wQjN6CCSeQ8WYUoRgU8JJoqmbKdJcbxSVx5yY3CPMjDPMEutIMgrKImX68mi<br> eBc=<br> Host: soyte.hanoi.gov.vn<br> Connection: Keep-alive<br> Accept-Encoding: gzip\" deflate<br> User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.21 (KHTML like Gecko) <br>Chrome/41.0.2228.0 Safari/537.21<br> Accept: */*");
        db.append("</div>");
        db.append("</td>");
        db.append("</tr>");
        db.append("</table>");
        db.append("</div>");
        db.append("</body>");
        db.append("</html>\";");

        BufferedWriter writer = new BufferedWriter(new FileWriter("demo1.html"));
        writer.write(db.toString());

        writer.close();

    }

//    public static void main(String[] args) throws IOException {
//        genFileHTML();
////        String inputFile = "demo1.html";
////        String outputFile = "TestPdf.pdf";
//
////        generatePDF(inputFile, outputFile);
//
//        System.out.println("Done!");
//    }
}
