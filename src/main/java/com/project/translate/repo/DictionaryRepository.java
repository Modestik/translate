package com.project.translate.repo;

import com.project.translate.model.db.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {
    long countByText(String text);
}