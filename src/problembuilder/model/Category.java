/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder.model;

import java.util.ArrayList;
import java.io.Serializable;
/**
 *
 * @author iqapp
 */
public class Category implements Serializable{
    private String categoryText;
    public final ArrayList<Question> questions;
    
    public Category(String categoryText){
        questions = new ArrayList();
        this.categoryText = categoryText;
    }
    
    public void addQuestion(int questionIndex, Question newQuestion){
        if(questionIndex < questions.size())
            questions.set(questionIndex, newQuestion);
        else
            questions.add(newQuestion);
    }
    
    public void setCategoryText(String text){
        this.categoryText = text;
    }
    
    public String getCategoryText(){
        return this.categoryText;
    }
}
