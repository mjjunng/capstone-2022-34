package com.capstone.momomeal.domain;

import com.capstone.momomeal.domain.Status.RateStatus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "member_review"
)
public class MemberReview {
    @Id
    @GeneratedValue
    @NotNull
    @Column(
            name = "review_id"
    )
    private Long review_id;
    private String review_text;
    @Enumerated(EnumType.STRING)
    private RateStatus rate;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "user_id"
    )
    private Members member;

    public MemberReview() {
    }

    public void Like() {
        this.setRate(RateStatus.LIKE);
    }

    public void Bad() {
        this.setRate(RateStatus.BAD);
    }

    public Long getReview_id() {
        return this.review_id;
    }

    public String getReview_text() {
        return this.review_text;
    }

    public RateStatus getRate() {
        return this.rate;
    }

    public Members getMember() {
        return this.member;
    }

    public void setReview_id(final Long review_id) {
        this.review_id = review_id;
    }

    public void setReview_text(final String review_text) {
        this.review_text = review_text;
    }

    public void setRate(final RateStatus rate) {
        this.rate = rate;
    }

    public void setMember(final Members member) {
        this.member = member;
    }

    public String toString() {
        Long var10000 = this.getReview_id();
        return "MemberReview(review_id=" + var10000 + ", review_text=" + this.getReview_text() + ", rate=" + this.getRate() + ", member=" + this.getMember() + ")";
    }
}
