package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import rock.service.QuestionService;

@Controller
public class HomeController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("questions", questionService.findQuestions());
		
		return "index";
	}
	
	@GetMapping("/home")
	public String home(String name, Integer age, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "home";
	}
	
}
