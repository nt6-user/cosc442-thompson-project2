Task 5 – JoshHack Testing

Class Tested: Inventory

Method Tested: get(int i)

Test 1: testGetItem()
This test checks the normal valid case. An item is added to an inventory and get(0) is used to retrieve it. The test passes because the correct item is returned.

Test 2: testGetLastItem()
This test checks the boundary case. The inventory has three spaces, so index 2 is the last valid index. The test adds three items and checks that the item at index 2 is returned correctly.

Test 3: testGetInvalidIndex()
This test checks an invalid case. The inventory has three spaces, so index 3 is outside the valid range. The test expects an ArrayIndexOutOfBoundsException when get(3) is called.

All three tests passed. No fault was found in the Inventory.get() method.