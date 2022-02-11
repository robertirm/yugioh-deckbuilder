package com.ygo.ygodeckbuilder.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Deck {
    private String deckName;
    private final List<Card> cardList;

    public Deck(String deckName) {
        this.deckName = deckName;
        cardList = new ArrayList<>();
    }

    public String getDeckName() {
        return deckName;
    }

    public List<Card> getCardList() {
        cardList.sort(Comparator.comparing(Card::getCardType).thenComparing(Card::getCardName));
        return cardList;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void addCard(Card card){
        if(cardList.size() < 20 && cardList.stream().filter(x-> x.getCardId().equals(card.getCardId())).count()<3)
            cardList.add(card);
    }

    public void deleteCard(Integer cardId){
        for (var card : cardList){
            if(cardId.equals(card.getCardId())) {
                cardList.remove(card);
                break;
            }
        }
    }

    public String getExportValueAsYdkFile(){
        StringBuilder text = new StringBuilder("#main\n");
        for (Card card : cardList){
            text.append(card.getCardId().toString()).append("\n");
        }
        text.append("\n#extra\n!side\n");
        return text.toString();
    }

}
