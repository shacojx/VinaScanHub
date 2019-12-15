/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.FuzzEntity;
import Information.Info;
import Information.ScanPort;
import Report.ReportPDF;
import Scan.Scan_WeakPassword;
import function.Scan;
import function.SpiderWeb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import paramstatic.Param;
import static paramstatic.Param.EXECUTOR_SERVICE;

/**
 *
 * @author toanvv1
 */
public class VSH extends javax.swing.JFrame {

    /**
     * Creates new form VSH
     */
    Scan s = new Scan();
    Info in = new Info();
    ScanPort scanport = new ScanPort();
    SpiderWeb spider = new SpiderWeb();
    String url = "";
    public static int dept = 0;
    public static int numberOfThreads = 0;
    public static ArrayList<FuzzEntity> fu = new ArrayList<>();

    public VSH() {
        initComponents();
        this.setTitle("Vina Scan Hub");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbUrl = new javax.swing.JLabel();
        tfUrl = new javax.swing.JTextField();
        btnScan = new javax.swing.JButton();
        lbDept = new javax.swing.JLabel();
        Clear = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        Stop = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        Report = new javax.swing.JButton();
        tfThread = new javax.swing.JTextField();
        lbThread = new javax.swing.JLabel();
        tfDept = new javax.swing.JTextField();
        Port = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Info = new javax.swing.JTextArea();
        Loading = new javax.swing.JLabel();
        Fuzz = new javax.swing.JButton();
        Addpay = new javax.swing.JButton();
        About = new javax.swing.JButton();
        Action = new javax.swing.JLabel();
        lbAuthen = new javax.swing.JLabel();
        cbAuthen = new javax.swing.JCheckBox();
        lbUser = new javax.swing.JLabel();
        tfUser = new javax.swing.JTextField();
        lbPassword = new javax.swing.JLabel();
        tfPassword = new javax.swing.JTextField();
        Tabmenu = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        LOG_CONSOLE = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        VulnResult = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        HistoryResult = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        LinkResult = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        PortResult = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        FuzzResult = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        OtherResult = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel1.setFont(new java.awt.Font("Wide Latin", 3, 14)); // NOI18N
        jLabel1.setText("Vina Scan Hub");

        lbUrl.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbUrl.setText(" URL");

        tfUrl.setText("http://testphp.vulnweb.com/");

        btnScan.setBackground(new java.awt.Color(102, 255, 102));
        btnScan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnScan.setText("Scan");
        btnScan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanActionPerformed(evt);
            }
        });

        lbDept.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDept.setText(" Dept");

        Clear.setBackground(new java.awt.Color(51, 51, 255));
        Clear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Clear.setForeground(new java.awt.Color(255, 255, 255));
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });

        Save.setBackground(new java.awt.Color(51, 51, 255));
        Save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Save.setForeground(new java.awt.Color(255, 255, 255));
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText(" Code by Shaco JX, Trung Baor Lax. We are Eyes Of God Team");

        Stop.setBackground(new java.awt.Color(204, 0, 0));
        Stop.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Stop.setText("STOP");
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(255, 0, 0));
        Delete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Delete.setText("Delete");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EOG Logo.jpg"))); // NOI18N

        Report.setBackground(new java.awt.Color(51, 51, 255));
        Report.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Report.setForeground(new java.awt.Color(255, 255, 255));
        Report.setText("Report");
        Report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportActionPerformed(evt);
            }
        });

        tfThread.setText("50");

        lbThread.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbThread.setText("Threads:");

        tfDept.setText("10");

        Port.setBackground(new java.awt.Color(204, 204, 0));
        Port.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Port.setText("Port");
        Port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PortActionPerformed(evt);
            }
        });

        Info.setEditable(false);
        Info.setColumns(20);
        Info.setLineWrap(true);
        Info.setRows(5);
        Info.setText("[ Information website ]\n");
        Info.setWrapStyleWord(true);
        jScrollPane4.setViewportView(Info);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Loading.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Loading.setText("Loading |===================>|");

        Fuzz.setBackground(new java.awt.Color(204, 204, 0));
        Fuzz.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Fuzz.setText("Fuzz");
        Fuzz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuzzActionPerformed(evt);
            }
        });

        Addpay.setBackground(new java.awt.Color(51, 51, 255));
        Addpay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Addpay.setForeground(new java.awt.Color(255, 255, 255));
        Addpay.setText("Add Payload");
        Addpay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddpayActionPerformed(evt);
            }
        });

        About.setBackground(new java.awt.Color(0, 0, 0));
        About.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        About.setForeground(new java.awt.Color(255, 255, 255));
        About.setText("About our team");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });

        Action.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Action.setText("Do Nothing");

        lbAuthen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbAuthen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAuthen.setText("Authen ");
        lbAuthen.setFocusable(false);
        lbAuthen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cbAuthen.setText("Yes / No");
        cbAuthen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbAuthenActionPerformed(evt);
            }
        });

        lbUser.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbUser.setText("User :");
        lbUser.setEnabled(false);

        tfUser.setEnabled(false);

        lbPassword.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbPassword.setText("Password :");
        lbPassword.setEnabled(false);

        tfPassword.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbDept, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfDept, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(70, 70, 70)
                                        .addComponent(lbThread)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfThread, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPassword)
                            .addComponent(lbUser))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAuthen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAuthen)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Report, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnScan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Port, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(About, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Stop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Fuzz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Addpay, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Loading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Action, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(Clear)
                                            .addComponent(Report)))
                                    .addComponent(Addpay, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Save)
                                    .addComponent(Delete))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Stop)
                                    .addComponent(btnScan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Port)
                                    .addComponent(Fuzz))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(About)
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Loading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Action))
                                .addGap(29, 29, 29))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tfUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbUrl, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                                    .addComponent(lbDept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfDept)
                                    .addComponent(lbThread, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfThread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbAuthen)
                                    .addComponent(cbAuthen))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbUser)
                                    .addComponent(tfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lbPassword)
                                    .addComponent(tfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        LOG_CONSOLE.setColumns(20);
        LOG_CONSOLE.setLineWrap(true);
        LOG_CONSOLE.setRows(5);
        LOG_CONSOLE.setWrapStyleWord(true);
        LOG_CONSOLE.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LOG_CONSOLEMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(LOG_CONSOLE);

        Tabmenu.addTab("Log", jScrollPane1);

        VulnResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vulnerability", "Link", "Payload", "Signature", "Level"
            }
        ));
        jScrollPane3.setViewportView(VulnResult);

        Tabmenu.addTab("Vulnerability", jScrollPane3);

        HistoryResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "URL", "Total Vuln"
            }
        ));
        jScrollPane2.setViewportView(HistoryResult);

        Tabmenu.addTab("History", jScrollPane2);

        LinkResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "URL", "Status"
            }
        ));
        jScrollPane5.setViewportView(LinkResult);

        Tabmenu.addTab("Link", jScrollPane5);

        PortResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Port", "Status"
            }
        ));
        jScrollPane6.setViewportView(PortResult);

        Tabmenu.addTab("Port", jScrollPane6);

        FuzzResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vuln"
            }
        ));
        FuzzResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FuzzResultMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(FuzzResult);

        Tabmenu.addTab("Fuzz", jScrollPane7);

        OtherResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Action"
            }
        ));
        jScrollPane8.setViewportView(OtherResult);

        Tabmenu.addTab("Other", jScrollPane8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1067, Short.MAX_VALUE)
            .addComponent(Tabmenu, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tabmenu, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void LOG_CONSOLEMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LOG_CONSOLEMouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        String s = source.getModel().getValueAt(row, column) + "";

        JOptionPane.showMessageDialog(null, s);
    }//GEN-LAST:event_LOG_CONSOLEMouseClicked

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        // TODO add your handling code here:
        About ab = new About(this, false);
        ab.setVisible(true);
    }//GEN-LAST:event_AboutActionPerformed

    private void AddpayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddpayActionPerformed
        // TODO add your handling code here:
        AddPay ap = new AddPay(this, false);
        ap.setVisible(true);
    }//GEN-LAST:event_AddpayActionPerformed

    private void FuzzActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuzzActionPerformed
        // TODO add your handling code here:
        SpiderWeb sp = new SpiderWeb();
        DefaultTableModel dtm = (DefaultTableModel) View.VSH.FuzzResult.getModel();
