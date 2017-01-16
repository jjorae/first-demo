package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Question;
import rock.service.QuestionService;
import rock.util.SessionUtil;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		model.addAttribute("question", questionService.findQuestion(id));
		
		return "qna/show";
	}
	
	@GetMapping
	public String qna(HttpSession session) {
		if(!SessionUtil.isLogined(session)) {
			return "redirect:/users/login";
		}
		
		return "qna/form";
	}
	
	@PostMapping
	public String add(Question question, HttpSession session) {
		questionService.save(question, session);
		
		return "redirect:/";
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable long id, HttpSession session) {
		questionService.delete(id, session);
		
		return "redirect:/";
	}

}
