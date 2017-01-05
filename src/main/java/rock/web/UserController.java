package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
