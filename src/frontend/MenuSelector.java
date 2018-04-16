/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.FileManager;
import backend.FileTypes;
import backend.Inventario;
import backend.MemoryFile;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rubén Escalante
 */
public class MenuSelector extends javax.swing.JFrame {

    /**
     * Creates new form MenuSelector
     */
    
    ArrayList<Inventario> menus;
    ArrayList<Inventario> seleccionados;
    public MenuSelector() {
        initComponents();
        menus = new ArrayList<>();
        seleccionados = new ArrayList<>();
        
        startMenu(existantList, FileTypes.MENUS);
    }
    
    public void startMenu(java.awt.List listField, FileTypes file) {
        menus = FileManager.readItemsInFile(file);
        Collections.sort(menus);
        String name;
        for (Inventario menu : menus) {
            name = menu.getName();
            
            // Añade cada producto a la lista en pantalla correspondiente
            listField.add(name);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        list1 = new java.awt.List();
        Head = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        Body = new javax.swing.JPanel();
        selectedList = new java.awt.List();
        removeBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        existantList = new java.awt.List();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        acceptBtn = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Seleccion de menús");
        setMinimumSize(new java.awt.Dimension(485, 516));
        setPreferredSize(new java.awt.Dimension(485, 516));
        setResizable(false);
        setSize(new java.awt.Dimension(485, 516));

        Head.setBackground(new java.awt.Color(51, 51, 51));
        Head.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Head.setPreferredSize(new java.awt.Dimension(1269, 50));

        logo.setFont(new java.awt.Font("Quarca Norm Light", 0, 24)); // NOI18N
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/DayNS32.png"))); // NOI18N
        logo.setText("NullSoft");
        logo.setForeground(new java.awt.Color(187, 187, 187));

        javax.swing.GroupLayout HeadLayout = new javax.swing.GroupLayout(Head);
        Head.setLayout(HeadLayout);
        HeadLayout.setHorizontalGroup(
            HeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, HeadLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        HeadLayout.setVerticalGroup(
            HeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(Head, java.awt.BorderLayout.PAGE_START);

        Body.setBackground(new java.awt.Color(51, 51, 51));
        Body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectedList.setBackground(new java.awt.Color(204, 204, 204));
        selectedList.setFont(new java.awt.Font("Quarca Cond Light", 0, 24)); // NOI18N
        selectedList.setForeground(new java.awt.Color(102, 102, 102));
        selectedList.setMultipleMode(true);
        selectedList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedListActionPerformed(evt);
            }
        });
        Body.add(selectedList, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 200, 300));

        removeBtn.setFont(new java.awt.Font("Quarca Ext Light", 1, 24)); // NOI18N
        removeBtn.setText("<");
        removeBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Body.add(removeBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 40, 40));

        addBtn.setFont(new java.awt.Font("Quarca Ext Light", 1, 24)); // NOI18N
        addBtn.setText(">");
        addBtn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });
        Body.add(addBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 40, 40));

        existantList.setBackground(new java.awt.Color(204, 204, 204));
        existantList.setFont(new java.awt.Font("Quarca Cond Light", 0, 24)); // NOI18N
        existantList.setForeground(new java.awt.Color(102, 102, 102));
        existantList.setMultipleMode(true);
        existantList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existantListActionPerformed(evt);
            }
        });
        Body.add(existantList, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 200, 300));

        jLabel1.setFont(new java.awt.Font("Quarca Norm Thin", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccionados");
        Body.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 200, 50));

        jLabel2.setFont(new java.awt.Font("Quarca Norm Thin", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Existentes");
        Body.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 200, 50));

        acceptBtn.setBackground(new java.awt.Color(0, 204, 0));
        acceptBtn.setFont(new java.awt.Font("Quarca Norm Regular", 0, 24)); // NOI18N
        acceptBtn.setLabel("Aceptar");
        acceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBtnActionPerformed(evt);
            }
        });
        Body.add(acceptBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 460, 50));

        getContentPane().add(Body, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void existantListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existantListActionPerformed
        // TODO add your handling code here:
        String menu = existantList.getSelectedItem();
        Inventario selected = null;
        
        for (Inventario element : menus) {
            if (menu.compareTo(element.getName()) == 0) {
                selected = element;
                break;
            }
        }
        
        if (selected != null) {
            seleccionados.add(selected);
            selectedList.add(selected.getName());
        }
    }//GEN-LAST:event_existantListActionPerformed

    private void selectedListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedListActionPerformed
        // TODO add your handling code here:
        String[] menu = selectedList.getSelectedItems();
        int index;
        
        for (int i = 0; i < menu.length; i++) {
            index = 0;
            
            for (Inventario element : seleccionados) {
                if (menu[i].compareTo(element.getName()) == 0) {
                    break;
                }
                index++;
            }

            if (index < seleccionados.size()) {
                seleccionados.remove(index);
                selectedList.remove(menu[i]);
            }
        }
    }//GEN-LAST:event_selectedListActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        String[] menu = existantList.getSelectedItems();
        Inventario selected;
        
        for (int i = 0; i < menu.length; i++) {
            selected = null;
            
            for (Inventario element : menus) {
                if (menu[i].compareTo(element.getName()) == 0) {
                    selected = element;
                    break;
                }
            }
            
            if (selected != null) {
                seleccionados.add(selected);
                selectedList.add(selected.getName());
            }
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void acceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptBtnActionPerformed
        // TODO add your handling code here:
        MemoryFile.resetMemoryData();
        for (Inventario seleccionado : seleccionados) {
            MemoryFile.addMemoryData(seleccionado);
        }
        MemoryFile.save();
        
        MainScreen.dayMenus = seleccionados;
        MainScreen.INSTANCE.resetUI();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_acceptBtnActionPerformed

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
            java.util.logging.Logger.getLogger(MenuSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuSelector().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JPanel Head;
    private java.awt.Button acceptBtn;
    private javax.swing.JButton addBtn;
    private java.awt.List existantList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private java.awt.List list1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton removeBtn;
    private java.awt.List selectedList;
    // End of variables declaration//GEN-END:variables
}