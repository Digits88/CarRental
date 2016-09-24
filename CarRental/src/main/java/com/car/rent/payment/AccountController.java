package com.car.rent.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.car.rent.dao.PaymentDAO;
import com.car.rent.domain.Payment;

@Controller
public class AccountController {
	@Autowired
	PaymentDAO paymentDao;
//	@RequestMapping(value = "/")
//	public String home() {
//		return "index";
//	}

	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public void addAccount() {
		Payment payment = new Payment();
		
		payment.setPaymentType(payment.getPaymentType().CREDITCARD);
		paymentDao.save(payment);
	}

}
