package com.project.translate.model.ry;

import lombok.Data;

import java.util.List;

@Data
public class ResponseYandex {
    private String text;
    private String transcription;
    private List<Translate> translate;

    private RYStatus status;
}

