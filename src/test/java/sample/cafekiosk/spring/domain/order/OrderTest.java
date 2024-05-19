package sample.cafekiosk.spring.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingType.SELLING;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductType;

class OrderTest {

    //Order 객체에 대한 단위 테스트
    @DisplayName("주문 생성 시 상품 리스트에서 주문의 총 금액을 계산한다.")
    @Test
    public void calculateTotalPrice(){
        //given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        //when
        Order order = Order.create(products);

        //then
        assertThat(order.getTotalPrice()).isEqualTo(3000);

    }


    //Builder로 Product를 만드니 너무 길다. -> 이런 걸 도와주는 메서드
    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                       .productNumber(productNumber)
                       .type(ProductType.HANDMADE)
                       .sellingStatus(SELLING)
                       .name("메뉴 이름")
                       .price(price)
                       .build();
    }

}
