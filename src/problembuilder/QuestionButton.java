/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder;
import javax.swing.JButton;
/**
 *
 * @author student
 */
public class QuestionButton extends JButton{
    public final int questionIndex;
    
    public QuestionButton(int questionIndex){
        super();
        this.questionIndex = questionIndex;
    }
}