//        for(String url : sp.links){
//            dtm.addRow(new Object[]{url,"SQL injection"});
//        }
        dtm.addRow(new Object[]{"SQL injection"});
        dtm.addRow(new Object[]{"Blind SQL injection"});
        dtm.addRow(new Object[]{"XSS"});
        dtm.addRow(new Object[]{"LFI"});
        dtm.addRow(new Object[]{"OS CMD injection"});
        dtm.addRow(new Object[]{"Code injection"});
        dtm.addRow(new Object[]{"XML Xpatch injection"});


    }//GEN-LAST:event_FuzzActionPerformed

    private void PortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PortActionPerformed
        // TODO add your handling code here:
        ScanPort scanport = new ScanPort();
        scanport.ScanPort(tfUrl.getText());

    }//GEN-LAST:event_PortActionPerformed

    private void ReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportActionPerformed

    }//GEN-LAST:event_ReportActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        Scan s = new Scan();
        s.list_vuln = new ArrayList<>();
        s.checkURLGET = new HashSet<>();
        s.checkURLPOST = new HashSet<>();

        ScanPort sp = new ScanPort();
        sp.ListPort = new ArrayList<>();

        SpiderWeb sw = new SpiderWeb();
        sw.links = new HashSet<>();

        Param p = new Param();
        p.listEmail = new HashSet<>();
        p.listAdmin = new HashSet<>();

        Scan_WeakPassword wp = new Scan_WeakPassword();
        wp.setCookieManager(null);
        wp.setUrlS("");
        wp.setCheckLogin(false);

        Info.setText("[ Information website ]\n");

        LOG_CONSOLE.setText("");

        DefaultTableModel vulnresult = (DefaultTableModel) View.VSH.VulnResult.getModel();
        vulnresult.getDataVector().removeAllElements();

        DefaultTableModel historyresult = (DefaultTableModel) View.VSH.HistoryResult.getModel();
        historyresult.getDataVector().removeAllElements();

        DefaultTableModel linkresult = (DefaultTableModel) View.VSH.LinkResult.getModel();
        linkresult.getDataVector().removeAllElements();

        DefaultTableModel portresult = (DefaultTableModel) View.VSH.PortResult.getModel();
        portresult.getDataVector().removeAllElements();

        DefaultTableModel fuzzresult = (DefaultTableModel) View.VSH.FuzzResult.getModel();
        fuzzresult.getDataVector().removeAllElements();

        DefaultTableModel otherresult = (DefaultTableModel) View.VSH.OtherResult.getModel();
        otherresult.getDataVector().removeAllElements();
    }//GEN-LAST:event_DeleteActionPerformed

    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(VSH.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            EXECUTOR_SERVICE.shutdownNow();
//            try {
//                // TODO add your handling code here:
//                EXECUTOR_SERVICE.awaitTermination(0, TimeUnit.MICROSECONDS);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            Scan s = new Scan();
//            s.service.shutdownNow();
//            try {
//                // TODO add your handling code here:
//                s.service.awaitTermination(0, TimeUnit.MICROSECONDS);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        LOG_CONSOLE.append("Scan Cancel!!!" + "\n");
    }//GEN-LAST:event_StopActionPerformed

    private void SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        // TODO add your handling code here:
        tfUrl.setText("");
        tfDept.setText("");
        tfThread.setText("");
        tfUser.setText("");
        tfPassword.setText("");
    }//GEN-LAST:event_ClearActionPerformed

    private void btnScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanActionPerformed
        try {
            setAllViewEnable(false);
            btnScan.setEnabled(false);

            String url = tfUrl.getText();
            if (!url.matches("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)")) {
                JOptionPane.showMessageDialog(null, "Check url11");
                return;
            }
            String deptText = tfDept.getText();
            if (!deptText.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Check dept!!");
                return;
            }
            dept = Integer.parseInt(deptText);
            String threadText = tfThread.getText();
            if (!threadText.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Check number of threads!!");
                return;
            }
            numberOfThreads = Integer.parseInt(threadText);
            Scan s = new Scan();
            s.Scan(url);
        } catch (Exception ex) {
            setAllViewEnable(true);
            btnScan.setEnabled(true);
            setViewAuthenEnable(cbAuthen.isSelected());
            System.out.println("ERROR Button Scan!!!");
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnScanActionPerformed

    private void cbAuthenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAuthenActionPerformed
        // TODO add your handling code here:
        setViewAuthenEnable(cbAuthen.isSelected());
    }//GEN-LAST:event_cbAuthenActionPerformed

    private void FuzzResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuzzResultMouseClicked
        // TODO add your handling code here:
        JTable table =(JTable) evt.getSource();
        DefaultTableModel dtmz = (DefaultTableModel) View.FuzzOut.FuzzOutResult.getModel();
        if (table.getSelectedRow() != -1 && evt.getClickCount() == 1) {
            FuzzOut fuzzout = new FuzzOut(this, true);
            fuzzout.setVisible(true);
            for (FuzzEntity fe : fu) {
                dtmz.addRow(new Object[]{fe.getLink(), fe.getPayload(), fe.getResponse()});
            }
        }


    }//GEN-LAST:event_FuzzResultMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VSH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VSH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VSH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VSH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VSH().setVisible(true);
            }
        });
    }

    public static void setAllViewEnable(boolean en) {
        lbAuthen.setEnabled(en);
        lbDept.setEnabled(en);
        lbPassword.setEnabled(en);
        lbThread.setEnabled(en);
        lbUrl.setEnabled(en);
        lbUser.setEnabled(en);
        tfDept.setEnabled(en);
        tfPassword.setEnabled(en);
        tfThread.setEnabled(en);
        tfUrl.setEnabled(en);
        tfUser.setEnabled(en);
        cbAuthen.setEnabled(en);
    }

    public static void setViewAuthenEnable(boolean en) {
        if (en) {
            lbUser.setEnabled(true);
            lbPassword.setEnabled(true);
            tfUser.setEnabled(true);
            tfPassword.setEnabled(true);
        } else {
            lbUser.setEnabled(false);
            lbPassword.setEnabled(false);
            tfUser.setEnabled(false);
            tfPassword.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton About;
    public static javax.swing.JLabel Action;
    private javax.swing.JButton Addpay;
    private javax.swing.JButton Clear;
    private javax.swing.JButton Delete;
    private javax.swing.JButton Fuzz;
    public static javax.swing.JTable FuzzResult;
    public static javax.swing.JTable HistoryResult;
    public static javax.swing.JTextArea Info;
    public static javax.swing.JTextArea LOG_CONSOLE;
    public static javax.swing.JTable LinkResult;
    public static javax.swing.JLabel Loading;
    public static javax.swing.JTable OtherResult;
    private javax.swing.JButton Port;
    public static javax.swing.JTable PortResult;
    private javax.swing.JButton Report;
    private javax.swing.JButton Save;
    private javax.swing.JButton Stop;
    private javax.swing.JTabbedPane Tabmenu;
    public static javax.swing.JTable VulnResult;
    public static javax.swing.JButton btnScan;
    public static javax.swing.JCheckBox cbAuthen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private static javax.swing.JLabel lbAuthen;
    private static javax.swing.JLabel lbDept;
    public static javax.swing.JLabel lbPassword;
    private static javax.swing.JLabel lbThread;
    private static javax.swing.JLabel lbUrl;
    public static javax.swing.JLabel lbUser;
    private static javax.swing.JTextField tfDept;
    public static javax.swing.JTextField tfPassword;
    public static javax.swing.JTextField tfThread;
    private static javax.swing.JTextField tfUrl;
    public static javax.swing.JTextField tfUser;
    // End of variables declaration//GEN-END:variables
}
