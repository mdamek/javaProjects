package Kanban;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.ArrayList;

public class TasksManager {
    public static ObservableList<Task> toDoTasks = FXCollections.observableArrayList(  );
    public static ObservableList<Task> inProgressTasks = FXCollections.observableArrayList(  );
    public static ObservableList<Task> doneTasks = FXCollections.observableArrayList(  );

    public static void MoveTask(Task task){
        for ( int i = 0; i < inProgressTasks.size(); i++ ){
            if(inProgressTasks.get( i ).Title.equals( task.Title )) {
                Task taskToMove = inProgressTasks.get( i );
                inProgressTasks.remove( i );
                doneTasks.add( taskToMove );
            }
        }
        for ( int i = 0; i < toDoTasks.size(); i++ ){
            if(toDoTasks.get( i ).Title.equals( task.Title )) {
                Task taskToMove = toDoTasks.get( i );
                toDoTasks.remove( i );
                inProgressTasks.add( taskToMove );
            }
        }
    }
    public static void RemoveTask(Task task){
        try {
            toDoTasks.removeIf( e -> e.Title.equals( task.Title ) );
            inProgressTasks.removeIf( e -> e.Title.equals( task.Title ) );
            doneTasks.removeIf( e -> e.Title.equals( task.Title ) );
        }catch (Exception ignored){}

    }
    public static void EditTask(Task oldTask, Task newTask) {
        Replace( oldTask, newTask, toDoTasks );
        Replace( oldTask, newTask, inProgressTasks );
        Replace( oldTask, newTask, doneTasks );
    }
    public static void GenerateTasks(){
        toDoTasks.add( new Task( "Task1", Priority.LOW, LocalDate.now(), "ABCDE") );
        toDoTasks.add( new Task( "Task2", Priority.LOW, LocalDate.now(), "ABCDE") );
        toDoTasks.add( new Task( "Task3", Priority.LOW, LocalDate.now(), "ABCDE") );
        inProgressTasks.add( new Task( "Task4", Priority.LOW, LocalDate.now(), "ABCDE") );
        inProgressTasks.add( new Task( "Task5", Priority.LOW, LocalDate.now(), "ABCDE") );
        inProgressTasks.add( new Task( "Task6", Priority.LOW, LocalDate.now(), "ABCDE") );
        doneTasks.add( new Task( "Task7", Priority.LOW, LocalDate.now(), "ABCDE") );
        doneTasks.add( new Task( "Task8", Priority.LOW, LocalDate.now(), "ABCDE") );
        doneTasks.add( new Task( "Task9", Priority.LOW, LocalDate.now(), "ABCDE") );


    }
    public static SerializableCollection GetToSerialization (){
        return new SerializableCollection(
                convertObservableCollectionToArrayList(toDoTasks),
                convertObservableCollectionToArrayList(inProgressTasks),
                convertObservableCollectionToArrayList(doneTasks));
    }

    public static void Deserialize(SerializableCollection serializableCollection){
        toDoTasks.removeAll(  );
        inProgressTasks.removeAll(  );
        doneTasks.removeAll(  );
        toDoTasks.addAll( serializableCollection.toDoList );
        inProgressTasks.addAll( serializableCollection.toDoList );
        doneTasks.addAll( serializableCollection.toDoList );
    }

    private static ArrayList<Task> convertObservableCollectionToArrayList(ObservableList<Task> observableCollection){
        return new ArrayList<>( observableCollection );
    }
    private static void Replace(Task oldTask, Task newTask, ObservableList<Task> tasks) {
        for ( int i = 0; i < tasks.size();i++ ) {
            if (tasks.get( i ).Title.equals( oldTask.Title )) {
                tasks.add( i, newTask );
                tasks.remove( i+1 );
                return;
            }
        }
    }
}
