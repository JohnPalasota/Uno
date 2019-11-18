package com.improving;

import com.improving.Enums.Colors;
import com.improving.Interfaces.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player implements IPlayer {
    private String name;
    private List<Card> hand = new ArrayList<>();


    public Player(String name, List<Card> hand) {
        this.name = name;
    }


    public void takeTurn(Game game) {
        Card playableCard = findPlayableCard(game, hand); //Will return null if there are no valid cards to play.
        System.out.println("" + getName() + "\'s Current Hand: " + hand);

        if (playableCard != null) { //If playableCard has a Card, it will then put that card down :)
            hand.remove(playableCard);
            if (playableCard.getColor() == Colors.Wild) {
                game.playCard(playableCard, Optional.of(declareOptional(playableCard)));
            } else {
                game.playCard(playableCard, null);
            }


        } else {
            System.out.println("No valid card found. Drawing...");
            draw(game);
            System.out.println("Seeing if new card can fit");
            playableCard = findPlayableCard(game, hand); //Will now draw a card and see if that card is valid. If it is, it will play the card.
            if (playableCard != null) {
                hand.remove(playableCard);
                if (playableCard.getColor() == Colors.Wild) {

                    game.playCard(playableCard, Optional.of(declareOptional(playableCard)));
                } else {
                    game.playCard(playableCard, null);
                }
            } else {
                System.out.println("New card can't fit. Turn ended.");

            }
        }
    }


    private Card findPlayableCard(Game game, List<Card> hand) {
        for (var card : hand) {
            if (game.isPlayable(card)) {

                return card;
            }
        }
        return null;
    }

    public Card draw(Game game) {
        hand.add(game.draw());
        System.out.println("Drew Card: " + hand.get(handSize() - 1));
        return hand.get(handSize() - 1);
    }

    public void drawFour(Game game) {
        draw(game);
        draw(game);
        draw(game);
        draw(game);
    }

    public void drawTwo(Game game) {
        draw(game);
        draw(game);
    }


    public String getName() {
        return name;
    }



    public int handSize() {
        return hand.size();
    }


    private List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public Boolean hasWon() {
        return handSize() == 0;
    }

    public boolean hasUno() {
        return handSize() == 1;
    }

    public Colors declareOptional(Card card) {
        { //If wild, will choose color from first in deck. Change later to be more strategized.
            Colors color;
            color = hand.get(0).getColor();


            System.out.println("Wild Card Chosen Color: " + color);
            return color;
        }
    }


}
