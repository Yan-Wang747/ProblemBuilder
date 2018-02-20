/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder.exception;

/**
 *
 * @author iqapp
 */
public class EmptyQuestionException extends Exception{
    public EmptyQuestionException(){
        super("Question is empty.");
    }
}
