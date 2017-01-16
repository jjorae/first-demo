package rock.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.User;
import rock.domain.UserRepository;
import rock.util.SessionUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}
	
	public void updateUser(long id, User user, HttpSession session) {

		if(!SessionUtil.getLoginUser(session).matchUserId(user)) {
			throw new IllegalStateException("User id is not matched. You can't update another user's information.");
		}
		
		if(findUser(id).matchPassword(user)) {
			save(user);	
		}
		
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findUser(long id) {
		return userRepository.findOne(id);
	}

	public User findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}
	
	public boolean login(String userId, String password, HttpSession session) {
		User user = findByUserId(userId);

		User loginUser = new User(userId, password);
		
		if(user.matchPassword(loginUser)) {
			SessionUtil.setLoginUser(user, session);
			
			return true;
		}
		
		return false;
	}

}
