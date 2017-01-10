package rock.web;

import javax.servlet.http.HttpSession;

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
	public String update(@PathVariable long id, User user, HttpSession session) {
		
		User s_user = (User)session.getAttribute("s_user");
		
		if(s_user == null) {
			return "redirect:/login";
		}
		
		if(!user.matchUserId(s_user)) {
			throw new IllegalStateException("User id is not matched. You can't update another user's information.");
		}
		
		User exist_user = userRepository.findOne(id);
		
		if(user.matchPassword(exist_user)) {
			userRepository.save(user);	
		}
		
		return "redirect:/users";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {

		User user = userRepository.findByUserId(userId);

		if(user.matchPassword(user)) {
			session.setAttribute("s_user", user);
		} else {
			return "/user/login_failed";
		}	
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("s_user");
		
		return "redirect:/";
	}

}
