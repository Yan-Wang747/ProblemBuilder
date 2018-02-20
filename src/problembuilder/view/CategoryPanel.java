/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder.view;

import java.awt.Panel;
import javax.swing.*;
import java.awt.Dimension;
import java.util.ArrayList;
/**
 *
 * @author student
 */
public class CategoryPanel extends Panel{
    public final JTextField categoryTextField;
    public final ArrayList<QuestionButton> questionButtons;
    public final int categoryIndex;
    
    public CategoryPanel(String categoryText, int categoryIndex){
        super();
        questionButtons = new ArrayList();
        categoryTextField = new JTextField();
        this.categoryIndex = categoryIndex;
        initComponents(categoryText);
    }
    
    private void initComponents(String categoryText){
        this.setLayout(new java.awt.GridLayout(6, 0, 0, 10));
        this.setPreferredSize(new Dimension(100, 369));
        categoryTextField.setText(categoryText);
        this.add(categoryTextField);
    }
    
    public QuestionButton addNewQuestionButton(String text){
        QuestionButton newQuestionButton = new QuestionButton(questionButtons.size());
        newQuestionButton.setText(text);
        questionButtons.add(newQuestionButton);
        this.add(newQuestionButton);
        
        return newQuestionButton;
    }
}
