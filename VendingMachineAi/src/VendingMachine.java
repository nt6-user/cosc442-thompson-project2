
public class VendingMachine {

    public static final int NUM_SLOTS = 4;
    public static final String A_CODE = "A";
    public static final String B_CODE = "B";
    public static final String C_CODE = "C";
    public static final String D_CODE = "D";

    private static final double INITIAL_BALANCE = 0;
    private static final String INVALID_CODE_MESSAGE =
            "Invalid code for vending machine item";
    private static final String INVALID_AMOUNT_MESSAGE =
            "Invalid amount.  Amount must be >= 0";
    private static final String SLOT_MESSAGE = "Slot ";
    private static final String ALREADY_OCCUPIED_MESSAGE =
            " already occupied";
    private static final String IS_EMPTY_MESSAGE =
            " is empty -- cannot remove item";

    protected double balance;
    private VendingMachineItem[] itemArray;

    /**
     * Creates a vending machine with four empty slots and a zero balance.
     */
    public VendingMachine() {
        itemArray = new VendingMachineItem[NUM_SLOTS];

        for (int i = 0; i < NUM_SLOTS; i++) {
            itemArray[i] = null;
        }

        this.balance = INITIAL_BALANCE;
    }

    private int getSlotIndex(String code)
            throws VendingMachineException {
        if (code.equals(A_CODE)) {
            return 0;
        } else if (code.equals(B_CODE)) {
            return 1;
        } else if (code.equals(C_CODE)) {
            return 2;
        } else if (code.equals(D_CODE)) {
            return 3;
        } else {
            throw new VendingMachineException(INVALID_CODE_MESSAGE);
        }
    }

    public void addItem(VendingMachineItem item, String code)
            throws VendingMachineException {
        int slotIndex = getSlotIndex(code);

        if (itemArray[slotIndex] != null) {
            throw new VendingMachineException(
                    SLOT_MESSAGE + code + ALREADY_OCCUPIED_MESSAGE);
        } else {
            itemArray[slotIndex] = item;
        }
    }

    protected VendingMachineItem getItem(String code)
            throws VendingMachineException {
        int slotIndex = getSlotIndex(code);
        return itemArray[slotIndex];
    }

    public VendingMachineItem removeItem(String code)
            throws VendingMachineException {
        int slotIndex = getSlotIndex(code);
        VendingMachineItem item = itemArray[slotIndex];
        itemArray[slotIndex] = null;

        if (item == null) {
            throw new VendingMachineException(
                    SLOT_MESSAGE + code + IS_EMPTY_MESSAGE);
        }

        return item;
    }

    public void insertMoney(double amount)
            throws VendingMachineException {
        if (amount < 1) {
            throw new VendingMachineException(INVALID_AMOUNT_MESSAGE);
        }

        this.balance += amount;
    }

    public double getBalance() {
        return this.balance;
    }

    public boolean makePurchase(String code) {
        boolean returnCode = false;
        VendingMachineItem item = getItem(code);

        if ((item != null) && (this.balance >= item.getPrice())) {
            removeItem(code);
            this.balance -= item.getPrice();
            returnCode = true;
        }

        return returnCode;
    }

    public double returnChange() {
        double change = this.balance;
        this.balance = 0;
        return change;
    }
}
