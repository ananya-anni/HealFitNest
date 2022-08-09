package com.example.HealFitNest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Service.CartService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartControllerTest {
    private MockMvc mockMvc;

    @MockBean
    CartService cartService;

    @Test
    public void getAllCart(){
        String uri = "/cart";
        MvcResult mvcResult = mockMvc.perform(get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
   
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Cart[] cartList = super.mapFromJson(Cart[].class);
        assertTrue(cartList.length > 0);
    }
    

}
