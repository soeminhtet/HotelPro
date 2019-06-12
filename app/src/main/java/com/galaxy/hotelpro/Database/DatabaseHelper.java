package com.galaxy.hotelpro.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import androidx.annotation.Nullable;

import com.galaxy.hotelpro.Model.AvailableRoomsModel;
import com.galaxy.hotelpro.Model.GuestInfoModel;
import com.galaxy.hotelpro.Model.PosUserModel;
import com.galaxy.hotelpro.Model.RoomChargesModel;
import com.galaxy.hotelpro.Utility.Json_Class;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final int DB_VERSION = 1;
    static final String DB_NAME = "Galaxy_Hotel";
    // SystemSetting
    static final String coluse_unit = "use_unit";
    static final String coluse_hotel = "use_hotel";
    // ServiceAddress
    static final String serviceaddressTable = "SystemSetting";
    static final String colipaddress = "ipaddress";
    static final String collanguage = "language";
    static final String colurl = "url";
    static final String coluseitemimage = "useitemimage";
    static final String colusemodifierpopup = "usemodifierpopup";
    static final String coluseeditboxpopup = "useeditpopup"; //Added by ArkarMoe on [20/12/2016]
    static final String colusesingleimageitem = "usesingleimageitem"; //Added by ArkarMoe on [23/12/2016]
    static final String coltimelog = "timelog";
    static final String colremark = "remark";
    static final String colregister = "register";
    static final String colexitpassword = "exitpassword";
    static final String coluse_customer_analysis = "use_customer_analysis";
    static final String colhideitemcancel = "hide_itemcancel";
    static final String colsalesmen_commission = "salesmen_commission";
    static final String colitemviewstyle = "itemviewstyle";
    static final String colbillprintfromtablet = "billprintfromtablet";
    static final String colusemonitorinterface = "usemonitorinterface";
    static final String coluseiscontinuoussave = "iscontinuoussave";
    static final String colDeviceName = "DeviceName";
    static final String DictionaryTable = "Dictionary";
    static final String colsrno = "srno";
    static final String colEnglish = "English";
    static final String colMyanmar = "Myanmar";
    static final String CustomerTable = "Customer";
    static final String colcustomercode = "customercode";
    static final String colcustomername = "name";
    static final String colisOffline = "isOffline";
    //Port
    static final String port_table = "Port_Table";
    static final String colport = "port";
    static final String port_no = "Port_No";

    // PosUser Table
    static final String PosUserTable = "posuser";
    static final String PosUserTableId = "id";
    static final String coluserid = "userid";
    static final String colusershort = "user_short";
    static final String colusername = "name";
    static final String colpassword = "password";
    static final String coluse_tabletuser = "use_tabletuser";
    static final String col_authorize = "is_authorize";

    Context context;


    public DatabaseHelper(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE If Not EXISTS " + serviceaddressTable + " ("
                + colipaddress + " TEXT, " + collanguage + " TEXT," + colurl
                + " TEXT," + coltimelog + " TEXT,"
                + coluseitemimage + " TEXT,"
                + colusemodifierpopup + " TEXT,"
                + coluseeditboxpopup + " TEXT,"
                + colusesingleimageitem + " TEXT,"
                + coluse_unit + " TEXT,"
                + coluse_hotel + " TEXT,"
                + colremark + " TEXT," + colregister + " TEXT,"
                + colexitpassword + " TEXT," + coluse_customer_analysis
                + " TEXT," + colhideitemcancel + " TEXT,"
                + colsalesmen_commission + " TEXT," + colitemviewstyle
                + " TEXT," + colbillprintfromtablet + " TEXT,"
                + colusemonitorinterface + " TEXT,"
                + coluseiscontinuoussave + " TEXT,"
                + colDeviceName + " TEXT,"
                + colisOffline + " TEXT);");

        db.execSQL("CREATE TABLE If Not EXISTS " + port_table + " ("
                + colport + " TEXT," + port_no + " TEXT);");

        db.execSQL("CREATE TABLE If Not EXISTS " + PosUserTable + " ("
                + PosUserTableId + " INTEGER PRIMARY KEY, "
                + coluserid + " INTEGER, " + colusershort
                + " TEXT, " + colusername + " TEXT, " + colpassword + " TEXT, "
                + coluse_tabletuser + " TEXT, " + col_authorize + " BOOLEAN)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void AddServiceAddress(String URL, String Language, String Remark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (!URL.equals(""))
            cv.put(colurl, URL);
        if (!Language.equals(""))
            cv.put(collanguage, Language);
        cv.put(colremark, Remark);

        if (getServiceURL().equals("")) {
            db.execSQL("Delete from  " + serviceaddressTable);
            db.insert(serviceaddressTable, null, cv);
        } else {
            db.update(serviceaddressTable, cv, null, null);
        }
        db.close();
    }



    public String getPortNoFlag() {
        SQLiteDatabase db = this.getWritableDatabase();
        String portNo = "";
        String selectQuery = "SELECT " + port_no + " FROM "
                + port_table;
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    portNo = cursor.getString(0);
                } while (cursor.moveToNext());
            }
            if (portNo == null)
                portNo = "";
            return portNo;
        } finally {
            cursor.close();
        }
    }


    public String getServiceURL() {
        SQLiteDatabase db = this.getWritableDatabase();
        String URL = "";
        String selectQuery = "SELECT " + colurl + " FROM "
                + serviceaddressTable;
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            cursor.moveToFirst();

            if (cursor.moveToFirst()) {
                do {
                    String port_no = this.getPortNoFlag();
                    if (port_no.equals(""))
                        URL = "http://" + cursor.getString(0);
                    else
                        URL = "http://" + cursor.getString(0) + ":" + port_no.trim()+"/";
                } while (cursor.moveToNext());
            }
            if (TextUtils.isEmpty(URL)) {
                URL = "";
            }
            return URL;
        } finally {
            cursor.close();
        }

    }

    public List<AvailableRoomsModel> getAvailalbeRooms() throws JSONException {
        final String availableUrl = "http://192.168.0.21/api/values";
        final String roomChargesUrl = "http://192.168.0.21/api/values/GetRoomCharges";
        List<AvailableRoomsModel> availableRoomsList = new ArrayList<AvailableRoomsModel>();
        List<RoomChargesModel> roomChargesList = new ArrayList<RoomChargesModel>();
        String availableResult = Json_Class.getJson(availableUrl);
        String roomChargesResult = Json_Class.getJson(roomChargesUrl);
        try {
            JSONArray availableArray = new JSONArray(availableResult);
            JSONArray roomChargesArray = new JSONArray(roomChargesResult);
            for(int j=0; j< roomChargesArray.length(); j++){
                JSONObject c = (JSONObject) roomChargesArray.get(j);

                RoomChargesModel roomChargesModel=new RoomChargesModel();
                roomChargesModel.setId(c.get("Room_TypeID").toString());
                roomChargesModel.setDollar(c.get("Date_Dollar").toString());
                roomChargesModel.setKyat(c.get("Date_Kyat").toString());

                roomChargesList.add(roomChargesModel);
            }
            for (int i = 0; i < availableArray.length(); i++) {
                JSONObject c = (JSONObject) availableArray.get(i);

                AvailableRoomsModel checkInModel=new AvailableRoomsModel();
                checkInModel.setSr("");
                checkInModel.setRoom(c.get("Room_Name").toString());
                checkInModel.setRoomType(c.get("Room_Type_Name").toString());

                checkInModel.setBedType(c.get("Bed_Type_Name").toString());
                checkInModel.setExtra("");

                checkInModel.setRoomCode(c.get("Room_Code").toString());

                boolean found=false;
                for (int k=0;k<roomChargesList.size() && found==false;k++){
                    if(roomChargesList.get(k).getId().equals(c.get("Room_TypeID").toString())){
                        checkInModel.setCharges(roomChargesList.get(k).getKyat());
                        checkInModel.setAmount(roomChargesList.get(k).getKyat());
                        checkInModel.setBalance(roomChargesList.get(k).getKyat());
                        found=true;
                    }
                }


                availableRoomsList.add(checkInModel);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return availableRoomsList;
    }

    public List<GuestInfoModel> getGuestInfo() {
        List<GuestInfoModel> guestInfoList = new ArrayList<>();

        final String guestInfoUrl = "http://192.168.0.21/api/values/GetGuestInfo";
        String guestInfoResult = Json_Class.getJson(guestInfoUrl);
        try {
            JSONArray guestInfoArray = new JSONArray(guestInfoResult);
            for (int j = 0; j < guestInfoArray.length(); j++) {
                JSONObject c = (JSONObject) guestInfoArray.get(j);

                GuestInfoModel guestInfoModel = new GuestInfoModel();
                guestInfoModel.setName(c.get("Name").toString());
                guestInfoModel.setDispercent(c.get("Dis_Percent").toString());

                guestInfoList.add(guestInfoModel);
            }
        }catch(JSONException e){
            e.printStackTrace();
        }
        return guestInfoList;
    }


    public void updateRegisterFlag(Boolean register, String DeviceName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(colregister, register);
        cv.put(colDeviceName, DeviceName);
        db.update(serviceaddressTable, cv, null, null);
        db.close();
    }

    public void AddPortAddress(String Port) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (!Port.equals(""))
            cv.put(port_no, Port);
        if (!Port.equals("")) {
            db.execSQL("Delete from  " + port_table);
            db.insert(port_table, null, cv);
        } else {
            cv.put(port_no,"");
            db.update(port_table, cv, null, null);
        }
        db.close();
    }

    public void addPosUser(String url){
        List<PosUserModel> posUserList=new ArrayList<PosUserModel>();
        String posUserResult = Json_Class.getJson(url);
        try{
            JSONArray posUserArray = new JSONArray(posUserResult);
            if (posUserArray.length() > 0) {
                ClearTable("posuser");
            }
            for (int j = 0; j < posUserArray.length(); j++) {
                JSONObject c = (JSONObject) posUserArray.get(j);

                PosUserModel posUser = new PosUserModel();
                posUser.set_userid(Integer.parseInt(c.get("userid").toString()));
                posUser.set_short(c.get("short").toString());
                posUser.set_name(c.get("name").toString());
                posUser.set_password(c.get("password").toString());
                posUser.set_use_tabletuser(c.getBoolean("tablet_user"));
                boolean authorize=c.getBoolean("All_Users");
                if (authorize){
                    posUser.set_is_authorize("true");
                }else {
                    posUser.set_is_authorize("false");
                }
                posUserList.add(posUser);
            }

            addPosUserToDb(posUserList);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void ClearTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
    }

    public void addPosUserToDb(List<PosUserModel> posUserList){
        SQLiteDatabase db = this.getWritableDatabase();

        for (int i=0;i<posUserList.size();i++){
            PosUserModel posUser=posUserList.get(i);
            ContentValues cv = new ContentValues();
            cv.put(coluserid, posUser.get_userid());
            cv.put(colusershort, posUser.get_short());
            cv.put(colusername, posUser.get_name());
            cv.put(colpassword, posUser.get_password());
            cv.put(coluse_tabletuser, posUser.get_use_tabletuser()==true?"true":"false");
            cv.put(col_authorize, posUser.get_is_authorize());

            db.insert(PosUserTable, null, cv);
        }
    }

    public List<PosUserModel> getAllPosUsers(){
        List<PosUserModel> posUserList=new ArrayList<PosUserModel>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=null;
        String sql="Select * from " + PosUserTable;
        cursor=db.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            do{
                PosUserModel Userobj = new PosUserModel();
                Userobj.set_userid(Integer.valueOf(cursor.getString(1)));
                Userobj.set_short(cursor.getString(2));
                Userobj.set_name(cursor.getString(3));
                Userobj.set_password(cursor.getString(4));
                if (cursor.getString(5).equals("true")){
                    Userobj.set_use_tabletuser(true);
                }
                else Userobj.set_use_tabletuser(false);

                Userobj.set_is_authorize(cursor.getString(6));

                posUserList.add(Userobj);
            }while (cursor.moveToNext());
        }
        return posUserList;
    }
}
