package com.example.HealFitNest.Controller;


import com.example.HealFitNest.Service.Implementation.PayPalServiceImp;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.HealFitNest.Model.Order;

@RequestMapping("/api/v12")
@RestController
public class PayPalController {

        @Autowired
        PayPalServiceImp service;

        public static final String SUCCESS_URL = "pay/success";
        public static final String CANCEL_URL = "pay/cancel";

        @GetMapping("/")
        public String home() {
            return "Starting Paymment";
        }

        @PostMapping("/pay")
        public String payment(Order order)  {
            try{
                Payment payment=service.createPayment(order.getTotalPrice(),
                        "http://localhost:8989/api/v12" + CANCEL_URL,
                        "http://localhost:8989/api/v12" + SUCCESS_URL);
                for(Links link:payment.getLinks()) {
                    if (link.getRel().equals("approval_url")) {
                        return "redirect:" + link.getHref();
                    }
                }
            }
            catch (PayPalRESTException e) {

                e.printStackTrace();
            }
            return  "payment success";
        }


        @GetMapping( "/pay/cancel")
        public String cancelPay() {
            return "payment cancelled";
        }

        @GetMapping("pay/success")
        public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
            try {
                Payment payment = service.executePayment(paymentId, payerId);
                System.out.println(payment.toJSON());
                if (payment.getState().equals("approved")) {
                    return "successfull payment";
                }
            } catch (PayPalRESTException e) {
                System.out.println(e.getMessage());
            }
            return "";
        }


}

//order.getIntent(), order.getDescription(),
//"http://localhost:8989/api/v12" + CANCEL_URL,
//                        "http://localhost:8989/api/v12" + SUCCESS_URL