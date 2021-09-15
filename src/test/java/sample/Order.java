package sample;


import java.util.List;


public class Order extends Common{
    private Long orderNumber;
    private String orderName;
    private String zipcode;
    private boolean isTelco;
    private List<Shipment> shipmentList;

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
