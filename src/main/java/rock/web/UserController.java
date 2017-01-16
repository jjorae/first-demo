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
import rock.service.UserService;
import rock.util.SessionUtil;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public String join(User user) {
		userService.save(user);

		return "redirect:/users";
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("users", userService.findAll());

		return "user/list";
	}
	
	@GetMapping("/{id}/form")
	public String form(@PathVariable long id, Model model) {
		model.addAttribute("user", userService.findUser(id));
		
		return "user/updateForm";
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable long id, User user, HttpSession session) {
		if(!SessionUtil.isLogined(session)) {
			return "redirect:/users/login";
		}
		
		userService.updateUser(id, user, session);
		
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
		if(!userService.login(userId, password, session)) {
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
