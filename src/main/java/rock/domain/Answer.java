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
	
	public Answer() {}
	
	public Answer(Question question, User writer, String contents) {
		super();
		this.question = question;
		this.writer = writer;
		this.contents = contents;
	}
	
	public Answer(long id, User writer, String contents) {
		super();
		this.id = id;
		this.writer = writer;
		this.contents = contents;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	public void printUserId() {
		System.out.println(this.writer.toString());
	}
	public boolean isWriter(User user) {
		return this.writer.matchUserId(user);
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
	}
	
}
