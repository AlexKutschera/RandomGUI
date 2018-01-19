package alexkutschera01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {

    private int min = 0, max = 0, searched;
    private Controller controller;
    private boolean isRunning = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Parent root = loader.load();
        controller = loader.getController();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        controller.min.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isInteger(newValue)){
                min = Integer.parseInt(newValue);
            } else {
                controller.min.textProperty().setValue("");
            }
        });
        controller.max.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isInteger(newValue)){
                max = Integer.parseInt(newValue);
            } else {
                controller.max.textProperty().setValue("");
            }
        });
        controller.search.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isInteger(newValue) && Integer.parseInt(newValue) >= min && Integer.parseInt(newValue) <= max){
                searched = Integer.parseInt(newValue);
            } else {
                controller.search.textProperty().setValue("");
            }
        });
        controller.run.setOnAction(event -> {
            if (!isRunning){
                run();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    private void run(){
        if (isRunning){
            return;
        }
        isRunning = true;
        int resultsCount = 0;
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        int result;
        while ((result = (min + random.nextInt(max - min))) != searched){
            System.out.print("\r" + result);
            resultsCount++;
        }
        long runTime = System.currentTimeMillis() - startTime;
        controller.output.textProperty().setValue(
                "Runtime: " + runTime + "\n" +
                        "Randoms: " + resultsCount
        );
        isRunning = false;
    }
}
