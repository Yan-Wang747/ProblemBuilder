/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder.controller;

import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
import problembuilder.model.Category;
import problembuilder.view.CategoryPanel;
import problembuilder.model.Question;
import problembuilder.view.QuestionButton;
import problembuilder.model.QuestionFileReader;
import problembuilder.model.QuestionFileWriter;
import problembuilder.constant.*;
/**
 *
 * @author iqapp
 */
public class MainWin extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form MainWin
     */
    private final ArrayList<CategoryPanel> categoryPanels;
    private ArrayList<Category> categories;
    private int categoryIndex, questionIndex;
    private File questionFile;
    private int fileIndex;
    private int newCredits;
    
    public MainWin() {
        initComponents();
        fileIndex = 0;
        categoryPanels = new ArrayList();
        categories = new ArrayList();
        reset();
    }

    private void reset(){
        categories.clear();
        fileNameText.setText(Constants.FILE_NAME + fileIndex);
        questionFile = new File(fileNameText.getText());
        if(questionFile.exists())
            try{
                QuestionFileReader questionReader = new QuestionFileReader(questionFile);
                categories = questionReader.readCategories();
            }catch(ClassNotFoundException | IOException e){
                //Do nothing
                System.out.println(e.getMessage());
            }
        
        categoryIndex = -1;
        questionIndex = -1;
        newCredits = -1;

        initCategoryPanels();
    }
    
    private void initCategoryPanels(){
        this.mainPanel.removeAll();
        categoryPanels.clear();
        
        for(Category category : categories){
            CategoryPanel newCategoryPanel = this.addNewCategoryPanel(category.getCategoryText());
            for(Question question : category.questions)
                newCategoryPanel.addNewQuestionButton(Integer.toString(question.credits), this);
            
            newCategoryPanel.addNewQuestionButton("+", this);
        }
        
        addNewCategoryPanel("").addNewQuestionButton("+", this);
        
        this.categoryPanels.get(0).categoryTextField.requestFocus();
    }
    
    private CategoryPanel addNewCategoryPanel(String categoryText){
        CategoryPanel newCategoryPanel = new CategoryPanel(categoryText, categoryPanels.size());
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
            if(sourcePanel.categoryIndex < categories.size())
                categories.get(sourcePanel.categoryIndex).setCategoryText(sourceTextField.getText());
            else{
                Category newCategory = new Category(sourceTextField.getText());
                categories.add(newCategory);
                this.addNewCategoryPanel("").addNewQuestionButton("+", this);
                this.repaint();
            }
        }else
            if(sourcePanel.categoryIndex < categories.size())
                sourcePanel.categoryTextField.setText(categories.get(sourcePanel.categoryIndex).getCategoryText());
    }
    
    @Override
    public void actionPerformed(ActionEvent evt){
        newQuestionButtonAction(evt);
    }
    
    public void newQuestionButtonAction(ActionEvent evt){
        this.errLabel.setText("");
        QuestionButton clickedButton = (QuestionButton) evt.getSource();
        this.categoryIndex = ((CategoryPanel)clickedButton.getParent()).categoryIndex;
        this.questionIndex = clickedButton.questionIndex;
        if(categoryIndex < this.categories.size()){
            Question theQuestion = null;
            if(this.questionIndex < categories.get(this.categoryIndex).questions.size())
                theQuestion = categories.get(this.categoryIndex).questions.get(this.questionIndex);
            
            new QuestionWin(this, theQuestion).setVisible(true);
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
        saveButton = new javax.swing.JButton();
        errLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(1280, 720));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        mainPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        scrollPanel.setViewportView(mainPanel);

        fileNameText.setEditable(false);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        errLabel.setText("Debug");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(fileNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 875, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(errLabel))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if(this.newCredits > 0){
            int numberOfQuestions = this.categories.get(this.categoryIndex).questions.size();
            if(numberOfQuestions < 5 && this.categoryPanels.get(this.categoryIndex).questionButtons.get(this.questionIndex).getText().equals("+"))
                categoryPanels.get(this.categoryIndex).addNewQuestionButton("+", this).requestFocus();
            
            this.categoryPanels.get(this.categoryIndex).questionButtons.get(this.questionIndex).setText(Integer.toString(this.newCredits));
            newCredits = 0;
            this.setVisible(true);
        }
    }//GEN-LAST:event_formWindowActivated

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        try{
            QuestionFileWriter questionWriter = new QuestionFileWriter(questionFile);
            questionWriter.writeCategories(this.categories);
            nextFile();
        }catch(IOException e){
            System.exit(1);
        }
    }//GEN-LAST:event_saveButtonActionPerformed
  
    private void nextFile(){
        this.fileIndex++;
        this.reset();
        this.setVisible(true);
    }
    public void addNewQuestion(Question newQuestion){        
        this.categories.get(this.categoryIndex).addQuestion(questionIndex, newQuestion);
        this.newCredits = newQuestion.credits;
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
    private javax.swing.JTextField fileNameText;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables
}
