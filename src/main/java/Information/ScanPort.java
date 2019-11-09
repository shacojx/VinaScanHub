/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Information;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Shaco JX
 */
public class ScanPort {
    public static ArrayList<Integer> ListPort = new ArrayList<>();
    public static String url = "";

    public void ScanPort(){
       ExecutorService executorService2 = Executors.newFixedThreadPool(1000);
        int port = 0;
        for (port = 1; port <= 10000; port++) {
            executorService2.execute(new thread(port));
        }
        executorService2.shutdown();
    }
    static class thread implements Runnable {
 
        private int port;
 
        public thread(int port) {
            this.port = port;
        }
 
        @Override
        public void run() {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(url, port), 1000);
                socket.close();
                ListPort.add(port);
                System.out.println("Port " + port + " is open");
 
            } catch (Exception ex) {
            }
        }
 
    }
}
