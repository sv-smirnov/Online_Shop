package com.geekbrains.spring.web.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@AllArgsConstructor
public class Cart {
    @Schema(description = "Покупки")
    private List<OrderItemDto> items;
    @Schema(description = "Общая сумма")
    private  int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(String cartName, CacheManager manager){
            this.items = new ArrayList<>();
            this.totalPrice = 0;
        }

    public boolean addProductCount(Long id){
        for(OrderItemDto o: items){
            if(o.getProductId().equals(id)){
                o.changeQuantity(1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void addProduct(ProductDto product){
        if(addProductCount(product.getId())){
            return;
        }
        items.add(new OrderItemDto(product));
        recalculate();
    }

    private void recalculate(){
        totalPrice = 0;
        for(OrderItemDto o: items){
            totalPrice += o.getPrice();
        }
    }

    public void removeProduct(Long id){
        items.removeIf(o -> o.getProductId().equals(id));
        recalculate();
    }

    public void decreaseProduct(Long id){
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()){
            OrderItemDto o = iter.next();
            if(o.getProductId().equals(id)){
                o.changeQuantity(-1);
                if(o.getQuantity() <= 0){
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public boolean decreaseProductCount(Long id){
        for(OrderItemDto o: items){
            if(o.getProductId().equals(id)){
                if (o.getQuantity() <= 0) removeProduct(id);
                else
                    o.changeQuantity(-1);
                recalculate();
                return true;
            }
        }
        return false;
    }

    public void clear(){
        items.clear();
        totalPrice = 0;
    }

    public void merge(Cart another) {
        for (OrderItemDto anotherItem : another.items) {
            boolean merged = false;
            for (OrderItemDto myItem : items) {
                if (myItem.getProductId().equals(anotherItem.getProductId())) {
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }
}
