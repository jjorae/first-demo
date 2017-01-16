package rock.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rock.domain.User;
import rock.domain.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserService userService;
	
	@Test
	public void findOne() {
		User newUser = new User(1L, "a", "b", "c", "d");
		when(userRepository.findOne(1L)).thenReturn(newUser);
		User user = userService.findUser(1L);
		assertEquals(newUser, user);
	}
	
	@Test
	public void save() throws Exception {
		User newUser = new User(1L, "a", "b", "c", "d");
		userService.save(newUser);
		verify(userRepository).save(newUser);
	}

}
