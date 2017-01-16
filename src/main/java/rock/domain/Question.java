package rock.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_writer"))
	private User writer;
	
	@Column(nullable = false)
	private String title;
	
	private String contents;
	
	@OneToMany(mappedBy = "question")
	private List<Answer> answers;
	
	@Column(columnDefinition = "int default 0")
	private int deleted;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_question_deleter"))
	private User deleter;
	
	private Date deleteDate;

	public Question() {}
	
	public Question(long id, User writer, String title, String contents, List<Answer> answers) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.answers = answers;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public boolean isWriter(User writer) {
		return this.writer.matchUserId(writer);
	}
	
	public boolean isEmptyAnswer() {
		return this.answers.size() < 1;
	}
	
	public boolean isAnswerUser() {
		for(Answer answer : answers) {
			if(!answer.isWriter(this.writer)) {
				return false;
			}
		}

		return true;
	}
	
	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}

	public boolean isDeletable(User loginUser) {
		if(!isWriter(loginUser)) {
			return false;
		}
		
		if(!isEmptyAnswer()) {
			return isAnswerUser();
		}
		
		return true;
	}
	
	public boolean delete(User loginUser) {
		if(!isDeletable(loginUser)) {
			return false;
		}
		
		this.deleter = loginUser;
		this.deleteDate = new Date();
		this.deleted = 1;
		
		return true;
	}

}
