package com.quizdroid.model;

import java.util.ArrayList;

public class QuizQuestion {	
	int questionId ;
	String questionName ; 
	QuizOption correctOption;
	ArrayList<QuizOption> answersOptions;
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public QuizOption getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(QuizOption correctOption) {
		this.correctOption = correctOption;
	}
	public ArrayList<QuizOption> getAnswersOptions() {
		return answersOptions;
	}
	public void addAnswerOption(QuizOption option)
	{
		if (this.answersOptions == null)
			this.answersOptions = new ArrayList<QuizOption>();
		this.answersOptions.add(option);
	}
}
