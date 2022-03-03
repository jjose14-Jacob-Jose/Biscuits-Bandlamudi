package com.biscuit.commands.project;

import com.biscuit.models.Project;

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
                outputFileName.append("\\" + ((Project) objectTobeSaved).name + " information.txt");
                System.out.println("Output FIle Name: " + outputFileName.toString());

                StringBuilder outputFileContents = new StringBuilder();
                outputFileContents.append(objectTobeSaved.toString());

                File outputFile = new File (outputFileName.toString());
                FileWriter fileWrite = new FileWriter(outputFile);
                fileWrite.write(outputFileContents.toString());
                fileWrite.close();

                System.out.println("Output file contents :"+outputFileContents.toString());
                System.out.println("Saved to a text file");
                System.out.printf("Text File address: "+ outputFileName);

            }


        }


        System.out.println("Saved to file. ");
        return true;
    }


//    @Override
//    public boolean execute() throws IOException {
//        String addressOfCurrentDirectory = System.getProperty("user.dir");
//        System.out.println("Current Directory: "+addressOfCurrentDirectory);
//        System.out.println("Project details has been printed. ");
//        return false;
//    }
}
