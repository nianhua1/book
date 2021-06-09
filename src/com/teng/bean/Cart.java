package com.teng.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author
 * @create 2021-04-24 21:41
 */
public class Cart {
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();


    /**
     * 增加商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        CartItem item = items.get(cartItem.getId());
        if(item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount()+1);//数量累加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }

    }

    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }

    public void updateCount(Integer id,Integer count){
        // 先查看购物车中是否有此商品。如果有，修改商品数量，更新总金额
        CartItem item = items.get(id);
        if(item != null){
            item.setCount(count);//数量更新
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));//更新总金额
        }
    }













    public Integer getTotalCount() {
        Integer totalCount = 0;
        Set<Map.Entry<Integer, CartItem>> entrySet = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entrySet) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        Set<Map.Entry<Integer, CartItem>> entrySet = items.entrySet();
        for (Map.Entry<Integer, CartItem> entry : entrySet) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
