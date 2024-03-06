package com.civka.calculatordemo.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "feedback")
public class QuestionData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "theme")
    private String theme;

    @Column(name = "question")
    private String question;

    @Column(name = "answer")
    private String answer;

    @Column(name = "question_date")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate questionDate;

    @Column(name = "answer_date")
    private LocalDate answerDate;

    public QuestionData() {
        questionDate = LocalDate.now();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getQuestionDate() {
        return questionDate;
    }

    public String getFormattedQuestionDate() {
        return questionDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void setQuestionDate(LocalDate questionDate) {
        this.questionDate = questionDate;
    }

    public LocalDate getAnswerDate() {
        return answerDate;
    }

    public String getFormattedAnswerDate() {
        return answerDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public void setAnswerDate(LocalDate answerDate) {
        this.answerDate = answerDate;
    }

    @Override
    public String toString() {
        return "QuestionData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", theme='" + theme + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", questionDate=" + questionDate +
                ", answerDate=" + answerDate +
                '}';
    }
}
