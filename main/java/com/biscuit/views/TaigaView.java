package com.biscuit.views;

import java.io.IOException;
import java.util.List;

import jline.console.completer.Completer;

public class TaigaView extends View {

	public TaigaView(View previousView, String name) {
		super(previousView, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	void addSpecificCompleters(List<Completer> completers) {
		// TODO Auto-generated method stub
		
	}

	@Override
	boolean executeCommand(String[] words) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
