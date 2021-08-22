import io.github.simplefixture.Fixture;
import sample.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FixtureTest {

    public static void main(String[] args) {
        Fixture fixture = new Fixture();
        Order order = fixture.create(Order.class);

        Assertions.assertEquals(order, "ordername");
    }

}