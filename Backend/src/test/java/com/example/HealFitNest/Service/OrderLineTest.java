//package com.example.HealFitNest.Service;
//
//import com.example.HealFitNest.Model.Order;
//import com.example.HealFitNest.Model.OrderLine;
//import com.example.HealFitNest.Repository.OrderLineRepo;
//import com.example.HealFitNest.Repository.OrderRepo;
//import com.example.HealFitNest.Service.Implementation.CartServiceImp;
//import com.example.HealFitNest.Service.Implementation.OrderLineServiceImp;
//import com.example.HealFitNest.Service.Implementation.OrderServiceImp;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class OrderLineTest {
//
//    OrderLineRepo orderLineRepo = mock(OrderLineRepo.class);
//    OrderRepo orderRepo = mock(OrderRepo.class);
//
//
//    @InjectMocks
//    OrderLineService orderLineService;
//
//    @Mock
//    OrderLineServiceImp orderLineServiceImp;
//
//    @InjectMocks
//    OrderService orderService;
//
//    @InjectMocks
//    CartService cartService;
//
//    @Test
//    public void addOrderLineByOrderIdTest(){
//        OrderLine orderLine = new OrderLine();
////        List<OrderLine> orderLines = new ArrayList<>();
//        orderLine.setOrderLineId("OLI1");
//        orderLine.setOrderId("OI1");
//        orderLine.setItemId("II1");
//        orderLine.setItemQuantity(100);
////        orderLines.add(orderLine);
//        when(orderLineRepo.save(orderLine)).thenReturn(orderLine);
//        assertEquals("Added",orderLineService.addOrderLineByOrderId(orderLine.getOrderId()));
//    }
//}
