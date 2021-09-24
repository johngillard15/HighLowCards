package com.game;

import com.card.Card;
import com.card.Deck;
import com.card.StandardDeck;
import com.utilities.Input;

import java.util.Arrays;

public class HighLow {
    private final Deck deck;
    private Card lastCard;
    private Card nextCard;

    public HighLow(){
        deck = new StandardDeck();
        deck.shuffle();

        lastCard = deck.draw();
    }

    public void play(){
        do{
            guess();
        }while(playAgain());
    }

    private void guess(){
        System.out.println("\n" + lastCard);

        System.out.println("Will the next card be (h)igher or (l)ower?");
        String choice = Input.getString("h", "l").toLowerCase();

        nextCard = deck.draw();
        System.out.println("\n" + nextCard);

        checkGuess(choice);
    }

    private void checkGuess(String choice){
        boolean higher = isHigher();
        switch(choice){
            case "h" -> System.out.println(higher ? "Nice guess!" : "Boo!");
            case "l" -> System.out.println(higher ? "Boo!" : "Nice guess!");
        }
    }

    private boolean isHigher(){
        return Arrays.asList(StandardDeck.VALUES).indexOf(nextCard.value) - Arrays.asList(StandardDeck.VALUES).indexOf(lastCard.value) > 0;
    }

    private boolean playAgain(){
        System.out.println("Would you like to play again? (y/n)");
        return switch(Input.getString("y", "yes", "n", "no").toLowerCase()){
            case "y", "yes" -> {
                deck.discard(nextCard);
                lastCard = nextCard;
                yield true;
            }
            case "n", "no" -> false;
            default -> {
                System.out.println("Goodbye");
                yield false;
            }
        };
    }
}
