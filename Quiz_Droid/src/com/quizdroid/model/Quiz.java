package com.quizdroid.model;

import java.io.Serializable;
import java.util.ArrayList;

public  class Quiz implements Serializable {
	String quizId;
	String quizCode;
	ArrayList<QuizQuestion> questions;
	QuizQuestion activeQuestion;
	public QuizQuestion getActiveQuestion() {
		return activeQuestion;
	}
	public void setActiveQuestion(QuizQuestion activeQuestion) {
		this.activeQuestion = activeQuestion;
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getQuizCode() {
		return quizCode;
	}
	public void setQuizCode(String quizCode) {
		this.quizCode = quizCode;
	}
	public ArrayList<QuizQuestion> getQuestions() {
		return questions;
	}

	public void addQuestion(QuizQuestion question)
	{
		if (this.questions == null)
			this.questions  = new ArrayList<QuizQuestion>();
		this.questions.add(question);
	}


}
