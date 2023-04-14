package word;

import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;


public class ModifyFrame extends JFrame{

	Container c = getContentPane();
	
	public ModifyFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		c.setBackground(C.a10035[1]);
		setLocationRelativeTo(null);
		setUndecorated(true);
		addKeyListener(new KeySetting());
	}
	
	
	class KeySetting extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				dispose();
				break;
			}
		}
	}	
}
