package com.biscuit.commands.project;

import com.biscuit.commands.Command;
import com.biscuit.models.Project;

import java.io.IOException;

public class PrintProject implements Command {


    @Override
    public boolean execute() throws IOException {
        System.out.println("Project details has been printed. ");
        return false;
    }
}
