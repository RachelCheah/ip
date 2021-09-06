package duke.commands;

import duke.Storage;
import duke.Tasklist;
import duke.Ui;
import duke.exceptions.NoSuchTaskException;
import duke.tasks.Task;

import java.io.IOException;

/**
 * Represents a Done Command which includes information about the Task
 * as well as how it should be executed
 */

public class DoneCommand extends Command {

    private int index;

    /**
     * Constructor of a Done Command
     *
     * @param index Index of task to be marked as Done
     */

    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Done Command by updating the task in the tasklist, updating the user
     * and updating the save file
     *
     * @param tasklist Tasklist containing list of Tasks
     * @param ui Ui that handles what is shown to the user
     * @param storage Storage that handles writing and reading save file
     * @throws NoSuchTaskException If task with input index cannot be retrieved
     */

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        try {
            Task temp = tasklist.getTask(index);
            tasklist.doneTask(index);
            ui.showDoneMessage(temp, tasklist);
            storage.writeToFile(tasklist);
        } catch (NoSuchTaskException | IOException e) {
            throw new NoSuchTaskException("Task index is out of bounds");
        }
    }
}