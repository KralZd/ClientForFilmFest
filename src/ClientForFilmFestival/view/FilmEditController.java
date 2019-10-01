/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientForFilmFestival.view;

/**
 * handle film festival edit frame
 *
 * @author admin
 */
import ClientForFilmFestival.model.Movie;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FilmEditController {
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private TextField movieField;
    
    @FXML
    private TextField cinemaField;

    /* @FXML
    private TextField dateField;*/
    @FXML
    private DatePicker dateField;
    
    @FXML
    private Button okButton;
    
    @FXML
    private TextField directorField;
    
    private Stage dialogStage;
    Movie movie;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    private LocalDate setDate() {
        LocalDate date;
        
        if (movie.getDate().equals("")) {
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(movie.getDate());
        }
        
        return date;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
        
        LocalDate date = setDate();
        
        dateField.setValue(date);
        movieField.setText(movie.getFilm());
        directorField.setText(movie.getDirector());
        cinemaField.setText(movie.getCinema());
        
    }

    public Movie getMovie() {
        
        movie.setDate(dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        movie.setFilm(movieField.getText());
        movie.setDirector(directorField.getText());
        movie.setCinema(cinemaField.getText());
        
        return movie;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (dateField.getValue() == null || dateField.getValue().toString().length() == 0) {
            errorMessage += "No valid date!\n";
        }
        if (movieField.getText() == null || movieField.getText().length() == 0) {
            errorMessage += "No valid movie!\n";
        }
        if (directorField.getText() == null || directorField.getText().length() == 0) {
            errorMessage += "No valid director!\n";
        }
        
        if (cinemaField.getText() == null || cinemaField.getText().length() == 0) {
            errorMessage += "No valid cinema!\n";

            /* if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }*/
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
    
}
