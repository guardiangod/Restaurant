package com.ryan.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ryan.app.common.CommonException;
import com.ryan.app.dao.RestaurantDAO;
import com.ryan.app.dto.RegistrationTO;
import com.ryan.app.dto.UserSessionTO;
import com.ryan.app.model.Member;

@Service
public class LoginService {
	
	@Autowired
	private RestaurantDAO restaurantDAO;

	@Transactional
	public UserSessionTO login(UserSessionTO credentials) throws CommonException {
		Member member = restaurantDAO.getMemberRecord(credentials.getUserName(),
				credentials.getUserPwd());
		if (member == null) {
			throw new CommonException("Username/password is incorrect");
		}

		UserSessionTO userTO = UserSessionTO.createUser(member);
		return userTO;
	}

	@Transactional
	public String registerUser(RegistrationTO registrationTo) throws CommonException {
		Member member = registrationTo.getMember();
		String userName = member.getUserId();

		boolean userExists = restaurantDAO.doesUserExists(userName);
		if (userExists) {
			throw new CommonException(
					"Username already exists. Please choose a different user name.");
		}

		restaurantDAO.insertMember(member);

		return userName;
	}

	@Transactional
	public Member getUserBillingInfo(String userName) throws CommonException {
		Member member = restaurantDAO.getMemberBillingRecord(userName);

		return member;
	}
}
