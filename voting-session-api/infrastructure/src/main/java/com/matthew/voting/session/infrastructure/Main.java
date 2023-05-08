package com.matthew.voting.session.infrastructure;

import com.matthew.voting.session.application.UseCase;

public class Main {

    public static void main(String[] args) {
        System.out.println(new UseCase().execute());
    }
}
