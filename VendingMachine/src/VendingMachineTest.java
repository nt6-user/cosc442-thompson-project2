import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    public void setUp() {
        vendingMachine = new VendingMachine();
    }

    @AfterEach
    public void tearDown() {
        vendingMachine = null;
    }

    @Test
    public void testGetBalance() {
        // Arrange
        double expected = 0.0;

        // Act
        double actual = vendingMachine.getBalance();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testAddItem() throws VendingMachineException {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        // Act
        vendingMachine.addItem(item, "A");

        // Assert
        assertEquals(item, vendingMachine.getItem("A"));
    }

    @Test
    public void testAddItemInvalidCode() {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);

        // Act and Assert
        try {
            vendingMachine.addItem(item, "X");
            fail("Expected VendingMachineException");
        } catch (VendingMachineException e) {
            // Expected exception
        }
    }

    @Test
    public void testAddItemOccupiedSlot() throws VendingMachineException {
        // Arrange
        VendingMachineItem item1 = new VendingMachineItem("Chips", 1.50);
        VendingMachineItem item2 = new VendingMachineItem("Candy", 1.00);

        vendingMachine.addItem(item1, "A");

        // Act and Assert
        try {
            vendingMachine.addItem(item2, "A");
            fail("Expected VendingMachineException");
        } catch (VendingMachineException e) {
            // Expected exception
        }
    }

    @Test
    public void testRemoveItem() throws VendingMachineException {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Chips", 1.50);
        vendingMachine.addItem(item, "A");

        // Act
        VendingMachineItem result = vendingMachine.removeItem("A");

        // Assert
        assertEquals(item, result);
        assertNull(vendingMachine.getItem("A"));
    }

    @Test
    public void testRemoveEmptyItem() {
        // Act and Assert
        try {
            vendingMachine.removeItem("A");
            fail("Expected VendingMachineException");
        } catch (VendingMachineException e) {
            // Expected exception
        }
    }

    @Test
    public void testInsertMoney() throws VendingMachineException {
        // Arrange
        double amount = 5.00;

        // Act
        vendingMachine.insertMoney(amount);

        // Assert
        assertEquals(5.00, vendingMachine.getBalance());
    }

    @Test
    public void testInsertNegativeMoney() {
        // Act and Assert
        try {
            vendingMachine.insertMoney(-1.00);
            fail("Expected VendingMachineException");
        } catch (VendingMachineException e) {
            // Expected exception
        }
    }

    @Test
    public void testInsertZeroMoney() throws VendingMachineException {
        // Act
        vendingMachine.insertMoney(0.0);

        // Assert
        assertEquals(0.0, vendingMachine.getBalance());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.01, 1.0, 2.50, 5.0, 10.0})
    public void testInsertDifferentAmounts(double amount) throws VendingMachineException {
        // Act
        vendingMachine.insertMoney(amount);

        // Assert
        assertEquals(amount, vendingMachine.getBalance());
    }

    @Test
    public void testReturnChange() throws VendingMachineException {
        // Arrange
        vendingMachine.insertMoney(5.00);

        // Act
        double change = vendingMachine.returnChange();

        // Assert
        assertEquals(5.00, change);
        assertEquals(0.0, vendingMachine.getBalance());
    }

    @Test
    public void testMakePurchase() throws VendingMachineException {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Chips", 2.00);
        vendingMachine.addItem(item, "A");
        vendingMachine.insertMoney(5.00);

        // Act
        boolean result = vendingMachine.makePurchase("A");

        // Assert
        assertTrue(result);
        assertEquals(3.00, vendingMachine.getBalance());
        assertNull(vendingMachine.getItem("A"));
    }

    @Test
    public void testMakePurchaseNotEnoughMoney() throws VendingMachineException {
        // Arrange
        VendingMachineItem item = new VendingMachineItem("Chips", 2.00);
        vendingMachine.addItem(item, "A");
        vendingMachine.insertMoney(1.00);

        // Act
        boolean result = vendingMachine.makePurchase("A");

        // Assert
        assertFalse(result);
        assertEquals(1.00, vendingMachine.getBalance());
    }

    @Test
    public void testMakePurchaseEmptySlot() {
        // Act
        boolean result = vendingMachine.makePurchase("A");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testVendingMachineItem() {
        // Arrange
        String name = "Chips";
        double price = 1.50;

        // Act
        VendingMachineItem item = new VendingMachineItem(name, price);

        // Assert
        assertEquals(name, item.getName());
        assertEquals(price, item.getPrice());
    }

    @Test
    public void testVendingMachineItemNegativePrice() {
        // Act and Assert
        try {
            new VendingMachineItem("Chips", -1.00);
            fail("Expected VendingMachineException");
        } catch (VendingMachineException e) {
            // Expected exception
        }
    }
}