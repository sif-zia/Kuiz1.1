package com.example.kuiz11;

import java.util.Objects;

public class Question {
    private String statement;
    private String[] options;
    private String answer;

    public Question(String statement, String[] options, String answer) {
        this.statement = statement;
        this.options = options;
        this.answer = answer;
    }

    boolean checkAnswer(String givenAnswer) {
        return Objects.equals(givenAnswer, answer);
    }

    public String getStatement() {
        return statement;
    }

    public String[] getOptions() {
        return options;
    }

    public String getAnswer() {
        return answer;
    }
}