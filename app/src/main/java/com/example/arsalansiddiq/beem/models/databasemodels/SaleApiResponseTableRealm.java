package com.example.arsalansiddiq.beem.models.databasemodels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by jellani on 9/2/2018.
 */

public class SaleApiResponseTableRealm extends RealmObject{

    @PrimaryKey
    private int id;
    private int saleStatus;
    private int sales_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(int saleStatus) {
        this.saleStatus = saleStatus;
    }

    public int getSales_id() {
        return sales_id;
    }

    public void setSales_id(int sales_id) {
        this.sales_id = sales_id;
    }
}
