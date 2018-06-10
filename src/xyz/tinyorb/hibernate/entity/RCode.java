package xyz.tinyorb.hibernate.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;


@Entity
@Table(name="codetable")
public class RCode {
	@Id
	@Column(name="idCodeTable")
	private String Id;
	
	@Column(name="Code")
	private String cryptKey;
	
	@Column(name="GenerateTime")
	private Timestamp GT;
	
	@Column(name="ExpireTime")
	private Timestamp ET;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getCryptKey() {
		return cryptKey;
	}

	public void setCryptKey(String cryptKey) {
		this.cryptKey = cryptKey;
	}

	public Timestamp getGT() {
		return GT;
	}

	public void setGT(Timestamp gT) {
		GT = gT;
	}

	public Timestamp getET() {
		return ET;
	}

	public void setET(Timestamp eT) {
		ET = eT;
	}
	
	
	
}
