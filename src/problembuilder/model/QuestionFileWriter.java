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
public class QuestionFileWriter {
    private final File questionFile;
    
    public QuestionFileWriter(File questionFile) {
        this.questionFile = questionFile;
    }
    
    public void writeCategories(ArrayList<Category> categories) throws IOException{
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(questionFile))) {
            outputStream.writeObject(categories);
            outputStream.close();
        }
    }
}
