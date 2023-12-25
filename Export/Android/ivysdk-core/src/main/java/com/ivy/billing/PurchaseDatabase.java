package com.ivy.billing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ivy.billing.PurchaseManager.PurchaseState;

public class PurchaseDatabase {
  private static final String TAG = "PurchaseDatabase";

  private static final int DATABASE_VERSION = 2;

  public static final String DATABASE_NAME = "purchase.db";

  public static final String PURCHASED_PRODUCT_ID_COL = "_id";
  private static final String PURCHASED_ITEMS_TABLE_NAME = "purchased";
  public static final String PURCHASED_QUANTITY_COL = "quantity";
  private static final String PURCHASE_HISTORY_TABLE_NAME = "history";

  public static final String SUBSCRIPTION_EXPIRATION = "expiration";

  public static final String HISTORY_DEVELOPER_PAYLOAD_COL = "developerPayload";
  public static final String HISTORY_ORDER_ID_COL = "_id";
  public static final String HISTORY_PRODUCT_ID_COL = "productId";
  public static final String HISTORY_PURCHASE_TIME_COL = "purchaseTime";
  public static final String HISTORY_STATE_COL = "state";


  private static final String[] HISTORY_COLUMNS = new String[]{HISTORY_ORDER_ID_COL, HISTORY_PRODUCT_ID_COL, HISTORY_STATE_COL, HISTORY_PURCHASE_TIME_COL, HISTORY_DEVELOPER_PAYLOAD_COL};
  private static final String[] PURCHASED_COLUMNS = new String[]{PURCHASED_PRODUCT_ID_COL, PURCHASED_QUANTITY_COL};
  private static final String[] SUBSCRIPTION_CANCEL_COLUMNS = new String[]{"_id", SUBSCRIPTION_EXPIRATION};
  private static final String SUBSCRIPTION_CANCEL_EXPIRATION_TABLE_NAME = "subscriptionExpiration";
  private DatabaseHelper mDatabaseHelper;
  private SQLiteDatabase mDb;

  private class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
      super(context, PurchaseDatabase.DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      createPurchaseTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      if (oldVersion == 1 && newVersion == 2) {
        db.execSQL("DROP TABLE IF EXISTS subscriptionExpiration");
        db.execSQL("CREATE TABLE subscriptionExpiration(_id TEXT PRIMARY KEY, expiration INTEGER)");
      } else if (newVersion != 2) {
        db.execSQL("DROP TABLE IF EXISTS history");
        db.execSQL("DROP TABLE IF EXISTS purchased");
        db.execSQL("DROP TABLE IF EXISTS subscriptionExpiration");
        createPurchaseTable(db);
      }
    }

    private void createPurchaseTable(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE history(_id TEXT PRIMARY KEY, state INTEGER, productId TEXT, developerPayload TEXT, purchaseTime INTEGER)");
      db.execSQL("CREATE TABLE purchased(_id TEXT PRIMARY KEY, quantity INTEGER)");
      db.execSQL("CREATE TABLE subscriptionExpiration(_id TEXT PRIMARY KEY, expiration INTEGER)");
    }
  }

  public PurchaseDatabase(Context context) {
    this.mDatabaseHelper = new DatabaseHelper(context);
    this.mDb = this.mDatabaseHelper.getWritableDatabase();
  }

  public void beginTransaction() {
    this.mDb.beginTransaction();
  }

  public void setTransactionSuccessfull() {
    this.mDb.setTransactionSuccessful();
  }

  public void endTransaction() {
    this.mDb.endTransaction();
  }

  public void close() {
    this.mDatabaseHelper.close();
  }

  private void insertOrder(String orderId, String productId, PurchaseState state, long purchaseTime, String developerPayload) {
    ContentValues values = new ContentValues();
    values.put("_id", orderId);
    values.put("productId", productId);
    values.put("state", state.ordinal());
    values.put(HISTORY_PURCHASE_TIME_COL, purchaseTime);
    values.put(HISTORY_DEVELOPER_PAYLOAD_COL, developerPayload);
    this.mDb.replace(PURCHASE_HISTORY_TABLE_NAME, null, values);
  }

  public void deletePurchasedItem(String productId) {
    this.mDb.delete(PURCHASED_ITEMS_TABLE_NAME, "_id=?", new String[]{productId});
  }

  public void updatePurchasedItem(String productId, int quantity) {
    if (quantity == 0) {
      this.mDb.delete(PURCHASED_ITEMS_TABLE_NAME, "_id=?", new String[]{productId});
      return;
    }
    ContentValues values = new ContentValues();
    values.put("_id", productId);
    values.put(PURCHASED_QUANTITY_COL, quantity);
    this.mDb.replace(PURCHASED_ITEMS_TABLE_NAME, null, values);
  }

  public synchronized int updatePurchase(String orderId, String productId, PurchaseState purchaseState, long purchaseTime, String developerPayload) {
    int quantity;
    insertOrder(orderId, productId, purchaseState, purchaseTime, developerPayload);
    Cursor cursor = this.mDb.query(PURCHASE_HISTORY_TABLE_NAME, HISTORY_COLUMNS, "productId=?", new String[]{productId}, null, null, null, null);
    if (cursor == null) {
      quantity = 0;
    } else {
      quantity = 0;
      while (cursor.moveToNext()) {
        try {
          if (PurchaseState.valueOf(cursor.getInt(2)) == PurchaseState.PURCHASED) {
            quantity++;
          }
        } catch (Throwable th) {
          if (cursor != null) {
            cursor.close();
          }
        }
      }
      updatePurchasedItem(productId, quantity);
      if (cursor != null) {
        cursor.close();
      }
    }
    return quantity;
  }

  public void addCanceledSubscription(String subscriptionId, long expirationTime) {
    ContentValues values = new ContentValues();
    values.put("_id", subscriptionId);
    values.put(SUBSCRIPTION_EXPIRATION, Long.valueOf(expirationTime));
    this.mDb.replace(SUBSCRIPTION_CANCEL_EXPIRATION_TABLE_NAME, null, values);
  }

  public void deleteCanceledSubscription(String subscriptionId) {
    this.mDb.delete(SUBSCRIPTION_CANCEL_EXPIRATION_TABLE_NAME, "_id=?", new String[]{subscriptionId});
  }

  public Cursor queryAllPurchasedItems() {
    return this.mDb.query(PURCHASED_ITEMS_TABLE_NAME, PURCHASED_COLUMNS, null, null, null, null, null);
  }

  public Cursor queryAllHistoryItems() {
    return this.mDb.query(PURCHASE_HISTORY_TABLE_NAME, HISTORY_COLUMNS, null, null, null, null, null);
  }

  public Cursor queryAllCanceledSubscriptions() {
    return this.mDb.query(SUBSCRIPTION_CANCEL_EXPIRATION_TABLE_NAME, SUBSCRIPTION_CANCEL_COLUMNS, null, null, null, null, null);
  }
}