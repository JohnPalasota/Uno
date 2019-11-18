package com.improving.Interfaces;

import com.improving.Card;
import com.improving.Enums.Colors;

import java.util.Optional;

public interface IGame {
    void playCard(Card card, Optional<Colors> color);
    boolean isPlayable(Card card);
    Card draw();


}
