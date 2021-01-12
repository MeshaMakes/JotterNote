package meshamakes.Controllers;

//Author: MeshaMakes || Date Created: 04/01/2021

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class NotepadController {

    private final FileChooser fileChooser = new FileChooser();
    private File file;
    private Boolean text = true;

    //fxml variables
    @FXML
    private TextArea inputArea;
    @FXML
    private MenuItem textID;

    public void onNew() {
        //clears the window and sets title
        inputArea.clear();
        Stage stage = (Stage) inputArea.getScene().getWindow();
        stage.setTitle("Untitled - JotterNote");
        file = null;
    }

    public void onSave() {
        String content = inputArea.getText();

        if (file != null) {
            //if your in current file it will save rather than creating a new file
            try {
                //if the file exists it will just save it and not create a new file
                if (file.getParentFile().exists()) {
                    //i stripTrailing to remove the extra .txt and just leave one extension there
                    FileWriter fw = text  ? new FileWriter(file.getAbsoluteFile().getAbsolutePath().stripTrailing() + ".txt") : new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //if it's a new file it will trigger the file chooser and save as a new file
            file = fileChooser.showSaveDialog(null);
            if (file != null) {
                Stage stage = (Stage) inputArea.getScene().getWindow();
                stage.setTitle(file.getName() + " - JotterNote");
                try {
                    FileWriter fw = text  ? new FileWriter(file.getAbsoluteFile() + ".txt") : new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(content);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onSaveAs() {
        //open file chooser again so you can rename the file and save it as something else
        file = fileChooser.showSaveDialog(null);

        String content = inputArea.getText();
        if (file != null) {
            Stage stage = (Stage) inputArea.getScene().getWindow();
            stage.setTitle(file.getName() + " - JotterNote");
            try {
                FileWriter fw = text  ? new FileWriter(file.getAbsoluteFile() + ".txt") : new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onOpen() {
        file = fileChooser.showOpenDialog(null);
        if(file != null) {
            //opens file chooser so you can select the file to edit
            try {
                Stage stage = (Stage) inputArea.getScene().getWindow();
                stage.setTitle(file.getName() + " - JotterNote");
                BufferedReader br;

                String currLine;
                br = new BufferedReader(new FileReader(file));
                inputArea.clear();
                while ((currLine = br.readLine()) != null) {
                    inputArea.appendText(currLine + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onAbout() {
        //shows a popup dialog to display info
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Made by MeshaMakes");
        alert.setTitle("About JotterNote");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.initModality(Modality.APPLICATION_MODAL);

        alert.showAndWait();
    }

    public void saveText() {
        //enables user to decide if they want to save as a text file or a custom file
        text = !text;
        if (text) {
            textID.setText("Text file");
        } else {
            textID.setText("Custom file");
        }
    }

    public void onExit() {
        System.exit(0);
    }
}