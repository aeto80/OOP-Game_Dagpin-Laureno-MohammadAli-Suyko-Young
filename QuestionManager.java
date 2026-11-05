/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author Jeremie Young
 */
import java.util.ArrayList;
import java.util.Random;

public class QuestionManager {
    private ArrayList<question> Questions;
    private Random random;
    private question currentQuestion;
    
    public QuestionManager(){
        Questions = new ArrayList<question>();
        random = new Random();
        createQuestions();
    }
    
    public void createQuestions(){
        //Level 1
        Questions.add(new question("int x = 20 ", ";", " ", 1));
        Questions.add(new question(" ", "String", "name = \"Louise\";", 1));
        Questions.add(new question(" ", "int", " age = 20;", 1));
        Questions.add(new question("System.out", ".println", "\"(Hello World)\";", 1));
        
        //Level 2
        Questions.add(new question("int sum = 10 + 5", ";", " ", 2));
        Questions.add(new question("if (age >= 18) { System.out.println(\"Adult\"); } ", "else", "{ System.out.println(\"Minor\"); }", 2));
        Questions.add(new question("for (int i = 0; i < 5", ";", "i++) { System.out.println(i); }", 2));
        Questions.add(new question("for (int i = 0; i < 5; i++", ")", "{ System.out.println(i); }", 2));
        
        //Level 3
        Questions.add(new question("public class Student", "{", " }", 3));
        Questions.add(new question("public void display()", "{", " System.out.println(\"Hello\"); }", 3));
        Questions.add(new question("public int getAge() { return age", ";", " }", 3));
        Questions.add(new question("public Student() ", "{", " }", 3));
        
        //Level 4
        Questions.add(new question("int[] numbers = {1, 2, 3}", ";", "", 4));
        Questions.add(new question("int[] numbers = new int[5", "]", ";", 4));
        Questions.add(new question("numbers[0", "]", " = 10;", 4));
        Questions.add(new question("int[][] matrix = new int[3]", "[", "3];", 4));
        Questions.add(new question("import java.util.Scanner*", ";", "", 4));
        
        //Level 5
        Questions.add(new question("try ", "{", " System.out.println(\"Hello\"); }", 5));
        Questions.add(new question("catch (Exception e) ", "{", " System.out.println(e); }", 5));
        Questions.add(new question("throw new IllegalArgumentException(\"Invalid\")", ";", "", 5));
        Questions.add(new question("switch (day) { case 1", ":", " System.out.println(\"Monday\"); break; }", 5));
        Questions.add(new question("switch (day) { default", ":", " System.out.println(\"Invalid day\"); }", 5));
        
        //Level 6
        Questions.add(new question("public ", "static", " void main(String[] args) { }", 6));
        Questions.add(new question(" ", "try", " { int result = 10 / divisor; }", 6));
        Questions.add(new question("catch (", "ArithmeticException e", ") {", 6));
        Questions.add(new question("import javax", ".util.Scanner", "*;", 4));
        Questions.add(new question("public void setName(String name) { this.name = ", "name", "; }", 6));
    }
    
    public question getQuestion(int level){
        ArrayList<question> levelQuestions = new ArrayList<question>();
        
        for(int i = 0; i < Questions.size(); i++){
            if (Questions.get(i).getLevel() == level){
                levelQuestions.add(Questions.get(i));
            }
        }
        
        if(levelQuestions.size() == 0){
            return null;
        }
        
        int randomNumber = random.nextInt(levelQuestions.size());
        
        currentQuestion = levelQuestions.get(randomNumber);
        
        return currentQuestion;
    }
    
    public question getCurrentQuestion(){
        return currentQuestion;
    }
    
    public boolean checkAnswer(String answer){
        if (currentQuestion == null){
            return false;
        }
        
        return currentQuestion.checkAnswer(answer);
    }
}