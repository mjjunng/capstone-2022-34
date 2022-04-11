
package com.capstone.momomeal.domain;

public class TransStringToEnum {
    /**
     *
     * @param categoryName 안드로이드에서 받은 카테고리 string값
     * @return enum으로 변환한 값
     */
    public Category transferStringToEnum(String categoryName){
        Category category;
        switch (categoryName){
            case "Chicken":
                category = Category.CHICKEN;
                break;
            case "Pizza":
                category = Category.PIZZA;
                break;
            case "Korean":
                category = Category.KOREAN;
                break;
            case "Chinese":
                category = Category.CHINESE;
                break;
            case "Japanese":
                category = Category.JAPANESE;
                break;
            case "Western":
                category = Category.WESTERN;
                break;
            case "Snackbar":
                category = Category.SNACKBAR;
                break;
            case "MidnightSnack":
                category = Category.MIDNIGHTSNACK;
                break;
            case "BoiledPork":
                category = Category.BOILEDPORK;
                break;
            case "CafeAndDesert":
                category = Category.CAFE;
                break;
            case "Fastfood":
                category = Category.FASTFOOD;
                break;
            default:
                category = Category.ETC;

                break;
        }
        return category;
    }
}
