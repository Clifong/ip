package Acheron.Tasks;  //same package as the class being tested
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskWriterTest {
    static final String TODOEMPTYSPACEERROR = "Check your to do input.\n" +
            "Make sure the text follows the format\n" +
            "todo [Some text]\n" +
            "(Note that the [ ] is not needed! E.g todo eat peach";
    static final String TODOEMPTYERROR = "Check your to do input.\n" +
            "Make sure the text follows the format\n" +
            "todo [Some text]\n" +
            "(Note that the [ ] is not needed! E.g todo eat peach";
    static final String DEADLINEWRONGGFORMATERROR = "Date is wrongly formatted!" +
            "Make sure it follows the YYYY-MM-DD format";
    static final String DEADLINEWRONGLENGTHBUTRIGHTFORMATERROR = "Date is wrongly" +
            "formatted! Make sure it follows the YYYY-MM-DD format";
    static final String EVENTWRONGERROR = "Date is wrongly" +
            "formatted! Make sure it follows the YYYY-MM-DD format";
    static final String CORRUPTEDFILEERROR = "Corrupted file. Cannot read data";

    /**
     * Check if task writer throws an error if to do task uses space as description
     */
    @Test
    public void TaskWriterTodoEmptySpace(){
        try {
            TaskWriter.createTask("todo ", new TaskList());
        } catch (Exception e) {
            assertEquals(TODOEMPTYSPACEERROR, e.toString());
        }
    }

    /**
     * Check if task writer throws an error if to do task has no description
     */
    @Test
    public void TaskWriterTodoEmpty(){
        try {
            TaskWriter.createTask("todo", new TaskList());
        } catch (Exception e) {
            assertEquals(TODOEMPTYERROR, e.toString());
        }
    }

    /**
     * Check if task writer throws an error if deadline task uses wrong date format
     */
    @Test
    public void TaskWriterDeadlineWrongFormat(){
        try {
            TaskWriter.createTask("deadline b /by 2019/02/12", new TaskList());
        } catch (Exception e) {
            assertEquals(DEADLINEWRONGGFORMATERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterDeadlineWrongLengthButRightFormat(){
        try {
            TaskWriter.createTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(DEADLINEWRONGLENGTHBUTRIGHTFORMATERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterEventWrong(){
        try {
            TaskWriter.createTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(EVENTWRONGERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileTodoEmptySpace(){
        try {
            TaskWriter.createSavedTask("todo ", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTEDFILEERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileTodoEmpty(){
        try {
            TaskWriter.createSavedTask("todo", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTEDFILEERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileDeadlineWrongFormat(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019/02/12", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTEDFILEERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileDeadlineWrongLengthButRightFormat(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTEDFILEERROR, e.toString());
        }
    }

    @Test
    public void TaskWriterSavedFileEventWrong(){
        try {
            TaskWriter.createSavedTask("deadline b /by 2019-02-1", new TaskList());
        } catch (Exception e) {
            assertEquals(CORRUPTEDFILEERROR, e.toString());
        }
    }
}