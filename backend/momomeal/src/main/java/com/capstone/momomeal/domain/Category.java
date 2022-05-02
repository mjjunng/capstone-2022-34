
package com.capstone.momomeal.domain;

public enum Category {
    CHICKEN("Chicken"), PIZZA("Pizza"), KOREAN("Korean"),
    CHINESE("Chinese"), JAPANESE("Japanese"), WESTERN("Western"), SNACKBAR("Snackbar")
    , MIDNIGHTSNACK("MidnightSnack"), BOILEDPORK("BoiledPork"), CAFE("CafeAndDesert"),
    FASTFOOD("Fastfood"), ETC("Etc");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
