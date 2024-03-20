package seedu.edulink.logic.parser;

import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.edulink.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.edulink.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.edulink.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.commands.DeleteCommand;

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
        assertParseSuccess(parser, " i/A0251893P", new DeleteCommand(ALICE.getId()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}