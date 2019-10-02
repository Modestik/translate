package com.project.translate.repo;

import com.project.translate.model.db.Phrases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhrasesRepository extends JpaRepository<Phrases, Integer> {
    long countByPhraseAndAcc(String phrase, String acc);
}