package cmc.hackaton.server.entity.constant;

import lombok.Getter;

import java.util.List;

@Getter
public enum FoodCategory {

    JOKBAL_BOSSAM(List.of("족발", "보쌈")),      // 족발, 보쌈
    JJIM_TANG_JJIGAE(List.of()),   // 찜, 탕, 찌개
    JAPANESE(List.of()),           // 일식 (돈까스, 회, 일식)
    PIZZA(List.of()),              // 피자
    MEAT(List.of()),               // 고기, 구이
    WESTERN(List.of()),            // 양식
    CHICKEN(List.of()),            // 치킨
    CHINESE(List.of()),            // 중식
    ASIAN(List.of()),              // 아시안
    BAEKBAN_JUK_NOODLE(List.of()), // 백반, 죽, 국수
    BUNSIK(List.of()),             // 분식
    FASTFOOD(List.of());           // 패스트푸드

    private final List<String> menu;

    FoodCategory(List<String> menu) {
        this.menu = menu;
    }
}
