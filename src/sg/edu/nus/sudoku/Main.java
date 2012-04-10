package sg.edu.nus.sudoku;

import sg.edu.nus.sudoku.controller.MainController;
import sg.edu.nus.sudoku.gui.Resources;

public class Main {

	public static void main(String[] args) {
		Resources.loadResources();
		MainController mc = new MainController();
		mc.initialize();
	}
}
