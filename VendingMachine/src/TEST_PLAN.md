Vending Machine Test Plan

VendingMachine Constructor

Valid case: Create a new VendingMachine.

Boundary case: Verify the machine starts with zero balance and all four slots are empty.

Expected result: The balance is 0.0 and each slot contains no item.

JUnit tests: Test the initial balance and verify that items can be added to each valid slot.

addItem()

Valid cases: Add an item to each valid slot (A, B, C, and D).

Invalid case: Attempt to add an item using an invalid slot code.

Exception case: Attempt to add an item to a slot that is already occupied.

Boundary cases: Add an item to the first slot (A) and last slot (D).

Expected result: The item is placed in the requested slot. Invalid codes and occupied slots throw VendingMachineException.

JUnit tests: Test adding valid items, invalid codes, and occupied slots.

getItem()

Valid case: Retrieve an item from an occupied slot.

Boundary case: Retrieve an item from the first and last valid slots.

Invalid case: Use an invalid slot code.

Expected result: The correct item is returned. An invalid code throws VendingMachineException. An empty slot returns null.

JUnit tests: Test valid, empty, and invalid slot codes.

removeItem()

Valid case: Remove an item from an occupied slot.

Invalid case: Attempt to remove an item from an empty slot.

Exception case: Use an invalid slot code.

Boundary cases: Remove items from slots A and D.

Expected result: The correct item is returned and the slot becomes empty. Empty or invalid slots produce VendingMachineException.

JUnit tests: Test successful removal, empty slots, and invalid codes.

insertMoney()

Valid case: Insert a positive amount such as 5.00.

Boundary case: Insert 0.00 because the documented precondition allows amounts greater than or equal to zero.

Invalid case: Insert a negative amount.

Multiple-value case: Insert money more than once.

Expected result: Positive amounts increase the balance. Zero does not change the balance. Negative amounts throw VendingMachineException.

JUnit tests: Test positive, zero, negative, and multiple deposits.

getBalance()

Valid case: Get the balance after inserting money.

Boundary case: Get the balance immediately after creating a new machine.

Expected result: The returned balance matches the amount currently stored in the machine.

JUnit tests: Test an initial balance of 0.0 and balances after inserting money.

makePurchase()

Valid case: Purchase an item when enough money has been inserted.

Invalid cases: Attempt to purchase when there is not enough money or when the selected slot is empty.

Exception case: Use an invalid slot code.

Boundary case: Purchase an item with exactly the amount required.

Expected result: A successful purchase returns true, removes the item, and subtracts its price from the balance. An unsuccessful purchase returns false.

JUnit tests: Test successful purchases, insufficient funds, empty slots, exact payment, and invalid codes.

returnChange()

Valid case: Return change after inserting money.

Boundary case: Return change when the balance is 0.0.

Expected result: The current balance is returned and the machine balance is reset to 0.0.

JUnit tests: Test returning a positive balance and returning change with a zero balance.

VendingMachineItem Constructor

Valid case: Create an item with a normal positive price.

Boundary case: Create an item with a price of 0.0.

Invalid case: Create an item with a negative price.

Expected result: Valid prices create the item successfully. A negative price throws VendingMachineException.

JUnit tests: Test positive, zero, and negative prices.

VendingMachineItem Getters

Valid case: Create an item and retrieve its name and price.

Expected result: getName() returns the item's name and getPrice() returns the item's price.

JUnit tests: Test both getter methods using typical item values.