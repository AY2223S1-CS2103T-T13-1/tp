package seedu.address.logic.commands.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.tutorialgroup.TutorialGroupDeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;


public class TaskDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(new Index[] {INDEX_FIRST_TASK});

        String expectedMessage = String.format(TaskDeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

//        assertCommandSuccess(taskDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(new Index[] {outOfBoundIndex});

        assertCommandFailure(taskDeleteCommand, model,
                Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TUTORIAL.getZeroBased());
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(new Index[] {INDEX_FIRST_TASK});

        String expectedMessage = String.format(taskDeleteCommand.MESSAGE_DELETE_TASK_SUCCESS,
                taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        //assertCommandSuccess(tutorialGroupDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTutorialGroupAtIndex(model, INDEX_FIRST_TUTORIAL);

        Index outOfBoundIndex = INDEX_SECOND_TUTORIAL;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTutorialGroupList().size());

        TutorialGroupDeleteCommand tutorialGroupDeleteCommand = new TutorialGroupDeleteCommand(outOfBoundIndex);

        assertCommandFailure(tutorialGroupDeleteCommand, model,
                Messages.MESSAGE_INVALID_TUTORIAL_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TaskDeleteCommand deleteFirstCommand = new TaskDeleteCommand(new Index[] {INDEX_FIRST_TASK});
        TaskDeleteCommand deleteSecondCommand = new TaskDeleteCommand(new Index[] {INDEX_SECOND_TASK});

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        TaskDeleteCommand deleteFirstCommandCopy = new TaskDeleteCommand(new Index[] {INDEX_FIRST_TASK});
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
