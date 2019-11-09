/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
 * @author phulhd
 */
public class ReadFileIO {
    public static String readFile(String filename) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {
            is = new FileInputStream(filename);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();            
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }  
            String fileAsString = sb.toString();
//            System.out.println("Contents : " + fileAsString);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CheckSiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CheckSiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(CheckSiteAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
    }
}