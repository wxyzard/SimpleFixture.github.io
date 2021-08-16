package sample;

import lombok.Data;

@Data
public class Sku {
    private Long id;
    private Long externalSkuId;
    private float skuRate;
    private int quantity;
}
