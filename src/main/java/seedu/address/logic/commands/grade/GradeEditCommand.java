package seedu.address.logic.commands.grade;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.student.StudentEditCommand;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;
import seedu.address.model.student.*;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

import java.util.*;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

public class GradeEditCommand extends Command {
    public static final String COMMAND_WORD = "gradeEdit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the grade of the student's task "
            + "by the index number used in the displayed student list and task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: STUDENT_INDEX TASK_INDEX (must be positive integers)"
            + "[" + PREFIX_GRADE + "GRADE]...\n"
            + "Example: " + COMMAND_WORD + " 1 2 "
            + PREFIX_GRADE + "T";
    public static final String MESSAGE_EDIT_GRADE_SUCCESS = "Edited Task and Student: %s %s";
    public static final String MESSAGE_NOT_EDITED = "T OR F must be provided";
    public static final String MESSAGE_STUDENT_TASK_PAIR_NOT_FOUND = "This student and task pair is not found.";
    private final Index studentIndex;
    private final Index taskIndex;
    private final EditGradeDescriptor editGradeDescriptor;
    /**
     * @param studentIndex of the student in the filtered student list to edit
     * @param taskIndex of the task in the filtered task list to edit
     * @param editGradeDescriptor details to edit the grade with
     */
    public GradeEditCommand(Index studentIndex, Index taskIndex, EditGradeDescriptor editGradeDescriptor) {
        requireNonNull(studentIndex);
        requireNonNull(taskIndex);
        requireNonNull(editGradeDescriptor);

        this.studentIndex = studentIndex;
        this.taskIndex = taskIndex;
        this.editGradeDescriptor = new EditGradeDescriptor(editGradeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (studentIndex.getZeroBased() >= lastShownStudentList.size()
                || taskIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        // TODO check if student is in task
        Student studentGradeToEdit = lastShownStudentList.get(studentIndex.getZeroBased());
        Task taskGradeToEdit = lastShownTaskList.get(taskIndex.getZeroBased());
        Grade gradeToEdit = model.getGrade(studentGradeToEdit, taskGradeToEdit);
        Grade editedGrade = createEditedGrade(studentGradeToEdit, taskGradeToEdit, editGradeDescriptor);
        model.setGrade(gradeToEdit, editedGrade);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_GRADE_SUCCESS, studentGradeToEdit, taskGradeToEdit));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Grade createEditedGrade(Student student, Task task, EditGradeDescriptor editGradeDescriptor) {
        assert student != null;
        assert task != null;

        Grade updatedGrade = editGradeDescriptor.getGrade();
        return new Student(updatedName, updatedPhone, updatedEmail, updatedTags, updatedTutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentEditCommand)) {
            return false;
        }

        // state check
        StudentEditCommand e = (StudentEditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditGradeDescriptor {
        private Grade grade;

        public EditGradeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditGradeDescriptor(EditGradeDescriptor toCopy) {
            setGrade(toCopy.grade);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(grade);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Grade getGrade() {
            return this.grade;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditGradeDescriptor)) {
                return false;
            }

            // state check
            EditGradeDescriptor e = (EditGradeDescriptor) other;

            return getGrade().equals(e.getGrade());
        }
    }
}
