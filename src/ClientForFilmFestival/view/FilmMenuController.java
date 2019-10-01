/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientForFilmFestival.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import ClientForFilmFestival.FilmApp;

/**
 * handle menu bar in main frame
 * @author admin
 */
public class FilmMenuController {
        FilmApp filmApp;

        @FXML
        private MenuItem closeMenu;

        @FXML
    private void initialize() {
    }
        @FXML
        void closeMenuhandlers(ActionEvent event) throws Exception {
           
            filmApp.ser.sendQuitMessage();
            filmApp.stop();
        }

    }


