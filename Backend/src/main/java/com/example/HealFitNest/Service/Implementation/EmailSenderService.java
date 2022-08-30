package com.example.HealFitNest.Service.Implementation;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Model.*;
import com.example.HealFitNest.Repository.UserRepo;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepo userRepo;



        @Autowired
        Configuration fmConfiguration;
    public void sendEmail (String toEmail, String subject,String userId,List<CartItem> cartItems,String orderId,String totalPrice) {
            MimeMessage mimeMessage =javaMailSender.createMimeMessage();
            try {

                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                mimeMessageHelper.setSubject(subject);
                mimeMessageHelper.setFrom("upcurveteamH12");
                mimeMessageHelper.setTo(toEmail);
               int length=cartItems.size();
                Date date =new Date();
             //   body.setContent(getContentFromTemplate(body.getModel()));
               // mimeMessageHelper.setText(body.getContent(), true);
                StringBuffer sb = new StringBuffer("<html><body>");
                sb.append("<h1>Congratulations!  Your Order has been placed</h1>\n" +
                        "<h3>Your order Id is "+ orderId+" </h3>\n" +
                        "<p>Date of order placed: "+date+"</p>\n" +
                        "<p>Items are:</p>\n");
                sb.append("<table style=\"border-color:white\">\n" +
                        "    <tr>\n" +
                        "<th style=\"margin:10px;padding:5px\">Item Name</th>" +
                        "<th style=\"margin:10px;padding:5px\">Item Quantity</th>" +
                        "<th style=\"margin:10px;padding:5px\">Item Price</th>" +
                        "      \n" +
                        "    </tr>");
                for (int i= 0; i<length; i++){
                    sb.append("<tr>\n" +
                            "        <td style=\"margin:10px;padding:5px\">");
                    sb.append(cartItems.get(i).getItemName()+"\n");
                    sb.append("</td><td style=\"margin:10px;padding:5px\">");
                    sb.append(cartItems.get(i).getItemQuantity());

                    sb.append("</td><td style=\"margin:10px;pdding:5px\">");
                    sb.append("Rs."+cartItems.get(i).getItemPrice());
                    sb.append("</td></tr>");
                }
                sb.append("</table><br>");
                sb.append("<strong>Total Price = </strong><strong>Rs."+totalPrice+"</strong>");
                sb.append("<hr style=\"width:30%;align:center;border:1 px solid black\">\n" +
                        "<footer>\n" +
                        "    <p style=\"color:grey\"><i>From <br> HealFitNest Team</i></p>\n" +
                        "\n" +
                        "</footer>");
                sb.append("</body></html>");
                mimeMessageHelper.setText("Plain message", sb.toString() );


                javaMailSender.send(mimeMessageHelper.getMimeMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

//        public String getContentFromTemplate(Map< String, Object > model)     {
//            StringBuffer content = new StringBuffer();
//
//            try {
//                content.append(FreeMarkerTemplateUtils.processTemplateIntoString(fmConfiguration.getTemplate("email-template.flth"), model));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return content.toString();
//        }
//
//
//    public MailBody sendBody(String userId,List<CartItem> cartItems,String orderId){
//        Users users=userRepo.findById(userId).orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
//        String firstName=users.getFirstName();
//        MailBody body=new MailBody();
//        int length=cartItems.size();
//        Map<String, Object> templateData = new HashMap<>();
//        templateData.put("firstName",firstName);
//        templateData.put("length",length);
////       for(int i=0;i<length;i++){
////           templateData.put("item"+(i+1)+"Name",cartItems.get(i).getItemName());
////           templateData.put("item"+(i+1)+"Quantity",String.valueOf(cartItems.get(i).getItemQuantity()));
////           templateData.put("item"+(i+1)+"Price",cartItems.get(i).getItemPrice().toString());
////       }
//
////   templateData.put("cartItems",cartItems.toString());
//        templateData.put("orderId",orderId);
//        Date date =new Date();
//
//        templateData.put("date",date.toString());
//        body.setModel(templateData);
//
//        return body;
//    }
}