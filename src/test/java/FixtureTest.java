import io.github.simplefixture.Fixture;
import sample.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class FixtureTest {

    public static void main(String[] args) {
        Fixture fixture = new Fixture();
        Order order = fixture
                .setProperty("zipcode", "1234")
                .setProperty("barcode", "abcde")
                .setProperty("shipmentList", new ArrayList<>())
                .create(Order.class);

        Assertions.assertEquals(order.getZipcode(), "1234");
    }

}