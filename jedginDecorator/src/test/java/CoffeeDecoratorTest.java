import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CoffeeDecoratorTest {

    @Test
    void testBasicCoffeeCost() {
        CoffeeOrder order = new CoffeeOrder();
        assertEquals(4.50, order.make(), 0.01);
    }

    @Test
    void testExtraShotOnly() {
        CoffeeOrder order = new CoffeeOrder();
        order.addExtraShot();
        assertEquals(4.50 + 1.20, order.make(), 0.01);
    }

    @Test
    void testCreamOnly() {
        CoffeeOrder order = new CoffeeOrder();
        order.addCream();
        assertEquals(4.50 + 0.50, order.make(), 0.01);
    }

    @Test
    void testSugarOnly() {
        CoffeeOrder order = new CoffeeOrder();
        order.addSugar();
        assertEquals(4.50 + 0.50, order.make(), 0.01);
    }

    @Test
    void testSyrupOnly() {
        CoffeeOrder order = new CoffeeOrder();
        order.addSyrup();
        assertEquals(4.50 + 0.75, order.make(), 0.01);
    }

    @Test
    void testBlendOnly() {
        CoffeeOrder order = new CoffeeOrder();
        order.blend();
        assertEquals(4.50 + 1.00, order.make(), 0.01);
    }

    @Test
    void testMultipleAddOns() {
        CoffeeOrder order = new CoffeeOrder();
        order.addExtraShot();
        order.addCream();
        order.addSugar();
        order.addSyrup();
        order.blend();
        double expected = 4.50 + 1.20 + 0.50 + 0.50 + 0.75 + 1.00;
        assertEquals(expected, order.make(), 0.01);
    }

    @Test
    void testRemoveExtraShot() {
        CoffeeOrder order = new CoffeeOrder();
        order.addExtraShot();
        order.addCream();
        order.remove(ExtraShot.class);
        assertEquals(4.50 + 0.50, order.make(), 0.01);
    }

    @Test
    void testRemoveMultipleAddOns() {
        CoffeeOrder order = new CoffeeOrder();
        order.addExtraShot();
        order.addCream();
        order.addSugar();
        order.remove(Cream.class);
        order.remove(Sugar.class);
        assertEquals(4.50 + 1.20, order.make(), 0.01);
    }

    @Test
    void testResetOrder() {
        CoffeeOrder order = new CoffeeOrder();
        order.addExtraShot();
        order.addCream();
        order.resetOrder();
        assertEquals(4.50, order.make(), 0.01);
    }
}

