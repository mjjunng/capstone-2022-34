package com.capstone.momomeal.domain;

import com.capstone.momomeal.domain.Status.RateStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "members")
public class Members {
    @Id
    @GeneratedValue
    @Column(name = "user_id", nullable = false)
    private Long user_id;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String pwd;
    @NotNull
    @Column(name = "real_name")
    private String realName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "img_dc")
    private String img;

    @Column(name = "x_value")
    private double x_value;

    @Column(name = "y_value")
    private double y_value;

    @Column(name = "user_rate")
    private Float user_rate;

    private double x_value;
    private double y_value;
    @OneToMany(
            mappedBy = "member"
    )
    private List<JoinedChatRoom> joinedChatRooms = new ArrayList();
    @OneToMany(
            mappedBy = "member",
            cascade = {CascadeType.ALL}
    )
    private List<MemberReview> memberReview;
    @OneToMany(
            mappedBy = "member",
            cascade = {CascadeType.ALL}
    )
    private List<RecommendMenu> recommendMenu = new ArrayList();

    public Members() {
    }

    public void addMemberReview(MemberReview memberReview) {
        this.memberReview.add(memberReview);
        memberReview.setMember(this);
    }

    public void addRecommendMenu(RecommendMenu recommendMenu) {
        this.recommendMenu.add(recommendMenu);
        recommendMenu.setMember(this);
    }

    public static Members createMember(Members members, MemberReview... memberReview) {
        Members member = new Members();
        MemberReview[] var3 = memberReview;
        int var4 = memberReview.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            MemberReview review = var3[var5];
            member.addMemberReview(review);
        }

        return members;
    }

    public List<MemberReview> returnReview(String member_id) {
        List<MemberReview> resultList = new ArrayList();
        Iterator var3 = this.memberReview.iterator();

        while(var3.hasNext()) {
            MemberReview review = (MemberReview)var3.next();
            if (review.getMember().getEmail() == this.email) {
                resultList.add(review);
            }
        }

        return resultList;
    }

    public int TotalRate() {
        int default_rate = 50;
        Iterator var2 = this.memberReview.iterator();

        while(var2.hasNext()) {
            MemberReview data = (MemberReview)var2.next();
            if (data.getRate() == RateStatus.LIKE) {
                ++default_rate;
            } else {
                --default_rate;
            }
        }

        return default_rate;
    }

    public void deleteJoinChatRoomFromMember(JoinedChatRoom joinedChatRoom) {
        this.joinedChatRooms.remove(joinedChatRoom);
    }

    public boolean belongsToMember(JoinedChatRoom joinedChatRoom) {
        return this.joinedChatRooms.contains(joinedChatRoom);
    }

    public void setUser_id(final Long user_id) {
        this.user_id = user_id;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    public void setRealName(final String realName) {
        this.realName = realName;
    }

    public void setAge(final Integer age) {
        this.age = age;
    }

    public void setPhone_number(final String phone_number) {
        this.phone_number = phone_number;
    }

    public void setImg(final String img) {
        this.img = img;
    }

    public void setUser_rate(final Float user_rate) {
        this.user_rate = user_rate;
    }

    public void setX_value(double x_value) {this.x_value = x_value;}

    public void setY_value(double y_value) {this.y_value = y_value; }

    public void setJoinedChatRooms(final List<JoinedChatRoom> joinedChatRooms) {
        this.joinedChatRooms = joinedChatRooms;
    }

    public void setMemberReview(final List<MemberReview> memberReview) {
        this.memberReview = memberReview;
    }

    public void setRecommendMenu(final List<RecommendMenu> recommendMenu) {
        this.recommendMenu = recommendMenu;
    }

    public Long getUser_id() {
        return this.user_id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPwd() {
        return this.pwd;
    }

    public String getRealName() {
        return this.realName;
    }

    public Integer getAge() {
        return this.age;
    }

    public String getPhone_number() {
        return this.phone_number;
    }

    public String getImg() {
        return this.img;
    }

    public Float getUser_rate() {
        return this.user_rate;
    }

    public double getX_value() {return x_value; }

    public double getY_value() {return y_value; }

    public List<JoinedChatRoom> getJoinedChatRooms() {
        return this.joinedChatRooms;
    }

    public List<MemberReview> getMemberReview() {
        return this.memberReview;
    }

    public List<RecommendMenu> getRecommendMenu() {
        return this.recommendMenu;
    }

    public String toString() {
        Long var10000 = this.getUser_id();
        return "Members(user_id=" + var10000 + ", email=" + this.getEmail() + ", pwd=" + this.getPwd() + ", realName=" + this.getRealName() + ", age=" + this.getAge() + ", phone_number=" + this.getPhone_number() + ", img=" + this.getImg() + ", user_rate=" + this.getUser_rate() + ", joinedChatRooms=" + this.getJoinedChatRooms() + ", memberReview=" + this.getMemberReview() + ", recommendMenu=" + this.getRecommendMenu() + ")";
    }
}
