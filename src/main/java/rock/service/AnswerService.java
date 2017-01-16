package rock.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.util.SessionUtil;

@Service
public class AnswerService {
	
	@Autowired
	private QuestionService questionService;

	@Autowired
	private AnswerRepository answerRepository;

	public void addAnswer(long questionId, String contents, HttpSession session) {
		answerRepository.save(new Answer(questionService.findQuestion(questionId), SessionUtil.getLoginUser(session), contents));
	}

	public Answer findAnswer(long id) {
		return answerRepository.findOne(id);
	}

	public void delete(long id, HttpSession session) {
		if(!findAnswer(id).isWriter(SessionUtil.getLoginUser(session))) {
			throw new IllegalStateException("Only writer can del answer.");
		}
		
		answerRepository.delete(id);
	}
	
	
}
