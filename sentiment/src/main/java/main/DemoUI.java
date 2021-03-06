/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static main.ResultPrinter.requestID;

/**
 *
 * @author Martin
 */
public class DemoUI extends javax.swing.JFrame {

    private int id = 1;
    private ResultPrinter printer;
    private RequestDispatcher dispatcher;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

    /**
     * Creates new form DemoUI
     */
    public DemoUI() {
        initComponents();
        printer = new ResultPrinter() {
            @Override
            public synchronized void printResult(int id, String key, Date since, Date until, double result) {
                txa_output.append(requestID + id + ": ");
                if (result == -1) {
                    txa_output.append(noTweets + key + "\n");
                } else {
                    txa_output.append(getResultString(id, key, since, until, result) + "\n");
                }
            }

            @Override
            public void printInitiated(int id, String key, Date since, Date until) {
                 txa_output.append(getInitatedString(id, key, since, until) + "\n");
            }
        };
        dispatcher = new RequestDispatcher(printer);
    }

    private Date parseDate(String str) {
        try {
            Date date = dateFormat.parse(str);
            return date;
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Cannot parse date: " + str, "Error:", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txf_key = new javax.swing.JTextField();
        txf_since = new javax.swing.JTextField();
        txf_until = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txa_output = new javax.swing.JTextArea();
        btn_analyze = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Analyse sentiment for: ");

        jLabel2.setText("Since: ");

        jLabel3.setText("Until: ");

        txf_key.setText("obama");

        txf_since.setText("2013-11-01");

        txf_until.setText("2013-11-30");

        txa_output.setColumns(20);
        txa_output.setRows(5);
        jScrollPane1.setViewportView(txa_output);

        btn_analyze.setText("Analyze!");
        btn_analyze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_analyzeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_analyze)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txf_key)
                        .addComponent(txf_since)
                        .addComponent(txf_until, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)))
                .addGap(0, 459, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txf_key, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txf_since, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txf_until, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_analyze)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_analyzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_analyzeActionPerformed
        Date since = parseDate(txf_since.getText());
        Date until = parseDate(txf_until.getText());
        if (since == null || until == null) {
            return;
        }
        String key = txf_key.getText();
        printer.printInitiated(id, key, since, until);
        dispatcher.dispatch(id, key, since, until);
        ++id;
    }//GEN-LAST:event_btn_analyzeActionPerformed

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
            java.util.logging.Logger.getLogger(DemoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DemoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DemoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DemoUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DemoUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_analyze;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txa_output;
    private javax.swing.JTextField txf_key;
    private javax.swing.JTextField txf_since;
    private javax.swing.JTextField txf_until;
    // End of variables declaration//GEN-END:variables
}
