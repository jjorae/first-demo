package rock.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;
	
	private String contents;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_question"))
	private Question question;
	
	public void setWriter(User writer) {
		this.writer = writer;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public boolean isWriter(User writer) {
		return writer.matchUserId(writer);
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
	}
	
}
