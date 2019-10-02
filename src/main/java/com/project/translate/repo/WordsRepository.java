package com.project.translate.repo;

import com.project.translate.model.db.Words;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordsRepository extends JpaRepository<Words, Integer> {
    long countByWordAndAcc(String word, String acc);
}
