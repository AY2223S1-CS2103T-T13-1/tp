package seedu.address.model.grade;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.student.Student;
import seedu.address.model.task.Task;

/**
 * Represents a Grade, which is a relationship between a Task and a Student.
 * Guarantees: details are present and not null, field values are validated and mutable.
 */
public class Grade {

    // Identity fields
    private final Student student;
    private final Task task;
    // Data field
    private final GradeState gradeState;

    /**
     * Constructs a Grade object which represents the state of not being graded by default.
     * @param student student who is graded, must not be null
     * @param task task that is graded, must not be null
     */
    public Grade(Student student, Task task) {
        requireAllNonNull(student, task);
        this.student = student;
        this.task = task;
        this.gradeState = new GradeState(false);
    }

    /**
     * Constructs a Grade object.
     * @param student student who is graded, must not be null
     * @param task task that is graded, must not be null
     * @param gradeState whether the task is graded or not
     */
    public Grade(Student student, Task task, GradeState gradeState) {
        requireAllNonNull(student, task);
        this.student = student;
        this.task = task;
        this.gradeState = gradeState;
    }

    /**
     * Get the grade status of the task
     * @return true if the task is graded for the student, false otherwise
     */
    public GradeState getGradeState() {
        return this.gradeState;
    }
}
