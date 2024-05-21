package sample.cafekiosk.spring.api.service.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.order.request.OrderCreateRequest;
import sample.cafekiosk.spring.api.service.order.response.OrderResponse;
import sample.cafekiosk.spring.domain.order.Order;
import sample.cafekiosk.spring.domain.order.OrderRepository;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductType;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderResponse createOrder(OrderCreateRequest request, LocalDateTime registeredDateTime) {
        List<String> productNumbers = request.getProductNumbers();
        //Product
        List<Product> products = findProductsBy(productNumbers);

        // 재고 차감 체크가 필요한 상품들 필터
        products.stream()
                .filter(product -> ProductType.containsStockType(product.getType()))
                .collect(Collectors.toList());
        // 재고 엔티티 조회
        // 상품별 counting
        // 재고 차감 시도

        //Order
        Order order = Order.create(products, registeredDateTime);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder); //ID값이 DB에 저장된 이후에 PK값으로 발급받음
    }

    private List<Product> findProductsBy(List<String> productNumbers) {
        List<Product> products = productRepository.findALlByProductNumberIn(productNumbers); //productNumbers에 "001", "001"로 들어가도 IN절로 중복 제거가 됨
        Map<String, Product> productMap = products.stream()
                                               .collect(Collectors.toMap(Product::getProductNumber, p -> p));

        return productNumbers.stream()
                                                  .map(productMap::get)
                                                  .toList();
    }
}
