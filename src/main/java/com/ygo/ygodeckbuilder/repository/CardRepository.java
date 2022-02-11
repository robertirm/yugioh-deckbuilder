package com.ygo.ygodeckbuilder.repository;

import com.ygo.ygodeckbuilder.domain.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Integer> {
    Card getCardByCardId(Integer cardId);
    Page<Card> findAllByCardNameContainingIgnoreCase(String text, Pageable pageable);
}
