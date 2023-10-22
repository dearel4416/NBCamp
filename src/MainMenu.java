import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MainMenu {
    Map<String, String> mainMenu = new LinkedHashMap<>(); // LinkedHashMap : 데이터를 추가한 순서 대로 출력됨
    Scanner scan = new Scanner(System.in);
    private String name = ""; // 메뉴 이름
    private String description = ""; // 메뉴 설명

    MainMenu() {
        mainMenu.put("Burgers", "앵거스 비프 통살을 다져만든 버거");
        mainMenu.put("Forzen Custard", "매장에서 신선하게 만드는 아이스크림");
        mainMenu.put("Drinks", "매장에서 직접 만드는 음료");
        mainMenu.put("Beer", "뉴욕 브루클린 브루어리에서 양조한 맥주");
    }

    // 메인 메뉴 목록 출력
    void printMainMenu() {
        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.\n아래 메뉴판을 보시고 메뉴를 골라 입력해주세요.\n");
        System.out.println("[ SHAKESHACK MENU ]");

        AtomicInteger indexHolder = new AtomicInteger(); // 인덱스 출력 하기 위함
        mainMenu.forEach((key, value) -> {
            System.out.println(indexHolder.getAndIncrement() + 1 + ". " + key + "\t| " + value);
        });

        System.out.println("\n[ ORDER MENU ]");
        System.out.println("5. Order       | 장바구니를 확인 후 주문합니다.");
        System.out.println("6. Cancel      | 진행중인 주문을 취소합니다.\n");
    }

    // 메뉴 선택 메소드
    private String chooseMenu() {
        while (true) {
            System.out.print("메뉴 선택 : ");
            String choose = scan.nextLine();
            System.out.println();

            // 메뉴명으로 입력한 경우
            for (String menu : mainMenu.keySet()) {
                if(choose.equals(menu)){
                    description = (String) mainMenu.get(menu); // 메뉴명(키값)에 매치 되는 value 값 저장
                    return choose;
                }
            }

            // 숫자로 입력 하거나 주문 옵션을 선택할 경우
            switch (choose) {
                case "1":
                    choose = "Burgers";
                    description = (String) mainMenu.get(choose);
                    return choose;
                case "2":
                    choose = "Forzen Custard";
                    description = (String) mainMenu.get(choose);
                    return choose;
                case "3":
                    choose = "Drinks";
                    description = (String) mainMenu.get(choose);
                    return choose;
                case "4":
                    choose = "Beer";
                    description = (String) mainMenu.get(choose);
                    return choose;
                case "5":
                    choose = "Order";
                case "Order":
                    return choose;
                case "6":
                    choose = "Cancel";
                case "Cancel":
                    return choose;
                default:
                    System.out.println("잘못된 입력입니다. 메뉴 번호나 이름을 입력해주세요.\n");
            }
        }
    }

    public void setName() {
        name = chooseMenu();
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }
}
