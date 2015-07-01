package ac.wits.elen7046.surveyapplication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;

import ac.wits.elen7046.surveyapplication.dao.SurveyDAO;
import ac.wits.elen7046.surveyapplication.dao.connection.DBConnection;
import ac.wits.elen7046.surveyapplication.entities.Survey;
import ac.wits.elen7046.surveyapplication.factory.DAOFactory;

public class SurveyDAOImpl implements SurveyDAO {

	@Override
	public boolean createSurvey(Survey survey) throws SQLException {
		
		String selectAllQuestion = "INSERT INTO SURVEY (name, version, location_fk, "
				+ "surveyor_fk, respondent_fk, question_fk, surveystatus_fk) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
		
		Connection conn = null;
		PreparedStatement statement = null;
	    boolean results = false;
		
		try {
			conn = DBConnection.getDBConnection();
			
		   // private MultivaluedHashMap<Long, List<String>> questionsToAnswersMapping = new MultivaluedHashMap<Long, List<String>>();

			//survey.getQuestionsToAnswersMapping();
			
			statement = conn.prepareStatement(selectAllQuestion);
            statement.setString(1, survey.getName());
            statement.setDouble(2, survey.getVersion());
            statement.setDouble(3, survey.getLocation().getId());
            statement.setDouble(4, survey.getSurveyor().getId());
            statement.setDouble(5, survey.getRespondent().getId());
            statement.setDouble(6, survey.getSurveyor().getId()); //getting the questions in here
            statement.setDouble(7, 0);
          
            if (statement.executeUpdate() < 1) {
	            throw new SQLException("Could not update the question ... " + survey);
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

}
