package sample;


public class Sku extends Common{
    private long id;
    private Long externalSkuId;
    private float skuRate;
    private int quantity;

    private Sku(long id, Long externalSkuId){
        this.id = id;
        this.externalSkuId = externalSkuId;
    }
}
