import org.example.CashMachine;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CashMachineTest {

    @Test
    public void testAddNotes() {
        CashMachine cashMachine = new CashMachine();
        assertEquals("OK", cashMachine.execute("+ USD 100 30"));
    }

    @Test
    public void testGetCash() {
        CashMachine cashMachine = new CashMachine();
        cashMachine.execute("+ USD 100 30");
        cashMachine.execute("+ USD 50 25");
        assertEquals("100 1\n50 1", cashMachine.execute("- USD 150"));
    }

    @Test
    public void testPrintCash() {
        CashMachine cashMachine = new CashMachine();
        cashMachine.execute("+ USD 100 30");
        cashMachine.execute("+ USD 50 25");
        cashMachine.execute("+ EUR 50 15");
        assertEquals("EUR 50 15\nUSD 50 25\nUSD 100 30\nOK", cashMachine.execute("?"));
    }

}
