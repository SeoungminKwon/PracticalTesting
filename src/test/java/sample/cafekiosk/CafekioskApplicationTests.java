package sample.cafekiosk;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sample.cafekiosk.unit.CafeKiosk;
import sample.cafekiosk.unit.beverage.Americano;


class CafekioskApplicationTests {

    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수 : " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료 : " + cafeKiosk.getBeverages().get(0).getName());

    }

    /*** 해당 테스트의 문제점 - 수동화된 테스트
     *  1. 최종 단계에 사람이 개입해야하는 문제점 (자동화된 테스트가 아님)
     *  2. 다른 사람이 해당 테스트를 봤을 때, 어떤게 맞는건지 어떤게 틀린건지 알 수 없음 - 무조건 성공하는 테스트
     */

}
