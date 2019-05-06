package Kanban;

import java.io.Serializable;
import java.util.ArrayList;

public class SerializableCollection implements Serializable {
    public ArrayList<Task> toDoList;
    public ArrayList<Task> inProgressList;
    public ArrayList<Task> doneList;

    public SerializableCollection(ArrayList<Task> toDoList, ArrayList<Task> inProgressList, ArrayList<Task> doneList) {
        this.toDoList = toDoList;
        this.inProgressList = inProgressList;
        this.doneList = doneList;
    }
}
