package word;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class Word {
    private String english;   // 영어 단어
    private String meaning;   // 단어의 뜻
    private int correctCount; // 맞춘 갯수
    public JCheckBox correctCheck = new JCheckBox();
    public JTextField tf = new JTextField(10);
    public Word(String english, String meaning, int correctCount) {
        this.english = english;
        this.meaning = meaning;
        this.correctCount = correctCount;
    }

    /**
     * 방금 문제에 체크박스에 체크가 됐으면 correctCount++ 체크가 안됐으면 correctCount = 0;
     */
    public void correctCount() {
    	if(isCorrect()) {
    		this.correctCount++;
    	}else if(isCorrect()) {
    		this.correctCount = 0;
    	}
    }

    /**
     * 체크박스가 체크되었는지 여부 판단
     * @return
     */
    public boolean isCorrect() {
    	return this.correctCheck.isSelected();
    }
    
    public String getEnglish() {
        return english;
    }

    public String getMeaning() {
        return meaning;
    }

    public int getCorrectCount() {
        return correctCount;
    }

    public void incrementCorrectCount() {
        correctCount++;
    }

    public void resetCorrectCount() {
        correctCount = 0;
    }
}
