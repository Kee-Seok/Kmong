package word;

public class Word {
    private String english;   // 영어 단어
    private String meaning;   // 단어의 뜻
    private int correctCount; // 맞춘 갯수

    public Word(String english, String meaning, int correctCount) {
        this.english = english;
        this.meaning = meaning;
        this.correctCount = correctCount;
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
