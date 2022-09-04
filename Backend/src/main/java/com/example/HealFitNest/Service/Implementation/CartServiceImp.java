package com.example.HealFitNest.Service.Implementation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.HealFitNest.Model.Inventory;
import com.example.HealFitNest.Repository.InventoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.HealFitNest.Handler.CartNotFoundException;
import com.example.HealFitNest.Handler.ItemNotFoundException;
import com.example.HealFitNest.Handler.UserNotFoundException;
import com.example.HealFitNest.Model.Cart;
import com.example.HealFitNest.Model.CartItem;
import com.example.HealFitNest.Model.Item;
import com.example.HealFitNest.Repository.CartRepo;
import com.example.HealFitNest.Repository.UserRepo;
import com.example.HealFitNest.Service.CartService;
import com.example.HealFitNest.Service.InventoryService;
import com.example.HealFitNest.Service.ItemService;

@Service
public class CartServiceImp implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ItemService itemService;

    @Autowired
    private InventoryService inventService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    //Saving the cart into the database
    public Cart createCart(Cart cart){
        return cartRepo.save(cart);
    }

    //Adding first item in the cart of a particular cart id
    public void addFirstItem(String userId, String cartId, String itemId, int quantity){
        Item item = itemService.findItemById(itemId);
        userRepo.findById(userId).orElseThrow(()-> new UserNotFoundException("User not found"));
        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
        List<CartItem> addCartItem =  new ArrayList<CartItem>();
        Inventory inventory= inventoryRepo.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item does not exists"));
        if(item.getItemAvailable()&&quantity<=inventory.getAmountPresent()){


            CartItem cartItem = new CartItem(itemId, item.getItemName(), item.getItemPrice(), quantity, item.getItemImage());
            addCartItem.add(cartItem);
            cart.setCartId(cartId);
            cart.setCartItems(addCartItem);
            cart.setUserId(userId);
            cartRepo.save(cart);
            int count = countItem(cartId);
            cart.setCountItem(count);
            BigDecimal total = totalPrice(cartId);
            cart.setTotalPrice(total);
            cartRepo.save(cart);


//            inventService.amountVariation(itemId, quantity);
//            boolean avail = inventService.itemAvailability(itemId);
//            item.setItemAvailable(avail);
//            itemService.saveItem(item);


        } else {
            throw new ItemNotFoundException("Sufficient amount of this item is not present.");
        }
    }

    //Add other items than first item in a particular cart
    public void addItem(String cartId, String itemId, int quantity) {
        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> new CartNotFoundException("Cart not found"));
        Item item = itemService.findItemById(itemId);
      //  userRepo.findById(cart.getUserId()).orElseThrow(()-> new UserNotFoundException("User not found"));
        boolean present = cartRepo.findById(cartId).isPresent();
        List<CartItem> addCartItem =  new ArrayList<CartItem>();
        if(present){
            System.out.println(present);
            Cart preCart = showCartofId(cartId);
            List<CartItem> previousCartItem = preCart.getCartItems();
            addCartItem.addAll(previousCartItem);
        }
        Inventory inventory= inventoryRepo.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item does not exists"));
        if(item.getItemAvailable()&&quantity<=inventory.getAmountPresent()){

            CartItem cartItem = new CartItem(itemId, item.getItemName(), item.getItemPrice(), quantity, item.getItemImage());
            addCartItem.add(cartItem);
            cart.setCartItems(addCartItem);
            cartRepo.save(cart);
            int count = countItem(cartId);
            cart.setCountItem(count);
            BigDecimal total = totalPrice(cartId);
            cart.setTotalPrice(total);
            cartRepo.save(cart);




//            inventService.amountVariation(itemId, quantity);
//            boolean avail = inventService.itemAvailability(itemId);
//            item.setItemAvailable(avail);
//            itemService.saveItem(item);
//



        } else {
            throw new ItemNotFoundException("Sufficient amount of this item is not present.");
        }
    }
    //Finding all carts present in the cart table
    public List<Cart> showCart(){
        return cartRepo.findAll();
    }

    //Finding the cart with all its details of a particular cart id
    public Cart showCartofId(String cartId){
        return cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart does not exists."));
    }

    //Counting the no of items present in a particular cart Id
    public int countItem(String cartId){
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart does not exist."));
        List<CartItem> cartItems = cart.getCartItems();
        int count = 0;
        for(CartItem eachCartItem : cartItems){
            count = count + eachCartItem.getItemQuantity();
        }
        return count;
    }

    //Giving the total price of all the cartitems of a particular cart id
    public BigDecimal totalPrice(String cartId){
        Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Cart does not exists."));
        List<CartItem> cartItems = cart.getCartItems();
        BigDecimal price = new BigDecimal(0);
        for(CartItem eachCartItem : cartItems){
            price = price.add(eachCartItem.getItemPrice().multiply(BigDecimal.valueOf(eachCartItem.getItemQuantity())));
        }
        return price;
    }

    //Removing all the items from the cart
    public String clearCart(String cartId){
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.clear();

        cart.setCartItems(cartItems);

        cart.setCountItem(0);
        cart.setTotalPrice(null);
        cart.setCartStatus(true);

        cartRepo.save(cart);
        return cartId;
    }

    //Deleting a single item from the cart
    public void removeItem(String cartId, String itemId){
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart does not exsists."));;
        List<CartItem> cartItems = cart.getCartItems();
        for(CartItem eachCartItem : cartItems){
            int index  = cartItems.indexOf(eachCartItem);
            if(eachCartItem.getItemId().equals(itemId)){
              //  int removeIndex = index;
                int quant = eachCartItem.getItemQuantity();
                cartItems.remove(index);

                cart.setCartItems(cartItems);

                cartRepo.save(cart);
                int count = countItem(cartId);
                cart.setCountItem(count);
                BigDecimal total = totalPrice(cartId);
                cart.setTotalPrice(total);
                cartRepo.save(cart);
                break;

//                inventService.amountVariation(itemId, (-quant));


            }
        }
    }

    //Increasing item quantity by one in the cart
    public void updateItemQuantityAdd(String cartId, String itemId){
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart does not exsist."));
        Item item = itemService.findItemById(itemId);
        List<CartItem> cartItems = cart.getCartItems();
        int updateIndex = 0;
        for(CartItem eachCartItem : cartItems){
            int index  = cartItems.indexOf(eachCartItem);
            if(eachCartItem.getItemId().equals(itemId)){
                updateIndex = index;
                break;
            }
        }
        CartItem eachCartItem = cartItems.get(updateIndex);
        Inventory inventory= inventoryRepo.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item does not exists"));
        if(item.getItemAvailable()&&1<=inventory.getAmountPresent()){
            int quantity = eachCartItem.getItemQuantity() + 1;
            eachCartItem.setItemQuantity(quantity);
            cartItems.set(updateIndex,eachCartItem);
            cart.setCartItems(cartItems);
            cartRepo.save(cart);
            int count = countItem(cartId);
            cart.setCountItem(count);
            BigDecimal total = totalPrice(cartId);
            cart.setTotalPrice(total);
            cartRepo.save(cart);



//            inventService.amountVariation(itemId,1);
//            boolean avail = inventService.itemAvailability(itemId);
//            item.setItemAvailable(avail);
//            itemService.saveItem(item);
//


        } else {
            throw new ItemNotFoundException("Inventory does not contain sufficient amount.");
        }
    }

    //Decreasing item quantity by 1 in the cart
    public void updateItemQuantitySub(String cartId, String itemId){
        Cart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new CartNotFoundException("Cart does not exsist."));
        Item item = itemService.findItemById(itemId);
        List<CartItem> cartItems = cart.getCartItems();
        int updateIndex = 0;
        for(CartItem eachCartItem : cartItems){
            int index  = cartItems.indexOf(eachCartItem);
            if(eachCartItem.getItemId().equalsIgnoreCase(itemId)){
                updateIndex = index;
                break;
            }
        }
        CartItem eachCartItem = cartItems.get(updateIndex);
       // if(item.getItemAvailable()){
            if(eachCartItem.getItemQuantity()>0){
                int quantity = eachCartItem.getItemQuantity() - 1;
                eachCartItem.setItemQuantity(quantity);
                cartItems.set(updateIndex,eachCartItem);
                cart.setCartItems(cartItems);
                cartRepo.save(cart);
                int count = countItem(cartId);
                cart.setCountItem(count);
                BigDecimal total = totalPrice(cartId);
                cart.setTotalPrice(total);
                cartRepo.save(cart);



//                inventService.amountVariation(itemId, -1);
//                boolean avail = inventService.itemAvailability(itemId);
//                item.setItemAvailable(avail);
//                itemService.saveItem(item);




            } else {
                throw new ItemNotFoundException("Quantity cannot be reduced further.");
            }
        //} else {
         //   throw new ItemNotFoundException("Inventory does not contain sufficient amount.");
       // }
    }

    //Show the status of the cart either it is active or not
    public String showCurrentStatus(String userId) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("userId").is(userId));
