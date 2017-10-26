/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.aurelien.todoswing.frame;


import fr.aurelien.todoswing.DAO.DbCn;
import fr.aurelien.todoswing.DAO.TaskDAO;
import fr.aurelien.todoswing.entity.Task_entity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aurelien Courgeau
 */
public class MainTodoswingFrame extends javax.swing.JFrame {

    private Task_entity entity ;
    private TaskDAO dao;
    private Connection cn;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel ComboBoxModel;
    private Map<String,Integer> mapIdCategorie;
    private Map<Integer,Integer> idColumn;
    
    public MainTodoswingFrame() throws SQLException {
        initComponents();
        
        setVisible(true);
        
        try {
            cn = DbCn.getInstance();
            //instanciation de l'entity
        entity = new Task_entity();
        
        //instanciation de la DAO
        dao = new TaskDAO(cn);
        
        //récupération de l'id de l'élément séléctionné dans le tableau
        
        idColumn = new HashMap<>();
        
        //récupération de l'id categorie
        Map<String,Integer> mapIdCategorie = new HashMap<>();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainTodoswingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //Désactivation des bouton 
        modifyButton.setEnabled(false);
        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        razTableButton.setVisible(false);
        
        initTable();
    }

    
    
    public void initTable() throws SQLException{
        // initialisation du tableau et de la ComboBox
        tableModel = (DefaultTableModel) taskTable.getModel();
        ComboBoxModel = (DefaultComboBoxModel) categorieComboBox.getModel();
        tableModel.setRowCount(0);
        ComboBoxModel.removeAllElements();
        
        //Récupération du résultat
        ResultSet resultSet =dao.getAllTaskData().getResultSet();
        resultSet.beforeFirst();
        
        // nouveau tableau a 5 colonnes
        Object column[] = new Object[5];
        
        //numéro de la ligne
        int nbRow = 0;
        
        //reset du map de correspondance row/id car à chaque appel de initTable cet correspondances changent
        idColumn.clear();
        //Boucle pour charger le tableau 
        while(resultSet.next()){
            
            //
            idColumn.put(nbRow,resultSet.getInt(1));
            column[0]=resultSet.getString(2);
            column[1]=resultSet.getString(3);
            column[2]=resultSet.getBoolean(4);
            column[3]=resultSet.getBoolean(5);
            
           
            tableModel.addRow(column);
            
            
            nbRow++;
        }
        //on renvoie le nom de l'id de categorie dans une Map
        mapIdCategorie = dao.getKeyMap();
        
        //
        Set<String> nameCat =  mapIdCategorie.keySet();
        
        //Plagia du code de Jean Marc pour le tri du ComboBox
        //instanciation d'un treeSet et convertion du set en TreeSet pour classer les catégorie en ordre alphabétique via une boucle for
        SortedSet<String> listCategorie = new TreeSet<>();
        for (String entry : nameCat) {
            listCategorie.add(entry);
            
        }
        //ajout des éléments classés par ordre alphabetique dans la combo box via son model
        for (String o : listCategorie) {
            
            ComboBoxModel.addElement(o);
            
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMessage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        newTaskTextField = new javax.swing.JTextArea();
        categorieComboBox = new javax.swing.JComboBox();
        addButton = new javax.swing.JButton();
        modifyButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        urgentCheckbox = new javax.swing.JCheckBox();
        executedCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        razTableButton = new javax.swing.JButton();

        jMessage.setText("Id = ");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        taskTable.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(153, 153, 153), new java.awt.Color(204, 204, 204)));
        taskTable.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 18)); // NOI18N
        taskTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Taches", "Catégorie", "Urgent", "Fait"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        taskTable.setRowHeight(25);
        taskTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taskTable);
        if (taskTable.getColumnModel().getColumnCount() > 0) {
            taskTable.getColumnModel().getColumn(0).setResizable(false);
            taskTable.getColumnModel().getColumn(1).setPreferredWidth(350);
            taskTable.getColumnModel().getColumn(2).setPreferredWidth(100);
            taskTable.getColumnModel().getColumn(3).setMaxWidth(70);
            taskTable.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        newTaskTextField.setColumns(20);
        newTaskTextField.setRows(5);
        newTaskTextField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        newTaskTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                newTaskTextFieldKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(newTaskTextField);

        categorieComboBox.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        categorieComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "titre1", " " }));
        categorieComboBox.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        categorieComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categorieComboBoxMouseClicked(evt);
            }
        });
        categorieComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categorieComboBoxActionPerformed(evt);
            }
        });

        addButton.setFont(new java.awt.Font("Matura MT Script Capitals", 1, 36)); // NOI18N
        addButton.setText("Ajouter");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        modifyButton.setFont(new java.awt.Font("Matura MT Script Capitals", 1, 36)); // NOI18N
        modifyButton.setText("Modify");
        modifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 36)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Matura MT Script Capitals", 1, 33)); // NOI18N
        resetButton.setText("R.A.Z");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        urgentCheckbox.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 24)); // NOI18N
        urgentCheckbox.setText("Urgent   ");
        urgentCheckbox.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        urgentCheckbox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        urgentCheckbox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        urgentCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urgentCheckboxActionPerformed(evt);
            }
        });

        executedCheckBox.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 24)); // NOI18N
        executedCheckBox.setText("Terminé   ");
        executedCheckBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        executedCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        executedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                executedCheckBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Nouvelle tâche : ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Selection Catégorie");

        jLabel3.setFont(new java.awt.Font("Matura MT Script Capitals", 3, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Liste des tâches");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        razTableButton.setFont(new java.awt.Font("Matura MT Script Capitals", 0, 24)); // NOI18N
        razTableButton.setText("Raz Table");
        razTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                razTableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(executedCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(urgentCheckbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(modifyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(44, 44, 44)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(categorieComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))))
                                .addGap(92, 92, 92)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(razTableButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(executedCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(urgentCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(razTableButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                    .addComponent(categorieComboBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(modifyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void categorieComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categorieComboBoxMouseClicked
        modifyButton.setEnabled(true);
        addButton.setEnabled(true);
    }//GEN-LAST:event_categorieComboBoxMouseClicked

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
         
        setFieldsToModify(this.entity);
        try {
            dao.addTask(this.entity);
            initTable();
            resetAll();
        } catch (SQLException ex) {
            Logger.getLogger(MainTodoswingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_addButtonActionPerformed

    private void modifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyButtonActionPerformed
         //affectation des variables de l'objet evt, variable d'instance de notre classe jframe
        setFieldsToModify(this.entity);
        try {
               dao.modifyTask(this.entity);
               initTable();
               resetAll();
                     
        }catch (SQLException ex) {
               Logger.getLogger(MainTodoswingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_modifyButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
         
        setFieldsToModify(this.entity);
        
        try {
            dao.deleteTask(this.entity);
                      
             initTable();
             resetAll();
             
        } catch (SQLException ex) {
            Logger.getLogger(MainTodoswingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        resetAll();
        modifyButton.setEnabled(false);
        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
        razTableButton.setVisible(true);
        
    }//GEN-LAST:event_resetButtonActionPerformed

    private void taskTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskTableMouseClicked
        // Récupération des champs de la ligne selectionnée
        int row = taskTable.getSelectedRow();
        
        newTaskTextField.setText((String) tableModel.getValueAt(row, 0));
        categorieComboBox.setSelectedItem((String) tableModel.getValueAt(row, 1));
        urgentCheckbox.setSelected((Boolean) tableModel.getValueAt(row, 2));
        executedCheckBox.setSelected((Boolean) tableModel.getValueAt(row, 3));
        jMessage.setText("ID = " + idColumn.get(taskTable.getSelectedRow()).toString());
        
        addButton.setEnabled(false);
        deleteButton.setEnabled(true);
        modifyButton.setEnabled(false);
        
        
        setFieldsToModify(this.entity);
        
      
        
    }//GEN-LAST:event_taskTableMouseClicked

    private void newTaskTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newTaskTextFieldKeyTyped
        if (taskTable.getSelectedRow() != -1){
            modifyButton.setEnabled(true);
            addButton.setEnabled(true);
        }else
        addButton.setEnabled(true);
    }//GEN-LAST:event_newTaskTextFieldKeyTyped

    private void categorieComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categorieComboBoxActionPerformed
         if (!newTaskTextField.getText().isEmpty() || !(newTaskTextField.getText().equals(""))) {
           modifyButton.setEnabled(true);
           addButton.setEnabled(true);
         }
    }//GEN-LAST:event_categorieComboBoxActionPerformed

    private void executedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_executedCheckBoxActionPerformed
         if (!newTaskTextField.getText().isEmpty() || !(newTaskTextField.getText().equals(""))) {
           modifyButton.setEnabled(true);
           addButton.setEnabled(true);
       }else{
             addButton.setEnabled(false);
         }
    }//GEN-LAST:event_executedCheckBoxActionPerformed

    private void urgentCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urgentCheckboxActionPerformed
         if (!newTaskTextField.getText().isEmpty() || !(newTaskTextField.getText().equals(""))) {
           modifyButton.setEnabled(true);
           addButton.setEnabled(true);
       }
    }//GEN-LAST:event_urgentCheckboxActionPerformed

    private void razTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_razTableButtonActionPerformed
        try {
            
            dao.deleteAllTaskData();
                      
             initTable();
             resetAll();
        } catch (SQLException ex) {
            Logger.getLogger(MainTodoswingFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_razTableButtonActionPerformed

    public void resetAll(){
        taskTable.clearSelection();
        jMessage.setText("ID = ");
        newTaskTextField.setText("");
        categorieComboBox.setSelectedIndex(0);
        urgentCheckbox.setSelected(false);
        executedCheckBox.setSelected(false);
        
        modifyButton.setEnabled(false);
        addButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }
    
    private void setFieldsToModify(Task_entity fieldsToModify){
        
        
        fieldsToModify.setTodo_task(newTaskTextField.getText());
        fieldsToModify.setTodo_categorie_id(mapIdCategorie.get((String)categorieComboBox.getSelectedItem()));
        fieldsToModify.setTodo_urgent(urgentCheckbox.isSelected());
        fieldsToModify.setTodo_executed(executedCheckBox.isSelected()); 
        
        //Renvoie -1 si aucune ligne n'est sélectionnée
        if (taskTable.getSelectedRow() != -1){
            fieldsToModify.setTodo_id(idColumn.get(taskTable.getSelectedRow()));
            
        }
    }
    /**
     * @param args the command line arguments
     */
   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JComboBox categorieComboBox;
    private javax.swing.JButton deleteButton;
    private javax.swing.JCheckBox executedCheckBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jMessage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton modifyButton;
    private javax.swing.JTextArea newTaskTextField;
    private javax.swing.JButton razTableButton;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable taskTable;
    private javax.swing.JCheckBox urgentCheckbox;
    // End of variables declaration//GEN-END:variables
}
