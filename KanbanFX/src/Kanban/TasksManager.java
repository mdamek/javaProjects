package Kanban;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TasksManager {
    public static ObservableList<Task> toDoTasks = FXCollections.observableArrayList(  );
    public static ObservableList<Task> inProgressTasks = FXCollections.observableArrayList(  );
    public static ObservableList<Task> doneTasks = FXCollections.observableArrayList(  );
}
