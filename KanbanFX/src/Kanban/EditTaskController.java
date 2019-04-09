package Kanban;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {
    public ChoiceBox<String> priority;
    public TextField title;
    public DatePicker date;
    public TextArea description;
    public Button editTaskButton;
    public static Task taskForEdit;

    public void editTask() {
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
            Task newTask = new Task( title, priority, date, description );
            Stage stage = (Stage) editTaskButton.getScene().getWindow();
            TasksManager.EditTask( taskForEdit, newTask );
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText( taskForEdit.Title );
        priority.setItems( AddTaskController.priorities );
        priority.setValue( taskForEdit.Priority.toString() );
        description.setText( taskForEdit.Description );
        date.setValue(taskForEdit.Date);
    }
}
