package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import seedu.address.model.student.Student;


class TaskTest {


    private Task underTest;
    private @Mock TaskName taskName;
    private @Mock TaskDescription taskDescription;
    private @Mock TaskDeadline taskDeadline;
    private @Mock Set<Student> students;

    @BeforeEach
    public void setUp() {
        taskDeadline = mock(TaskDeadline.class);
        taskName = mock(TaskName.class);
        taskDescription = mock(TaskDescription.class);
        Student student = mock(Student.class);
        students = new HashSet<>();
        students.add(student);
        students.add(student);
        students.add(student);
        underTest = new Task(taskName, taskDescription, taskDeadline, students);
    }

    @Test
    void getTaskName() {
        assertEquals(underTest.getTaskName(), taskName);
    }

    @Test
    void getTaskDescription() {
        assertEquals(underTest.getTaskDescription(), taskDescription);
    }

    @Test
    void getTaskDeadline() {
        assertEquals(underTest.getTaskDeadline(), taskDeadline);
    }

    @Test
    void getStudents() {
        assertEquals(underTest.getStudents(), students);
    }

    @Test
    void isSameTask() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}
