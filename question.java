package com.mycompany.main;

public class question {
    private String codeBefore;
    private String correctAnswer;
    private String codeAfter;
    private int level;

    // Constructor
    public question(String codeBefore, String correctAnswer, String codeAfter, int level) {
        this.codeBefore = codeBefore;
        this.correctAnswer = correctAnswer;
        this.codeAfter = codeAfter;
        this.level = level;
    }

    // Getters
    public String getCodeBefore() {
        return codeBefore;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCodeAfter() {
        return codeAfter;
    }

    public int getLevel() {
        return level;
    }

    // Check Answer
    public boolean checkAnswer(String answer) {
        return answer.trim().equals(correctAnswer);
    }

    // Get Full Code
    public String getFullCode() {
        return codeBefore + "_____" + codeAfter;
    }
}