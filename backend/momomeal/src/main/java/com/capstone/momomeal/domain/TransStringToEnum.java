//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

public class TransStringToEnum {
    public TransStringToEnum() {
    }

    public Category transferStringToEnum(String categoryName) {
        byte var4 = -1;
        switch(categoryName.hashCode()) {
            case -2041773788:
                if (categoryName.equals("Korean")) {
                    var4 = 2;
                }
                break;
            case -1884306027:
                if (categoryName.equals("Chicken")) {
                    var4 = 0;
                }
                break;
            case -1883983667:
                if (categoryName.equals("Chinese")) {
                    var4 = 3;
                }
                break;
            case -1390252750:
                if (categoryName.equals("Western")) {
                    var4 = 5;
                }
                break;
            case -688086063:
                if (categoryName.equals("Japanese")) {
                    var4 = 4;
                }
                break;
            case -484940793:
                if (categoryName.equals("BoiledPork")) {
                    var4 = 8;
                }
                break;
            case -367780034:
                if (categoryName.equals("MidnightSnack")) {
                    var4 = 7;
                }
                break;
            case 77130856:
                if (categoryName.equals("Pizza")) {
                    var4 = 1;
                }
                break;
            case 337268005:
                if (categoryName.equals("Snackbar")) {
                    var4 = 6;
                }
                break;
            case 1033812698:
                if (categoryName.equals("Fastfood")) {
                    var4 = 10;
                }
                break;
            case 1989309327:
                if (categoryName.equals("CafeAndDesert")) {
                    var4 = 9;
                }
        }

        Category category;
        switch(var4) {
            case 0:
                category = Category.CHICKEN;
                break;
            case 1:
                category = Category.PIZZA;
                break;
            case 2:
                category = Category.KOREAN;
                break;
            case 3:
                category = Category.CHINESE;
                break;
            case 4:
                category = Category.JAPANESE;
                break;
            case 5:
                category = Category.WESTERN;
                break;
            case 6:
                category = Category.SNACKBAR;
                break;
            case 7:
                category = Category.MIDNIGHTSNACK;
                break;
            case 8:
                category = Category.BOILEDPORK;
                break;
            case 9:
                category = Category.CAFE;
                break;
            case 10:
                category = Category.FASTFOOD;
                break;
            default:
                category = Category.ETC;
        }

        return category;
    }
}
