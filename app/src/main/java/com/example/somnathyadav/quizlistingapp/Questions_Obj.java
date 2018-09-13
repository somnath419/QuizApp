package com.example.somnathyadav.quizlistingapp;

/**
 * Created by Somnath Yadav on 11-09-2018.
 */

public class Questions_Obj {

    private int id;
    private String que;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String optionE;
    private String answer;

    public Questions_Obj(){
        id=0;
        que ="";
        optionA = "";
        optionB = "";
        optionC = "";
        optionE="";
        optionD="";
        answer = "";
    }

    public Questions_Obj(String question, String optionA, String optionB, String optionC,String optionD,String optionE,String answer){
        this.que = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD=  optionD;
        this.optionE=  optionE;
        this.answer = answer;
    }


    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOptA() {
        return optionA;
    }

    public String getOptC() {
        return optionC;
    }

    public String getOptB() {
        return optionB;
    }



    public String getOptD() {
        return optionD;
    }
    public String getOptE() {
        return optionE;
    }

    public String getQue() {
        return que;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQue(String que) {
        this.que = que;
    }

    public void setOptA(String optA) {
        this.optionA = optA;
    }

    public void setOptB(String optB) {
        this.optionB = optB;
    }

    public void setOptC(String optC) {
        this.optionC = optC;
    }
    public void setOptD(String optD) {
        this.optionD = optD;
    }
    public void setOptE(String optE) {
        this.optionE = optE;
    }



    public void setAnswer(String answer) {
        this.answer = answer;
    }


}
