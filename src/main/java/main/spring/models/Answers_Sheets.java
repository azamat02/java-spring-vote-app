package main.spring.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity(name="Answers_sheetsEntity")
@Table(name = "answers_sheets")
public class Answers_Sheets implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "blank_id")
    public int blank_id;

    @Column(name = "user_id")
    public int user_id;

    @Column(name = "q_id")
    public int q_id;

    @Column(name = "q_ans")
    public String q_ans;

    public Answers_Sheets(int blank_id, int user_id, int q_id, String q_ans) {
        this.blank_id = blank_id;
        this.user_id = user_id;
        this.q_id = q_id;
        this.q_ans = q_ans;
    }

    public Answers_Sheets() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBlank_id() {
        return blank_id;
    }

    public void setBlank_id(int blank_id) {
        this.blank_id = blank_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQ_id() {
        return q_id;
    }

    public void setQ_id(int q_id) {
        this.q_id = q_id;
    }

    public String getQ_ans() {
        return q_ans;
    }

    public void setQ_ans(String q_ans) {
        this.q_ans = q_ans;
    }
}
