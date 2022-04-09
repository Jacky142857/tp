package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.BuyerCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FORTH_BUYER;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_BUYER;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.seller.HouseTypeMatchBuyerPredicate;
import seedu.address.testutil.TypicalBuyers;
import seedu.address.testutil.TypicalClients;
import seedu.address.testutil.TypicalSellers;

class MatchHouseTypeCommandTest {

    private Model model = new ModelManager(TypicalClients.getTypicalAddressBook(), new UserPrefs(),
        TypicalSellers.getTypicalSellerAddressBook(), TypicalBuyers.getTypicalBuyerAddressBook());

    @Test
    public void execute_invalidIndex_failure() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            model.getFilteredBuyerList().get(-1);
        });
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Buyer buyerToMatch = model.getFilteredBuyerList().get(INDEX_THIRD_BUYER.getZeroBased());

        MatchHouseTypeCommand matchCommand = new MatchHouseTypeCommand(INDEX_THIRD_BUYER);

        HouseTypeMatchBuyerPredicate predicate = new HouseTypeMatchBuyerPredicate(buyerToMatch);

        String expectedMessage = String.format(Messages.MESSAGE_SELLERS_LISTED_OVERVIEW, 7);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(),
            new UserPrefs(), new SellerAddressBook(),
            TypicalBuyers.getTypicalBuyerAddressBook());

        expectedModel.updateFilteredSellerList(predicate);

        assertCommandSuccess(matchCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noPropertyAdded_failure() {
        Buyer buyerToMatch = model.getFilteredBuyerList().get(INDEX_FORTH_BUYER.getZeroBased());

        MatchHouseTypeCommand matchCommand = new MatchHouseTypeCommand(INDEX_FORTH_BUYER);

        HouseTypeMatchBuyerPredicate predicate = new HouseTypeMatchBuyerPredicate(buyerToMatch);

        String expectedMessage = String.format(Messages.MESSAGE_NO_PROPERTY_ADDED);

        assertCommandFailure(matchCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromZeroBased(1);
        Index secondIndex = Index.fromZeroBased(1);
        Index thirdIndex = Index.fromZeroBased(2);

        MatchHouseTypeCommand matchFirstCommand = new MatchHouseTypeCommand(firstIndex);
        MatchHouseTypeCommand matchSecondCommand = new MatchHouseTypeCommand(secondIndex);
        MatchHouseTypeCommand matchThirdCommand = new MatchHouseTypeCommand(thirdIndex);

        // same object -> returns true
        assertTrue(matchFirstCommand.equals(matchFirstCommand));

        // same values -> returns true
        assertTrue(matchFirstCommand.equals(matchSecondCommand));

        // different types -> returns false
        assertFalse(matchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(matchFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(matchFirstCommand.equals(matchThirdCommand));
    }
}