//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.capstone.momomeal.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RecommendCategory {
    @Id
    @GeneratedValue
    @Column(name = "recommend_category_id")
    private Long id;

    @OneToOne(mappedBy = "recommendCategory")
    private Members members;

    private int cntChicken;
    private int cntPizza;
    private int cntKorean;
    private int cntChinese;
    private int cntJapanese;
    private int cntWestern;
    private int cntSnackbar;
    private int cntMidnightSnack;
    private int cntBoiledPork;
    private int cntCafeAndDesert;
    private int cntFastfood;
    private int cnEtc;

    public RecommendCategory(Members members) {
        this.members = members;
        this.cntChicken = 0;
        this.cntPizza = 0;
        this.cntKorean = 0;
        this.cntChinese = 0;
        this.cntJapanese = 0;
        this.cntWestern = 0;
        this.cntSnackbar = 0;
        this.cntMidnightSnack = 0;
        this.cntBoiledPork = 0;
        this.cntCafeAndDesert = 0;
        this.cntFastfood = 0;
        this.cnEtc = 0;
    }
}
