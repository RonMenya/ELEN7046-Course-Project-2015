package ac.wits.elen7046.surveyapplication.entities;


import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement
public class Question {

    /**
     * 
     */
    private long id;
    
    /**
     * 
     */
    private String text;
    private List<String> options;

    /**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	private QuestionType questionType;
    
    /**
     * 
     * @return 
     */
    public long getId() {
        return this.id;
    }

    /**
     * 
     * @param 
     */
    public void setText(String text) {
        if (text == null || text.length() == 0) {
            throw new RuntimeException("description can not be null or empty.");
        }
        
        this.text = text;
    }

    /**
     * 
     * @return 
     */
    public String getText() {
        return this.text;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }
    
    
    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    /**
     * 
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Question other = (Question) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.text == null) ? (other.text != null) : !this.text.equals(other.text)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", description=" + text + '}';
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getOptions() {
        return this.options;
    }
}