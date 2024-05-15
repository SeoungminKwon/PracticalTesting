package sample.cafekiosk.spring.api.service.product.response;

import lombok.Builder;
import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingType;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponse {

    private Long id;
    private String productNumber;
    private ProductType type;
    private ProductSellingType sellingType;
    private String name;
    private int price;

    /**
     * Builder 어노테이션을 특정 생성자에 붙히면, 해당 생성자에 맡는 빌더가 만들어짐
     * 클래스에 Builder를 붙히면, 모든 필드를 요구하는 빌더가 만들어짐
     */
    @Builder
    public ProductResponse(Long id, String productNumber, ProductType type, ProductSellingType sellingType, String name,
                           int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingType = sellingType;
        this.name = name;
        this.price = price;
    }

    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                       .id(product.getId())
                       .productNumber(product.getProductNumber())
                       .type(product.getType())
                       .sellingType(product.getSellingType())
                       .name(product.getName())
                       .price(product.getPrice())
                       .build();
    }
}
