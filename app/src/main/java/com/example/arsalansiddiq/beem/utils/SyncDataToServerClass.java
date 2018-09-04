package com.example.arsalansiddiq.beem.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.arsalansiddiq.beem.databases.RealmCRUD;
import com.example.arsalansiddiq.beem.interfaces.SampleInterface;
import com.example.arsalansiddiq.beem.models.HolderListModel;
import com.example.arsalansiddiq.beem.models.responsemodels.LoginResponse;

import java.util.List;

import io.realm.Realm;

/**
 * Created by jellani on 9/2/2018.
 */

public class SyncDataToServerClass {

    private final String LOG_TAG = SyncDataToServerClass.class.getName();

    private Context context;
    private NetworkUtils networkUtils;
    private Realm realm;
    private RealmCRUD realmCRUD;


    public SyncDataToServerClass(Context context) {
        this.context = context;
        networkUtils = new NetworkUtils(context);
        realm = Realm.getDefaultInstance();
        realmCRUD = new RealmCRUD();
    }

//    public void updateOrderStatus(List<HolderListModel> holderListModelList) {
    public void updateOrderStatus() {
        if (networkUtils.isNetworkConnected()) {

            List<HolderListModel> holderListModelList = realmCRUD.getPendingOrder();

            for (int i = 0; i < holderListModelList.size(); i++) {

                final HolderListModel holderListModel = holderListModelList.get(i);

//                if (isConnected) {
                    networkUtils.sendOrderDetail(holderListModel.getStoreId(), holderListModel.getSalesId(), holderListModel.getoDate(),
                            holderListModel.getBrand(), holderListModel.getSkuCategory(), holderListModel.getSKU(),
                            holderListModel.getSaleType(), holderListModel.getNoItem(), holderListModel.getPrice(),
                            holderListModel.getsAmount(), new SampleInterface() {

                                @Override
                                public void success(LoginResponse loginResponse) {
                                    if (loginResponse.getStatus() == 1) {
                                        realmCRUD.updateOrderStatus(holderListModel.getId(), Integer.parseInt(loginResponse.getOrder_id()), loginResponse.getStatus());
                                    }
                                }

                                @Override
                                public void failed(String error) {
                                    alerts();
                                }
                            });
                }

                } else {
            alerts();
        }
    }

    void alerts() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("Network")
                .setMessage("Please Check your internet connection")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                            insertorAddORder(sales_id, isConnected);
                    }
                });

        alertBuilder.show();
    }
}
