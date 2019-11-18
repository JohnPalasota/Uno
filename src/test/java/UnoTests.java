import com.improving.*;
import com.improving.Enums.Colors;
import com.improving.Enums.Faces;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnoTests {


    @Test
    public void discard_should_reshuffle_when_drawpile_empty() {
        //Arrange
        Game game = new Game(0);
        Player nowOutOfLimits = new Player("John", new ArrayList<>());

        //Act
        for (int i = 0; i < game.getDeck().getDrawPile().size() - 3; i++) {            //Leaves the drawPiled with only 3 cards left. And puts all of the ones removed
            game.getDeck().getDiscardPile().add(game.getDeck().getDrawPile().get(i)); // into the discardPile. Now should reshuffle when no more in the
            game.getDeck().getDrawPile().remove(game.getDeck().getDrawPile().get(i)); // draw pile.
        }

        game.getStartingHand(nowOutOfLimits);
        //Assert
        assertTrue(game.getDeck().getDrawPile().size() > 0);
    }

    @Test
    public void hand_should_lose_cards_when_hand_played() {
        //Arrange
        Game game = new Game(0);
        Player player = new Player("John", null);
        Card[] inserted = {new Card(Faces.Two, Colors.Red), new Card(Faces.Nine, Colors.Blue), new Card(Faces.Six, Colors.Green)};
        List<Card> cardList = new ArrayList<>(Arrays.asList(inserted));
        player.setHand(cardList);
        game.setTopCard(new Card(Faces.Nine, Colors.Green));
        //Act
        game.getStartingHand(player);
        player.takeTurn(game);
        //Assert
        assertEquals(6+3, player.handSize()); //3 were added to assure one card would get played no matter what.
    }

    @Test
    public void discard_should_add_cards_when_hand_played() {
        //Arrange
        Game game = new Game(0);
        Player player = new Player("John", null);

        Card[] inserted = {new Card(Faces.Two, Colors.Red), new Card(Faces.Nine, Colors.Blue), new Card(Faces.Six, Colors.Green)};
        List<Card> cardList = new ArrayList<>(Arrays.asList(inserted));
        player.setHand(cardList);
        game.setTopCard(new Card(Faces.Nine, Colors.Green));
        //Act
        game.getStartingHand(player);
        player.takeTurn(game);
        //Assert
        assertEquals(1, game.getDeck().getDiscardPile().size());

    }

    @Test
    public void drawPile_should_lose_7_cards_when_making_a_hand() {
        //Arrange
        Game game = new Game(0);
        Player player = new Player("John", null);
        game.getStartingHand(player);
        var size = game.getDeck().getDrawPile().size();

        //Act
        game.getStartingHand(player);
        //Assert
        assertEquals(size - 7, game.getDeck().getDrawPile().size());
    }

    @Test
    public void when_game_starts_players_hand_should_have_7_cards() {
        //Arrange
        Game game = new Game(0);

        Player player = new Player("John", new ArrayList<>());
        game.getStartingHand(player);
        //Act
        var result = player.handSize();
        //Assert
        assertEquals(7, result);
    }


    @Test
    public void drawPile_should_have_112_cards() {
        //Arrange
        Deck deck = new Deck();
        //Act
        var result = deck.getDrawPile().size();
        //Assert
        assertEquals(112, result);
    }
}
