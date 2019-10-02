package com.project.translate.servers;

import com.project.translate.repo.DictionaryRepository;
import com.project.translate.repo.PhrasesRepository;
import com.project.translate.repo.WordsRepository;
import com.project.translate.model.db.Dictionary;
import com.project.translate.model.db.Phrases;
import com.project.translate.model.db.Words;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class DbService {

    private final DictionaryRepository dictionaryRepository;
    private final WordsRepository wordsRepository;
    private final PhrasesRepository phrasesRepository;

    @Autowired
    public DbService(DictionaryRepository dictionaryRepository,
                     WordsRepository wordsRepository,
                     PhrasesRepository phrasesRepository) {
        this.dictionaryRepository = dictionaryRepository;
        this.wordsRepository = wordsRepository;
        this.phrasesRepository = phrasesRepository;
    }


    void addWords(Words word) {
        wordsRepository.save(word);
    }

    void addPhrases(Phrases phrase) {
        phrasesRepository.save(phrase);
    }

    void addDictionary(Dictionary dictionary) {
        dictionaryRepository.save(dictionary);
    }

    boolean isNotExistsDictionary(String text) {
        return dictionaryRepository.countByText(text) == 0;
    }

    boolean isNotExistsWords(String word, String acc) {
        return wordsRepository.countByWordAndAcc(word, acc) == 0;
    }

    boolean isNotExistsPhrases(String phrase, String acc) {
        return phrasesRepository.countByPhraseAndAcc(phrase, acc) == 0;
    }
}
