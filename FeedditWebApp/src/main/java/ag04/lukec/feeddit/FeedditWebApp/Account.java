package ag04.lukec.feeddit.FeedditWebApp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String username;
	private String password;
	
	public Account() {
		this.username = null;
		this.password = null;
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getIdUsera() {
		return id;
	}

}
