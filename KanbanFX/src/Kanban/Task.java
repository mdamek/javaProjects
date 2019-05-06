package Kanban;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable{
    public String Title;
    public Priority Priority;
    public LocalDate Date;
    public String Description;

    public Task(String title, Kanban.Priority priority, LocalDate date, String description) {
        Title = title;
        Priority = priority;
        Date = date;
        Description = description;
    }

    @Override
    public String toString() {
        return Title;
    }
}
