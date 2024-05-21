package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTypeTest {
    /**
     * 이렇게 간단한것도 언제 변경될지 모름, -> 그래서 테스트를 작성해 미래 시점을 대비하는 것이 좋음
     */

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    public void containsStockType(){
        //given
        ProductType givenType = ProductType.HANDMADE;

        //when
        boolean result = ProductType.containsStockType(givenType);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    public void containsStockType2(){
        //given
        ProductType givenType = ProductType.BAKERY;

        //when
        boolean result = ProductType.containsStockType(givenType);

        //then
        assertThat(result).isTrue();
    }

}
