package Kanban;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        else if(doneList.getSelectionModel().getSelectedItem() != null)
            return doneList.getSelectionModel().getSelectedItem();
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
    //actions
    public void save() {
        Map<String, String> extensions = new HashMap<>(  );
        extensions.put( "Text file", "*.txt" );
        File fileToSave = openFileChooser("Save file", extensions, ChooserType.SAVE);
        try
        {
            FileOutputStream fos = new FileOutputStream(fileToSave);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(TasksManager.GetToSerialization());
            oos.close();
            fos.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void open() {
        Map<String, String> extensions = new HashMap<>(  );
        extensions.put( "Text file", "*.txt" );
        File fileToOpen = openFileChooser("Open file", extensions, ChooserType.LOAD);
        ObjectInputStream inputStream = null;
        try {
            FileInputStream fileInputStream = new FileInputStream( fileToOpen );
            inputStream = new ObjectInputStream(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert inputStream != null;
            SerializableCollection deserialized = (SerializableCollection) inputStream.readObject();
            TasksManager.Deserialize(deserialized);
        }catch (Exception e){
            System.out.println( e.getMessage() );
        }

    }

    public void exportAction() {
        Map<String, String> extensions = new HashMap<>(  );
        extensions.put( "CSV file", "*.csv" );
        File fileToExport = openFileChooser("Export file", extensions, ChooserType.SAVE);
        try
        {
            FileWriter fileWriter = new FileWriter( fileToExport );
            List<Task> toDoTasks = TasksManager.toDoTasks;
            for ( Task toDoTask : toDoTasks ) {

                fileWriter.append(toDoTask.Title);
                fileWriter.append( "," );
                fileWriter.append(toDoTask.Priority.toString());
                fileWriter.append( "," );
                fileWriter.append(toDoTask.Date.toString());
                fileWriter.append( "," );
                fileWriter.append(toDoTask.Description);
                fileWriter.append( "," );
                fileWriter.append("toDoTasks");
                fileWriter.append( System.lineSeparator() );
            }
            List<Task> inProgressTasks = TasksManager.inProgressTasks;
            for ( Task inProgressTask : inProgressTasks ) {
                fileWriter.append(inProgressTask.Title);
                fileWriter.append( "," );
                fileWriter.append(inProgressTask.Priority.toString());
                fileWriter.append( "," );
                fileWriter.append(inProgressTask.Date.toString());
                fileWriter.append( "," );
                fileWriter.append(inProgressTask.Description);
                fileWriter.append( "," );
                fileWriter.append("inProgressTasks");
                fileWriter.append( System.lineSeparator() );
            }
            List<Task> doneTasks = TasksManager.doneTasks;
            for ( Task doneTask : doneTasks ) {
                fileWriter.append(doneTask.Title);
                fileWriter.append( "," );
                fileWriter.append(doneTask.Priority.toString());
                fileWriter.append( "," );
                fileWriter.append(doneTask.Date.toString());
                fileWriter.append( "," );
                fileWriter.append(doneTask.Description);
                fileWriter.append( "," );
                fileWriter.append("doneTasks");
                fileWriter.append( System.lineSeparator() );
            }
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }

    }

    public void importAction() {
        Map<String, String> extensions = new HashMap<>(  );
        extensions.put( "CSV file", "*.csv" );
        File importFile = openFileChooser("Export file", extensions, ChooserType.LOAD);
        try (BufferedReader br = new BufferedReader(new FileReader( importFile ))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                final LocalDate dt = LocalDate.parse( values[2],  dtf);
                if(values[4].equals( "toDoTasks" )){
                    TasksManager.toDoTasks.add( new Task( values[0], Priority.valueOf( values[1] ), dt, values[3] ) );
                }
                if(values[4].equals( "inProgressTasks" )){
                    TasksManager.inProgressTasks.add( new Task( values[0], Priority.valueOf( values[1] ), dt, values[3] ) );
                }
                if(values[4].equals( "doneTasks" )){
                    TasksManager.doneTasks.add( new Task( values[0], Priority.valueOf( values[1] ), dt, values[3] ) );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private File openFileChooser(String title, Map<String, String> extensions, ChooserType chooserType){
        FileChooser fileChooser = new FileChooser();
        for (String i : extensions.keySet()) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter( i, extensions.get( i ) ));
        }
        fileChooser.setTitle( title );
        if(chooserType == ChooserType.SAVE)
            return fileChooser.showSaveDialog( null );
        else
            return fileChooser.showOpenDialog( null );
    }


    public void GenerateTasks() {
        TasksManager.GenerateTasks();
    }
}
