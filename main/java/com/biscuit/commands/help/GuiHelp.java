package com.biscuit.commands.help;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.biscuit.views.DashboardView;

public class GuiHelp {

	public GuiHelp() {
		System.out.println("Showing the Help on the GUI");
		JTable helpTable = new JTable(new String[][]{
           
        {"clear", "Clear the terminal screen"},
		{"exit", "Exit/terminate the program"},
		{"dashboard", "Go to dashboard"},
		{"go_to dashboard", "Go to dashboard"},
		{"connect_to taiga", "Connect to taiga"},
		{"help", "Show help"}

       }, new String[]{"Name of the command", "Description of the command"});
		JScrollPane scrollBar = new JScrollPane(helpTable);	
    DashboardView.panel.add( scrollBar, BorderLayout.CENTER );
    DashboardView.panel.repaint();
    DashboardView.frame.repaint();
    DashboardView.frame.pack();
    DashboardView.frame.setVisible(true);	
	}
 
}
