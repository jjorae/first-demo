package rock.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.util.SessionUtil;

@Service
public class QuestionService {

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public Question findQuestion(long id) {
		return questionRepository.findOne(id);
	}

	public void save(Question question, HttpSession session) {
		if(!SessionUtil.isLogined(session)) {
			throw new IllegalStateException("Just login user can write question.");
		}
		
		question.setWriter(SessionUtil.getLoginUser(session));
		
		questionRepository.save(question);
	}

	public List<Question> findQuestions() {
		return questionRepository.findByDeleted(0);
	}
	
	public void delete(long id, HttpSession session) {
		if(!SessionUtil.isLogined(session)) {
			throw new IllegalStateException("Just login user can delete question.");
		}
		
		Question question = findQuestion(id);
		
		if(question.delete(SessionUtil.getLoginUser(session))) {
			questionRepository.save(question);
		}
	}

}
