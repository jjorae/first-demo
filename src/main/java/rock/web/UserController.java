package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.User;
import rock.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@PostMapping
	public String join(User user) {

		System.out.println("User : " + user);
		userRepository.save(user);

		return "redirect:/users";

	}

	@GetMapping
	public String list(Model model) {

		model.addAttribute("users", userRepository.findAll());

		return "user/list";

	}
	
	@GetMapping("/{id}/form")
	public String form(@PathVariable long id, Model model) {
		model.addAttribute("user", userRepository.findOne(id));
		
		return "user/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable long id, User user) {
		System.out.println(user.toString());
		
		User exist_user = userRepository.findOne(id);
		
		if(user.getPassword().equals(exist_user.getPassword())) {
			userRepository.save(user);	
		}
		
		return "redirect:/users";
	}

}
