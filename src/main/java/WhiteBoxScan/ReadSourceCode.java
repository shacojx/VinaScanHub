/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhiteBoxScan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class ReadSourceCode {

    public static File folder = new File("C:\\Users\\toanvv1\\Downloads\\DVWA-master\\dvwa");
    static String temp = "";

    public static ArrayList<String> listFile = new ArrayList<>();

    public static void listFilesForFolder(final File folder) {

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
//                 System.out.println("Reading files under the folder "+folder.getAbsolutePath());
                listFilesForFolder(fileEntry);
            } else {
                if (fileEntry.isFile()) {
                    temp = fileEntry.getName();
                    if ((temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("txt")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("php")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("jsp")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("java")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("aspx")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("config")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("html")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("js")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("css")
                            || (temp.substring(temp.lastIndexOf('.') + 1, temp.length()).toLowerCase()).equals("form")) {
                        System.out.println("File= " + folder.getAbsolutePath() + "\\" + fileEntry.getName());
                        listFile.add(folder.getAbsolutePath() + "\\" + fileEntry.getName());

                    }
                }

            }
        }

    }

}
