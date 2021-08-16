import io.github.simplefixture.Fixture;
import sample.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FixtureTest {

    Fixture fixture = new Fixture();

    @Test
    void create() {

        Order order = fixture.create(Order.class);

        Assertions.assertEquals(order.getOrderName(), "ordername");
    }
}