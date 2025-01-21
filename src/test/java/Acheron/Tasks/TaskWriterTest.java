package Acheron.Tasks;  //same package as the class being tested
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskWriterTest {
    @Test
    public void TaskWriterTodoEmptySpace(){
        String error = "Check your to do input.\n" +
                "Make sure the text follows the format\n" +
                "todo [Some text]\n" +
                "(Note that the [ ] is not needed! E.g todo eat peach";
        try {
            TaskWriter.createTask("todo ", new TaskList());
        } catch (Exception e) {
            assertEquals(error, e.toString());
        }
    }

    @Test
    public void TaskWriterTodoEmpty(){
        String error = "Check your to do input.\n" +
                "Make sure the text follows the format\n" +
                "todo [Some text]\n" +
                "(Note that the [ ] is not needed! E.g todo eat peach";
        try {
            TaskWriter.createTask("todo", new TaskList());
        } catch (Exception e) {
            assertEquals(error, e.toString());
        }
    }

    @Test
    public void TaskWriterDeadlineWrongFormat(){
        try {
            TaskWriter.createTask("deadline b /by 2019/02/12", new TaskList());
        } catch (Exception e) {
            assertEquals("Date is wrongly formatted! Make sure it follows the YYYY-MM-DD format", e.toString());
        }
    }

    @Test
    public void TaskWriterDeadlineWrongLengthButRightFormat(){
        try {
            TaskWriter.createTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals("Date is wrongly formatted! Make sure it follows the YYYY-MM-DD format", e.toString());
        }
    }

    @Test
    public void TaskWriterEventWrong(){
        try {
            TaskWriter.createTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals("Date is wrongly formatted! Make sure it follows the YYYY-MM-DD format", e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileTodoEmptySpace(){
        try {
            TaskWriter.createSavedTask("todo ", new TaskList());
        } catch (Exception e) {
            assertEquals("Corrupted file. Cannot read data", e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileTodoEmpty(){
        try {
            TaskWriter.createSavedTask("todo", new TaskList());
        } catch (Exception e) {
            assertEquals("Corrupted file. Cannot read data", e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileDeadlineWrongFormat(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019/02/12", new TaskList());
        } catch (Exception e) {
            assertEquals("Corrupted file. Cannot read data", e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileDeadlineWrongLengthButRightFormat(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals("Corrupted file. Cannot read data", e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileEventWrong(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals("Corrupted file. Cannot read data", e.toString());
        }
    }
}