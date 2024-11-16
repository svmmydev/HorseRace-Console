
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/WelcomeView.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Entry point for the Horse Race Betting game application.
     *
     * <p>This class initializes the main components of the game, including:
     * <ul>
     *     <li>{@link model.PlayerManager} - Manages the players and their states.</li>
     *     <li>{@link model.GameHorsesRace} - Core game logic and flow control for the race.</li>
     *     <li>{@link view.ConsoleView} - Console-based user interface for interaction and display.</li>
     * </ul>
     * <p>The main method creates an instance of the {@link controllers.GameController},
     * passing the initialized components to it, and starts the game by calling {@code gameController.init()}.
     * <hr>
     *
     * @see controllers.GameController
     * @see model.PlayerManager
     * @see model.GameHorsesRace
     * @see view.ConsoleView
     *
     * @author Samuel Mateos
     * @author Leandro Struni
     * @author Roger Navarro
     */
    /*public static void main(String[] args) {

        PlayerManager playerManager = new PlayerManager();
        GameHorsesRace gameHorsesRace = new GameHorsesRace();
        ConsoleView consoleView = new ConsoleView();

        GameController gameController = new GameController(playerManager, gameHorsesRace, consoleView);

        gameController.init();
    }*/
}