package com.capstone.momomeal.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@Entity
@Table(name = "foods")
public class Foods {

    @NotNull
    @Id
    @Column(name = "restaurant", nullable = false)
    private String restaurant;

    @NotNull
    @Column(name = "userid")
    private String userid;

    @NotNull
    @Column(name = "userName")
    private String username;

    @Column(name = "foodRate")
    private float foodrate;

    @Column(name = "footrateText")
    private String foodratetext;

}
