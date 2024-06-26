package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.commands.CommandTestUtil.EMPTY_ID_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.INVALID_ID_DESC;
import static seedu.edulink.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.edulink.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.edulink.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.commands.DeleteCommand;
import seedu.edulink.model.student.Id;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validIndexArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_validStudentIdArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, ID_DESC_AMY , new DeleteCommand(new Id(VALID_ID_AMY)));
    }

    @Test
    public void parse_validAllArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "all", new DeleteCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // non-integer index
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteCommand.MESSAGE_USAGE));

        // empty student ID
        assertParseFailure(parser, EMPTY_ID_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            Id.MESSAGE_CONSTRAINTS));

        // invalid student ID
        assertParseFailure(parser, INVALID_ID_DESC, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            Id.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_failure() {
        assertParseFailure(parser, ID_DESC_AMY + NAME_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeleteCommand.MESSAGE_USAGE));
    }
}
