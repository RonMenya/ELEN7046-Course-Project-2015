package ac.wits.elen7046.surveyapplication.restresources;


import ac.wits.elen7046.surveyapplication.entities.Question;
import ac.wits.elen7046.surveyapplication.services.QuestionsService;
import ac.wits.elen7046.surveyapplication.services.impl.QuestionsServiceImpl;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/questions")
@Singleton
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class QuestionsResource  {
    private static Logger logger = Logger.getLogger(QuestionsResource.class.getName());
    private QuestionsService questionsService = new QuestionsServiceImpl();
    
    @GET
    @Path("/getall")
    public List<Question> getAll() {
        logger.info("Getting all questions: " + questionsService.getAll());
        return questionsService.getAll();
    }
    
    @GET
    @Path("/question")
    public Question getQuestion(long questionId) {
        logger.info("Getting question with id: " + questionId);
        logger.info(questionsService.getQuestion(questionId).toString());
        return questionsService.getQuestion(questionId);
    }

    @POST
    @Path("/add")
    public boolean addQuestion(Question add) {
        logger.info("Adding question: " + add.getText());
        return questionsService.addQuestion(add);
    }

    @PUT
    @Path("/update")
    public boolean updateQuestion(Question question) {
        logger.info("updating question: " + question.getText() + " with id = " + question.getId());
        return questionsService.updateQuestion(question);
    }

    @DELETE
    @Path("/remove")
    public void deleteQuestion(String questionId) {
    	long id = Long.parseLong(questionId);
        logger.info("deleting question with id: " + id);
        questionsService.deleteQuestion(id);
    }
}
