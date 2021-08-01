package sample;

import lombok.Data;

@Data
public class Sku {
    private Long id;
    private Long externalSkuId;
    private int quantity;
}
