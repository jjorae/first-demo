package rock.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home(String name, Integer age, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "home";
	}
	
}
