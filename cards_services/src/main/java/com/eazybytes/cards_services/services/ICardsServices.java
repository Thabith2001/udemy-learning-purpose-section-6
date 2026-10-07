package com.eazybytes.cards_services.services;


import com.eazybytes.cards_services.dto.CardsDto;

public interface ICardsServices {

    void createCard(String nic);

    CardsDto fetchCard(String nic);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String nic);

}
