package joules.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import joules.Joules;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Joules joules;

    // userImage Source: https://www.flaticon.com/free-icons/success
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    // joules Image Source: https://www.flaticon.com/free-icons/chatbot
    private Image joulesImage = new Image(this.getClass().getResourceAsStream("/images/Joules.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setJoules(Joules joules) {
        this.joules = joules;
        dialogContainer.getChildren().addAll(
                DialogBox.getJoulesDialog(joules.welcome(), joulesImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = joules.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJoulesDialog(response, joulesImage)
        );
        userInput.clear();
    }
}
