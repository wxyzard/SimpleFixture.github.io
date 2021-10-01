package sample;


import java.time.ZonedDateTime;
import java.util.List;


public class Order extends Common{
    private static final String ORDER_PREPIX = "Order_";
    private Long orderNumber;
    private String orderName;
    private String zipcode;
    private boolean isTelco;
    private List<Shipment> shipmentList;
    private ZonedDateTime eventTime;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public boolean isTelco() {
        return isTelco;
    }

    public List<Shipment> getShipmentList() {
        return shipmentList;
    }
}
