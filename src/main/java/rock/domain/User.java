package rock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "uid", length = 20, nullable = false, unique = true)
	private String userId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	private String email;

	public void setId(long id) {
		this.id = id;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}

}
