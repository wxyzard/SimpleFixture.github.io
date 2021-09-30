package sample;

public class Shipment extends Common{
    private Long id;
    private String shipmentName;
    private String barcode;
    private ShipmentItem shipmentItem;
    private ShipStatus status;
    private Order order;

    public Shipment(Long id, String shipmentName, String barcode, ShipmentItem shipmentItem, ShipStatus status, Order order) {
        this.id = id;
        this.shipmentName = shipmentName;
        this.barcode = barcode;
        this.shipmentItem = shipmentItem;
        this.status = status;
        this.order = order;
    }

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
