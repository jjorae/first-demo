package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.service.AnswerService;
import rock.util.SessionUtil;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerService answerService;
	
	@PostMapping
	public String add(@PathVariable long questionId, String contents, HttpSession session) {
		if(!SessionUtil.isLogined(session)) {
			throw new IllegalStateException("Only login user can write answer.");
		}
		
		answerService.addAnswer(questionId, contents, session);
		
		return "redirect:/questions/" + questionId;
	}
	
	@DeleteMapping("/{id}")
	public String del(@PathVariable long questionId, @PathVariable long id, HttpSession session) {
		if(!SessionUtil.isLogined(session)) {
			throw new IllegalStateException("Only login user can del answer.");
		}
		
		answerService.delete(id, session);
		
		return "redirect:/questions/" + questionId;
	}

}
