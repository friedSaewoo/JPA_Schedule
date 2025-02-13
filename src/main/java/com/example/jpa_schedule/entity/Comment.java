package com.example.jpa_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    public Comment() {
    }

    public Comment(String comment, User user, Schedule schedule) {
        this.comment = comment;
        this.user = user;
        this.schedule = schedule;
    }

    public void updateComment(String comment) {
        this.comment = comment;
    }
}
