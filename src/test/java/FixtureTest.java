import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.FixtureConfig;
import sample.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class FixtureTest {

    Fixture fixture = new Fixture();

    @Test
    void create() {

        FixtureConfig config = new FixtureConfig(1);

        Order order = fixture
                .config(config)
                .create(Order.class);

        Assertions.assertEquals(order.getOrderName(), "orderName");
    }
}