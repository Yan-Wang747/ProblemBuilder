/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.problembuilder.controller;

import jeopardygame.problembuilder.constant.Constants;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import jeopardygame.sharedview.*;
import jeopardygame.sharedmodel.*;
/**
 *
 * @author iqapp
 */
public class MainWin extends javax.swing.JFrame implements ActionListener, Observer{

    /**
     * Creates new form MainWin
     */
    private final ArrayList<CategoryPanel> categoryPanels;
    private QuestionManager theQuestionManager;
    private File questionFile;
    private int fileIndex;
    private final int maxCategories = 6;
    private final int maxQuestions = 5;
    
    public MainWin() {
        initComponents();
        fileIndex = 0;
        categoryPanels = new ArrayList();
        
        reset();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void update(Observable o, Object arg){
        initCategoryPanels();
        this.saveFile();
    }
    
    private void reset(){
        theQuestionManager = new QuestionManager();
        fileNameText.setText(Constants.FILE_NAME + fileIndex);
        questionFile = new File(fileNameText.getText());
        if(questionFile.exists())
            try{
                QuestionFileReader questionReader = new QuestionFileReader(questionFile);
                theQuestionManager = questionReader.read();
            }catch(ClassNotFoundException | IOException e){
                //Do nothing
            }
        
        theQuestionManager.addObserver(this);
        initCategoryPanels();
    }
    
    private void initCategoryPanels(){
        this.mainPanel.removeAll();
        categoryPanels.clear();
        
        for(int i = 0; i < theQuestionManager.getNumOfCategories(); i++){
            Category category = theQuestionManager.getCategory(i);
            CategoryPanel newCategoryPanel = this.addNewCategoryPanel(category.getCategoryText());
            category.questions.forEach((question) -> {
                newCategoryPanel.addNewQuestionButton(Integer.toString(question.getCredits()), this, Constants.BUTTON_SIZE);
            });
            
            if(category.getNumberOfQuestions() < maxQuestions)
                newCategoryPanel.addNewQuestionButton("+", this, Constants.BUTTON_SIZE);
        }
        
        if(theQuestionManager.getNumOfCategories() < maxCategories)
            addNewCategoryPanel("").addNewQuestionButton("+", this, Constants.BUTTON_SIZE);
        
        this.categoryPanels.get(categoryPanels.size() - 1).categoryTextField.requestFocus();
    }
    
    private CategoryPanel addNewCategoryPanel(String categoryText){
        CategoryPanel newCategoryPanel = new CategoryPanel(categoryText, categoryPanels.size(), Constants.GAP, Constants.CATEGORY_PANEL_SIZE);
        newCategoryPanel.categoryTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                categoryTextFieldFocusLost(evt);
            }
        });
        mainPanel.add(newCategoryPanel);
        categoryPanels.add(newCategoryPanel);
        
        return newCategoryPanel;
    }
    
    public void categoryTextFieldFocusLost(java.awt.event.FocusEvent evt){
        this.errLabel.setText("");
        JTextField sourceTextField = (JTextField)evt.getSource();
        CategoryPanel sourcePanel = (CategoryPanel)sourceTextField.getParent();
        if(!sourceTextField.getText().equals("")){
            if(sourcePanel.categoryIndex < theQuestionManager.getNumOfCategories()){
                theQuestionManager.categories.get(sourcePanel.categoryIndex).setCategoryText(sourceTextField.getText());
            }else { 
                Category newCategory = new Category(sourceTextField.getText(),sourcePanel.categoryIndex);
                theQuestionManager.addNewCategory(newCategory);
                
                if (categoryPanels.size() < maxCategories){
                    this.addNewCategoryPanel("").addNewQuestionButton("+", this, Constants.BUTTON_SIZE);
                    this.repaint();
                }
            }
        }else
            if(sourcePanel.categoryIndex < theQuestionManager.categories.size())
                sourcePanel.categoryTextField.setText(theQuestionManager.categories.get(sourcePanel.categoryIndex).getCategoryText());
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        newQuestionButtonAction(evt);
    }
    
    public void newQuestionButtonAction(ActionEvent evt){
        this.errLabel.setText("");
        QuestionButton clickedButton = (QuestionButton) evt.getSource();
        int categoryIndex = ((CategoryPanel)clickedButton.getParent()).categoryIndex;
        int questionIndex = clickedButton.questionIndex;
        if(categoryIndex < theQuestionManager.categories.size()){
            Question theQuestion = new Question("", "", 0, categoryIndex, questionIndex);
            if(questionIndex < theQuestionManager.categories.get(categoryIndex).questions.size())
                theQuestion = theQuestionManager.categories.get(categoryIndex).questions.get(questionIndex);
            
            new QuestionWin(this, this.theQuestionManager, theQuestion).setVisible(true);
            this.setVisible(false);
        }else{
            errLabel.setText("category text is empty.");
            ((CategoryPanel)clickedButton.getParent()).categoryTextField.requestFocus();
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

        scrollPanel = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();
        fileNameText = new javax.swing.JTextField();
        errLabel = new javax.swing.JLabel();
        nextFileButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING);
        flowLayout1.setAlignOnBaseline(true);
        mainPanel.setLayout(flowLayout1);
        scrollPanel.setViewportView(mainPanel);

        fileNameText.setEditable(false);

        nextFileButton.setText("Next File");
        nextFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextFileButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Question File:");

        exportButton.setText("Export to TXT");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addComponent(fileNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(580, 580, 580)
                .addComponent(errLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exportButton)
                .addGap(18, 18, 18)
                .addComponent(nextFileButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextFileButton)
                    .addComponent(exportButton))
                .addGap(67, 67, 67))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveFile(){
        try{
            QuestionFileWriter questionWriter = new QuestionFileWriter(questionFile);
            questionWriter.write(theQuestionManager);
        }catch(IOException e){
            System.exit(1);
        }
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.saveFile();
    }//GEN-LAST:event_formWindowClosing

    private void nextFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextFileButtonActionPerformed
        // TODO add your handling code here:
        nextFile();
    }//GEN-LAST:event_nextFileButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        // TODO add your handling code here:
        try {
            PrintWriter txtOutput = new PrintWriter(new File("Question" + Integer.toString(fileIndex) + ".txt"));
            for(Category category : theQuestionManager.categories) {
                String leadingText = category.getCategoryText() + "_";
                
                for (Question question : category.questions) {
                    String heading = leadingText + Integer.toString(question.getCredits()) + ": ";
                    String questionLine = heading + question.questionText;
                    txtOutput.println(questionLine);
                    String answerLine = heading + question.answerText;
                    txtOutput.println(answerLine);
                    txtOutput.println();
                }
            }
            
            txtOutput.flush();
            txtOutput.close();
            this.setTitle("Export to txt file succeeded.");
        } catch (IOException e){
            
        }
    }//GEN-LAST:event_exportButtonActionPerformed
  
    private void nextFile(){
        if(!this.theQuestionManager.categories.isEmpty()){
            this.fileIndex++;
            this.reset();
            this.setVisible(true);
        }
    }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errLabel;
    private javax.swing.JButton exportButton;
    private javax.swing.JTextField fileNameText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton nextFileButton;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
