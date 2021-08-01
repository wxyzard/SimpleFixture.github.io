package sample;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ShipmentItem {
    private Long id;
    private String vendorItemName;
    private Integer[] ribinPaths;
    private List<Sku> skuList;
    private Map<String, Sku> skuMap;
    private Shipment shipment;

}
