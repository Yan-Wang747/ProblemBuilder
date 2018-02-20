/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder.model;

import java.io.Serializable;
/**
 *
 * @author iqapp
 */
public class Question implements Serializable{
    public final String questionText;
    public final String answerText;
    public final int credits;
    
    public Question(String questionText, String answerText, int credits){
        this.questionText = questionText;
        this.answerText = answerText;
        this.credits = credits;
    }
}
