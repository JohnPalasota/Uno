package com.improving.Interfaces;

import com.improving.Card;
import com.improving.Game;

import java.util.List;

public interface IPlayer extends IPlayerInfo{
    Card draw(IGame iGame);
    void takeTurn(IGame iGame);

}
