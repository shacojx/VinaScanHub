/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Information;

import View.VSH;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Shaco JX
 */
public class ScanPort {

    public static ArrayList<Integer> ListPort = new ArrayList<>();
//    public static String url = "";

    public void ScanPort(String url) {
        try {
            // Fetch IP address by getByName() 
            String ipx = new URL(url).getHost();

            VSH.LOG_CONSOLE.append("Port Scan Start" + "\n");
            VSH.LOG_CONSOLE.append("URL: " + ipx + "\n");
            VSH.Action.setText("Scanning Port!!!");
            VSH.Loading.setText("");
            VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
            ExecutorService executorService2 = Executors.newFixedThreadPool(3000);
            int port = 0;
            for (port = 1; port <= 65535; port++) {
                executorService2.execute(new thread(port, ipx));
            }
            //check done
            new Thread(() -> {
//                VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                executorService2.shutdown();
                // Wait until all threads are finish
                while (!executorService2.isTerminated()) {
                    try {
                        // Running ...
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ScanPort.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                System.out.println("Finished Scan Port!!!");
                VSH.LOG_CONSOLE.append("Scan Port Done!!!" + "\n");
                VSH.Loading.setText("Loading |===================>|");
                VSH.Action.setText("Do Nothing");
                VSH.Port.setEnabled(true);
            }).start();
        } catch (Exception e) {
//            System.out.println("ERROR port!!!");
//            e.printStackTrace();
        }
    }

    static class thread implements Runnable {

        private int port;
        private String url;

        public thread(int port, String url) {
            this.port = port;
            this.url = url;
        }

        @Override
        public void run() {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(url, port), 1000);
                socket.close();
                ListPort.add(port);
                VSH.LOG_CONSOLE.append("Port " + port + " is open" + "\n");
                DefaultTableModel dtm = (DefaultTableModel) View.VSH.PortResult.getModel();
                dtm.addRow(new Object[]{port, "Open"});
                VSH.LOG_CONSOLE.setCaretPosition(VSH.LOG_CONSOLE.getDocument().getLength());
                System.out.println("Port " + port + " is open");
            } catch (Exception ex) {
//                System.out.println("ERROR thread scan port!!!!");
//                ex.printStackTrace();
                System.out.println("Port " + port + " is'n open!!!!");
            }
        }

    }
}
