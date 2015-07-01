
package ac.wits.elen7046.surveyapplication.entities;


import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MultivaluedHashMap;



public class Survey {
   
    private long id;
    private String name;
    private double version;
    private Location location;
    private SurveyParticipant surveyor;
    private SurveyParticipant respondent;
    private List<Question> questions;
    private Date creationDate;
    private SurveyStatus surveyStatus;
    private MultivaluedHashMap<Long, List<String>> questionsToAnswersMapping = new MultivaluedHashMap<Long, List<String>>();
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVersion() {
        return this.version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public SurveyParticipant getSurveyor() {
        return this.surveyor;
    }

    public void setSurveyor(SurveyParticipant surveyor) {
        this.surveyor = surveyor;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
   
    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    public SurveyParticipant getRespondent() {
        return respondent;
    }

    public void setRespondent(SurveyParticipant respondent) {
        this.respondent = respondent;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public SurveyStatus getSurveyStatus() {
        return surveyStatus;
    }

    public void setSurveyStatus(SurveyStatus surveyStatus) {
        this.surveyStatus = surveyStatus;
    }

    public MultivaluedHashMap<Long, List<String>> getQuestionsToAnswersMapping() {
        return questionsToAnswersMapping;
    }

    public void setQuestionsToAnswersMapping(MultivaluedHashMap<Long, List<String>> questionsToAnswersMapping) {
        this.questionsToAnswersMapping = questionsToAnswersMapping;
    }
    
}
