import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Order {
    Map<String, String[]> orderList = new LinkedHashMap<>(); // 장바구니 목록
    ArrayList<String[]> totalOrder = new ArrayList<String[]>(); // 총 판매상품 목록
    Scanner scan = new Scanner(System.in);
    private double totalPrice = 0.0; // 장바구니에 담긴 상품 총 금액
    private double salesAmount = 0.0; // 지금까지의 총 판매 금액

    //void addCart(String cName, double cPrice, String cDescription) {
    //    totalPrice += cPrice;
    //    orderList.put(cName, new String[]{String.valueOf(cPrice), cDescription}); // Map : 같은 상품(키 값)이면 add 되지 않는 문제 있음
    //}

    // 장바구니에 상품 더하기 (같은 상품이면 num + 1)
    void addCart(String cName, double cPrice, String cDescription) {
        totalPrice += cPrice;

        String num; // 주문 개수
        if (orderList.containsKey(cName)) { // 같은 키값을 가지고 있다면
            num = Integer.toString((Integer.valueOf(orderList.get(cName)[1]) + 1)); // num++
        } else {
            num = "1";
        }
        orderList.put(cName, new String[]{String.valueOf(cPrice), num, cDescription}); // num으로 주문한 상품 개수를 명시해서 장바구니 Map에 추가
    }

    // 장바구니에 목록 확인 후 담긴 상품 주문 여부 선택
    int orderCart() {
        int ch = 0;

        if (orderList.isEmpty()) { // 장바구니에 아무 항목도 없는 경우
            return ch;
        }

        System.out.println("아래와 같이 주문 하시겠습니까?\n\n[ Orders ]");
        orderList.forEach((key, value) -> {
            System.out.println(key + "\t| W " + value[0] + " | " + value[1] + "개 | " + value[2]);
        });
        System.out.println();
        System.out.println("[ Total ]\n");
        System.out.println("W " + (double) Math.round(totalPrice * 10) / 10.0 + "\n");
        System.out.println("1. 주문\t2. 메뉴판");
        while (true) {
            ch = scan.nextInt();
            if (ch == 1) {
                salesAmount += totalPrice; // 지금까지의 총 판매 금액에 장바구니에 담긴 상품 총 금액 더하기
                addTotalOrder(); // 총 판매상품 목록 totalOrder에 주문한 상품 추가
                break;
            } else if (ch == 2) {
                break;
            } else {
                System.out.println("'1' 또는 '2'를 입력해주세요.");
            }
        }
        return ch;
    }

    // 장바구니 비우기
    void clearCart() {
        orderList.clear();
        totalPrice = 0.0;
    }

    // 총 판매상품 목록에 주문한 상품 추가
    private void addTotalOrder() {
        orderList.forEach((key, value) -> {
            int count = Integer.parseInt(value[1]);
            for (int i = 0; i < count; i++) { // 장바구니의 상품 개수가 1개 이상이면 개수만큼 totalOrder에 추가
                totalOrder.add(new String[]{key, value[0]});
            }
        });
    }

    // 총 판매상품 목록 출력
    void printTotalOrder() {
        for (String[] strings : totalOrder) {
            System.out.println("- " + strings[0] + "\t| W " + Math.round(Double.parseDouble(strings[1])) * 10 / 10.0);
        }
        System.out.println();
    }

    public double getSalesAmount() {
        return salesAmount;
    }
}
