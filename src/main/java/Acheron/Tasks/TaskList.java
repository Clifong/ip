package Acheron.Tasks;

import Acheron.UI.UI;

import java.util.ArrayList;

/**
 * Represents the an object that stores all tasks
 */
public class TaskList {
    private ArrayList<Tasks> tasks = new ArrayList<>();

    /**
     * Adds a task
     * @param task A task to be added
     */
    public void addTask(Tasks task) {
        addTaskFromStorage(task);
        UI.displayText("Got it. I've added this task:\n" +
                task +
                "\n" +
                "Now you have " + tasks.size() +" tasks in the list.");
    }

    /**
     * Adds a task based on data in the saved file. A distinction is needed so the text displayed
     * is different
     * @param task A task to be added
     */
    public void addTaskFromStorage(Tasks task) {
        tasks.add(task);
    }

    /**
     * Remove the ith task
     * @param i The position of the task in the array list
     */
    public void removeTask(int i) {
        Tasks removeTask = tasks.get(i);
        tasks.remove(i);
        UI.displayText("Noted. I've removed this task:\n" +
                removeTask +
                "\n" +
                "Now you have " + tasks.size() +" tasks in the list.");
    }

    /**
     * Mark the ith task
     * @param i The position of the task in the array list
     */
    public void markTask(int i) {
        tasks.get(i).mark();
        UI.displayText("Nice! I've marked this task as done:\n" + tasks.get(i));
    }

    /**
     * Unmark the ith task
     * @param i The position of the task in the array list
     */
    public void unmarkTask(int i) {
        tasks.get(i).unmark();
        UI.displayText("OK, I've marked this task as not done yet:\n" + tasks.get(i));
    }

    /**
     Overrides the to string method with a custom version
     * @return A string format of  the task list
     */
    @Override
    public String toString() {
        String listOfTasks = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (i < tasks.size() - 1) {
                listOfTasks += String.format("%d. %s\n", i + 1, tasks.get(i));
            } else {
                listOfTasks += String.format("%d. %s", i + 1, tasks.get(i));
            }
        }
        return "Here are the tasks in your list:\n" + listOfTasks;
    }

    /**
     * Used to extract out all the contents of the existing tasks
     * @return All the contents of the existing tasks
     */
    public String getAllTasksContent() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            String savedContent = tasks.get(i).saveTask(i == tasks.size() - 1);
            stringBuilder.append(savedContent);
        }
        return stringBuilder.toString();
    }

    /**
     * Find all tasks with the same keyword
     * @param keyword The keyword we are interested
     */
    public void findAllTaskWithKeyword(String keyword) {
        ArrayList<Tasks> rightTask = new ArrayList<>();
        for (Tasks task : this.tasks) {
            if (task.containsKeyword(keyword)) {
                rightTask.add(task);
            }
        }
        String listOfTasks = "";
        for (int i = 0; i < rightTask.size(); i++) {
            if (i < rightTask.size() - 1) {
                listOfTasks += String.format("%d. %s\n", i + 1, rightTask.get(i));
            } else {
                listOfTasks += String.format("%d. %s", i + 1, rightTask.get(i));
            }
        }
        UI.displayText("Here are the matching tasks in your list:\n" + listOfTasks);
    }
}
