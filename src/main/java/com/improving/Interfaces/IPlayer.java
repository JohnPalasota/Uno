package com.improving.Interfaces;

import com.improving.Card;
import com.improving.Game;

import java.util.List;

public interface IPlayer {
    int handSize();
    Card draw(Game game);
    void takeTurn(Game game);
}
