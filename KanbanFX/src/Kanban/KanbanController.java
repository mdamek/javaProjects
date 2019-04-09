package Kanban;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KanbanController implements Initializable {

    public Button newTaskViewButton;
    public ListView<Task> toDoList;
    public ListView<Task> inProgressList;
    public ListView<Task> doneList;
    public Button moveTaskButton;
    private ContextMenu contextMenu = new ContextMenu();

    public void openNewTaskWindow() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource( "AddTaskView.fxml" ));
            Scene secondScene = new Scene(root, 600, 400);
            Stage secondStage = new Stage();
            secondStage.setTitle("Add new task");
            secondStage.setResizable( false );
            secondStage.setScene(secondScene);
            secondStage.show();
        }
        catch (IOException e){
            System.out.println( e.getMessage() );
        }
    }

    private void openEditTaskWindow(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource( "EditTaskView.fxml" ));
            Scene secondScene = new Scene(root, 600, 400);
            Stage secondStage = new Stage();
            secondStage.setTitle("Edit task");
            secondStage.setResizable( false );
            secondStage.setScene(secondScene);
            secondStage.show();
        }
        catch (IOException ignored){}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toDoList.setItems( TasksManager.toDoTasks );
        inProgressList.setItems( TasksManager.inProgressTasks );
        doneList.setItems( TasksManager.doneTasks );
    }

    public void moveTask() {
        try{
            Task task = getSelectedTask();
            TasksManager.MoveTask( task );
        }
        catch (NullPointerException ignored){}
    }

    private Task getSelectedTask(){
        if(toDoList.getSelectionModel().getSelectedItem() != null)
            return toDoList.getSelectionModel().getSelectedItem();
        else if(inProgressList.getSelectionModel().getSelectedItem() != null)
            return inProgressList.getSelectionModel().getSelectedItem();
        return null;
    }

    public void closeProgram() {
        System.exit(0);
    }

    public void showAbout() {
        Alert alert = new Alert( Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setContentText("It is Kanban application wrote in JavaFx by Marcin Damek");
        alert.showAndWait();
    }

    public void openDialogWindow(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseButton.SECONDARY){
                contextMenu.hide();
                contextMenu = null;
                contextMenu = new ContextMenu(  );
                Task selectedTask = getSelectedTask();
                MenuItem item1 = new MenuItem("Delete");
                item1.setOnAction( event -> TasksManager.RemoveTask( selectedTask ) );
                MenuItem item2 = new MenuItem("Edit");
                item2.setOnAction( event -> {
                    EditTaskController.taskForEdit = selectedTask;
                    openEditTaskWindow();
                } );
                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(toDoList, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        }
    }
}
