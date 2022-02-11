package com.ygo.ygodeckbuilder.domain;

import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "card_id")
    private Integer cardId;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "card_subtype")
    private String cardSubtype;

    @Column(name = "monster_level")
    private String monsterLevel;

    @Column(name = "monster_attribute")
    private String monsterAttribute;

    @Column(name = "monster_atk")
    private Integer monsterAttack;

    @Column(name = "monster_def")
    private Integer monsterDefence;

    @Column(name = "card_text")
    private String cardText;

    public Card() {
    }


    public Card(Integer id, Integer cardId, String cardName,
                String cardType, String cardSubtype,
                String monsterLevel, String monsterAttribute, Integer monsterAttack,
                Integer monsterDefence, String cardText) {
        this.id = id;
        this.cardId = cardId;
        this.cardName = cardName;
        this.cardType = cardType;
        this.cardSubtype = cardSubtype;
        this.monsterLevel = monsterLevel;
        this.monsterAttribute = monsterAttribute;
        this.monsterAttack = monsterAttack;
        this.monsterDefence = monsterDefence;
        this.cardText = cardText;
    }

    public Integer getCardId() {
        return cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardSubtype() {
        return cardSubtype;
    }

    public String getMonsterLevel() {
        return monsterLevel;
    }

    public String getMonsterAttribute() {
        return monsterAttribute;
    }

    public Integer getMonsterAttack() {
        return monsterAttack;
    }

    public Integer getMonsterDefence() {
        return monsterDefence;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public void setCardSubtype(String cardSubtype) {
        this.cardSubtype = cardSubtype;
    }

    public void setMonsterLevel(String monsterLevel) {
        this.monsterLevel = monsterLevel;
    }

    public void setMonsterAttribute(String monsterAttribute) {
        this.monsterAttribute = monsterAttribute;
    }

    public void setMonsterAttack(Integer monsterAttack) {
        this.monsterAttack = monsterAttack;
    }

    public void setMonsterDefence(Integer monsterDefence) {
        this.monsterDefence = monsterDefence;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCssColorIdentity(){
        switch (this.cardType) {
            case "SPELL":
                return "rgba(88, 248, 91, 0.43)";
            case "TRAP":
                return "rgba(248, 88, 245, 0.43)";
            case "MONSTER":
                if (this.cardSubtype.contains("FUSION")) return "rgba(91, 88, 248, 0.43)";
                else if (this.cardSubtype.contains("RITUAL")) return "rgba(88, 165, 248, 0.43)";
                else if (this.cardSubtype.contains("EFFECT")) return "rgba(248, 170, 88, 0.43)";
                else return "rgba(245, 248, 88, 0.43)";

        }
        return "";
    }
}
