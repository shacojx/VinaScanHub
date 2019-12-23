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

    public void genFileHTML(String url) throws IOException {
        String vuln = "";
        String vulnx = "";
        String pay = "";
        String sig = "";

        for (String x : function.Scan.list_vuln) {
            if (x.split(": ")[1].contains("&")) {
                x.split(": ")[1].split("&");
                vulnx = x.split(": ")[1].split("&")[0] + "&amp;" + x.split(": ")[1].split("&")[1];
            } else {
                vulnx = x.split(": ")[1];
            }
            pay = x.split(": ")[2];
            if(pay.contains("<") && pay.contains(">")){
                pay = pay.replaceAll("<", "(tagOpen)");
                pay = pay.replaceAll(">", "(tagClose)");
                
            }
            sig = x.split(": ")[3];
            if(sig.contains("<") && sig.contains(">")){
                sig = sig.replaceAll("<", "(tagOpen)");
                sig = sig.replaceAll(">", "(tagClose)");
            }

            vuln = vuln + "<tr>\n"
                    + "<td style=\"padding-right: 100px; border: 1px solid black;\"><b>" + x.split(": ")[0] + "</b></td>\n"
                    + "<td style=\"padding-right: 100px; border: 1px solid black;\"><b>" + vulnx + "</b></td>\n"
                    + "<td style=\"padding-right: 100px; border: 1px solid black;\"><b>" + pay + "</b></td>\n"
                    + "<td style=\"padding-right: 100px; border: 1px solid black;\"><b>" + sig + "</b></td>\n"
                    + " </tr>\n";
        }

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
        db.append("background-image: url(https://www.upsieutoc.com/images/2019/12/20/Hinh1.jpg);");
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
        db.append("<img style=\"height: 100px;\" src=\"https://www.upsieutoc.com/images/2019/12/20/EOG-Logo.jpg\">");
        db.append("</div>");
        db.append("<br>");
        db.append("<div class=\"text-center text-muted\" id=\"Items\">");
        db.append("<h1>Vina Scan Hub <br>Report Scan</h1>");
        db.append("<p class=\"pt-5\">VSH website audit</p>");
        db.append("<p class=\"pt-5\">===================</p>");
        db.append("</div>");
        db.append("<div class=\"pt-5 text-center\">");
        db.append("<div>");
        db.append("<h6>WEB APPLICATION SECURITY</h6>");
        db.append("<hr>");
        db.append("<p class=\"title-bottom-web\">Generated by VSH Reporter</p>");
        db.append("</div>");
        db.append("</div>");
        db.append("</div>");
        db.append("</div>");
        db.append("<br>");
        db.append("<br>");
        db.append("<br>");
        db.append("");
        db.append("<div class=\"container\">");
        db.append("<div class=\"text-center\" id=\"Scan\"><h4><strong>Scan of " + url + "</strong></h4></div>");
        db.append("<div class=\"pt-3\"><h5><strong>Scan Details</strong></h5></div>");
        db.append("</div>");
        db.append("<div class=\"container pt-1\">");
        db.append("<table style=\"width:100%\">");
        db.append("<tr>");
        db.append("<th colspan=\"2\">Scan information </th>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<tr>");
        db.append("<td>Start url</td>");
        db.append("<td>" + url + "</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("<td>Host</td>");
        db.append("<td>" + url + "</td>");
        db.append("</tr>");
        db.append("<tr>");
        db.append("</table>");
        db.append("</div>");
        db.append("<br>");
        db.append("<div class=\"pt-3\"><h5><strong>Alerts distribution</strong></h5></div>");
        db.append("<hr>");
        db.append("</div>");

        db.append("    <div style=\"\n"
                + "    text-align: center;\n"
                + "    margin-left: 1%;\">\n"
                + "        <table style=\" border: 1px solid black;\">\n"
                + "            <tr>\n"
                + "            <th style=\"padding-right: 40px; border: 1px solid black;\">Vulnerability</th>\n"
                + "            <th style=\"padding-right: 40px; border: 1px solid black;\">Link</th>\n"
                + "            <th style=\"padding-right: 40px; border: 1px solid black;\">PayLoad</th>\n"
                + "            <th style=\"padding-right: 40px; border: 1px solid black;\">Signature</th>\n"
                + "            </tr>\n"
                + vuln
                + "        </table>\n"
                + "    </div>\n");

        db.append("</body>");
        db.append("</html>\";");

        BufferedWriter writer = new BufferedWriter(new FileWriter("reportscan.html"));
        writer.write(db.toString());

        writer.close();

    }

}
