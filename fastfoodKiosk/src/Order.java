import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Order {
    Map<String, String[]> orderList = new LinkedHashMap<>(); // 장바구니 상품 목록 저장 컬렉션
    ArrayList<String[]> totalOrder = new ArrayList<String[]>(); // 총 판매 상품 목록 저장 컬렉션
    Scanner scan = new Scanner(System.in);
    private double totalPrice = 0.0; // 장바구니에 담긴 상품 총 금액
    private double salesAmount = 0.0; // 지금까지의 총 판매 금액

    //void addCart(String cName, double cPrice, String cDescription) {
    //    totalPrice += cPrice;
    //    orderList.put(cName, new String[]{String.valueOf(cPrice), cDescription}); // Map : 같은 상품(키 값)이면 add 되지 않는 문제 있음
    //}

    // 선택한 상품을 장바구니에 추가
    void addCart(String cName, double cPrice, String cDescription) { // 매개변수 : 상품명, 상품 가격, 상품 설명
        totalPrice += cPrice; // 장바구니에 담긴 상품 총 금액 + 선택한 상품 가격

        String num; // 상품명 별 장바구니에 담겨 있는 개수
        if (orderList.containsKey(cName)) { // orderList에 cName과 동일한 키값이 있다면
            num = Integer.toString((Integer.valueOf(orderList.get(cName)[1]) + 1)); // 해당 키값의 value[1] 값인 num++
        } else { // 새로운 cName(키값)이 들어오면 num = 1
            num = "1";
        }
        orderList.put(cName, new String[]{String.valueOf(cPrice), num, cDescription}); // num으로 주문한 상품 개수를 명시해서 orderList 세팅
    }

    // 장바구니 목록 확인 후 상품 주문 여부 선택
    int orderCart() {
        int ch = 0;

        if (orderList.isEmpty()) { // 장바구니가 비어있는 경우
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
                addTotalOrder(); // 총 판매상품 목록 totalOrder에 주문한 상품을 추가
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

    // 총 판매상품 목록에 주문한 상품을 추가
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
