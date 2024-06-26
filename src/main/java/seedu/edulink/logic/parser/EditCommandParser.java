package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_INTAKE;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.edulink.commons.core.index.Index;
import seedu.edulink.logic.commands.EditCommand;
import seedu.edulink.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.edulink.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_ID, PREFIX_MAJOR, PREFIX_INTAKE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_MAJOR, PREFIX_INTAKE, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        if (!ParserUtil.areValidPrefixes(argMultimap, args, PREFIX_ID, PREFIX_MAJOR, PREFIX_INTAKE, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            editPersonDescriptor.setId(ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get()));
        }
        if (argMultimap.getValue(PREFIX_MAJOR).isPresent()) {
            editPersonDescriptor.setMajor(ParserUtil.parseMajor(argMultimap.getValue(PREFIX_MAJOR).get()));
        }
        if (argMultimap.getValue(PREFIX_INTAKE).isPresent()) {
            editPersonDescriptor.setIntake(ParserUtil.parseIntake(argMultimap.getValue(PREFIX_INTAKE).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, editPersonDescriptor);
    }

}
