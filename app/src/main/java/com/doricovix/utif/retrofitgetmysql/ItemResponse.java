package com.doricovix.utif.retrofitgetmysql;

import java.util.List;

/**
 * Created by utif on 8/8/2017.
 */

public class ItemResponse {
    private String result;
    private String message;
    private List<ItemScc> itemScc;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ItemScc> getItemScc() {
        return itemScc;
    }

    public void setItemScc(List<ItemScc> itemScc) {
        this.itemScc = itemScc;
    }
}
