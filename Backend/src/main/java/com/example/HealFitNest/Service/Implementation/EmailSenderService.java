//package com.example.HealFitNest.Service.Implementation;
//
//import com.example.HealFitNest.Model.MailBody;
//import com.example.HealFitNest.Model.Order;
//import com.example.HealFitNest.Service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailSenderService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    private OrderService orderService;

//    public void sendEmail(String toEmail,String subject,String body){
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setFrom("upcurvetarget@gmail.com");
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//
//        javaMailSender.send(message);
//        System.out.println("Mail sent successfully");
//    }
//
//    public String sendBody(String userId){
//        MailBody body=new MailBody();
//        Order order=orderService.showOrderByUserId(userId);
//
//    }
//}
