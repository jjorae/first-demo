package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/{id}")
	public String show(@PathVariable long id, Model model) {
		
		model.addAttribute("question", questionRepository.findOne(id));
		
		return "qna/show";
	}
	
	@GetMapping
	public String qna(HttpSession session) {
		User user = (User)session.getAttribute("s_user");
		
		if(user == null) {
			return "redirect:/login";
		}
		
		return "qna/form";
	}
	
	@PostMapping
	public String add(Question question, HttpSession session) {
		
		User user = (User)session.getAttribute("s_user");
		
		if(user == null) {
			throw new IllegalStateException("Only login user can write question.");
		}
		
		question.setWriter(user);
			
		questionRepository.save(question);
		
		return "redirect:/";
	}

}
