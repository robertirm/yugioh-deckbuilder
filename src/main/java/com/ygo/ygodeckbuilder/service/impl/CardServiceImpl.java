package com.ygo.ygodeckbuilder.service.impl;

import com.ygo.ygodeckbuilder.domain.Card;
import com.ygo.ygodeckbuilder.domain.Deck;
import com.ygo.ygodeckbuilder.repository.CardRepository;
import com.ygo.ygodeckbuilder.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private Deck currentDeck;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
        this.currentDeck = new Deck("new deck 1");
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardByCardId(Integer cardId) {
        return cardRepository.getCardByCardId(cardId);
    }

    @Override
    public Deck getCurrentDeck() {
        return currentDeck;
    }

    @Override
    public Page<Card> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,String searchFilter) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        if(searchFilter.equals(""))
            return this.cardRepository.findAll(pageable);
        else
            return this.cardRepository.findAllByCardNameContainingIgnoreCase(searchFilter,pageable);
    }

    @Override
    public void addCardToDeck(Integer cardId) {
        currentDeck.addCard(this.getCardByCardId(cardId));
    }

    @Override
    public void deleteCardFromDeck(Integer cardId) {
        currentDeck.deleteCard(cardId);
    }

    @Override
    public void clearDeck() {
        this.currentDeck.getCardList().clear();
    }

    @Override
    public void setDeckName(String name) {
        this.currentDeck.setDeckName(name);
    }

    @Override
    public String getDeckAsYdkFile() {
        return currentDeck.getExportValueAsYdkFile();
    }

    @Override
    public Card getRandomCard() {
        long quantity = cardRepository.count();
        int index = (int)(Math.random() * quantity);

        Page<Card> cardPage = cardRepository.findAll(PageRequest.of(index,1));

        Card card = null;
        if(cardPage.hasContent()){
            card = cardPage.getContent().get(0);
        }

        return card;
    }

}
