package cmc.hackaton.server.entity.constant;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Getter
public enum FoodCategory {

    JOKBAL_BOSSAM(List.of("족발", "보쌈")),      // 족발, 보쌈
    JJIM_TANG_JJIGAE(List.of("부대찌개", "김치찌개", "아구찜", "순댓국")),   // 찜, 탕, 찌개
    JAPANESE(List.of("돈까스", "라멘", "초밥", "소바", "우동")),           // 일식 (돈까스, 회, 일식)
    PIZZA(List.of("하와이안 피자", "페퍼로니 피자", "시카고 피자")),              // 피자
    MEAT(List.of("삼겹살", "돼지갈비", "소갈비")),               // 고기, 구이
    WESTERN(List.of("스테이크", "파스타", "리조또")),            // 양식
    CHICKEN(List.of("치킨", "닭강정")),            // 치킨
    CHINESE(List.of("짜장면", "마라탕", "짬뽕", "꿔바로우", "양꼬치")),            // 중식
    ASIAN(List.of("팟타이", "쌀국수", "우육면")),              // 아시안
    BAEKBAN_JUK_NOODLE(List.of("국수", "죽", "냉면")), // 백반, 죽, 국수
    BUNSIK(List.of("떡볶이", "라면")),             // 분식
    FASTFOOD(List.of("햄버거", "샌드위치", "타코야끼"));           // 패스트푸드

    private final List<String> menu;

    public String getRandomMenu(FoodCategory foodCategory) {

//        return Arrays.stream(FoodCategory.values())
//                .filter(category -> category.equals(foodCategory))
//                .collect(Collectors.toList()).get(0).getMenu().get(0);
        List<String> menu1 = Arrays.stream(FoodCategory.values())
                .filter(category -> category.equals(foodCategory))
                .collect(Collectors.toList()).get(0).getMenu();
        Random rand = new Random();
        return menu1.get(rand.nextInt(menu1.size()));
    }

    FoodCategory(List<String> menu) {
        this.menu = menu;
    }
}