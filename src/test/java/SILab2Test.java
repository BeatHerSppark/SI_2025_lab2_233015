import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SILab2Test {
    @Test
    public void everyStatementTest() {
        // allItems list exception
        RuntimeException err = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(null, "123"));
        assertEquals("allItems list can't be null!", err.getMessage());

        // Invalid item exception
        List<Item> items = List.of(new Item(null, 1, 0, 0));
        err = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items, "123"));
        assertEquals("Invalid item!", err.getMessage());

        // Every statement for items, bad card
        List<Item> items2 = List.of(new Item("Shirt", 11, 350, 0.25),
                new Item("Socks", 5, 200, 0));
        err = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items2, "123"));
        assertEquals("Invalid card number!", err.getMessage());

        // Every statement for items, invalid char in card
        err = assertThrows(RuntimeException.class,
                () -> SILab2.checkCart(items2, "111122223333@444"));
        assertEquals("Invalid character in card number!", err.getMessage());

        // Every statement, reaches return sum
        assertEquals(3857.5, SILab2.checkCart(items2, "1111222233334444"));
    }

    @Test
    public void multipleConditionTest() {
        // TXX
        Item item1 = new Item("Pants", 2, 350, 0);
        assertEquals(670, SILab2.checkCart(List.of(item1), "1111222233334444"));

        // FTX
        Item item2 = new Item("Shoes", 2, 200, 0.5);
        assertEquals(170, SILab2.checkCart(List.of(item2), "1111222233334444"));

        // FFT
        Item item3 = new Item("Belt", 12, 200, 0);
        assertEquals(2370, SILab2.checkCart(List.of(item3), "1111222233334444"));

        // FFF
        Item item4 = new Item("Papuchi", 2, 250, 0);
        assertEquals(500, SILab2.checkCart(List.of(item4), "1111222233334444"));
    }
}
