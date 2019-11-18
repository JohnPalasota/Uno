package com.improving;

import com.improving.Enums.Colors;
import com.improving.Enums.Faces;

public class Card {
    private Colors color;
    private Faces face;
    private boolean addressed = false;

    public Card(Faces face, Colors color) {
        this.color = color;
        this.face = face;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Faces getFace() {
        return face;
    }

    public void setFace(Faces face) {
        this.face = face;
    }

    public boolean isAddressed() {
        return addressed;
    }

    public void setAddressed(boolean addressed) {
        this.addressed = addressed;
    }

    @Override
    public String toString() {
        return color + " " + face;
    }
}
