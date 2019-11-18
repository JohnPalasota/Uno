package com.improving;

import com.improving.Enums.Colors;
import com.improving.Enums.Faces;
import com.improving.Interfaces.IDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck implements IDeck {
    private ArrayList<Card> drawPile = new ArrayList<>();
    private ArrayList<Card> discardPile = new ArrayList<>();

    public Deck() {
        for (var face : Faces.values()) {
            for (var color : Colors.values()) {
                if (face.getValue() <= 20 && color.getColor().equals(Colors.Wild.getColor()) != true) {
                    for (int i = 0; i < 2; i++) {
                        drawPile.add(new Card(face, color));
                    }
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            drawPile.add(new Card(Faces.Wild, Colors.Wild));
            drawPile.add(new Card(Faces.Draw4, Colors.Wild));
        }
        Collections.shuffle(drawPile);

    }


    public List<Card> getDrawPile() {
        return drawPile;
    }

    @Override
    public int getDrawPileSize() {
        return drawPile.size();
    }

    @Override
    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }


    public Card getTopCard() {
        return discardPile.get(discardPile.size() - 1);
    }




}

