/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.problembuilder.controller;

import jeopardygame.sharedmodel.*;
import jeopardygame.problembuilder.exception.EmptyAnswerException;
import jeopardygame.problembuilder.exception.EmptyQuestionException;

/**
 *
 * @author iqapp
 */
public class QuestionWin extends javax.swing.JFrame {

    /**
     * Creates new form QuestionWin
     */
    private final MainWin theMainWin;
    private Question theQuestion;
    private final QuestionManager theQuestionManager;
    
    public QuestionWin(MainWin theMainWin, QuestionManager theQuestionManager, Question theQuestion) {
        initComponents();
        this.theMainWin = theMainWin;
        this.theQuestionManager = theQuestionManager;   
        this.theQuestion = theQuestion;
        
        reset();
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

        answerTextField = new javax.swing.JTextArea();
        questionTextField = new javax.swing.JTextArea();
        creditsTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        errLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        answerTextField.setColumns(20);
        answerTextField.setRows(5);
        answerTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                answerTextFieldFocusGained(evt);
            }
        });

        questionTextField.setColumns(20);
        questionTextField.setRows(5);
        questionTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                questionTextFieldFocusGained(evt);
            }
        });

        creditsTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                creditsTextFieldFocusGained(evt);
            }
        });

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        errLabel.setText("Debug");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(questionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(creditsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton))
                            .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(questionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creditsTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton)
                    .addComponent(cancelButton))
                .addGap(28, 28, 28)
                .addComponent(errLabel)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void dispose(){
        theMainWin.setVisible(true);
        super.dispose();
    }
    
    private void reset(){
        this.answerTextField.setText(theQuestion.answerText);
        this.questionTextField.setText(theQuestion.questionText);
        this.creditsTextField.setText(Integer.toString(theQuestion.getCredits()));
        this.questionTextField.requestFocus();
    }
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        try{
            validateData();
            String questionText = questionTextField.getText();
            String answerText = answerTextField.getText();
            int credits = Integer.parseInt(creditsTextField.getText());
            theQuestion = new Question(questionText, answerText, credits, theQuestion.categoryIndex, theQuestion.questionIndex);
            this.theQuestionManager.addQuestion(theQuestion);
            this.theMainWin.setVisible(true);
            this.dispose();
        }catch(EmptyQuestionException e){
            this.errLabel.setText(e.getMessage());
            this.questionTextField.setText(this.theQuestion.questionText);
            this.questionTextField.requestFocus();
        }catch(EmptyAnswerException e){
            this.errLabel.setText(e.getMessage());
            this.answerTextField.setText(this.theQuestion.answerText);
            this.answerTextField.requestFocus();
        }catch(NumberFormatException e){
            this.errLabel.setText("Credits have to be a number larger than 0");
            this.creditsTextField.setText(Integer.toString(this.theQuestion.getCredits()));
            this.creditsTextField.requestFocus();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void validateData() throws EmptyQuestionException, EmptyAnswerException{
        if("".equals(this.questionTextField.getText()))
            throw new EmptyQuestionException();
        if("".equals(this.answerTextField.getText()))
            throw new EmptyAnswerException();
        if(Integer.parseInt(creditsTextField.getText()) <= 0)
            throw new NumberFormatException();
    }
    private void questionTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_questionTextFieldFocusGained
        // TODO add your handling code here:
        this.questionTextField.selectAll();
    }//GEN-LAST:event_questionTextFieldFocusGained

    private void answerTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_answerTextFieldFocusGained
        // TODO add your handling code here:
        this.answerTextField.selectAll();
    }//GEN-LAST:event_answerTextFieldFocusGained

    private void creditsTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_creditsTextFieldFocusGained
        // TODO add your handling code here:
        this.creditsTextField.selectAll();
    }//GEN-LAST:event_creditsTextFieldFocusGained

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JTextArea answerTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField creditsTextField;
    private javax.swing.JLabel errLabel;
    private javax.swing.JTextArea questionTextField;
    // End of variables declaration//GEN-END:variables
}
