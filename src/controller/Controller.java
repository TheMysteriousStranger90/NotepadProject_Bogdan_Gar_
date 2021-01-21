package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import static algorithm.ROT13.rot13;

public class Controller {

    @FXML
    private Button exitButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextArea fieldText;

    @FXML
    private Label countSym;

    private File file;
    private Boolean text = true;
    FileChooser fileChooser = new FileChooser();

    @FXML
    private void newOnAction() throws IOException {
        fieldText.clear();
        file = null;
        Stage stage = (Stage) fieldText.getScene().getWindow();
    }

    @FXML
    private void saveOnAction() {
        String tmp = fieldText.getText();

        if (file != null) {
            text = false;
            try {
                if (file.getParentFile().exists()) {
                    FileWriter fw = text  ? new FileWriter(file.getAbsoluteFile().getAbsolutePath().stripTrailing()) : new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(tmp);
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            file = fileChooser.showSaveDialog(null);
            if (file != null) {
                Stage stage = (Stage) fieldText.getScene().getWindow();
                stage.setTitle(file.getName());
                try {
                    FileWriter fw = text  ? new FileWriter(file.getAbsoluteFile() + ".txt") : new FileWriter(file.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(tmp);
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void loadOnAction() {

        file = fileChooser.showOpenDialog(null);

        if(file != null) {
            try {
                Stage stage = (Stage) fieldText.getScene().getWindow();
                stage.setTitle(file.getName());
                BufferedReader br;

                String currLine;
                br = new BufferedReader(new FileReader(file));
                fieldText.clear();
                while ((currLine = br.readLine()) != null) {
                    fieldText.appendText(currLine + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void closeOnAction() {
        System.exit(0);
    }

    @FXML
    private void aboutOnAction() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Информация");
        alert.setContentText("Текстовый редактор");
        alert.show();
    }

    @FXML
    public void rot13ButtonOnAction() {
        String tmp = fieldText.getText();
        fieldText.setText(rot13(tmp));
    }

    @FXML
    public void sumButtonOnAction() {
        String tmp = fieldText.getText();
        countSym.setText(String.valueOf(tmp.length()));
    }

    public void exitButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    public void resetButtonOnAction(ActionEvent event) {
        fieldText.clear();
    }
}
