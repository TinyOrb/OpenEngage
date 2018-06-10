package xyz.tinyorb.hibernate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "getPasswordRequestCode",
	query = "CALL GetPasswordRequestCode(:username)",
	resultClass = PasswordRecoveryRequest.class
	)
})

@Entity
@Table(name="passwordchangerequest")
public class PasswordRecoveryRequest {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idChangeRequest")
	private int id;
	
	@Column(name="RequestCode")
	private String code;
	
	@Column(name="RequestedDate")
	private Timestamp RequestedDate;
	
	public Timestamp getRequestedDate() {
		return RequestedDate;
	}

	@Column(name="RequestedUser")
	private String user;
	
	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getCode() {
		return code;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Column(name="Requested")
	private boolean used;
	
}
