package com.ygo.ygodeckbuilder.service;

import com.ygo.ygodeckbuilder.domain.Card;
import com.ygo.ygodeckbuilder.domain.Deck;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CardService {
    List<Card> getAllCards();
    Card getCardByCardId(Integer cardId);
    Deck getCurrentDeck();
    Page<Card> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,String searchFilter);
    void addCardToDeck(Integer cardId);
    void deleteCardFromDeck(Integer cardId);
    void clearDeck();
    void setDeckName(String name);
    String getDeckAsYdkFile();
    Card getRandomCard();
}
