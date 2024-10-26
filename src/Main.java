import controller.GameController;
import model.GameHorsesRace;
import model.PlayerManager;
import view.ConsoleView;

public class Main {
        public static void main(String[] args) {

            PlayerManager playerManager = new PlayerManager();
            GameHorsesRace gameHorsesRace = new GameHorsesRace();
            ConsoleView consoleView = new ConsoleView();

            GameController gameController = new GameController(playerManager, gameHorsesRace, consoleView);

            gameController.init();

        }
}