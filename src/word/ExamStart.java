package word;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ExamStart extends JFrame{

	Container c = getContentPane();
	JLabel[] lb = {new JLabel("영어단어"), new JLabel("답")};
	static ArrayList<Word> word = new ArrayList<>(); //영어단어
	static ArrayList<Word> word2 = word; //다시 정렬
	static ArrayList<Word> newWord = new ArrayList<>(); //시험 본 후 저장된단어들, 나중에 답이 맞는지 틀린지 채점 후 Word클래스의 correctCount 숫자 변화시킬거임.
	static JLabel[] num;
	static JLabel[] exam;
	static JTextField[] answer, answer2;
	static JCheckBox[] check;

	ArrayList<String> onceInstance = new ArrayList<>();
	
	JButton submitBtn = new JButton("제출");
	
	int labelWidth = 100; //라벨의 가로 길이
	int labelHeight = 30; //라벨의 세로 길이
	int textFieldWidth = 200; //텍스트 필드의 가로 길이
	int textFieldHeight = 30; //텍스트 필드의 세로 길이
	
	int fontSize = 26;

	//
	JPanel checkPanel = new JPanel();
	JPanel checkPanel2 = new JPanel();
	
	JPanel examPanel1 = new JPanel(); //examPanel2와 submitBtn을 담을 패널
	JPanel examPanel2 = new JPanel();
	JScrollPane scroll,scroll2;
	public ExamStart(int k) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLayout(null);
		
		checkPanel.setBounds(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		checkPanel.setLayout(null);
		
		examPanel1.setBounds(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		examPanel1.setLayout(null);
		c.setBackground(C.a10035[1]);
		Collections.shuffle(word);
		scroll = new JScrollPane(examPanel2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2 = new JScrollPane(checkPanel2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		int examPanelHeight = (fontSize + 4*13/10) * k;
		examPanel2.setPreferredSize(new Dimension(scroll.getViewport().getWidth(), examPanelHeight));
		examPanel2.setLayout(null);
		scroll.setBounds(Main.GAB,Main.GAB,Main.SCREEN_WIDTH*4/5-Main.GAB*2,Main.SCREEN_HEIGHT-Main.GAB*2);
		num = new JLabel[k];
		exam = new JLabel[k];
		answer = new JTextField[k];
		answer2 = new JTextField[k];
		check = new JCheckBox[k];
		System.out.println(k);
		for(int i = 0; i < k; i++) {
			num[i] = new JLabel(i+1+".");
			num[i].setFont(C.examFont);
			num[i].setBounds(10, 10 + labelHeight * i, labelWidth, labelHeight);
			exam[i] = new JLabel(word.get(i).getEnglish());
			exam[i].setFont(C.examFont);
			exam[i].setBounds(labelWidth-Main.GAB*2, 10 + labelHeight * i, 300, labelHeight);

			answer[i] = new JTextField(10);
			answer[i].setFont(C.examFont);
			answer[i].setBounds(10 + scroll.getWidth()*1/3, 10 + labelHeight * i, scroll.getWidth()*7/11-Main.GAB, textFieldHeight);
			
			examPanel2.add(num[i]);
			examPanel2.add(exam[i]);
			examPanel2.add(answer[i]);
			newWord.add(word.get(i));
			System.out.println(num[i].getText()+" "+exam[i].getText());
		}
		
		checkPanel2.setPreferredSize(new Dimension(scroll2.getViewport().getWidth(), examPanelHeight));
		checkPanel2.setLayout(null);
		scroll2.setBounds(Main.GAB,Main.GAB,Main.SCREEN_WIDTH*5/5-Main.GAB*2,Main.SCREEN_HEIGHT-Main.GAB*2);
		for(int i = 0; i < k; i++) {
			num[i] = new JLabel(i+1+".");
			num[i].setFont(C.examFont);
			num[i].setBounds(10, 10 + labelHeight * i, labelWidth, labelHeight);
			exam[i] = new JLabel(word.get(i).getEnglish());
			exam[i].setFont(C.examFont);
			exam[i].setBounds(10 + labelWidth, 10 + labelHeight * i, 300, labelHeight);

			answer2[i] = word.get(i).tf;
			answer2[i].setFont(C.examFont);
			answer2[i].setBounds(10 + labelWidth + 100, 10 + labelHeight * i, textFieldWidth*2, textFieldHeight);
			
			check[i] = word.get(i).correctCheck;
			check[i].setFont(C.examFont);
			check[i].setBounds(10 + labelWidth +textFieldWidth*2+110, 10 + labelHeight * i, labelHeight, labelHeight);
			
			
			
			checkPanel2.add(num[i]);
			checkPanel2.add(exam[i]);
			checkPanel2.add(answer2[i]);
			checkPanel2.add(check[i]);
		}
		
		examPanel1.setBackground(C.a614124[3]);
		checkPanel.setBackground(C.a614124[2]);
		repaint();
		setLocationRelativeTo(null);
		setUndecorated(true);
		addKeyListener(new KeySetting());
		submitBtn.setBounds(Main.SCREEN_WIDTH-Main.SCREEN_WIDTH/5,Main.SCREEN_HEIGHT*2/5-Main.GAB*2,Main.SCREEN_WIDTH/5-Main.GAB*2,Main.SCREEN_HEIGHT*1/5-Main.GAB*2);
		submitBtn.addActionListener(new ActionSetting());
		submitBtn.setFont(C.fiftyFont);
		submitBtn.setFocusable(true);
		examPanel1.add(scroll);
		examPanel1.add(submitBtn);
		checkPanel.add(scroll2);
		checkPanel.setVisible(false);
		c.add(checkPanel);
		c.add(examPanel1);
	}
	
	public void goToCheckPanel() {
		examPanel1.setVisible(false);
		checkPanel.setVisible(true);
		checkPanel.repaint();
	}
	
	public void goToExamPanel() {
		examPanel1.setVisible(true);
		checkPanel.setVisible(false);
	}
	
	class KeySetting extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				dispose();
				break;
			case KeyEvent.VK_BACK_SPACE :
				goToExamPanel();
				break;
			}
		}
	}
	
	class ActionSetting implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==submitBtn) {
				requestFocus();
				for(int i = 0; i < ExamSetFrame.k; i++) {
					answer2[i].setText(answer[i].getText());
				}
				goToCheckPanel();
			}
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
	}
}
	
