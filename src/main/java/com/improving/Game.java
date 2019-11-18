package com.improving;

import com.improving.Enums.Colors;
import com.improving.Enums.Faces;
import com.improving.Interfaces.IGame;

import java.util.*;

public class Game implements IGame {

    private Deck deck = new Deck();
    private List<Player> players = new ArrayList<>();
    private Card topCard = null;
    boolean turnSkip = false;
    boolean reverseOrder = false;
    private int turnDirection = 1;
    private int count = 0;
    private int turn = 0;
    private Optional<Colors> optionalColor = null;
    private Player player = null;
    Scanner scanner = new Scanner(System.in);

    public Game(int playerAmount) {
        for (int i = 0; i < playerAmount; i++) {
            System.out.println("Player " + (i + 1) + " enter your name.");
            System.out.print(">");
            String name = scanner.nextLine();
            players.add(new Player(name, new ArrayList<>()));
        }
    }

    public void play() {
        makePlayers();

        deck.getDiscardPile().add(deck.getDrawPile().get(0));
        deck.getDrawPile().remove(0); //Getting the starting card for the game.
        boolean gameInProgress = true;

        for (var player : players) {
            getStartingHand(player);
        }
        topCard = deck.getTopCard();
        System.out.println("top card: " + topCard);

        while (gameInProgress) {
            if (count == 0) {
                evaluateTopCard(players.get(0));
            }
            player = players.get(safeModulo(players));
//            currentPlayer = player;

            count++;

            System.out.println("\n" + player.getName() + "\'s turn.");
            System.out.println("Top Card: " + topCard);
            player.takeTurn(this); //THIS NEEDS TO BE CHANGED SO THAT THEY SOMEHOW EVALUATE THE CARD???? idk

            topCard = deck.getTopCard();
            evaluateTopCard(player);

            if (player.hasUno()) {
                System.out.println(player.getName() + ": \"Uno!\"");
            }
            if (player.hasWon()) {
                System.out.println(player.getName() + " has won.");
                gameInProgress = false;
                break;
            }

            turn += turnDirection;
        }

        System.out.println("Game ended after " + count + " turns.");
    }

    private int safeModulo(List<Player> players) {
        if (turn < 0) {
            this.turn += players.size();
        }
        return this.turn % players.size();
    }


    public Deck getDeck() {
        return deck;
    }

    public List<Player> getPlayers() {
        return players;
    }

    private void makePlayers() {
         //needed because nextInt is stupid


    }


    public Card getTopCard() {
        return topCard;
    }

    public boolean isPlayable(Card card) {
        if (optionalColor != null) {
            if (optionalColor.get() == card.getColor())
                return true;
        }
        return card.getColor() == Colors.Wild || card.getFace() == topCard.getFace() || card.getColor() == topCard.getColor();
    }

    @Override
    public Card draw() {
        if (deck.getDrawPile().size() == 0) {
            reshuffle();
        }
        var card = deck.getDrawPile().get(deck.getDrawPile().size() - 1);
        deck.getDrawPile().remove(deck.getDrawPile().size() - 1);
        return card;
    }

    private void evaluateTopCard(Player player) {
        if (topCard.getFace() == Faces.Draw2 && topCard.isAddressed() == false) {
            System.out.println("Drawing two cards.");
            if (players.indexOf(player) == players.size() - 1 && turnDirection == 1) {
                System.out.println("\n" + players.get(0).getName() + " draws two cards.");
                players.get(0).drawTwo(this);
            } else if (players.indexOf(player) == 0 && turnDirection == -1) {
                System.out.println("\n" + players.get(players.size() - 1).getName() + " draws two cards.");
                players.get(players.size() - 1).drawTwo(this);
            } else {
                System.out.println("\n" + players.get(players.indexOf(player) + turnDirection).getName() + " draws two cards.");
                players.get(players.indexOf(player) + turnDirection).drawTwo(this);
            }
            topCard.setAddressed(true);
            turn += turnDirection;
        }
        if (topCard.getFace() == Faces.Draw4 && topCard.isAddressed() == false) {
            System.out.println("Drawing four cards.");
            if (players.indexOf(player) == players.size() - 1 && turnDirection == 1) {
                System.out.println("\n" + players.get(0).getName() + " draws two cards.");
                players.get(0).drawFour(this);
            } else if (players.indexOf(player) == 0 && turnDirection == -1) {
                System.out.println("\n" + players.get(players.size() - 1).getName() + " draws four cards.");
                players.get(players.size() - 1).drawFour(this);
            } else {
                System.out.println("\n" + players.get(players.indexOf(player) + turnDirection).getName() + " draws four cards.");
                players.get(players.indexOf(player) + turnDirection).drawFour(this);
            }
            topCard.setAddressed(true);
            turn += turnDirection;
        }
        if (topCard.getFace() == Faces.Skip && topCard.isAddressed() == false) {
            System.out.println("Skipping player");
            if (players.indexOf(player) == players.size() - 1 && turnDirection == 1) {
                System.out.println("\n" + players.get(0).getName() + " is skipped.");
            } else if (players.indexOf(player) == 0 && turnDirection == -1) {
                System.out.println("\n" + players.get(players.size() - 1).getName() + " is skipped.");
            } else {
                System.out.println("\n" + players.get(players.indexOf(player) + turnDirection).getName() + " is skipped.");
            }
            topCard.setAddressed(true);
            turn += turnDirection;
        }

        if (topCard.getFace() == Faces.Reverse && topCard.isAddressed() == false) {
            System.out.println("Reversing order.");
            topCard.setAddressed(true);
            turnDirection *= -1;
            if (count == 0) {
                turn += turnDirection;
            }
        }

    }

    public void playCard(Card card, Optional<Colors> optionalColor) {
        var face = card.getFace();
        deck.getDiscardPile().add(card);
        if (optionalColor != null) {
            this.optionalColor = optionalColor;
        }
        else {
            this.optionalColor = null;
        }
        if (face ==Faces.Draw4 || face == Faces.Draw2 || face == Faces.Reverse || face == Faces.Skip) {
            evaluateTopCard(player);
        }


        System.out.println("Played: " + card.getColor() + " " + card.getFace());




    }

    public Optional<Colors> getOptionalColor() {
        return optionalColor;
    }

    public int getCount() {
        return count;
    }

    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    public void getStartingHand(Player player) {
        for (int i = 0; i < 7; i++) {
            player.draw(this); //gets the player, adds the 7 starting cards to their hand. //CHANGE TO draw(game)
        }
    }

    public void reshuffle() {
        Card topCard = getTopCard();
        deck.getDrawPile().addAll(deck.getDiscardPile());
        Collections.shuffle(deck.getDrawPile());
        deck.getDiscardPile().clear();
        deck.getDrawPile().remove(topCard);
        deck.getDiscardPile().add(topCard);
        for (var card : deck.getDrawPile()) {
            card.setAddressed(false);
        }


    }
}
