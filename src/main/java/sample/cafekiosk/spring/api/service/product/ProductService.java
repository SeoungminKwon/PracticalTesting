package sample.cafekiosk.spring.api.service.product;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingType;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findALlBySellingStatusIn(ProductSellingType.forDisplay());

        List<ProductResponse> collect = products.stream()
                                                .map(ProductResponse::of)
                                                .collect(Collectors.toList());
        return products.stream()
                       .map(ProductResponse::of)
                       .collect(Collectors.toList());
    }
}
