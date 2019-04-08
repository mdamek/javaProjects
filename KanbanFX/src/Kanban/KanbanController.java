package Kanban;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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

    public void openNewTaskWindow() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource( "NewTaskView.fxml" ));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toDoList.setItems( TasksManager.toDoTasks );
        inProgressList.setItems( TasksManager.inProgressTasks );
        doneList.setItems( TasksManager.doneTasks );
    }

    public void showDescription(MouseEvent mouseEvent) {
        /*Window avc = toDoList.getScene().getWindow();
        if(!tooltip.isActivated()){
            tooltip.setText( "av" );
            tooltip.show( avc, mouseEvent.getSceneX(), mouseEvent.getSceneY() );
        }
*/
    }

}
