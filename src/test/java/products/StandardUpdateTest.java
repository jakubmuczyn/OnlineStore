package products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class StandardUpdateTest {
    protected static void testUpdateAndSetter(String initialValue, String newValue, String getterNewValue, String databaseNewValue) {
        assertNotEquals(newValue, initialValue, "Value you are trying to change to is the same as initial value.");
        assertEquals(newValue, getterNewValue, "Setter or getter doesn't work, value didn't change.");
        assertEquals(newValue, databaseNewValue, "Database update didn't work, value didn't change.");
    }
    protected static void testUpdateAndSetter(Double initialValue, Double newValue, Double getterNewValue, Double databaseNewValue) {
        assertNotEquals(newValue, initialValue, "Value you are trying to change to is the same as initial value.");
        assertEquals(newValue, getterNewValue, "Setter or getter doesn't work, value didn't change.");
        assertEquals(newValue, databaseNewValue, "Database update didn't work, value didn't change.");
    }
    protected static void testUpdateAndSetter(int initialValue, int newValue, int getterNewValue, int databaseNewValue) {
        assertNotEquals(newValue, initialValue, "Value you are trying to change to is the same as initial value.");
        assertEquals(newValue, getterNewValue, "Setter or getter doesn't work, value didn't change.");
        assertEquals(newValue, databaseNewValue, "Database update didn't work, value didn't change.");
    }
}
