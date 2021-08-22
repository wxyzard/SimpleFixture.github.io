package sample;

public class Shipment extends Common{
    private Long id;
    private String shipmentName;
    private String barcode;
    private ShipmentItem shipmentItem;
    private ShipStatus status;
    private Order order;
}
