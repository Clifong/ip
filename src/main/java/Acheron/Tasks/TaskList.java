package Acheron.Tasks;

import Acheron.UI.UI;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Tasks> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTask(Tasks task) {
        addTaskFromStorage(task);
        UI.displayText("Got it. I've added this task:\n" +
                task +
                "\n" +
                "Now you have " + tasks.size() +" tasks in the list.");
    }

    public void addTaskFromStorage(Tasks task) {
        tasks.add(task);
    }

    public void removeTask(int i) {
        Tasks removeTask = tasks.get(i);
        tasks.remove(i);
        UI.displayText("Noted. I've removed this task:\n" +
                removeTask +
                "\n" +
                "Now you have " + tasks.size() +" tasks in the list.");
    }

    public void markTask(int i) {
        tasks.get(i).mark();
        UI.displayText("Nice! I've marked this task as done:\n" + tasks.get(i));
    }

    public void unmarkTask(int i) {
        tasks.get(i).unmark();
        UI.displayText("OK, I've marked this task as not done yet:\n" + tasks.get(i));
    }

    public void listAllTasks() {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                listOfTasks += String.format("%d. %s\n", i + 1, tasks.get(i));
            } else {
                listOfTasks += String.format("%d. %s", i + 1, tasks.get(i));
            }
        }
        UI.displayText("Here are the tasks in your list:\n" + listOfTasks);
    }

    public String getHowManyTask() {
        return String.valueOf(tasks.size());
    }

    public String getAllTasksContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String savedContent = tasks.get(i).saveTask(i == tasks.size() - 1);
            stringBuilder.append(savedContent);
        }
        return stringBuilder.toString();
    }
}
