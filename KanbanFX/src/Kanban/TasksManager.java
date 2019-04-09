package Kanban;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TasksManager {
    public static ObservableList<Task> toDoTasks = FXCollections.observableArrayList(  );
    public static ObservableList<Task> inProgressTasks = FXCollections.observableArrayList(  );
    public static ObservableList<Task> doneTasks = FXCollections.observableArrayList(  );

    public static void MoveTask(Task task){
        for ( int i = 0; i < toDoTasks.size(); i++ ){
            if(toDoTasks.get( i ).Title.equals( task.Title )) {
                Task taskToMove = toDoTasks.get( i );
                toDoTasks.remove( i );
                inProgressTasks.add( taskToMove );
                return;
            }
        }
        for ( int i = 0; i < inProgressTasks.size(); i++ ){
            if(inProgressTasks.get( i ).Title.equals( task.Title )) {
                Task taskToMove = inProgressTasks.get( i );
                inProgressTasks.remove( i );
                doneTasks.add( taskToMove );
                return;
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
