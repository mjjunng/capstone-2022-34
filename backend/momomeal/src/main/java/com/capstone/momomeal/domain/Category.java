//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

public enum Category {
    CHICKEN("Chicken"),
    PIZZA("Pizza"),
    KOREAN("Korean"),
    CHINESE("Chinese"),
    JAPANESE("Japanese"),
    WESTERN("Western"),
    SNACKBAR("Snackbar"),
    MIDNIGHTSNACK("MidnightSnack"),
    BOILEDPORK("BoiledPork"),
    CAFE("CafeAndDesert"),
    FASTFOOD("Fastfood"),
    ETC("Etc");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
