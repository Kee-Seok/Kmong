package word;

import java.awt.Toolkit;

public class Main {

	public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()*3/5;
	public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()*9/14;
	public static final int GAB = 10;
	
	public static void main(String[] args) {
		new WordTestProgram();
		Ex.getWordsExcel();
	}
	
}
