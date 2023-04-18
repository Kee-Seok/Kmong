package word;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ExamStart extends JFrame{

	Point p = new Point();
	Point newP = new Point();
	Container c = getContentPane();
	JLabel[] lb = {new JLabel("영어단어"), new JLabel("답")};
	static ArrayList<Word> word = new ArrayList<>(); //영어단어
	static ArrayList<Word> word2 = word; //다시 정렬
	static ArrayList<Word> lastWord = new ArrayList<>(); //마지막에 정답 확인 후 단어를 다시 집어넣을 변수
	static ArrayList<Word> newWord = new ArrayList<>(); //시험 본 후 저장된단어들, 나중에 답이 맞는지 틀린지 채점 후 Word클래스의 correctCount 숫자 변화시킬거임.
	static JLabel[] num;
	static JLabel[] exam;
	static JTextField[] answer, answer2, correctAnswer;
	static JCheckBox[] check;
	static JLabel[] title = {
		new JLabel("번호"),
		new JLabel("단어"),
		new JLabel("내가 쓴 답"),
		new JLabel("정답"),
		new JLabel("답 확인"),
	};
	ArrayList<String> onceInstance = new ArrayList<>();
	
	JButton submitBtn = new JButton("제출");
	JButton lastSubmitBtn = new JButton("제출");
	
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
	
	int k;
	public ExamStart(int k) {
		this.k = k;
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
		correctAnswer = new JTextField[k];
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
		scroll2.setBounds(Main.GAB,Main.GAB*2+labelHeight,Main.SCREEN_WIDTH*5/5-Main.GAB*2,Main.SCREEN_HEIGHT-Main.GAB*3-labelHeight);
		title[0].setBounds(10, 10 ,labelWidth, labelHeight);
		title[1].setBounds(10 + labelWidth/2, 10 ,labelWidth, labelHeight);
		title[2].setBounds(10 + labelWidth+100, 10  ,labelWidth, labelHeight);
		title[3].setBounds(10 + labelWidth +textFieldWidth*2 + 100, 10 ,labelWidth, labelHeight);
		title[4].setBounds(10 + labelWidth +textFieldWidth*4+110, 10 ,labelWidth, labelHeight);
		for(int i = 0; i < k; i++) {
			num[i] = new JLabel(i+1+".");
			num[i].setFont(C.examFont);
			num[i].setBounds(10, 10 + labelHeight + labelHeight * i, labelWidth, labelHeight);
			exam[i] = new JLabel(word.get(i).getEnglish());
			exam[i].setFont(C.examFont);
			exam[i].setBounds(10 + labelWidth/2, 10 +labelHeight + labelHeight * i, 300, labelHeight);

			answer2[i] = word.get(i).tf;
			answer2[i].setFont(C.examFont);
			answer2[i].setBounds(10 + labelWidth + 100, 10 +labelHeight + labelHeight * i, textFieldWidth*2, textFieldHeight);
			
			correctAnswer[i] = new JTextField(word.get(i).getMeaning());
			correctAnswer[i].setFont(C.examFont);
			correctAnswer[i].setBounds(10 + labelWidth +textFieldWidth*2 + 100, 10 +labelHeight + labelHeight * i, textFieldWidth*2, textFieldHeight);
			
			check[i] = word.get(i).correctCheck;
			check[i].setFont(C.examFont);
			check[i].setBounds(10 + labelWidth +textFieldWidth*4+110, 10 +labelHeight + labelHeight * i, labelHeight, labelHeight);
			
			lastWord.add(new Word(word.get(i).getEnglish(),word.get(i).getMeaning(),word.get(i).getCorrectCount()));
			
			checkPanel2.add(num[i]);
			checkPanel2.add(exam[i]);
			checkPanel2.add(answer2[i]);
			checkPanel2.add(correctAnswer[i]);
			checkPanel2.add(check[i]);
		}
		//JLabel 문제 체크 화면의 위 제목 패널에 붙이기
		for(int j = 0; j < title.length; j++) {
			title[j].setFont(C.examFont);
			checkPanel2.add(title[j]);
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
		lastSubmitBtn.setBounds(Main.SCREEN_WIDTH-Main.GAB-labelWidth,Main.GAB,labelWidth,labelHeight);
		lastSubmitBtn.setFont(C.examFont);
		lastSubmitBtn.setFocusable(true);
		lastSubmitBtn.addActionListener(new ActionSetting());
		
		
		examPanel1.add(scroll);
		examPanel1.add(submitBtn);
		examPanel1.addMouseListener(new MouseSetting());
		examPanel1.addMouseMotionListener(new MouseSetting());
		checkPanel.addMouseListener(new MouseSetting());
		checkPanel.addMouseMotionListener(new MouseSetting());
		checkPanel.add(scroll2);
		checkPanel.add(lastSubmitBtn);
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
				//JCheckBox 정답 여부 모두 체크 후 제출 버튼 누르면 단어장 수정 내용으로 넘어감.
			}else if(e.getSource()==lastSubmitBtn) {
				int b = 0; //체크박스가 몇개 체크됐는지.
				for(int i = 0; i < k; i++) {
					if(check[i].isSelected()){
						b++;
						for(int j = 0; j < word2.size(); j++) { //원래의 단어장 배열에서 맞춘 정답의 카운트만 ++하기 위해 비교할꺼임.
							if(lastWord.get(i).getEnglish().equals(word2.get(j).getEnglish())) {
								word2.get(j).correctCount++;
								System.out.println(word2.get(j).getEnglish()+"를 맞춘 갯수는 : "+word2.get(j).correctCount);
								break;
							}
						}
						lastWord.get(i).correctCount++;
						System.out.println(lastWord.get(i).getEnglish()+"에 관한 문제를 맞춘 갯수는 "+lastWord.get(i).getCorrectCount()+"개 입니다.");
					}else {
						for(int j = 0; j < word2.size(); j++) { //체크하지 않았으면 --할꺼임.
							if(lastWord.get(i).getEnglish().equals(word2.get(j).getEnglish())) {
								word2.get(j).correctCount = 0;
								break;
							}
						}
						
						lastWord.get(i).correctCount = 0;
						System.out.println(lastWord.get(i).getEnglish()+"에 관한 문제를 틀린 갯수는 "+lastWord.get(i).getCorrectCount()+"개 입니다.");
					}
				}
				Ex.save(word2);
				JOptionPane.showMessageDialog(c, "맞은 갯수는 "+b+"개 입니다.");
				System.out.println("맞은 갯수는 "+b+"개 입니다.");
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
	
	//윈도우 창을 움직일 마우스 리스너
	class MouseSetting implements MouseMotionListener, MouseListener{

		public void mouseClicked(MouseEvent e) {
			
		}

		public void mousePressed(MouseEvent e) {
			p = e.getPoint();
			requestFocus();
		}

		public void mouseReleased(MouseEvent e) {
			
		}

		public void mouseEntered(MouseEvent e) {
			
		}

		public void mouseExited(MouseEvent e) {
			
		}

		public void mouseDragged(MouseEvent e) {
			newP = new Point(e.getLocationOnScreen().x-p.x,e.getLocationOnScreen().y-p.y);
			setLocation(newP.x,newP.y);
			requestFocus();
		}

		public void mouseMoved(MouseEvent e) {
			
		}
		
	}
}
	
