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
public class EmptyAnswerException extends Exception{
    public EmptyAnswerException(){
        super("Answer is empty.");
    }
}
