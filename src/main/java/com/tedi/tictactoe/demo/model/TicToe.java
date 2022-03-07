package com.tedi.tictactoe.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicToe {
    X(1), O(2);

    private Integer value;

    TicToe(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return this.value;
    }
}
