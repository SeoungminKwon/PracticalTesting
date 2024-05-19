package sample.cafekiosk.spring.domain.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static sample.cafekiosk.spring.domain.product.ProductSellingType.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test") //test 코드 실행시 TEST profile로 실행이됨
//@SpringBootTest //SpringBoot 를 띄워서 테스트하게 도와줌
@DataJpaTest //JPA 관련된 빈들만 띄워서 테스트를 진행함 -> SpringBootTest보다 속도가 빠름
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다. ")
    @Test
    public void findALlBySellingStatusIn(){
        //given
        Product product1 = Product.builder()
                                   .productNumber("001")
                                   .type(HANDMADE)
                                   .sellingStatus(SELLING)
                                   .name("아메리카노")
                                   .price(4000)
                                   .build();

        Product product2 = Product.builder()
                                   .productNumber("002")
                                   .type(HANDMADE)
                                   .sellingStatus(HOLD)
                                   .name("카페라떼")
                                   .price(4500)
                                   .build();

        Product product3 = Product.builder()
                                   .productNumber("003")
                                   .type(HANDMADE)
                                   .sellingStatus(STOP_SELLING)
                                   .name("팥빙수")
                                   .price(7000)
                                   .build();
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findALlBySellingStatusIn(List.of(SELLING, HOLD));

        //then

        //강사님은 리스트에 대한 검증을 이렇게 보통함
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus") //검증하고자 하는 필드만 추출가능
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );

        /**
         * containsExactly : 정확하게 순서까지 맞는지 검증
         * containsExactlyInAnyOrder : 순서 상관없이 확인을 해줌
         */
    }

    @DisplayName("상품 번호 리스트로 상품들을 조회한다. ")
    @Test
    public void findALlByProductNumberIn(){
        //given
        Product product1 = Product.builder()
                                   .productNumber("001")
                                   .type(HANDMADE)
                                   .sellingStatus(SELLING)
                                   .name("아메리카노")
                                   .price(4000)
                                   .build();

        Product product2 = Product.builder()
                                   .productNumber("002")
                                   .type(HANDMADE)
                                   .sellingStatus(HOLD)
                                   .name("카페라떼")
                                   .price(4500)
                                   .build();

        Product product3 = Product.builder()
                                   .productNumber("003")
                                   .type(HANDMADE)
                                   .sellingStatus(STOP_SELLING)
                                   .name("팥빙수")
                                   .price(7000)
                                   .build();
        productRepository.saveAll(List.of(product1, product2, product3));

        //when
        List<Product> products = productRepository.findALlByProductNumberIn(List.of("001", "002"));

        //then

        //강사님은 리스트에 대한 검증을 이렇게 보통함
        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus") //검증하고자 하는 필드만 추출가능
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "카페라떼", HOLD)
                );
    }
}
