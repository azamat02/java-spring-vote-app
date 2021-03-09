package main.spring.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="QuesionEntity")
@Table(name = "questions")
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "question_text")
    public String question_text;

    @Column(name = "question_variant_a")
    public String question_variant_a;

    @Column(name = "question_variant_b")
    public String question_variant_b;

    @Column(name = "question_variant_c")
    public String question_variant_c;

    @Column(name = "blank_id")
    public int blank_id;

    public Question(String question_text, String question_variant_a, String question_variant_b, String question_variant_c, int blank_id) {
        this.question_text = question_text;
        this.question_variant_a = question_variant_a;
        this.question_variant_b = question_variant_b;
        this.question_variant_c = question_variant_c;
        this.blank_id = blank_id;
    }

    public Question() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public String getQuestion_variant_a() {
        return question_variant_a;
    }

    public void setQuestion_variant_a(String question_variant_a) {
        this.question_variant_a = question_variant_a;
    }

    public String getQuestion_variant_b() {
        return question_variant_b;
    }

    public void setQuestion_variant_b(String question_variant_b) {
        this.question_variant_b = question_variant_b;
    }

    public String getQuestion_variant_c() {
        return question_variant_c;
    }

    public void setQuestion_variant_c(String question_variant_c) {
        this.question_variant_c = question_variant_c;
    }

        public int getBlank_id() {
        return blank_id;
    }

    public void setBlank_id(int blank_id) {
        this.blank_id = blank_id;
    }
}
