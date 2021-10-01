package sample;

public class Shipment extends Common{
    private final Long id;
    private final String shipmentName;
    private final String barcode;
    private final ShipmentItem shipmentItem;
    private final ShipStatus status;
    private final Order order;
    private final byte tels;

    public Shipment(Long id, String shipmentName, String barcode, ShipmentItem shipmentItem, ShipStatus status, Order order,  byte tels) {
        this.id = id;
        this.shipmentName = shipmentName;
        this.barcode = barcode;
        this.shipmentItem = shipmentItem;
        this.status = status;
        this.order = order;
        this.tels = tels;
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
