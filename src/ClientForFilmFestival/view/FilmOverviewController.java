/*

 */
package ClientForFilmFestival.view;

import ClientForFilmFestival.FilmApp;
import ClientForFilmFestival.model.Movie;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * handle main film festival frame
 * @author admin
 */
public class FilmOverviewController {

    @FXML
    private TableView<Movie> filmTable;

    @FXML
    private TableColumn<Movie, String> filmColumn;

    @FXML
    private TableColumn<Movie, String> cinemaColumn;

    @FXML
    private TableColumn<Movie, String> dateColumn;

    @FXML
    private TableColumn<Movie, String> directorColumn;

    
    @FXML
    private Button quitButton;
    
    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Button addButton;

    /**
    * delete chosen movie.
    * in the case there is not selected any movie it call alert window
    */
    @FXML
    void handleDeleteBtton(ActionEvent event) throws IOException {
       

        int selectedIndex = filmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            
        String idOfMovie = filmTable.getSelectionModel().getSelectedItem().getID();
        filmApp.ser.sendDeleteMessage(idOfMovie);
        filmApp.ser.listen();
       
        
        }else{
         // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(FilmApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No movie Selected");
        alert.setContentText("Please select a movie in the table.");

        alert.showAndWait();
        }

    }
    
    /**
 * Called when the user clicks the new button. Opens a dialog to edit
 * details for a new movie.
 */
@FXML
 void handleAddButton(ActionEvent event) throws IOException {
    Movie tempMovie = new Movie();
    boolean okClicked = filmApp.showFilmEditDialog(tempMovie);
    if (okClicked) {
       
       tempMovie= filmApp.controller.getMovie();
       
       String date = tempMovie.getDate();
       String movie =tempMovie.getFilm();
       String director =tempMovie.getDirector();
       String cinema = tempMovie.getCinema();
        
       
       filmApp.ser.sendNewMovie(date,movie,director,cinema);
       filmApp.ser.listen();
       
    }
}

/**
 * Called when the user clicks the edit button. Opens a dialog to edit
 * details for the selected movie.
 */
@FXML
 void handleEditButton(ActionEvent event) throws IOException {
    Movie selectedMovie = filmTable.getSelectionModel().getSelectedItem();
   
    
  if (selectedMovie != null) {
       System.out.println(selectedMovie.getFilm());
       boolean okClicked = filmApp.showFilmEditDialog(selectedMovie);
        if (okClicked) {
           
            
            selectedMovie=filmApp.controller.getMovie();
            String id = selectedMovie.getID();
            String date =selectedMovie.getDate();
            String name = selectedMovie.getFilm();
            String director = selectedMovie.getDirector();
            String cinema = selectedMovie.getCinema();
            
            filmApp.ser.sendEditedMovie(id, date, name, director, cinema);
           
            filmApp.ser.listen();
     
        }

    } else {
        // Nothing selected.
        Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(FilmApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No movie Selected");
        alert.setContentText("Please select a movie in the table.");

        alert.showAndWait();
    }
}
   @FXML
    void handleQuitButton(ActionEvent event) {
        filmApp.ser.sendQuitMessage();
         // get a handle to the stage
    Stage stage = (Stage) quitButton.getScene().getWindow();
    // do what you have to do
    stage.close();
        
    }

    // Reference to the main application.
    private FilmApp filmApp;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public FilmOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        filmColumn.setCellValueFactory(cellData -> cellData.getValue().filmProperty());
        directorColumn.setCellValueFactory(cellData -> cellData.getValue().directorProperty());
        cinemaColumn.setCellValueFactory(cellData -> cellData.getValue().cinemaProperty());

    }

    
    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param filmApp
     */
    public void setFilmApp(FilmApp filmApp) {
        this.filmApp = filmApp;

        // Add observable list data to the table    
        filmTable.setItems(filmApp.getFilmData());
    }

}
