package com.project.translate.servers;

import com.project.translate.model.db.Dictionary;
import com.project.translate.model.db.Phrases;
import com.project.translate.model.db.Words;
import com.project.translate.model.ry.RYStatus;
import com.project.translate.model.ry.ResponseYandex;
import com.project.translate.model.ry.Translate;
import com.project.translate.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TranslateService {

    @Autowired
    ResponseUtils responseUtils;
    @Autowired
    DbService dbService;

    public ResponseEntity<String> translate(String text) {
        ResponseYandex response = responseUtils.getResponseYandex(text);
        if (response.getStatus() != RYStatus.NO) {
            response.setText(text);
            int code = addInDB(response);
        }

        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }

    private int addInDB(ResponseYandex response) {
        String text = response.getText();
        if (response.getStatus() == RYStatus.WORD) {
            if (dbService.isNotExistsWords(text, "test user")) {
                dbService.addWords(new Words()
                        .setWord(text)
                        .setTranscription(response.getTranscription())
                        .setAcc("test user")
                );
            }
        }
        if (response.getStatus() == RYStatus.PHRASE) {
            if (dbService.isNotExistsPhrases(text, "test user")) {
                dbService.addPhrases(new Phrases()
                        .setPhrase(text)
                        .setAcc("test user")
                );
            }
        }
        if (dbService.isNotExistsDictionary(text)) {
            for (Translate tr : response.getTranslate()) {
                dbService.addDictionary(new Dictionary()
                        .setText(text)
                        .setPos(tr.getPos())
                        .setTranslate(tr.getWord())
                );
            }
        }
        return -1;
    }
}
