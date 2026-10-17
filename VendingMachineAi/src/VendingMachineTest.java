
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        // This currently throws ArrayIndexOutOfBoundsException
        // because the constructor loop uses i <= NUM_SLOTS.
        vendingMachine = new VendingMachine();
    }

    // VendingMachine constructor

    @Test
    public void testConstructorInitializesMachine() {
        assertNotNull(vendingMachine);
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }

    // VendingMachineItem tests

    @Test
    public void testItemStoresName() {
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        assertEquals("Chips", item.getName());
    }

    @Test
    public void testItemStoresPrice() {
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        assertEquals(1.50, item.getPrice(), 0.001);
    }

    @Test
    public void testItemAllowsZeroPrice() {
        VendingMachineItem item = new VendingMachineItem("Free sample", 0.0);

        assertEquals(0.0, item.getPrice(), 0.001);
    }

    @Test
    public void testItemRejectsNegativePrice() {
        VendingMachineException exception = assertThrows(
            VendingMachineException.class,
            () -> new VendingMachineItem("Chips", -1.0)
        );

        assertEquals(
            "Price cannot be less than zero",
            exception.getMessage()
        );
    }

    @Test
    public void testItemAcceptsSmallPositivePrice() {
        VendingMachineItem item = new VendingMachineItem("Gum", 0.01);

        assertEquals(0.01, item.getPrice(), 0.001);
    }

    @Test
    public void testItemAcceptsEmptyName() {
        VendingMachineItem item = new VendingMachineItem("", 1.00);

        assertEquals("", item.getName());
        assertEquals(1.00, item.getPrice(), 0.001);
    }

    // VendingMachineException tests

    @Test
    public void testDefaultExceptionHasNoMessage() {
        VendingMachineException exception = new VendingMachineException();

        assertNull(exception.getMessage());
    }

    @Test
    public void testExceptionStoresMessage() {
        VendingMachineException exception =
            new VendingMachineException("Test error");

        assertEquals("Test error", exception.getMessage());
    }

    // Vending-machine behavior tests.
    // These tests currently fail during @BeforeEach because of
    // the constructor defect, before their assertions are reached.

    @Test
    public void testInitialBalanceIsZero() {
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testAddItemToSlotA() {
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        vendingMachine.addItem(item, "A");

        assertEquals(item, vendingMachine.getItem("A"));
    }

    @Test
    public void testAddItemToSlotB() {
        VendingMachineItem item = new VendingMachineItem("Candy", 1.00);

        vendingMachine.addItem(item, "B");

        assertEquals(item, vendingMachine.getItem("B"));
    }

    @Test
    public void testAddItemToSlotC() {
        VendingMachineItem item = new VendingMachineItem("Water", 2.00);

        vendingMachine.addItem(item, "C");

        assertEquals(item, vendingMachine.getItem("C"));
    }

    @Test
    public void testAddItemToSlotD() {
        VendingMachineItem item = new VendingMachineItem("Juice", 2.50);

        vendingMachine.addItem(item, "D");

        assertEquals(item, vendingMachine.getItem("D"));
    }

    @Test
    public void testAddItemWithInvalidCodeThrowsException() {
        assertThrows(
            VendingMachineException.class,
            () -> vendingMachine.addItem(
                new VendingMachineItem("Chips", 1.50), "X"
            )
        );
    }

    @Test
    public void testAddingItemToOccupiedSlotThrowsException() {
        vendingMachine.addItem(
            new VendingMachineItem("Chips", 1.50), "A"
        );

        assertThrows(
            VendingMachineException.class,
            () -> vendingMachine.addItem(
                new VendingMachineItem("Candy", 1.00), "A"
            )
        );
    }

    @Test
    public void testRemovingItemReturnsItem() {
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);
        vendingMachine.addItem(item, "A");

        assertEquals(item, vendingMachine.removeItem("A"));
    }

    @Test
    public void testRemovingItemFromEmptySlotThrowsException() {
        assertThrows(
            VendingMachineException.class,
            () -> vendingMachine.removeItem("A")
        );
    }

    @Test
    public void testInsertZeroMoney() {
        vendingMachine.insertMoney(0.0);

        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testInsertSmallPositiveAmount() {
        vendingMachine.insertMoney(0.01);

        assertEquals(0.01, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testInsertOneDollar() {
        vendingMachine.insertMoney(1.00);

        assertEquals(1.00, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testInsertNegativeMoneyThrowsException() {
        assertThrows(
            VendingMachineException.class,
            () -> vendingMachine.insertMoney(-1.00)
        );
    }

    @Test
    public void testReturnChangeResetsBalance() {
        vendingMachine.insertMoney(5.00);

        assertEquals(5.00, vendingMachine.returnChange(), 0.001);
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }

    @Test
    public void testPurchaseWithEnoughMoney() {
        vendingMachine.addItem(
            new VendingMachineItem("Chips", 2.00), "A"
        );
        vendingMachine.insertMoney(5.00);

        assertTrue(vendingMachine.makePurchase("A"));
        assertEquals(3.00, vendingMachine.getBalance(), 0.001);
        assertNull(vendingMachine.getItem("A"));
    }

    @Test
    public void testPurchaseWithInsufficientMoney() {
        vendingMachine.addItem(
            new VendingMachineItem("Chips", 2.00), "A"
        );
        vendingMachine.insertMoney(1.00);

        assertFalse(vendingMachine.makePurchase("A"));
        assertEquals(1.00, vendingMachine.getBalance(), 0.001);
        assertNotNull(vendingMachine.getItem("A"));
    }

    @Test
    public void testPurchaseFromEmptySlotReturnsFalse() {
        assertFalse(vendingMachine.makePurchase("A"));
    }

    @Test
    public void testPurchaseWithInvalidCodeThrowsException() {
        assertThrows(
            VendingMachineException.class,
            () -> vendingMachine.makePurchase("X")
        );
    }

    @Test
    public void testReturnChangeWhenBalanceIsZero() {
        assertEquals(0.0, vendingMachine.returnChange(), 0.001);
        assertEquals(0.0, vendingMachine.getBalance(), 0.001);
    }
}
