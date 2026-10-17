
package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import org.junit.jupiter.api.Test;

public class InventoryTest {

    private Item createSword() {
        return new Item('s', Color.WHITE, "Sword", "Sword");
    }

    private Item createPotion() {
        return new Item('p', Color.WHITE, "Potion", "Potion");
    }

    private Item createArmor() {
        return new Item('a', Color.WHITE, "Armor", "Armor");
    }

    // Constructor and get methods

    @Test
    public void testNewInventoryContainsEmptySlots() {
        Inventory inventory = new Inventory(3);

        assertEquals(3, inventory.getItems().length);
        assertNull(inventory.get(0));
        assertNull(inventory.get(1));
        assertNull(inventory.get(2));
    }

    @Test
    public void testGetItem() {
        Inventory inventory = new Inventory(3);
        Item item = createSword();

        inventory.add(item);

        assertEquals(item, inventory.get(0));
    }

    @Test
    public void testGetLastItem() {
        Inventory inventory = new Inventory(3);
        Item item1 = createSword();
        Item item2 = createPotion();
        Item item3 = createArmor();

        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

        assertEquals(item3, inventory.get(2));
    }

    @Test
    public void testGetInvalidIndex() {
        Inventory inventory = new Inventory(3);

        assertThrows(
            ArrayIndexOutOfBoundsException.class,
            () -> inventory.get(3)
        );
    }

    @Test
    public void testGetItemsReturnsInventoryArray() {
        Inventory inventory = new Inventory(2);
        Item item = createSword();

        inventory.add(item);

        assertEquals(item, inventory.getItems()[0]);
        assertEquals(2, inventory.getItems().length);
    }

    // Adding items

    @Test
    public void testAddItemToFirstEmptySlot() {
        Inventory inventory = new Inventory(3);
        Item item = createSword();

        inventory.add(item);

        assertEquals(item, inventory.get(0));
    }

    @Test
    public void testAddItemUsesNextEmptySlot() {
        Inventory inventory = new Inventory(3);
        Item item1 = createSword();
        Item item2 = createPotion();

        inventory.add(item1);
        inventory.add(item2);

        assertEquals(item1, inventory.get(0));
        assertEquals(item2, inventory.get(1));
    }

    @Test
    public void testAddItemAfterRemovalUsesEmptySlot() {
        Inventory inventory = new Inventory(3);
        Item item1 = createSword();
        Item item2 = createPotion();
        Item item3 = createArmor();

        inventory.add(item1);
        inventory.add(item2);
        inventory.remove(item1);
        inventory.add(item3);

        assertEquals(item3, inventory.get(0));
        assertEquals(item2, inventory.get(1));
    }

    @Test
    public void testAddingItemToFullInventoryDoesNotReplaceItems() {
        Inventory inventory = new Inventory(2);
        Item item1 = createSword();
        Item item2 = createPotion();
        Item item3 = createArmor();

        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

        assertEquals(item1, inventory.get(0));
        assertEquals(item2, inventory.get(1));
    }

    // Removing items

    @Test
    public void testRemoveItem() {
        Inventory inventory = new Inventory(3);
        Item item = createSword();

        inventory.add(item);
        inventory.remove(item);

        assertNull(inventory.get(0));
        assertFalse(inventory.contains(item));
    }

    @Test
    public void testRemoveItemFromMiddleSlot() {
        Inventory inventory = new Inventory(3);
        Item item1 = createSword();
        Item item2 = createPotion();
        Item item3 = createArmor();

        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

        inventory.remove(item2);

        assertEquals(item1, inventory.get(0));
        assertNull(inventory.get(1));
        assertEquals(item3, inventory.get(2));
    }

    @Test
    public void testRemoveItemNotInInventoryDoesNothing() {
        Inventory inventory = new Inventory(2);
        Item item1 = createSword();
        Item item2 = createPotion();

        inventory.add(item1);
        inventory.remove(item2);

        assertEquals(item1, inventory.get(0));
        assertNull(inventory.get(1));
    }

    // isFull()

    @Test
    public void testEmptyInventoryIsNotFull() {
        Inventory inventory = new Inventory(3);

        assertFalse(inventory.isFull());
    }

    @Test
    public void testPartiallyFilledInventoryIsNotFull() {
        Inventory inventory = new Inventory(3);
        inventory.add(createSword());

        assertFalse(inventory.isFull());
    }

    @Test
    public void testFullInventoryIsFull() {
        Inventory inventory = new Inventory(2);
        inventory.add(createSword());
        inventory.add(createPotion());

        assertTrue(inventory.isFull());
    }

    @Test
    public void testInventoryIsNotFullAfterRemoval() {
        Inventory inventory = new Inventory(2);
        Item item1 = createSword();
        Item item2 = createPotion();

        inventory.add(item1);
        inventory.add(item2);
        inventory.remove(item1);

        assertFalse(inventory.isFull());
    }

    // contains()

    @Test
    public void testContainsExistingItem() {
        Inventory inventory = new Inventory(3);
        Item item = createSword();

        inventory.add(item);

        assertTrue(inventory.contains(item));
    }

    @Test
    public void testDoesNotContainMissingItem() {
        Inventory inventory = new Inventory(3);

        assertFalse(inventory.contains(createSword()));
    }

    @Test
    public void testContainsUsesObjectIdentity() {
        Inventory inventory = new Inventory(2);
        Item storedItem = createSword();
        Item differentItem = createSword();

        inventory.add(storedItem);

        assertTrue(inventory.contains(storedItem));
        assertFalse(inventory.contains(differentItem));
    }

    @Test
    public void testContainsNullWhenInventoryHasEmptySlot() {
        Inventory inventory = new Inventory(2);

        assertTrue(inventory.contains(null));
    }

    @Test
    public void testDoesNotContainRemovedItem() {
        Inventory inventory = new Inventory(2);
        Item item = createSword();

        inventory.add(item);
        inventory.remove(item);

        assertFalse(inventory.contains(item));
    }
}
