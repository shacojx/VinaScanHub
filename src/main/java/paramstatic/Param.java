/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paramstatic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Shaco JX
 */
public class Param {
    public static Set<String> listEmail = new HashSet<>();
    public static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(1000);
    public static HashSet<String> listAdmin = new HashSet<>();
    public static String COOKIE = "";
    public static String USERAL= "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36";
}
