package com.project.translate.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.translate.model.ry.RYStatus;
import com.project.translate.model.ry.ResponseYandex;
import com.project.translate.model.ry.Translate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResponseUtils {

    private RestTemplate restTemplate;

    public ResponseUtils() {
        restTemplate = new RestTemplate();
    }

    public ResponseYandex getResponseYandex(String text) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String urlDic = UrlUtils.getTranslateURL(text, 0);
            String responseStrDic = postForObject(urlDic);
            JsonNode jsonDic = objectMapper.readTree(responseStrDic);
            ResponseYandex responseYandexDic = jsonNodeToResponseYandexDictionary(jsonDic);
            if (responseYandexDic == null) {
                String urlTran = UrlUtils.getTranslateURL(text, 1);
                String responseStrTran = postForObject(urlTran);
                JsonNode jsonTran = objectMapper.readTree(responseStrTran);
                return jsonNodeToResponseYandexTranslate(jsonTran, text);
            }
            return responseYandexDic;
        } catch (Exception e) {
            return null;
        }
    }

    private String postForObject(String url) {
        HttpEntity<String> entity = new HttpEntity<>(new HttpHeaders());
        try {
            return restTemplate.postForObject(url, entity, String.class);
        } catch (Exception e) {
            return null;
        }
    }

    private ResponseYandex jsonNodeToResponseYandexDictionary(JsonNode json) {
        JsonNode def = json.findValue("def");
        List<Translate> listTr = new ArrayList<>();
        if (def.size() <= 2) {
            return null;
        }
        for (JsonNode defItem : def
        ) {
            String pos = defItem.findValue("pos").asText();
            JsonNode tr = defItem.findValue("tr");
            for (JsonNode trItem : tr) {
                String word = trItem.get("text").asText();
                listTr.add(new Translate(pos, word));
            }
        }
        ResponseYandex responseYandex = new ResponseYandex();
        responseYandex.setTranscription(json.findValue("ts").asText());
        responseYandex.setTranslate(listTr);
        responseYandex.setStatus(RYStatus.WORD);
        return responseYandex;
    }

    private ResponseYandex jsonNodeToResponseYandexTranslate(JsonNode json, String text) {
        ResponseYandex responseYandex = new ResponseYandex();
        JsonNode textJSON = json.findValue("text");
        List<Translate> listTr = new ArrayList<>();
        for (JsonNode textItem : textJSON) {
            String translate = textItem.asText();
            if (!translate.equals(text)) {
                listTr.add(new Translate(null, textItem.asText()));
            }
        }
        if (listTr.size() == 0) {
            responseYandex.setStatus(RYStatus.NO);
        } else {
            responseYandex.setTranslate(listTr);
            responseYandex.setStatus(RYStatus.PHRASE);
        }
        return responseYandex;
    }
}


