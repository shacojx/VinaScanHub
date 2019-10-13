/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import com.lowagie.text.DocumentException;
import java.io.*;
import org.xhtmlrenderer.pdf.ITextRenderer;
/**
 *
 * @author Shaco JX
 */
public class ReportPDF {
    
    public static void generatePDF(String inputHtmlPath, String outputPdfPath) {
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
        String str = "<body>"
                + "<h1 style=\"color : red;\">Test</h1>"
                + "<h1>Demo</h1>"
                + "</body>";
        BufferedWriter writer = new BufferedWriter(new FileWriter("demo1.html"));
        writer.write(str);

        writer.close();
        
    }

    public static void main(String[] args) throws IOException {
        genFileHTML();
        String inputFile = "demo1.html";
        String outputFile = "TestPdf.pdf";

        generatePDF(inputFile, outputFile);

        System.out.println("Done!");
    }
    
}
