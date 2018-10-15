package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TextField lengthTF;
    public ChoiceBox angleCB;
    public ChoiceBox widthCB;
    public ColorPicker cp;
    public Button exitButton;
    @FXML private Canvas img ;

    private GraphicsContext gc ;

    @FXML
    private void drawCanvas(ActionEvent event) {
        gc.setFill(Color.AQUA);
        gc.fillRect(10,10,100,100);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = img.getGraphicsContext2D();
//        gc.setFill(Color.BLACK);
//        System.out.println("color set to black");
//        gc.fillRect(50, 50, 100, 100);
//        System.out.println("draw rectangle");

        for (int i = 2; i < 8; i++) {
            widthCB.getItems().add(i);
            angleCB.getItems().add(i);
        }

        widthCB.getSelectionModel().selectFirst();
        angleCB.getSelectionModel().selectFirst();
        cp.setValue(Color.BLUEVIOLET);


    }

    private void drawShapes(GraphicsContext gc, int length, Color color, int width, int angle) {
        //gc.setFill(Color.GREEN);

        gc.setStroke(color);
        gc.setLineWidth(width);

        double[] xPoints = new double[6], yPoints = new double[6];

        for (int i = 0; i < 6; i++) {
            xPoints[i] = (200 + length * Math.cos(i * 2 * Math.PI / 6));
            yPoints[i] = (200 + length * Math.sin(i * 2 * Math.PI / 6));
        }

        System.out.println(Arrays.toString(xPoints));
        System.out.println(Arrays.toString(yPoints));

        gc.strokePolygon(xPoints, yPoints,  6);
        gc.rotate(angle);
        gc.strokePolygon(xPoints, yPoints,  6);
        gc.rotate(angle);
        gc.strokePolygon(xPoints, yPoints,  6);
        gc.rotate(angle);
        gc.strokePolygon(xPoints, yPoints,  6);
        gc.rotate(angle);
        gc.strokePolygon(xPoints, yPoints,  6);
        gc.rotate(angle);
        gc.strokePolygon(xPoints, yPoints,  6);
    }

    public void drawEvent(ActionEvent actionEvent) {

        int length = Integer.parseInt(lengthTF.getText());
        int width = Integer.parseInt(widthCB.getValue().toString());
        int angle = Integer.parseInt(angleCB.getValue().toString());

        drawShapes(gc, length, cp.getValue(), width, angle);
    }

    public void exitEvent(ActionEvent actionEvent) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}