package com.project.translate.model.ry;

import lombok.Data;

@Data
public class Translate {
    private String pos;
    private String word;

    public Translate(String pos, String word) {
        this.pos = pos;
        this.word = word;
    }
}
