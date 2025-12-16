package com.ryan.app.dto;

import com.ryan.app.model.Member;

public class RegistrationTO {

	private Member member;
	
	public RegistrationTO() {
		
	}
	
	public RegistrationTO(Member member) {
		this.member = member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
	
	
}
