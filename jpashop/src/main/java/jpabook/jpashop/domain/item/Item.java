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
@DiscriminatorColumn(name = "dtype") // 구분 컬럼
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name; // 이름
    private int price;
    private int stockQuantity; // 재고수량

    @ManyToMany
    @JoinTable(name = "category_item", joinColumns = @JoinColumn(name="category_id"),
    inverseJoinColumns = @JoinColumn(name="item_id"))
    private List<Category> categories = new ArrayList<>();

    /*
    * 비즈니스 로직
    * stockQuantity를 변경할 일이 있으면, setter 보다는 비즈니스 로직을 사용한하는 게 좋다.
    */

    /*
    * stock 증가
    */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /*
    * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;

        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }

        this.stockQuantity = restStock;
    }


}
