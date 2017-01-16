package rock.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {

	@Mock
	private QuestionRepository questionRepository;
	
	@Mock
	private AnswerRepository answerRepository;
	
	@InjectMocks
	private QuestionService questionService;
	
	@InjectMocks
	private AnswerService answerService;
	
	/*
	 * 로그인 사용자와 질문한 사람이 같아야 한다.
• 답변이 없는 경우 삭제가 가능하다.
• 질문자와 답변자가 같은 경우 삭제가 가능하다.
• 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.*/
	
	@Test
	public void checkLoginUser() {
		User loginUser = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(true, question.isWriter(loginUser));
	}
	
	@Test
	public void emptyAnswer() {
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User answer_writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(1L, answer_writer, "답"));
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(false, question.isEmptyAnswer());
	}
	
	@Test
	public void sameQuestionAnswerWriter() {
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User answer_writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(1L, answer_writer, "답"));
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(true, question.isAnswerUser());
	}
	
	@Test
	public void delete() {
		User loginUser = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User answer_writer = new User(2L, "test2", "test2", "테스트2", "test2@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(1L, answer_writer, "답"));
		answers.add(new Answer(2L, writer, "답"));
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(false, question.isDeletable(loginUser));
		
		if(question.isDeletable(loginUser)) {
			questionRepository.delete(1L);
			verify(questionRepository).delete(1L);
		}
	}
	
	@Test
	public void delete_diff_loginUser() {
		User loginUser = new User(2L, "test2", "test2", "테스트2", "test2@test.com");
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User answer_writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(1L, answer_writer, "답"));
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(false, question.isDeletable(loginUser));
		
		if(question.isDeletable(loginUser)) {
			questionRepository.delete(question);
			verify(questionRepository).delete(question);
		}
	}
	
	@Test
	public void delete_empty_answer() {
		User loginUser = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(true, question.isDeletable(loginUser));
		
		if(question.isDeletable(loginUser)) {
			questionRepository.delete(question);
			verify(questionRepository).delete(question);
		}
	}
	
	@Test
	public void delete_diff_answer_user() {
		User loginUser = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User writer = new User(1L, "test1", "test1", "테스트1", "test1@test.com");
		User answer_writer = new User(2L, "test2", "test2", "테스트2", "test2@test.com");
		
		List<Answer> answers = new ArrayList<Answer>();
		answers.add(new Answer(1L, answer_writer, "답"));
		
		Question newQuestion = new Question(1L, writer, "질문1", "질문 내용", answers);
		
		when(questionRepository.findOne(1L)).thenReturn(newQuestion);
		Question question = questionRepository.findOne(1L);
		
		assertEquals(false, question.isDeletable(loginUser));
		
		if(question.isDeletable(loginUser)) {
			questionRepository.delete(question);
			verify(questionRepository).delete(question);
		}
	}

}
