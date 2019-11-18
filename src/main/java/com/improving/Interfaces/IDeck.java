package com.improving.Interfaces;

import com.improving.Card;

import java.util.ArrayList;

public interface IDeck {
    int getDrawPileSize();
    ArrayList<Card> getDiscardPile();
}
