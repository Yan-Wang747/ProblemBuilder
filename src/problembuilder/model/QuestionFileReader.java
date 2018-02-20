/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package problembuilder.model;
import java.io.*;
import java.util.ArrayList;
/**
 *
 * @author iqapp
 */
public class QuestionFileReader {
    private final File questionFile;
    
    public QuestionFileReader(File questionFile){
        this.questionFile = questionFile;
    }
    
    public ArrayList<Category> readCategories() throws ClassNotFoundException, IOException{
        ArrayList<Category> res;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(questionFile))) {
            res = (ArrayList<Category>)inputStream.readObject();
            inputStream.close();
        }
        
        return res;
    }
}
