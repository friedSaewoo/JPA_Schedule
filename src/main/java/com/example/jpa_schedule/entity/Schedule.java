package com.example.jpa_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Entity
@Table(name = "schedule")
@Slf4j
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column
    private String title;

    @Column
    private String todo;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Schedule(){

    }

    public Schedule(String title, String todo, User user) {
        this.title = title;
        this.todo = todo;
        this.user = user;
    }

    public void updateTodo(String todo){
        this.todo = todo;
    }

}
