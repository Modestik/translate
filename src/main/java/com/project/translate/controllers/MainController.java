package com.project.translate.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.translate.model.db.Dictionary;
import com.project.translate.servers.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class MainController {

    @Autowired
    TranslateService translateService;

    @RequestMapping(value = "/translate", method = POST)
    public ResponseEntity<String> create(final @RequestBody JsonNode textForDictionary) {
        return translateService.translate(textForDictionary.findValue("text").asText());
    }
}
