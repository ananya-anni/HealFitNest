package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.MailBody;
import com.example.HealFitNest.Model.Users;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserRepo userRepo;

    public void sendEmail(String toEmail, String subject, MailBody body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("upcurveteamH12");
        message.setTo(toEmail);
        message.setText(body.toString());
        message.setSubject(subject);

        javaMailSender.send(message);
        System.out.println("Mail sent successfully");
    }

    public MailBody sendBody(String userId, List<CartItem> cartItems, String orderId){
        Users users=userRepo.findById(userId).orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
        String firstName=users.getFirstName();
        MailBody body=new MailBody();
        body.setCartItems(cartItems);
        body.setOrderId(orderId);
        body.setMessage("Congratulations!"+firstName+"\n"+ "Your Order Summary is Ready");
        return body;
    }
}