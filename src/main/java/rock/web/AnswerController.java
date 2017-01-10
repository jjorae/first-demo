package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;

@Controller
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("/questions/{questionId}/answers")
	public String add(@PathVariable long questionId, String contents, HttpSession session) {
		
		User user = (User)session.getAttribute("s_user");
		
		if(user == null) {
			throw new IllegalStateException("Only login user can write answer.");
		}
		
		Question question = questionRepository.findOne(questionId);
		
		Answer answer = new Answer();
		
		answer.setQuestion(question);
		answer.setWriter(user);
		answer.setContents(contents);
		
		answerRepository.save(answer);
		
		return "redirect:/questions/" + questionId;
	}
	
	@GetMapping("/questions/{questionId}/answers/{id}/del")
	public String del(@PathVariable long questionId, @PathVariable long id, HttpSession session) {
		User user = (User)session.getAttribute("s_user");
		
		if(user == null) {
			throw new IllegalStateException("Only login user can del answer.");
		}
		
		Answer answer = answerRepository.findOne(id);
		
		if(!answer.isWriter(user)) {
			throw new IllegalStateException("Only writer can del answer.");
		}
		
		answerRepository.delete(id);
		
		return "redirect:/questions/" + questionId;
	}

}
