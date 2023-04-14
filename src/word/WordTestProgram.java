package word;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class WordTestProgram extends JFrame implements MouseListener, MouseMotionListener{

	String title = "단어 시험";
	
	Container c = getContentPane();
	Point p = new Point();
	JButton[] btn = {new JButton("시험"), new JButton("제출"), new JButton("사진변경"), new JButton("닫기")};
	
	JPanel btnPanel;
	int btnPanelWidth,btnPanelHeight, firstX, firstY;
	
	File dir = new File("./img/");
	File[] files = dir.listFiles(new FilenameFilter() {
		public boolean accept(File dir, String name) {
			return name.endsWith("jpeg")||name.endsWith("jpg")||name.endsWith("png");
		}
	});
	MainPanel mainPanel = new MainPanel();
	Image mainPanelImage = new ImageIcon(files[0].getAbsolutePath()).getImage().getScaledInstance(mainPanel.getWidth()-btnPanelWidth-Main.GAB*3, mainPanel.getHeight()-Main.GAB*2, Image.SCALE_SMOOTH);
 
	public WordTestProgram() {
		init();
		
	}
	
	public void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true); 
		addKeyListener(new KeySetting());
		addMouseListener(this);
		addMouseMotionListener(this);
		add();
	}
	
	//첫번째 화면. 레이아웃은 왼쪽 버튼박스 패널, 오른쪽은 사진을 불러와서 출력할 수 있는 화면으로 나눌꺼임. 1:4로
	public void add() {
		mainPanel.setBounds(Main.GAB,Main.GAB,Main.SCREEN_WIDTH-Main.GAB*2,Main.SCREEN_HEIGHT-Main.GAB*2);
		mainPanel.setLayout(null);
		c.add(mainPanel);
		c.setBackground(C.a614124[2]);
		btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBackground(C.a10035[2]);
		btnPanel.setBounds(Main.GAB,Main.GAB*15,btnPanelWidth=mainPanel.getWidth()/5-Main.GAB*2,btnPanelHeight=mainPanel.getHeight()-Main.GAB*22);
		mainPanelImage = new ImageIcon(files[0].getAbsolutePath()).getImage().getScaledInstance(mainPanel.getWidth()-btnPanelWidth-Main.GAB*3, mainPanel.getHeight()-Main.GAB*2, Image.SCALE_SMOOTH);
		//버튼 세팅
		for(int i = 0; i < 4; i++) {
		btn[i].setBounds(Main.GAB,Main.GAB + (100+Main.GAB)*i, btnPanel.getWidth()-Main.GAB*2,100);
		btnPanel.add(btn[i]);
		btn[i].setFont(C.btnFont);
		btn[i].addActionListener(new ActionSetting());
		btn[i].addMouseListener(this);
		btn[i].addMouseMotionListener(this);
		}
		mainPanel.add(btnPanel);
	}
	
	//버튼과 사진을 담을 수 있는 초기 패널 세팅
	class MainPanel extends JPanel{
		public void paintComponent(Graphics g) {
			
			g.setColor(C.a10035[1]);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(C.black);
			g.setFont(C.fiftyFont);
			g.drawString(title, Main.GAB*2, Main.GAB*10);
			if(mainPanelImage!=null) {
			g.drawImage(mainPanelImage,btnPanelWidth+Main.GAB*2,Main.GAB,this);
			}
		}
		
	}
	
	class KeySetting extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				System.exit(0);
				break;
			}
		}
	}
	
	class ActionSetting implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if(command=="시험") {
				requestFocus();
				ExamSetFrame ex = new ExamSetFrame();
				ex.setVisible(true);
			}else if(command=="제출") {
				ModifyFrame mo = new ModifyFrame();
				mo.setVisible(true);
				requestFocus();
			}else if(command=="닫기") {
				requestFocus();
				System.exit(0);
			}else if(command=="사진변경") {
				requestFocus();
				JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setCurrentDirectory(new File("./img"));
	            FileNameExtensionFilter filter = new FileNameExtensionFilter("이미지 파일", "jpg", "jpeg", "png", "gif");
	            fileChooser.setFileFilter(filter);

	            int result = fileChooser.showOpenDialog(c);
	            if (result == JFileChooser.APPROVE_OPTION) {
	                File selectedFile = fileChooser.getSelectedFile();
	                String filePath = selectedFile.getAbsolutePath();
	                System.out.println(filePath);
	                mainPanelImage = new ImageIcon(filePath).getImage().getScaledInstance(mainPanel.getWidth()-btnPanelWidth-Main.GAB*3, mainPanel.getHeight()-Main.GAB*2, Image.SCALE_SMOOTH);
	                // 이미지 라벨 추가
	                mainPanel.repaint();
			}
		}
	}
	}

	public void mouseDragged(MouseEvent e) {
		if(e.getSource()==this) {
		setLocation(e.getLocationOnScreen().x-firstX,e.getLocationOnScreen().y-firstY);
		requestFocus();
		}
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getSource()!=btn[0]||e.getSource()!=btn[1]||e.getSource()!=btn[2]||e.getSource()!=btn[3])
		firstX = e.getPoint().x;
		firstY = e.getPoint().y;
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	@SuppressWarnings("deprecation")
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==btn[0]||e.getSource()==btn[1]||e.getSource()==btn[2]||e.getSource()==btn[3]) {
			setCursor(12);
		}
	}

	@SuppressWarnings("deprecation")
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==btn[0]||e.getSource()==btn[1]||e.getSource()==btn[2]||e.getSource()==btn[3]) {
			setCursor(Cursor.DEFAULT_CURSOR);
		}
	}
	
}
