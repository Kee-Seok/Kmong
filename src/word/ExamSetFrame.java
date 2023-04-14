package word;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ExamSetFrame extends JFrame{

	Container c = getContentPane();
	
	JLabel title = new JLabel("횟수");
	JLabel lb = new JLabel("개");
	static JTextField tf = new JTextField(8);
	JButton startBtn = new JButton("시작");
	static int k; //단어 개수 지정 후 그 개수를 담을 변수
	ExamStart es;
	
	public ExamSetFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		c.setBackground(C.a10035[1]);
		
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10,10,10,10);
		title.setFont(C.fiftyFont);
		c.add(title, constraints);
		
		GridBagConstraints constraints1 = new GridBagConstraints();
		constraints1.gridx = 1;
		constraints1.gridy = 0;
		constraints1.insets = new Insets(10,10,10,10);
		tf.setFont(C.fiftyFont);
		c.add(tf, constraints1);
		tf.addKeyListener(new KeySetting());
		
		GridBagConstraints constraints2 = new GridBagConstraints();
		constraints2.gridx = 2;
		constraints2.gridy = 0;
		constraints2.insets = new Insets(10,20,10,10);
		lb.setFont(C.fiftyFont);
		c.add(lb, constraints2);
		lb.addKeyListener(new KeySetting());
		
		GridBagConstraints constraints3 = new GridBagConstraints();
		constraints3.gridx = 1;
		constraints3.gridy = 1;
		constraints3.insets = new Insets(10,10,10,10);
		startBtn.setFont(C.fiftyFont);
		startBtn.addActionListener(new ActionSetting());
		c.add(startBtn, constraints3);
		setUndecorated(true);
		addKeyListener(new KeySetting());
	}
	
	/**
	 * 다시 단어 목록 배열을 재정렬한다.
	 */
	public void reAlignWord() {
		ExamStart.word = ExamStart.word2;
	}
	
	public static void shuffle() {
		Collections.shuffle(ExamStart.word);
	}
	
	public void goToExam() {
		k = Integer.parseInt(tf.getText());
		es = new ExamStart(k);
		es.setVisible(true);
		Ex.getWordsExcel();
		System.out.println(k);
		shuffle();
//		for(int i = 0; i < k; i++) {
//		System.out.print(i+1+" ");
//		System.out.print(ExamStart.word.get(i).getEnglish());
//		System.out.print(" "+ExamStart.word.get(i).getMeaning());
//		System.out.println(" "+ExamStart.word.get(i).getCorrectCount());
//		}
	}
	
	  /**
	   * 시험문항 갯수 설정할때 숫자가 아니면 false를 반환하는 bool 메소드
	   * @param str1
	   * @return
	   */
	  public boolean isNumber(String str1) {
		    boolean isInt = true;
		    try {
		    Integer.parseInt(str1);
		    }catch(NumberFormatException e) {
		    	isInt = false;
		    }
		    return isInt;
		    }
	
	class KeySetting extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				dispose();
				break;
			case KeyEvent.VK_ENTER :
				requestFocus();
				if(isNumber(tf.getText())) {
				 goToExam();
				}else {
					JOptionPane.showMessageDialog(c, "숫자를 입력하세요.");
				}
				tf.setText("");
				es.requestFocus();
				break;
			}
		}
	}
	
	class ActionSetting implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==startBtn) {
				requestFocus();
				if(isNumber(tf.getText())) {
				 goToExam();
				}else {
					JOptionPane.showMessageDialog(c, "숫자를 입력하세요.");
				}
				tf.setText("");
			}
		}

	}
}
	
