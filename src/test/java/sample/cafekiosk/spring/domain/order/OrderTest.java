package sample.cafekiosk.spring.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductSellingType.SELLING;

import java.time.LocalDateTime;
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
        Order order = Order.create(products, LocalDateTime.now());

        //then
        assertThat(order.getTotalPrice()).isEqualTo(3000);

    }

    @DisplayName("주문 생성 시 주문 상태는 INIT이다. ")
    @Test
    public void init(){
        //given
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("002", 2000)
        );

        //when
        Order order = Order.create(products, LocalDateTime.now());

        //then
        assertThat(order.getOrderStatus()).isEqualByComparingTo(OrderStatus.INIT); //isEqualByComparingTo : Enum 그 자체로 값을 비교

    }
    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    @Test
    public void registeredDateTime(){
        //given
        LocalDateTime registeredDateTime = LocalDateTime.now();//LocalDateTime.now()가 중요 데이터여서 변수로 추출해서 관리
        List<Product> products = List.of(
                createProduct("001", 1000),
                createProduct("001", 2000)
        );
        //when
        Order order = Order.create(products, registeredDateTime);

        //then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);

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
