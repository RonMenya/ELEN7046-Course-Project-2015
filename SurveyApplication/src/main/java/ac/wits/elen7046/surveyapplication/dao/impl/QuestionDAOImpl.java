package ac.wits.elen7046.surveyapplication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ac.wits.elen7046.surveyapplication.dao.QuestionDAO;
import ac.wits.elen7046.surveyapplication.dao.connection.DBConnection;
import ac.wits.elen7046.surveyapplication.entities.Question;
import ac.wits.elen7046.surveyapplication.entities.QuestionType;
import ac.wits.elen7046.surveyapplication.factory.DAOFactory;

public class QuestionDAOImpl implements QuestionDAO {

	@Override
	public List<Question> getAll() {
		
		String selectAllQuestion = "SELECT q.id, q.q_text, q_type.q_type_text "
				+ "FROM QUESTION q, QUESTIONTYPE q_type "
				+ "WHERE q_type_fk = q_type.id";
		
		List<Question> list = new ArrayList<Question>();
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getDBConnection();
			statement = conn.createStatement(); 
			rs = statement.executeQuery(selectAllQuestion);
			
			while (rs.next()) {
				Question question = new Question();
				question.setId(rs.getLong(1));	
				question.setText(rs.getString(2));	
				question.setQuestionType(QuestionType.valueOf(rs.getString(3)));	
				list.add(question);
			}
		} catch (SQLException e) { 
			System.out.println(e.getMessage());			
		} finally { 
			
			DBConnection.closeConnection(statement, conn); 
		}
		
		return list;
	}
	
	

	@Override
	public boolean updateQuestion(Question question) {
		
		String updateQuestion = "UPDATE QUESTION SET q_text = ?, q_type_fk = ?"
				+ " WHERE id = ?";
		Connection conn = null;
		PreparedStatement statement = null;
	    boolean results = false;
		
		try {
			conn = DBConnection.getDBConnection();
			
			long questionTypeKey = getQuestionTypeId(question);
			
			statement = conn.prepareStatement(updateQuestion);
            statement.setString(1, question.getText());
            statement.setLong(2, questionTypeKey);
            statement.setLong(3, question.getId());
          
            if (statement.executeUpdate() < 1) {
	            throw new SQLException("Could not update the question ... " + question);
	        } else {
	        	results = true;
	        }
			
		} catch (SQLException e) { 
			System.out.println(e.getMessage());			
		} finally { 
			
			DBConnection.closeConnection(statement, conn); 
		}
		
		return results;

	}

	@Override
	public boolean addQuestion(Question question) {
		
		String addQuestion = "INSERT INTO QUESTION (q_text, q_type_fk) VALUES (?, ?)";
		Connection conn = null;
		PreparedStatement statement = null;
	    boolean results = false;
		
		try {
			conn = DBConnection.getDBConnection();
			
			long questionTypeKey = getQuestionTypeId(question);
			
			statement = conn.prepareStatement(addQuestion);
            //statement.setLong(1, question.getId());
            statement.setString(1, question.getText());
            statement.setLong(2, questionTypeKey);
          
            if (statement.executeUpdate() < 1) {
	            throw new SQLException("Could not insert the question... " + question);
	        } else {
	        	results = true;
	        }
			
		} catch (SQLException e) { 
			System.out.println(e.getMessage());			
		} finally { 
			
			DBConnection.closeConnection(statement, conn); 
		}
		
		return results;
	}
	
	private long getQuestionTypeId(Question question) {
		
		String getQuestionType = "SELECT id FROM QUESTIONTYPE WHERE q_type_text = ?";
		Connection conn = null;
		PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    long categoryKey = 0;
		
		try {
			conn = DBConnection.getDBConnection();			
			statement = conn.prepareStatement(getQuestionType);
	        statement.setString(1, question.getQuestionType().toString());
	        resultSet = statement.executeQuery();
	        
	        System.out.println("question text: " + question.getQuestionType().toString());
	        while (resultSet.next()) {
	        	categoryKey = resultSet.getLong(1);
			}
		} catch (SQLException e) { 
			System.out.println(e.getMessage());			
		} finally { 
			
			DBConnection.closeConnection(statement, conn); 
		}
		
		return categoryKey;
              
	}
	
	@Override
	public boolean deleteQuestion(long questionId) {
		
		String deleteQuestion = "DELETE FROM QUESTION WHERE id = ?";
		Connection conn = null;
		PreparedStatement statement = null;
	    boolean results = false;
		
		try {
			conn = DBConnection.getDBConnection();
			
			statement = conn.prepareStatement(deleteQuestion);
            statement.setLong(1, questionId);
          
            if (statement.executeUpdate() < 1) {
	            throw new SQLException("Could not update the question ... " + questionId);
	        } else {
	        	results = true;
	        }
			
		} catch (SQLException e) { 
			System.out.println(e.getMessage());			
		} finally { 
			
			DBConnection.closeConnection(statement, conn); 
		}
		
		return results;
	}

	@Override
	public boolean getQuestion(long questionId) {
		// to be implemented when the time arrives
		return false;
	}
	
	 public static void main(String[] args) {
	       
		 //testing getting questions
		 System.out.println("getting list of questions");
		 List<Question> questionList = DAOFactory.getQuestionDAO().getAll();
		 for (Question question : questionList) {
			 System.out.println(question.getId());
			 System.out.println(question.getText());
			 System.out.println(question.getQuestionType());
		 }

		 //testing getting category
		 System.out.println("getting category");
		 Question question1 = new Question();
		 question1.setQuestionType(QuestionType.MULTIPLE_CHOICE);
		 long catKey = new QuestionDAOImpl().getQuestionTypeId(question1);
		 System.out.println("Category Key: " + catKey);
		 
		 //testing updating question
		 Question question2 = new Question();
		 
		 question2.setId(new Long("1435001723279"));
		 question2.setText("This is my test question 1 updated refreshed");
		 question2.setQuestionType(QuestionType.YES_NO);
		 boolean response = DAOFactory.getQuestionDAO().updateQuestion(question2);
		 System.out.println("Response: " + response);
		 
		 //testing deleting a question
		 Question question3 = new Question();
		 question3.setId(new Long("1435001723279"));
		 boolean response1 = DAOFactory.getQuestionDAO().deleteQuestion(question3.getId());
		 System.out.println("Response: " + response1);
		 
//		 //testing adding question
		 System.out.println("adding questions");
		 Question question4 = new Question();
		 question4.setText("This is my test question 3");
		 question4.setQuestionType(QuestionType.YES_NO);
		 response = DAOFactory.getQuestionDAO().addQuestion(question4);
		 System.out.println("Response: " + response);
		 
		 
	    }



}
