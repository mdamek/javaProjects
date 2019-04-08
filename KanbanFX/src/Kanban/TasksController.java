package Kanban;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TasksController implements Initializable {

    public ChoiceBox<String> priority;
    public TextField title;
    public DatePicker date;
    public TextArea description;
    public Button addTaskButton;

    private ObservableList<String> priorities = FXCollections.observableArrayList(
            Priority.LOW.toString(), Priority.MEDIUM.toString(), Priority.HEIGHT.toString());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.setItems( priorities );
    }

    public void addNewTask() {
        try{
            if(this.title.getText().equals( "" ))
                throw new IllegalArgumentException( "Title can not be empty!" );
            String title = this.title.getText();
            if(this.priority.getSelectionModel().getSelectedItem() == null)
                throw new IllegalArgumentException( "You have to select priority!" );
            Priority priority = Priority.valueOf( this.priority.getSelectionModel().getSelectedItem() );
            if(this.date.getValue() == null)
                throw new IllegalArgumentException("You have to select valid date!" );
            LocalDate date = this.date.getValue();
            if(this.description.getText().equals( "" ))
                throw new IllegalArgumentException( "Description can not be empty!" );
            String description = this.description.getText();
            Task task = new Task( title, priority, date, description );
            TasksManager.toDoTasks.add( task );
            Stage stage = (Stage) addTaskButton.getScene().getWindow();
            stage.close();

        }catch (IllegalArgumentException e ){
            AlertAboutErrors( e.getMessage() );
        }
    }

    private void AlertAboutErrors(String errorContent){
        Alert alert = new Alert( Alert.AlertType.ERROR);
        alert.setTitle("Argument error");
        alert.setContentText(errorContent);
        alert.showAndWait();
    }
}
