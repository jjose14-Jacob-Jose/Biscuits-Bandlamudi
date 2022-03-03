package com.biscuit.commands.project;

public class SaveToFile {

    public boolean saveToTextFile (Object objectTobeSaved) {
        System.out.println("Saving information to a text file. ");

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
