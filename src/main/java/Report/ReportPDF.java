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

    public void genFileHTML(ArrayList<String> list) throws IOException {
        String vuln = "";
        String vulnx = "";
        for (String x : list) {
            if(x.split(": ")[1].contains("&")){
                x.split(": ")[1].split("&");
                vulnx = x.split(": ")[1].split("&")[0]+"&amp;"+x.split(": ")[1].split("&")[1];
            }else{
                vulnx = x.split(": ")[1];
            }
            
            vuln = vuln + "<tr>\n"
                    + "<td style=\"padding-right: 100px;\"><b>" + x.split(": ")[0] + "</b></td>\n"
                    + "<td><b>" + vulnx + "</b></td>\n"
                    + " </tr>\n";
        }
        String str = "<body>\n"
                + "    <div style=\"\n"
                + "    text-align: center;\n"
                + "    background-color: green;\n"
                + "    color: red;\">\n"
                + "        <h1>Vina Scan Hub</h1>\n"
                + "        <h3>Report Scan Result</h3>\n"
                + "    </div>\n"
                + "    <div style=\"\n"
                + "    text-align: center;\n"
                + "    margin-left: 40%;\">\n"
                + "        <table>\n"
                + "            <tr>\n"
                + "            <th style=\"padding-right: 40px;\">Vulnerability</th>\n"
                + "            <th>Link</th>\n"
                + "            </tr>\n"
                + vuln
                + "        </table>\n"
                + "    </div>\n"
                + "</body>";
        BufferedWriter writer = new BufferedWriter(new FileWriter("demo1.html"));
        writer.write(str);

        writer.close();

    }

//    public static void main(String[] args) throws IOException {
////        genFileHTML();
//        String inputFile = "demo1.html";
//        String outputFile = "TestPdf.pdf";
//
//        generatePDF(inputFile, outputFile);
//
//        System.out.println("Done!");
//    }

}
