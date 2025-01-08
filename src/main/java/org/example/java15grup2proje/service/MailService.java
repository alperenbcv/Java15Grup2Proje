package org.example.java15grup2proje.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@RequiredArgsConstructor
@CrossOrigin("*")
public class MailService {
	@Autowired
	JavaMailSenderImpl javaMailSenderImpl;
	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendManagerActivationMail(String alici, String activationToken){
		String activationUrl = "http://localhost:3000/activate?token=" + activationToken;
		String body="Please click the URL to activate your account="+activationUrl ;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(alici);
		message.setFrom("bilgejavamailsender@gmail.com");
		message.setSubject("Account Activation");
		message.setText(body);
		System.out.println(alici);
		javaMailSenderImpl.send(message);
	}
	
	public void sendEmployeeActivationMail(String alici, String activationToken){
		String activationUrl = "http://localhost:3000/activate-employee?token="+activationToken;
		String body = "Please click the URL and fill the necessary fields to activate your account="+activationUrl;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(alici);
		message.setSubject("Employee Account Activation");
		message.setText(body);
		javaMailSenderImpl.send(message);
	}
	
	public void sendPasswordRecoveryMail(String mail){
		String recoveryUrl = "http://localhost:3000/password-recovery?email="+mail;
		String body = "Please click the URL and recover your password="+recoveryUrl;
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail);
		message.setSubject("Password Recovery");
		message.setText(body);
		javaMailSenderImpl.send(message);
	}
	
	public void sendShiftNotificationMail(String mail){
		String body = "A shift has been assigned to you. You can check it's detail in the CoreHR Dashboard";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail);
		message.setSubject("Shift Information");
		message.setText(body);
		javaMailSenderImpl.send(message);
	}
}