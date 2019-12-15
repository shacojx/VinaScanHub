/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import PaySig.psCMDInjection;
import PaySig.psCodeInjection;
import PaySig.psLFI;
import PaySig.psSQLi;
import PaySig.psXMLXpatchi;
import PaySig.psXSS;
import static View.VSH.FuzzResult;
import function.SpiderWeb;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Shaco JX
 */
public class FuzzOut extends javax.swing.JDialog {

    /**
     * Creates new form FuzzOut
     */
    FuzzOut2 fuzzout2;
    public FuzzOut(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
       fuzzout2 = new FuzzOut2(parent, true);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        FuzzOutResult = new javax.swing.JTable();
        VulnName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Fuzz Result");

        FuzzOutResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Link"
            }
        ));
        FuzzOutResult.setVerifyInputWhenFocusTarget(false);
        FuzzOutResult.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FuzzOutResultMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(FuzzOutResult);

        VulnName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(VulnName, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VulnName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FuzzOutResultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FuzzOutResultMouseClicked
        // TODO add your handling code here:
        
        
        DefaultTableModel dtmz = (DefaultTableModel) View.FuzzOut2.PayVuln.getModel();
        int index = FuzzOutResult.getSelectedRow();
        TableModel model = FuzzOutResult.getModel();
        String vuln = this.VulnName.getText();
        String link_pay = (String) model.getValueAt(index, 0);
        SpiderWeb sp = new SpiderWeb();
        fuzzout2.payvuln.setText(vuln);
        fuzzout2.link_payload.setText(link_pay);
        
        if(vuln.equalsIgnoreCase("XSS")){
            psXSS pxss = new psXSS();
            for(String s : pxss.getArrPayXSS()){
                dtmz.addRow(new Object[]{s});
            }
        }else if(vuln.equalsIgnoreCase("Blind SQL injection")){
            psXSS pxss = new psXSS();
            for(String s : pxss.getArrPayXSS()){
                dtmz.addRow(new Object[]{s});
            }
        }else if(vuln.equalsIgnoreCase("OS CMD injection")){
            psCMDInjection pcmdi = new psCMDInjection();
            for(String s : pcmdi.getArrPayCMDi()){
                dtmz.addRow(new Object[]{s});
            }
        }else if(vuln.equalsIgnoreCase("Code injection")){
            psCodeInjection pci = new psCodeInjection();
            for(String s : pci.getArrPayCI()){
                dtmz.addRow(new Object[]{s});
            }
        }else if(vuln.equalsIgnoreCase("LFI")){
            psLFI plfi = new psLFI();
            for(String s : plfi.getArrPayLFI()){
                dtmz.addRow(new Object[]{s});
            }
        }else if(vuln.equalsIgnoreCase("SQL injection")){
            psSQLi psqli = new psSQLi();
            for(String s : psqli.getArrPaySQLin()){
                dtmz.addRow(new Object[]{s});
            }
        }else if(vuln.equalsIgnoreCase("XML Xpatch injection")){
            psXMLXpatchi pxml = new psXMLXpatchi();
            for(String s : pxml.getArrPayXMLXPathin()){
                dtmz.addRow(new Object[]{s});
            }
        }
        
        
        fuzzout2.setVisible(true);
    }//GEN-LAST:event_FuzzOutResultMouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(FuzzOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(FuzzOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(FuzzOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(FuzzOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                FuzzOut dialog = new FuzzOut(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable FuzzOutResult;
    public static javax.swing.JLabel VulnName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
