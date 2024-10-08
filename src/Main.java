import model.Card;
import model.CardsDeck;
import model.GameHorsesRace;
import model.GameManager;

public class Main {
        public static void main(String[] args) {
            GameHorsesRace gameRace = new GameHorsesRace();
            //CardsDeck cardsDeck = new CardsDeck();
            //Card card = cardsDeck.getCardFromDeck();
            //llamar a la clase Game
            //System.out.println(card);


            gameRace.gameRaceLogic();

            //GameManager game = new GameManager();
            //game.gameMenu();

        }
}