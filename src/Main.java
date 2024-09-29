import org.practica.model.Card;
import org.practica.model.CardsDeck;

public class Main {
        public static void main(String[] args) {
            CardsDeck cardsDeck = new CardsDeck();
            Card card = cardsDeck.getCardFromDeck();
            System.out.println(card);
        }

}