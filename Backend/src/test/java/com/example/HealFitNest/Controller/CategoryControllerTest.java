//package com.example.HealFitNest.Controller;
//
//import com.example.HealFitNest.Model.Category;
//import com.example.HealFitNest.Model.Item;
//import com.example.HealFitNest.Service.CategoryService;
//import com.example.HealFitNest.Service.ItemService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.coyote.Response;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.util.Assert;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class CategoryControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    CategoryService categoryService;
//
//    @MockBean
//    ItemService itemService;
//
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    @Test
//    public void displayItemInASubCategoryTest() throws Exception{
//        List<Category> categories = new ArrayList<>();
//        Category category = new Category();
//        List<String> subCat = new ArrayList<>();
//        List<String> subName = new ArrayList<>();
//        subCat.add("SCI22");
//        subCat.add("SCI2");
//        subName.add("Fruits and Vegetables");
//        subName.add("Snacks");
//        category.setCategoryId("CI1");
//        category.setSubCategoryId(subCat);
//        category.setCategoryName("Ready to eat");
//        category.setSubCategoryName(subName);
//        categories.add(category);
//
//        List<Item> items = new ArrayList<>();
//        Item item = new Item();
//        item.setSubCategoryId("SCI22");
//        item.setCategoryId("CI1");
//        item.setItemName("Mango");
//        item.setItemId("II1");
//        item.setItemDescription("Juicy and tasty");
//        item.setItemPrice(BigDecimal.valueOf(30));
//        item.setItemAvailable(true);
//        item.setItemImage("avx");
//        items.add(item);
//        Mockito.when(categoryService.findAllCategories()).thenReturn(categories);
//        Mockito.when(itemService.getAllItem("SCI22")).thenReturn(items);
////
////        String jsonConverted = mapper.writeValueAsString(category);
//
//        mockMvc.perform(get("/categories/{CategoryName}/{SubCategoryName}")
//                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
//
////        String resultInString = result.getResponse().getContentAsString();
////
////        Response response = mapper.readValue(resultInString, Response.class);
//
//
//
//    }
//}
