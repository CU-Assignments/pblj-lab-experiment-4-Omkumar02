Experiment 4.2: Card Collection System
===============================================

  import java.util.*;

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return Objects.equals(rank, card.rank) && Objects.equals(suit, card.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

class CardCollection {
    private List<Card> cardList;
    private Map<String, Set<Card>> suitMap;

    public CardCollection() {
        cardList = new ArrayList<>();
        suitMap = new HashMap<>();
    }

    public void addCard(Card card) {
        if (cardList.contains(card)) {
            System.out.println("Error: Card \"" + card + "\" already exists.");
            return;
        }
        cardList.add(card);
        suitMap.computeIfAbsent(card.getSuit(), k -> new HashSet<>()).add(card);
        System.out.println("Card added: " + card);
    }

    public void findCardsBySuit(String suit) {
        Set<Card> cards = suitMap.get(suit);
        if (cards == null || cards.isEmpty()) {
            System.out.println("No cards found for " + suit + ".");
        } else {
            System.out.println("Cards of suit " + suit + ":");
            cards.forEach(System.out::println);
        }
    }

    public void displayAllCards() {
        if (cardList.isEmpty()) {
            System.out.println("No cards found.");
        } else {
            cardList.forEach(System.out::println);
        }
    }

    public void removeCard(Card card) {
        if (cardList.remove(card)) {
            suitMap.get(card.getSuit()).remove(card);
            System.out.println("Card removed: " + card);
        } else {
            System.out.println("Error: Card \"" + card + "\" not found.");
        }
    }
}

public class CardCollectionSystem {
    public static void main(String[] args) {
        CardCollection collection = new CardCollection();

        System.out.println("Test Case 1: No Cards Initially");
        collection.displayAllCards();
        System.out.println();

        System.out.println("Test Case 2: Adding Cards");
        collection.addCard(new Card("Ace", "Spades"));
        collection.addCard(new Card("King", "Hearts"));
        collection.addCard(new Card("10", "Diamonds"));
        collection.addCard(new Card("5", "Clubs"));
        System.out.println();

        System.out.println("Test Case 3: Finding Cards by Suit");
        collection.findCardsBySuit("Hearts");
        System.out.println();

        System.out.println("Test Case 4: Searching Suit with No Cards");
        collection.findCardsBySuit("Diamonds");
        System.out.println();

        System.out.println("Test Case 5: Displaying All Cards");
        collection.displayAllCards();
        System.out.println();

        System.out.println("Test Case 6: Preventing Duplicate Cards");
        collection.addCard(new Card("King", "Hearts"));
        System.out.println();

        System.out.println("Test Case 7: Removing a Card");
        collection.removeCard(new Card("10", "Diamonds"));
        collection.displayAllCards();
    }
}


===========================================
  Test Case 1: No Cards Initially
No cards found.

Test Case 2: Adding Cards
Card added: Ace of Spades
Card added: King of Hearts
Card added: 10 of Diamonds
Card added: 5 of Clubs

Test Case 3: Finding Cards by Suit
Cards of suit Hearts:
King of Hearts

Test Case 4: Searching Suit with No Cards
No cards found for Diamonds.

Test Case 5: Displaying All Cards
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

Test Case 6: Preventing Duplicate Cards
Error: Card "King of Hearts" already exists.

Test Case 7: Removing a Card
Card removed: 10 of Diamonds
Ace of Spades
King of Hearts
5 of Clubs

=============================================

Test Cases

Test Case 1: No Cards Initially
Input:
Display All Cards
Expected Output:
No cards found.

Test Case 2: Adding Cards
Input:
Add Card: Ace of Spades
Add Card: King of Hearts
Add Card: 10 of Diamonds
Add Card: 5 of Clubs
Expected Output:
Card added: Ace of Spades
Card added: King of Hearts
Card added: 10 of Diamonds
Card added: 5 of Clubs

Test Case 3: Finding Cards by Suit
Input:
Find All Cards of Suit: Hearts
Expected Output:
King of Hearts

Test Case 4: Searching Suit with No Cards
Input:
Find All Cards of Suit: Diamonds
(If no Diamonds were added)
Expected Output:
No cards found for Diamonds.

Test Case 5: Displaying All Cards
Input:
Display All Cards
Expected Output:
Ace of Spades
King of Hearts
10 of Diamonds
5 of Clubs

Test Case 6: Preventing Duplicate Cards
Input:
Add Card: King of Hearts
Expected Output:
Error: Card "King of Hearts" already exists.

Test Case 7: Removing a Card
Input:
Remove Card: 10 of Diamonds
Expected Output:
Card removed: 10 of Diamonds
