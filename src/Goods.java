import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Goods extends MainMenu {
    private String gName;
    private String gDescription;
    private double price;
    Map<Integer, String[]> goodsMenu = new LinkedHashMap<>();

    Goods(String gName){
        this.gName = gName;
    }

    // 선택한 메인 메뉴에 따라 상품 정보를 map에 추가
    private void setGoodsMap(){
        switch (gName) {
            case "Burgers":
                goodsMenu.put(1, new String[]{"ShackBurger", "6.9", "토마토, 양상추, 쉑소스가 토핑된 치즈버거"});
                goodsMenu.put(2, new String[]{"SmokeShack", "8.9", "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"});
                goodsMenu.put(3, new String[]{"Shroom Burger", "9.4", "몬스터 치즈와 체다 치즈로 속을 채운 베지테리안 버거"});
                goodsMenu.put(4, new String[]{"Cheeseburger", "6.9", "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"});
                goodsMenu.put(5, new String[]{"Hamburger", "5.4", "비프패티를 기반으로 야채가 들어간 기본버거"});
                break;
            case "Forzen Custard":
                goodsMenu.put(1, new String[]{"Classic Hand-Spun Shakes", "6.5", "쫀득하고 진한 커스터드가 들어간 클래식 쉐이크"});
                goodsMenu.put(2, new String[]{"Floats", "6.5", "부드러운 바닐라 커스터드와 톡톡 터지는 탄산이 만나 탄생한 색다른 음료"});
                goodsMenu.put(3, new String[]{"Cup & Cones", "5.4", "매일 점포에서 신선하게 제조하는 쫀득하고 진한 아이스크림"});
                break;
            case "Drinks":
                goodsMenu.put(1, new String[]{"Fountain Soda", "2.9", "코카콜라, 코카콜라 제로, 스프라이트, 환타 오렌지, 환타 그레이프, 환타 파인애플"});
                goodsMenu.put(2, new String[]{"Lemonade", "4.3", "매장에서 직접 만드는 상큼한 레몬에이드"});
                goodsMenu.put(3, new String[]{"Fifty/Fifty", "3.8", "레몬에이드와 유기농 홍차를 우려낸 아이스 티가 만나 탄생한 시그니처 음료"});
                break;
            case "Beer":
                goodsMenu.put(1, new String[]{"Abita Root Beer", "4.8", "청량감 있는 독특한 맥주"});
                break;
        }
    }

    // 상품 목록 출력
    void printGoods() {
        setGoodsMap();

        System.out.println("SHAKESHACK BURGER 에 오신걸 환영합니다.\n아래 상품메뉴판을 보시고 상품을 골라 입력해주세요.\n");
        System.out.println("[ " + gName + " MENU ]");

        AtomicInteger indexHolder = new AtomicInteger(); // 인덱스 출력 하기 위함
        goodsMenu.forEach((key, value) -> {
            System.out.println(key + ". " + value[0] + "\t| W " + value[1] + " | " + value[2]);
        });
        System.out.println();
    }

    // 상품 선택 메소드
    private String chooseGoods() {
        String n;
        while (true) {
            System.out.print("상품 선택 : ");
            int choose = scan.nextInt();

            for (int menu : goodsMenu.keySet()) {
                if (choose == menu) {
                    n = (String) goodsMenu.get(menu)[0];
                    price = Double.parseDouble(goodsMenu.get(menu)[1]); // 상품명(키값)에 매치 되는 value[0] 값 저장
                    gDescription = (String) goodsMenu.get(menu)[2];
                    return n;
                }
            }

            System.out.println("올바른 상품 번호를 입력해주세요.\n");
        }
    }

    // 선택한 상품을 장바구니에 추가할 것인지 체크
    int check(String n, Double p, String d){
        int ch;
        System.out.println(n + "\t| W " + p + " | " + d);
        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?\n1. 확인\t2. 취소");
        while (true){
            ch = scan.nextInt();
            if(ch == 1 || ch == 2){
                break;
            }
            else{
                System.out.println("'1' 또는 '2'를 입력해주세요.");
            }
        }
        return ch;
    }

    public void setGName() {
        gName = chooseGoods();
    }

    public String getGName() {
        return gName;
    }

    public Double getPrice(){
        return price;
    }

    public String getGDescription(){
        return gDescription;
    }
}
