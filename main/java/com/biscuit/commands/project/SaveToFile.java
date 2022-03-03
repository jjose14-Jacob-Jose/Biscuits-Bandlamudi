package com.biscuit.commands.project;

import com.biscuit.models.Backlog;
import com.biscuit.models.Project;
import com.biscuit.models.UserStory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveToFile {

    public boolean saveToTextFile (Object objectTobeSaved) throws IOException {
        System.out.println("Saving information to a text file. ");

        if(objectTobeSaved.toString() == null) {
            System.out.println("Unable to save the information to file");
        }
        else {
            StringBuilder outputFileName = new StringBuilder();
            outputFileName.append(System.getProperty("user.dir"));

            if(objectTobeSaved instanceof Project) {
                outputFileName.append("\\" + ((Project) objectTobeSaved).name);
            }  else if (objectTobeSaved instanceof UserStory) {
                outputFileName.append("\\" + ((UserStory) objectTobeSaved).title);
            }  else if (objectTobeSaved instanceof Backlog) {
                outputFileName.append("\\" + ((Backlog) objectTobeSaved).project);
            }  else {
                outputFileName.append("\\ ");
            }



            outputFileName.append(" information.txt");
            System.out.println("Output File name "+outputFileName);
            StringBuilder outputFileContents = new StringBuilder();
            outputFileContents.append(objectTobeSaved.toString());

            File outputFile = new File (outputFileName.toString());
            FileWriter fileWrite = new FileWriter(outputFile);
            fileWrite.write(outputFileContents.toString());
            fileWrite.close();

            System.out.println("Output file contents :"+outputFileContents.toString());
            System.out.println("Saved to a text file");
            System.out.println("Text File address: "+ outputFileName);
            System.out.println("\n");
        }
        
        return true;
    }
}
