package com.ryan.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ryan.app.common.CommonException;
import com.ryan.app.common.Constants;
import com.ryan.app.dto.RegistrationTO;
import com.ryan.app.dto.UserSessionTO;
import com.ryan.app.facade.RestaurantFacade;
import com.ryan.app.model.factory.ModelFactory;

@Controller
@SessionAttributes(Constants.SS_USER_INFO)
public class LoginController extends BaseController {

	@Autowired
	private RestaurantFacade restaurantFacade;

	@RequestMapping(value = "/showlogin", method = RequestMethod.GET)
	public String getLogin(Model model, SessionStatus status) throws CommonException {
		status.setComplete();
		model.addAttribute("userTo", new UserSessionTO());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String submitLogin(@ModelAttribute("memberAccount") UserSessionTO credentials, Model model)
			throws CommonException {
		UserSessionTO userDetailTO = restaurantFacade.getLoginService().login(credentials);

		model.addAttribute(Constants.SS_USER_INFO, userDetailTO);
		return "logout";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String getRegistration(Model model) throws CommonException {
		RegistrationTO registrationTo = new RegistrationTO(ModelFactory.getInstance().createMember(null,
				null));
		model.addAttribute("registrationTo", registrationTo);
		return "registermember";
	}

	@RequestMapping(value = "/newuser/submit", method = RequestMethod.POST)
	public @ResponseBody
	String submitRegistration(@ModelAttribute("registrationTo") RegistrationTO registrationTo)
			throws CommonException {
		String userName = restaurantFacade.getLoginService().registerUser(registrationTo);
		return "Thank you for registering with HPE Restaurant Service. Your username is "
				.concat(userName)
				.concat(". Please log in to write reviews about restaurants or register your own restaurant.");
	}

}
