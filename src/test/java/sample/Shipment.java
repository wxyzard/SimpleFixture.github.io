package sample;

public class Shipment extends Common{
    private Long id;
    private String shipmentName;
    private String barcode;
    private ShipmentItem shipmentItem;
    private ShipStatus status;
    private Order order;

    public Long getId() {
        return id;
    }

    public String getShipmentName() {
        return shipmentName;
    }

    public String getBarcode() {
        return barcode;
    }

    public ShipmentItem getShipmentItem() {
        return shipmentItem;
    }

    public ShipStatus getStatus() {
        return status;
    }

    public Order getOrder() {
        return order;
    }
}
