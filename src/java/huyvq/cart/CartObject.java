/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huyvq.cart;

import huyvq.tblProducts.ProductDTO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Quoc Huy
 */
public class CartObject {
    private Map<String, ProductDTO> cart;

    public CartObject() {
    }

    public CartObject(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }

    public Map<String, ProductDTO> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductDTO> cart) {
        this.cart = cart;
    }
    
    public void add(ProductDTO list){
        if(this.cart == null){
            cart = new HashMap<>();
        }
        if(this.cart.containsKey(String.valueOf(list.getBookID()))){
            int quantity = this.cart.get(String.valueOf(list.getBookID())).getQuantity();
            list.setQuantity(quantity + list.getQuantity());
        }
        this.cart.put(String.valueOf(list.getBookID()), list);
    }
    
    public void delete(int id){
        if(this.cart == null){
            return;
        }
        if(this.cart.containsKey(String.valueOf(id))){
            this.cart.remove(String.valueOf(id));
//            if(cart.isEmpty()){
//                cart = null;
//            }
        }
    }
    public void update(int id, int quantity){
        if(this.cart == null){
            return;
        }
        if(this.cart.containsKey(String.valueOf(id))){
            this.cart.get(String.valueOf(id)).setQuantity(quantity);
        }
    }
    @Override
    public String toString() {
        return "CartObject{" + "cart=" + cart + '}';
    }
}
