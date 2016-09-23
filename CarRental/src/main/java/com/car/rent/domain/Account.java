package com.car.rent.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {
	@Id
	@GeneratedValue
	private Integer accountId;
	private String username;
	private String password;
	private Boolean active;
	private AccountType accountType;
	@OneToOne(mappedBy = "account")
	private Person personOwner;

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Person getPersonOwner() {
		return personOwner;
	}

	public void setPersonOwner(Person personOwner) {
		this.personOwner = personOwner;
	}

}
