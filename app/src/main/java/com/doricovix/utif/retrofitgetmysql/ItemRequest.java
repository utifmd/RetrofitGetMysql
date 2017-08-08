package com.doricovix.utif.retrofitgetmysql;

/**
 * Created by utif on 8/8/2017.
 */

public class ItemRequest {
    private String operation;
    private ItemScc scc;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public ItemScc getScc() {
        return scc;
    }

    public void setScc(ItemScc scc) {
        this.scc = scc;
    }
}
