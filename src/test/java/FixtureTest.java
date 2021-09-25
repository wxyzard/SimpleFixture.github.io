import io.github.simplefixture.Fixture;
import io.github.simplefixture.config.FixtureConfig;
import sample.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class FixtureTest {

    @Test
    public void testBasicFixture() {
        Fixture fixture = new Fixture();
        Order order = fixture
                .setProperty("zipcode", "1234")
                .setProperty("barcode", "barcode-xxx")
                .create(Order.class);

        Assertions.assertEquals(order.getZipcode(), "1234");
        Assertions.assertEquals(order.getShipmentList().get(0).getBarcode(), "barcode-xxx");
        Assertions.assertEquals(order.getShipmentList().get(1).getBarcode(), "barcode-xxx2");
        Assertions.assertEquals(order.getShipmentList().size(), 2);
    }

    @Test
    public void testFixtureWithConfig() {

        FixtureConfig config = new FixtureConfig
                .Builder()
                .maxCollectionSize(3)
                .build();

        Fixture fixture = new Fixture();
        Order order = fixture
                .config(config)
                .create(Order.class);

        Assertions.assertEquals(order.getShipmentList().size(), 3);

    }

    @Test
    public void testJsonFixture() {
        Fixture fixture = new Fixture();
        Order order = fixture
                .create("{\"orderNumber\": \"1234\",\"orderName\": \"user\",\"zipcode\": \"1234\",\"isTelco\": \"true\", \"createdAt\": \"2021-01-01 12:00:00\", \"updatedAt\": \"2021-01-01 12:00:00\"}",Order.class);


        Assertions.assertEquals(order.getOrderName(), "user");
        Assertions.assertEquals(order.getOrderNumber(), 1234);
        Assertions.assertEquals(order.getZipcode(), "1234");
    }









}