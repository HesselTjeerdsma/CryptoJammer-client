/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import commands.EncryptionDecription;
import commands.FileFinder;
import commands.ServerCommunication;
import commands.SymmetricKey;
import commands.UniqueID;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author admin
 */
public class DecryptionProcess extends javax.swing.JFrame {
    
    SecretKey decSymmetricKey;
    HashMap<byte[], Path> ivAndFile;
    
    /**
     * Creates new form DecryptionProcess
     */
    public DecryptionProcess(SecretKey decSymmetricKey, HashMap<byte[], Path> ivAndFile) {
        initComponents();
        labelDec.setVisible(false);
        this.decSymmetricKey = decSymmetricKey;
        this.ivAndFile = ivAndFile;
    }

    private DecryptionProcess() {
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
        decButton = new javax.swing.JButton();
        pbDec = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelDec = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        decButton.setBackground(new java.awt.Color(255, 51, 51));
        decButton.setText("Start Decryption");
        decButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decButtonActionPerformed(evt);
            }
        });

        pbDec.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Press the button below to start the decryption process.");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Make sure not to close the application during the decryption is not over.");

        labelDec.setBackground(new java.awt.Color(255, 51, 51));
        labelDec.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        labelDec.setText("DO NOT CLOSE THE APPLICATION!");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(pbDec, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(decButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(labelDec)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(decButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pbDec, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDec)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void decButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decButtonActionPerformed
        try {
            new DecryptionProcess.DecryptionAndBarUpdate(pbDec, labelDec, this).execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "There was a problem decrypting. Try restart the processes.","", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }//GEN-LAST:event_decButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DecryptionProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DecryptionProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DecryptionProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DecryptionProcess.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DecryptionProcess().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton decButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelDec;
    private javax.swing.JProgressBar pbDec;
    // End of variables declaration//GEN-END:variables
    
    private class DecryptionAndBarUpdate extends SwingWorker<Void, Integer> {

        JProgressBar pb;
        JLabel mesAttention;
        DecryptionProcess window;

        public DecryptionAndBarUpdate(JProgressBar pb, JLabel mesAttention, DecryptionProcess window) {
            this.pb = pb;
            this.mesAttention = mesAttention;
            this.window = window;
        }

        @Override
        protected void process(List<Integer> chunks) {
            int i = chunks.get(chunks.size()-1);
            pb.setValue(i);
            mesAttention.setVisible(true);
        }

        @Override
        protected Void doInBackground() throws Exception {
            
            publish(0);// Progress bar and Labels initialized

            int step = (int) Math.floor((100.0/ivAndFile.size())); // Bar increase per encrypted file
            int intToPublish = step;
            
            // Creating an EncryptionDecryption object
            EncryptionDecription encDec = new EncryptionDecription();
            
            // Decrypting every file
            Iterator<Map.Entry<byte[], Path>> itEnc = ivAndFile.entrySet().iterator();
            while (itEnc.hasNext()) {
                Map.Entry<byte[], Path> ivPath = (Map.Entry<byte[], Path>) itEnc.next();
                try {
                byte[] content = encDec.getBytesFile(ivPath.getValue());
                IvParameterSpec ivSpec = new IvParameterSpec(ivPath.getKey());
                byte[] decrypted = encDec.decryptBytesFile(decSymmetricKey, ivSpec, content);
                // Saving the encrypted file
                encDec.bytesToFile(decrypted, ivPath.getValue());
                } catch (Exception e) {
                    
                }
                
                if (intToPublish < 100) {
                    publish(intToPublish); // Progress bar updated
                    intToPublish = intToPublish + step;
                }
            }
            
            publish(100); 
            return null;
        }

        @Override
        protected void done() {
            try {
                get();
                labelDec.setText("Decryption Completed");
                Thread.sleep(2000);
                window.setVisible(false);
                JOptionPane.showMessageDialog(null, "Decryption Completed.", "", JOptionPane.WARNING_MESSAGE);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}