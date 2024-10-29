import controller.GameController;
import model.GameHorsesRace;
import model.PlayerManager;
import view.ConsoleView;

/**
 * Entry point for the Horse Race Betting game application.
 *
 * <p>This class initializes the main components of the game, including:
 * <ul>
 *     <li>{@link model.PlayerManager} - Manages the players and their states.</li>
 *     <li>{@link model.GameHorsesRace} - Core game logic and flow control for the race.</li>
 *     <li>{@link view.ConsoleView} - Console-based user interface for interaction and display.</li>
 * </ul>
 * <p>The main method creates an instance of the {@link controller.GameController},
 * passing the initialized components to it, and starts the game by calling {@code gameController.init()}.
 * <hr>
 *
 * @see controller.GameController
 * @see model.PlayerManager
 * @see model.GameHorsesRace
 * @see view.ConsoleView
 *
 * @author Samuel Mateos
 * @author Leandro Struni
 * @author Roger Navarro
 */
public class Main {
        public static void main(String[] args) {

            PlayerManager playerManager = new PlayerManager();
            GameHorsesRace gameHorsesRace = new GameHorsesRace();
            ConsoleView consoleView = new ConsoleView();

            GameController gameController = new GameController(playerManager, gameHorsesRace, consoleView);

            gameController.init();

        }
}