//        List<Cart> cartList = mongoTemplate.find(query, Cart.class);
//        for(Cart eachCart : cartList){
//            if(eachCart.isCartStatus())
//                return eachCart.getCartId();
//        }
        Cart cart=cartRepo.findByUserId(userId);
        if(cart!=null){
            return cart.getCartId();
        }
        return "Cart does not exists.";
    }


//    public void updateItemQuantity(String cartId, String itemId, int quantity){
//        Cart cart = cartRepo.findById(cartId)
//                .orElseThrow(() -> new CartNotFoundException("Cart does not exsist."));
//        Item item = itemService.findItemById(itemId);
//        List<CartItem> cartItems = cart.getCartItems();
//        int updateIndex = 0;
//        for(CartItem eachCartItem : cartItems){
//            int index  = cartItems.indexOf(eachCartItem);
//            if(eachCartItem.getItemId().equalsIgnoreCase(itemId)){
//                updateIndex = index;
//            }
//        }
//        CartItem eachCartItem = cartItems.get(updateIndex);
//        if(item.getItemAvailable()){
//            eachCartItem.setItemQuantity(quantity);
//            cartRepo.save(cart);
//            int count = countItem(cartId);
//            cart.setCountItem(count);
//            BigDecimal total = totalPrice(cartId);
//            cart.setTotalPrice(total);
//            cartRepo.save(cart);
//            inventService.amountVariation(itemId, quantity);
//            boolean avail = inventService.itemAvailability(itemId);
//            item.setItemAvailable(avail);
//            itemService.saveItem(item);
//        } else {
//            throw new ItemNotFoundException("Inventory does not contain sufficient amount.");
//        }
//    }




}