package com.example.HealFitNest.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Service.Implementation.CartServiceImp;
import com.example.HealFitNest.Service.Implementation.InventoryServiceImp;
import com.example.HealFitNest.Service.Implementation.ItemServiceImp;
import org.springframework.data.mongodb.core.MongoTemplate;

public class CartServiceTest {

    CartRepo cartRepo = mock(CartRepo.class);
    ItemService itemService = mock(ItemService.class);
    InventoryService inventService = mock(InventoryService.class);

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    CartServiceImp cartServiceImp;

    @InjectMocks
    ItemServiceImp itemServiceImp;

    @InjectMocks
    InventoryServiceImp inventoryServiceImp;

    @Mock
    MongoTemplate mongoTemplate;

    @Test
    public void createCart(){
        Cart cart = new Cart();
        cart.setCartId("123");
        CartItem cartItem1 = new CartItem("abc", "tealeaf", BigDecimal.valueOf(150), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem2 = new CartItem("def", "sugar", BigDecimal.valueOf(100), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart.setCartItems(cartItems);
        cart.setCountItem(4);
        cart.setTotalPrice(BigDecimal.valueOf(250));
        cart.setUserId("456");

        when(cartRepo.save(any())).thenReturn(cart);
        Cart cart2 = cartServiceImp.createCart(cart);
        assertEquals("123", cart2.getCartId());
    }

    @Test
    public void showCart(){
        List<Cart> carts = new ArrayList<>();
        Cart cart1 = new Cart();
        CartItem cartItem1 = new CartItem("abc", "tealeaf", BigDecimal.valueOf(150), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem2 = new CartItem("def", "sugar", BigDecimal.valueOf(100), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart1.setCartItems(cartItems);
        cart1.setCountItem(4);
        cart1.setTotalPrice(BigDecimal.valueOf(250));
        cart1.setUserId("123");

        Cart cart2 = new Cart();
        CartItem cartItem3 = new CartItem("ghi", "coffee", BigDecimal.valueOf(100), 1, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem4 = new CartItem("jkl", "water", BigDecimal.valueOf(50), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems2 = new ArrayList<CartItem>();
        cartItems2.add(cartItem3);
        cartItems2.add(cartItem4);
        cart2.setCartItems(cartItems2);
        cart1.setCartItems(cartItems);
        cart1.setCountItem(3);
        cart1.setTotalPrice(BigDecimal.valueOf(150));
        cart1.setUserId("456");

        carts.add(cart1);
        carts.add(cart2);
        when(cartRepo.findAll()).thenReturn(carts);
        List<Cart> cartsGet = cartServiceImp.showCart();
        assertEquals(2, cartsGet.size());
    }

    @Test
    public void showCartofId(){
        Cart cart = new Cart();
        cart.setCartId("123");
        CartItem cartItem1 = new CartItem("abc", "tealeaf", BigDecimal.valueOf(150), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem2 = new CartItem("def", "sugar", BigDecimal.valueOf(100), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart.setCartItems(cartItems);

        when(cartRepo.findById(any())).thenReturn(Optional.of(cart));
        Cart cart2 = cartServiceImp.showCartofId(cart.getCartId());
        assertEquals("123", cart2.getCartId());
    }

    @Test
    public void showCurrentStatusTest(){
        Cart cart = new Cart();
        cart.setCartId("123");
        cart.setUserId("UI1");
        CartItem cartItem1 = new CartItem("abc", "tealeaf", BigDecimal.valueOf(150), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem2 = new CartItem("def", "sugar", BigDecimal.valueOf(100), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart.setCartItems(cartItems);
        cart.setCartStatus(false);
        cart.setTotalPrice(BigDecimal.valueOf(20));
        cart.setCountItem(2);

        when(cartRepo.findById(any())).thenReturn(Optional.of(cart));
        String cartActive = cartServiceImp.showCurrentStatus(cart.getUserId());
        assertEquals("Cart does not exists.",cartActive);

    }

    @Test
    public void countItemTest(){
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setCartId("123");
        CartItem cartItem1 = new CartItem("abc", "tealeaf", BigDecimal.valueOf(150), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem2 = new CartItem("def", "sugar", BigDecimal.valueOf(100), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart.setCartItems(cartItems);
        cart.setCartStatus(true);
        cart.setTotalPrice(BigDecimal.valueOf(20));
        cart.setCountItem(2);


        when(cartRepo.findById(any())).thenReturn(Optional.of(cart));
        int cartItemCount = cartServiceImp.countItem(cart.getCartId());
        assertEquals(4,cartItemCount);
    }

    @Test
    public void totalPriceTest() {
        List<Cart> carts = new ArrayList<>();
        Cart cart = new Cart();
        cart.setCartId("123");
        CartItem cartItem1 = new CartItem("abc", "tealeaf", BigDecimal.valueOf(150), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        CartItem cartItem2 = new CartItem("def", "sugar", BigDecimal.valueOf(100), 2, "https://www.narayanahealth.org/blog/coconut-benefits/");
        List<CartItem> cartItems = new ArrayList<CartItem>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);
        cart.setCartItems(cartItems);
        cart.setCartStatus(true);
        cart.setTotalPrice(BigDecimal.valueOf(20));
        cart.setCountItem(2);


        when(cartRepo.findById(any())).thenReturn(Optional.of(cart));
        BigDecimal cartTotalPrice = cartServiceImp.totalPrice(cart.getCartId());
        assertEquals(BigDecimal.valueOf(500), cartTotalPrice);
    }
}