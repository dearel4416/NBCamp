import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(); // 메인메뉴 클래스
        Order order = new Order(); // 주문 클래스
        String mainMenuName;
        String mainMenuDct;
        String goodsName;
        double goodsPrice;
        String goodsDct;
        int turn = 0; // 대기 번호
        Scanner scan = new Scanner(System.in);

        while (true) {
            mainMenu.printMainMenu();
            mainMenu.setName(); // 메인메뉴 선택

            mainMenuName = mainMenu.getName();
            mainMenuDct = mainMenu.getDescription();

            // 상품 주문
            if (mainMenuName.equals("Order")) {
                int check1 = order.orderCart();
                if (check1 == 0) { // 장바구니가 비었다면
                    System.out.println("장바구니가 비어있습니다.\n");
                } else if (check1 == 2) { // 주문 하지 않고, 메인 메뉴로 돌아 가기
                    // do nothing
                } else { // 장바구니 주문
                    System.out.println("주문이 완료되었습니다!\n");
                    order.clearCart(); // 장바구니 비움
                    ++turn;
                    System.out.println("대기번호는 [ " + turn + " ] 번 입니다.");
                    System.out.println("(3초후 메뉴판으로 돌아갑니다.)");
                    try {
                        Thread.sleep(3000); // 3초 sleep
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } // 상품 취소
            else if (mainMenuName.equals("Cancel")) {
                System.out.println("진행하던 주문이 취소 되었습니다.\n");
                order.clearCart(); // 장바구니 비움
            } // 총 판매금액 조회
            else if(mainMenuName.equals("Total")) {
                System.out.println("[ 총 판매금액 현황 ]\n현재까지 총 판매된 금액은 [ W " + Math.round(order.getSalesAmount() * 10) / 10.0 + " ] 입니다.\n");
                while (true) {
                    System.out.println("1. 돌아가기 ('1' 입력)");
                    if (scan.nextInt() == 1) {
                        break;
                    }
                }
            } // 상품 선택
            else {
                while (true) {
                    Goods goods = new Goods(mainMenuName);
                    goods.printGoods();
                    goods.setGName(); // 상품 선택

                    goodsName = goods.getGName();
                    goodsDct = goods.getGDescription();
                    goodsPrice = goods.getPrice();

                    // 장바구니에 상품 추가 여부 확인
                    int check2 = goods.check(goodsName, goodsPrice, goodsDct);
                    if (check2 == 1) { // 추가 동의
                        System.out.println(mainMenuName + " 가 장바구니에 추가되었습니다.\n");
                        order.addCart(goodsName, goodsPrice, goodsDct);
                        break; // 메인 메뉴로 돌아감
                    } else { // 추가 취소
                        System.out.println();
                        break; // 메인 메뉴로 돌아감
                    }
                }
            }
        }
    }
}