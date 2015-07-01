/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.wits.elen7046.surveyapplication.services.impl;


import ac.wits.elen7046.surveyapplication.entities.Question;
import ac.wits.elen7046.surveyapplication.entities.QuestionType;
import ac.wits.elen7046.surveyapplication.factory.DAOFactory;
import ac.wits.elen7046.surveyapplication.services.QuestionsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionsServiceImpl implements QuestionsService{
    
    private Map<Long, Question> questions = questions = new HashMap<Long, Question>();
    
    public QuestionsServiceImpl() {

    }

    public List<Question> getAll() {
    	
    	return DAOFactory.getQuestionDAO().getAll();
    }

    public Question getQuestion(long questionId) {
        Question question = questions.get(questionId);
        System.out.println("XXXXXXXXXXXXXX: " + question.toString());
        return question;
    }

    public boolean addQuestion(Question question) {
    	boolean isQuestionAdded = DAOFactory.getQuestionDAO().addQuestion(question);
        System.out.println("Has question being added? " + isQuestionAdded);
        return isQuestionAdded;
                
    }

    public boolean updateQuestion(Question question) {
    	boolean isQuestionUpdated = false;
    	isQuestionUpdated = DAOFactory.getQuestionDAO().updateQuestion(question);
    	return isQuestionUpdated;
    }

    public void deleteQuestion(long questionId) {
    	DAOFactory.getQuestionDAO().deleteQuestion(questionId);
    	System.out.println("Question with id = " + questionId + " was deleted.");
    }
    
    public static void main(String[] args) {
        QuestionsService questionsService = new QuestionsServiceImpl();
        List<Question> questions = questionsService.getAll();
        System.out.println("" + questions);
    }
}
