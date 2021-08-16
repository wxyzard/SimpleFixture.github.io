import com.yarm.fixturegen.Fixture;
import com.yarm.fixturegen.config.FixtureConfig;
import com.yarm.fixturegen.theme.FCTheme;
import sample.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FixtureTest2 {

    Fixture fixture = new Fixture();

    @Test
    void create() {

        Order order = fixture.create(Order.class);

        Assertions.assertEquals(order.getOrderName(), "ordername");
    }
}