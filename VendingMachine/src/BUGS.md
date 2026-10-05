Vending Machine Bugs




Bug 1 – VendingMachine Constructor

Observed failure: All tests initially failed with an ArrayIndexOutOfBoundsException when creating a new VendingMachine.

Test: The tests that create a VendingMachine in setUp() exposed the problem.

Source fault: The constructor used i <= NUM_SLOTS in the for loop. This caused the code to access index 4 even though the array only has indexes 0 through 3.

Diagnosis: NUM_SLOTS is 4, so the loop should stop before reaching index 4.

Correction: Changed i <= NUM_SLOTS to i < NUM_SLOTS.




Bug 2 – insertMoney() Rejects Zero

Observed failure: testInsertZeroMoney() failed with VendingMachineException: Invalid amount. Amount must be >= 0.

Test: testInsertZeroMoney().

Source fault: insertMoney() used if (amount < 1), which rejects 0.0.

Diagnosis: The error message and requirements allow amounts greater than or equal to zero, so 0.0 should be accepted.

Correction: Changed the condition from amount < 1 to amount < 0.