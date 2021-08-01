package sample;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long orderNumber;
    private String orderName;
    private String zipcode;
    private boolean isTelco;
    private List<Shipment> shipmentList;

}
