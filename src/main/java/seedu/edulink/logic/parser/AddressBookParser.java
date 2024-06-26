package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.edulink.commons.core.LogsCenter;
import seedu.edulink.logic.commands.AddCommand;
import seedu.edulink.logic.commands.ClearCommand;
import seedu.edulink.logic.commands.Command;
import seedu.edulink.logic.commands.DeleteCommand;
import seedu.edulink.logic.commands.DeleteGradeCommand;
import seedu.edulink.logic.commands.DeleteTagCommand;
import seedu.edulink.logic.commands.EditCommand;
import seedu.edulink.logic.commands.EditTagCommand;
import seedu.edulink.logic.commands.ExitCommand;
import seedu.edulink.logic.commands.ExportCommand;
import seedu.edulink.logic.commands.FilterCommand;
import seedu.edulink.logic.commands.FindCommand;
import seedu.edulink.logic.commands.GradeCommand;
import seedu.edulink.logic.commands.HelpCommand;
import seedu.edulink.logic.commands.ImportCommand;
import seedu.edulink.logic.commands.ListCommand;
import seedu.edulink.logic.commands.TagCommand;
import seedu.edulink.logic.commands.UndoCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.storage.Storage;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);
    private Storage storage;

    public AddressBookParser(Storage storage) {
        this.storage = storage;
    }

    public AddressBookParser() {

    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord.toLowerCase()) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FilterCommand.COMMAND_WORD:
            return new FilterCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExportCommand.COMMAND_WORD:
            return new ExportCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommandParser().parse(arguments);

        case TagCommand.COMMAND_WORD:
            return new TagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case EditTagCommand.COMMAND_WORD:
            return new EditTagCommandParser().parse(arguments);

        case ImportCommand.COMMAND_WORD:
            return new ImportCommandParser(storage).parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case DeleteGradeCommand.COMMAND_WORD:
            return new DeleteGradeCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
