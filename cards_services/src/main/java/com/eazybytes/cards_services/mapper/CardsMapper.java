package com.eazybytes.cards_services.mapper;

import com.eazybytes.cards_services.constants.CardTypes;
import com.eazybytes.cards_services.dto.CardsDto;
import com.eazybytes.cards_services.model.Cards;

public class CardsMapper {

    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setCardType(String.valueOf(cards.getCardType()));
        cardsDto.setNic(cards.getNic());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setUsedAmount(cards.getUsedAmount());
        return cardsDto;
    }

    public static Cards mapToCards(CardsDto cardsDto, Cards cards) {
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setCardType(CardTypes.valueOf(cardsDto.getCardType()));
        cards.setNic(cardsDto.getNic());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setUsedAmount(cardsDto.getUsedAmount());
        return cards;
    }
}
