package com.eazybytes.cards_services.services.impl;

import com.eazybytes.cards_services.constants.CardTypes;
import com.eazybytes.cards_services.constants.CardsConstants;
import com.eazybytes.cards_services.dto.CardsDto;
import com.eazybytes.cards_services.exception.CardAlreadyExistsException;
import com.eazybytes.cards_services.exception.ResourceNotFoundException;
import com.eazybytes.cards_services.mapper.CardsMapper;
import com.eazybytes.cards_services.model.Cards;
import com.eazybytes.cards_services.repo.CardRepo;
import com.eazybytes.cards_services.services.ICardsServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class CardsServicesImpl implements ICardsServices {

    private final CardRepo cardRepo;

    @Override
    public void createCard(String nic) {
        Optional<Cards> cards = cardRepo.findByNic(nic);
        if (cards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given Nic" + nic);
        }
        cardRepo.save(createNewCard(nic));
    }

    @Override
    public CardsDto fetchCard(String nic) {
        Cards cards = cardRepo.findByNic(nic).orElseThrow(() -> new ResourceNotFoundException("Card ", "Nic", nic));
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardRepo.findByNic(cardsDto.getNic()).orElseThrow(() -> new ResourceNotFoundException("Card", "Nic", cardsDto.getNic()));
        Cards card = CardsMapper.mapToCards(cardsDto, cards);
        cardRepo.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String nic) {
        Cards cards = cardRepo.findByNic(nic).orElseThrow(() -> new ResourceNotFoundException("Card", "Nic", nic));
        cardRepo.deleteById(cards.getCardId());
        return true;
    }

    private Cards createNewCard(String nic) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setNic(nic);
        newCard.setCardType(CardTypes.DEBITCARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setUsedAmount(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
