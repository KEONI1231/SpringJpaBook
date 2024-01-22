package jpabook.jpashop.domain.item;


import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
//@Setter //@Setter를 쓰지 말고 비즈니스 메서드를 이요해서
@DiscriminatorColumn(name = "dtype")
public abstract class Item {
    //객체지향적으로 생각했을때 데이터를 가지고 있는 쪽에 비즈니스 메서드가 있는게 가장 좋다.
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public Item() {}

    // ======= 비즈니스 로직 ====== //
    /**
     * stock 증가
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int realStock = this.stockQuantity - quantity;
        if(realStock < 0 ) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = realStock;
    }
}
