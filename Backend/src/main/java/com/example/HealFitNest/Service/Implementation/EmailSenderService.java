package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Handler.OrderNotFoundException;
import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Repository.OrderRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.context.Context;
import java.text.SimpleDateFormat;  
import java.util.Date;  


import java.util.List;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private CartService cartService;


    // public void sendEmail(String toEmail,String subject,MailBody body){
    //     SimpleMailMessage message = new SimpleMailMessage();
    //     message.setFrom("upcurveteamH12");
    //     message.setTo(toEmail);
    //     message.setText(body.toString());
    //     message.setSubject(subject);

    //     message.setTo(toEmail);
    //     message.setSubject("Order Summary");

    //     Users user = userRepo.findByEmail(toEmail);
        
    //     final Context ctx = new Context(LocaleContextHolder.getLocale());
    //     ctx.setVariable("email", toEmail);
    //     ctx.setVariable("name", user.getFirstName()+user.getLastName());

    // final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

    // email.setText(htmlContent, true);

    //     javaMailSender.send(message);
    //     System.out.println("Mail sent successfully");
    // }

    public void sendHtmlMessage(String orderId, String subject, Users user, Address address) throws MessagingException{
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
        Context context = new Context();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();  
        System.out.println(formatter.format(date)); 
        Order order = orderRepo.findById(orderId).orElseThrow(()-> new OrderNotFoundException("Order does not exists.")); 
        Cart cart = cartService.showCartofId(order.getCartId());
        context.setVariable("email", user.getEmail());
        context.setVariable("orderId", orderId);
        context.setVariable("date", date);
        context.setVariable("addressLine1", address.getAddressLine1());
        context.setVariable("addressLine2", address.getAddressLine2());
        context.setVariable("city", address.getCity());
        context.setVariable("state", address.getState());
        context.setVariable("pin", address.getPostalCode());
        context.setVariable("firstname", user.getFirstName());
        context.setVariable("lastname", user.getLastName());
        context.setVariable("totalPrice", order.getTotalPrice());
        context.setVariable("items", cart.getCountItem());
        helper.setFrom("upcurveteamH12");
        helper.setTo(user.getEmail());
        helper.setSubject(subject);
        String html = templateEngine.process("EmailOrderSummary.html", context);
        helper.setText(html, true);

        // log.info("Sending email: {} with html body: {}", email, html);
        javaMailSender.send(message);
    }

    public MailBody sendBody(String userId,List<CartItem> cartItems,String orderId){
        Users users=userRepo.findById(userId).orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
        String firstName=users.getFirstName();
        MailBody body=new MailBody();
       body.setCartItems(cartItems);
       body.setOrderId(orderId);
        body.setMessage("Congratulations!"+firstName+"\n"+ "Your Order Summary is Ready");
        return body;
    }
}
