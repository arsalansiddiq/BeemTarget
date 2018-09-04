package com.example.arsalansiddiq.beem.databases;

import android.util.Log;

import com.example.arsalansiddiq.beem.activities.LoginActivity;
import com.example.arsalansiddiq.beem.models.HolderListModel;
import com.example.arsalansiddiq.beem.models.databasemodels.MarkAttendance;
import com.example.arsalansiddiq.beem.models.databasemodels.SaleApiResponseTableRealm;
import com.example.arsalansiddiq.beem.models.databasemodels.SalesAndNoSales;
import com.example.arsalansiddiq.beem.models.responsemodels.LoginResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by jellani on 9/1/2018.
 */

public class RealmCRUD {

    private final String LOG_TAG = RealmCRUD.class.getName();
    private boolean check;

//    private Context context;
    private Realm realm;


    public RealmCRUD() {
//        this.context = context;
        realm = Realm.getDefaultInstance();
    }

    public void addUserLoginInformation(final LoginResponse loginResponse, final int loginStatus) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNumber = realm.where(LoginResponse.class).max("id");
                int nextId;

                if (currentIdNumber == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNumber.intValue() + 1;
                }

                LoginResponse loginResponse1 = realm.createObject(LoginResponse.class, nextId);
                loginResponse1.setUserId(loginResponse.getUserId());
                loginResponse1.setName(loginResponse.getName());
                loginResponse1.setBrand(loginResponse.getBrand());
                loginResponse1.setuT(loginResponse.getuT());
                loginResponse1.setStoreId(loginResponse.getStoreId());
                loginResponse1.setStatus(loginResponse.getStatus());
                loginResponse1.setLoginStatus(loginStatus);
            }
        });
    }

    public void updateLoginStatus(final int userId, final int status) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                LoginResponse loginResponse = realm.where(LoginResponse.class).equalTo("userId", userId).findFirst();

                loginResponse.setLoginStatus(status);
                realm.copyToRealmOrUpdate(loginResponse);
                realm.commitTransaction();
            }
        });
    }

    public LoginResponse getLoginInformationDetails() {

        LoginResponse loginResponse1;

        RealmResults<LoginResponse> loginResponse = realm.where(LoginResponse.class).findAll();

        if (loginResponse.size() > 0) {
            loginResponse1 = loginResponse.last();
        } else {
            loginResponse1 = null;
        }

        return loginResponse1;
    }

    public boolean checkLoginIdExist(int id) {

        LoginResponse loginResponse = realm.where(LoginResponse.class).equalTo("userId", id).findFirst();

        if (loginResponse != null) {
            return true;
        } else {
            return false;
        }
    }

    public void addMarkAttendanceDetails(final MarkAttendance markAttendance) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNumber = realm.where(MarkAttendance.class).max("id");
                int nextId;

                if (currentIdNumber == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNumber.intValue() + 1;
                }
                MarkAttendance markAttendanceSet = realm.createObject(MarkAttendance.class, nextId);
                markAttendanceSet.setEmpid(markAttendance.getEmpid());
                markAttendanceSet.setDate(markAttendance.getDate());
                markAttendanceSet.setName(markAttendance.getName());
                markAttendanceSet.setStartImage(markAttendance.getStartImage());
                markAttendanceSet.setStartTime(markAttendance.getStartTime());
                markAttendanceSet.setLatitude(markAttendance.getLatitude());
                markAttendanceSet.setLongitude(markAttendance.getLongitude());
                markAttendanceSet.setStatus(markAttendance.getStatus());
            }
        });
    }

    public List<MarkAttendance> getUnsavedMarkAttendanceData() {
        List<MarkAttendance> markAttendanceList = realm.where(MarkAttendance.class).equalTo("status", 0).findAll();

        if (markAttendanceList != null) {
            return markAttendanceList;
        } else {
            return null;
        }
    }

    public void insertSalesApiResponse(final SaleApiResponseTableRealm saleApiResponseTableRealm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                Number currentIdNumber = realm.where(SaleApiResponseTableRealm.class).max("id");
                int nextId;

                if (currentIdNumber == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNumber.intValue() + 1;
                }

                SaleApiResponseTableRealm saleApiResponseTableRealm1 = realm.createObject(SaleApiResponseTableRealm.class, nextId);
                saleApiResponseTableRealm1.setSaleStatus(saleApiResponseTableRealm.getSaleStatus());
                saleApiResponseTableRealm1.setSales_id(saleApiResponseTableRealm.getSales_id());
            }
        });
    }


    public void insertOrderDetails(final HolderListModel holderListModel, final String getDate, final int sales_id,
                                   final int orderId, final int orderStatus) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

//                for (int i = 0; i < orderApiResponseTableRealmList.size(); i++) {

                    Number currentIdNumber = realm.where(HolderListModel.class).max("id");
                    int nextId;

                    if (currentIdNumber == null) {
                        nextId = 1;
                    } else {
                        nextId = currentIdNumber.intValue() + 1;
                    }

//                    OrderApiResponseTableRealm holderListModel = orderApiResponseTableRealmList.get(i);
                    HolderListModel holderListModel1 = realm.createObject(HolderListModel.class, nextId);
                    holderListModel1.setStoreId(holderListModel.getStoreId());
                    holderListModel1.setSalesId(sales_id);
                    holderListModel1.setoDate(getDate);
                    holderListModel1.setBrand(holderListModel.getBrand());
                    holderListModel1.setSkuCategory(holderListModel.getSkuCategory());
                    holderListModel1.setSKU(holderListModel.getSKU());
                    holderListModel1.setSaleType(holderListModel.getSaleType());
                    holderListModel1.setNoItem(holderListModel.getNoItem());
                    holderListModel1.setPrice(holderListModel.getPrice());
                    holderListModel1.setsAmount(holderListModel.getsAmount());
                    holderListModel1.setOrderStatus(orderStatus);
                    holderListModel1.setOrderId(orderId);
//                }

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(LOG_TAG, "Insertion Success");
                check = true;
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i(LOG_TAG, "Insertion Failed");
                check = false;
            }
        });
    }

    public List<HolderListModel> getPendingOrder() {
        List<HolderListModel> holderListModelList = realm.where(HolderListModel.class).equalTo("orderStatus", 0).findAll();

        if (holderListModelList.size() > 0) {
            return holderListModelList;
        } else {
            return null;
        }
    }

    public void updateOrderStatus (final int id, final int orderId, final int orderStatus) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                HolderListModel holderListModel = realm.where(HolderListModel.class).equalTo("id", id).findFirst();

                holderListModel.setOrderId(orderId);
                holderListModel.setOrderStatus(orderStatus);
//                realm.copyToRealmOrUpdate(holderListModel);
//                realm.commitTransaction();
            }
        });
    }

    public SalesAndNoSales getSaleAndNoSales () {

        SalesAndNoSales salesAndNoSales = null;

        try {
            int sale = (int) realm.where(HolderListModel.class).equalTo("orderStatus", 1).count();
            int noSale = (int) realm.where(HolderListModel.class).equalTo("orderStatus", 0).count();

        salesAndNoSales = new SalesAndNoSales(sale, noSale);

        }catch (NumberFormatException e) {

        }

        return salesAndNoSales;


    }

    public int getLastLoggedInUserStoreID() {
        List<LoginResponse> loginResponses = realm.where(LoginResponse.class).equalTo("loginStatus", 1).findAll();

        if (loginResponses.size() != 0) {
            return Integer.parseInt(loginResponses.get(loginResponses.size() - 1).getStoreId());
        } else {
            return 0;
        }
    }

}
