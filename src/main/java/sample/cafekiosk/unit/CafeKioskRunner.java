package sample.cafekiosk.unit;

import java.time.LocalDateTime;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Latte;

public class CafeKioskRunner {
    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());
        System.out.println(">>>> 아메리카노 추가 ");

        cafeKiosk.add(new Latte());
        System.out.println(">>>> 라떼 추가 ");

        int totalPrice = cafeKiosk.calculateTotalPrice();
        System.out.println("총 주문가격 " + totalPrice);

        cafeKiosk.createOrder(LocalDateTime.now()); //production코드에서는 now를 넣어주고, testCode에서는 원하는 값을 넣어줌
    }

}
