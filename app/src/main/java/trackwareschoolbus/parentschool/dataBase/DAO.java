package trackwareschoolbus.parentschool.dataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import trackwareschoolbus.parentschool.bean.ImportantNotification;
import trackwareschoolbus.parentschool.bean.MessageHistoryObject;
import trackwareschoolbus.parentschool.bean.NotificationObj;
import trackwareschoolbus.parentschool.utilityParent.DateTools;
import trackwareschoolbus.parentschool.utilityParent.MyGson;
import trackwareschoolbus.parentschool.utilityParent.StringUtil;


public class DAO {

    public static final String TAG = DAO.class.getSimpleName();

    public static final String CheckInOut_TBL = "CheckInOut";
    //    public static final String CheckInOut_checkid = "checkid";
    public static final String CheckInOut_message = "message";
    public static final String CheckInOut_data = "data";
    public static final String CheckInOut_date = "date";
    /**/
    public static final String LonLat_TBL = "LonLat";
    //    public static final String LonLat_LonLatId = "LonLatId";
    public static final String LonLat_longitude = "longitude";
    public static final String LonLat_latitude = "latitude";
    public static final String LonLat_date = "date";

    /**/
    //////////////////
    public static final String tbl_unsent_messages = "tbl_unsent_messages";
    //    public static final String student_id = "student_id";
    public static final String pending_message = "pending_message";
    //////////////////
    /**/
    /*tbl_sent_messages*/
    public static final String tbl_sent_messages = "tbl_sent_messages";
    public static final String student_id = "student_id";
    public static final String school_id = "school_id";
    public static final String sender = "sender";
    public static final String date_time = "date_time";
    public static final String type = "type";
    public static final String message = "message";
    public static final String sender_id = "sender_id";
    //////////////////
    public static final String tbl_notifications = "tbl_notifications";
    /**/

    /**/


    public static class ImportantNotificationTable {
        private static final String table_name = "tbl_important_notifications";
        private static final String notification_id = "notification_id";
        private static final String notification_data = "notification_data";
        private static final String notification_type = "notification_type";


        public static ImportantNotification getOne(SQLiteDatabase database) {
            Cursor cursor = database.rawQuery("select * FROM " + table_name, null);
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(notification_id));
                    String type = cursor.getString(cursor.getColumnIndex(notification_type));
                    String data = cursor.getString(cursor.getColumnIndex(notification_data));

                    if (type.equals(ImportantNotification.DRIVER)) {
                        NotificationObj resp = MyGson.getGson().fromJson(data, NotificationObj.class);
                        cursor.close();
                        return new ImportantNotification().setId(id).setType(type).setMessage(resp);
                    } else {
                        NotificationObj resp = MyGson.getGson().fromJson(data, NotificationObj.class);
                        cursor.close();
                        return new ImportantNotification().setId(id).setType(type).setMessage(resp);
                    }


                } while (cursor.moveToNext());
            }
            return null;
//            return dataList;

        }

        //        LogInData resp = MyGson.getGson().fromJson(data, LogInData.class);
        public static ArrayList<ImportantNotification> getAll(SQLiteDatabase database) {
            Cursor cursor = database.rawQuery("select * FROM " + table_name, null);
            ArrayList<ImportantNotification> dataList = new ArrayList<ImportantNotification>();
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(cursor.getColumnIndex(notification_id));
                    String type = cursor.getString(cursor.getColumnIndex(notification_type));
                    String data = cursor.getString(cursor.getColumnIndex(notification_data));

                    if (type.equals(ImportantNotification.DRIVER)) {
                        NotificationObj resp = MyGson.getGson().fromJson(data, NotificationObj.class);
                        dataList.add(new ImportantNotification().setId(id).setType(type).setMessage(resp));
                    } else {
                        NotificationObj resp = MyGson.getGson().fromJson(data, NotificationObj.class);
                        dataList.add(new ImportantNotification().setId(id).setType(type).setMessage(resp));
                    }


                } while (cursor.moveToNext());
            }
            cursor.close();
            return dataList;

        }

        public static void delete(SQLiteDatabase database, String id) {
            if (id == null) {
                return;
            }
            database.beginTransaction();
            database.delete(table_name, notification_id + " = ?", new String[]{id});
            database.setTransactionSuccessful();
            database.endTransaction();
        }


        public static void deleteAll(SQLiteDatabase database) {
            database.beginTransaction();
            database.delete(table_name, null, null);
            database.setTransactionSuccessful();
            database.endTransaction();
        }

        public static void add(SQLiteDatabase database, ImportantNotification importantNotification) {
           String data = MyGson.getGson().toJson(importantNotification.getMessage()).toString();
            database.beginTransaction();

                ContentValues values = new ContentValues();
                CursorUtil.putObject(values, notification_id, importantNotification.getId());
                CursorUtil.putObject(values, notification_data, data);
                CursorUtil.putObject(values, notification_type, importantNotification.getType());

                database.insert(table_name, null, values);


            database.setTransactionSuccessful();
            database.endTransaction();
        }


    }


    public static final String classroom_id = "classroom_id";

    public static void addCheck(SQLiteDatabase database, ArrayList<CheckInOut> checkInOutList) {

        database.beginTransaction();
        for (int i = 0; i < checkInOutList.size(); i++) {
            ContentValues values = new ContentValues();
            CursorUtil.putObject(values, CheckInOut_message, checkInOutList.get(i).getMessage());
            CursorUtil.putObject(values, CheckInOut_data, checkInOutList.get(i).getData());
            CursorUtil.putObject(values, CheckInOut_date, checkInOutList.get(i).getDate());

            database.insert(CheckInOut_TBL, null, values);
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public static void addLonLat(SQLiteDatabase database, ArrayList<LonLat> lonLatList) {

        database.beginTransaction();
        for (int i = 0; i < lonLatList.size(); i++) {
            ContentValues values = new ContentValues();
            CursorUtil.putObject(values, LonLat_longitude, lonLatList.get(i).getLongitude());
            CursorUtil.putObject(values, LonLat_latitude, lonLatList.get(i).getLatitude());
            CursorUtil.putObject(values, LonLat_date, lonLatList.get(i).getDate());

            database.insert(LonLat_TBL, null, values);
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }


    public static ArrayList<CheckInOut> getAllRemindersObj(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("select * FROM " + CheckInOut_TBL, null);
        ArrayList<CheckInOut> reminderObjectList = new ArrayList<CheckInOut>();
        if (cursor.moveToFirst()) {
            do {
//                Integer reminder_id = cursor.getInt(cursor.getColumnIndex(CheckInOut_checkid));
                String message = cursor.getString(cursor.getColumnIndex(CheckInOut_message));
                String data = cursor.getString(cursor.getColumnIndex(CheckInOut_data));
                String date = cursor.getString(cursor.getColumnIndex(CheckInOut_date));
                CheckInOut CheckInOut_object = new CheckInOut(message, data, date);

                reminderObjectList.add(CheckInOut_object);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reminderObjectList;

    }


    public static ArrayList<LonLat> getAllLonLat(SQLiteDatabase database) {
        Cursor cursor = database.rawQuery("select * FROM " + LonLat_TBL, null);
        ArrayList<LonLat> reminderObjectList = new ArrayList<LonLat>();
        if (cursor.moveToFirst()) {
            do {
//                Integer reminder_id = cursor.getInt(cursor.getColumnIndex(CheckInOut_checkid));
                String longitude = cursor.getString(cursor.getColumnIndex(LonLat_longitude));
                String latitude = cursor.getString(cursor.getColumnIndex(LonLat_latitude));
                String date = cursor.getString(cursor.getColumnIndex(LonLat_date));
                LonLat LonLat_object = new LonLat(longitude, latitude, date);

                reminderObjectList.add(LonLat_object);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reminderObjectList;

    }

    public static void deleteItem(SQLiteDatabase database, Integer id, String
            tableName, String idName) {
        if (id == null) {
            return;
        }
        database.beginTransaction();
        database.delete(tableName, idName + " = ?", new String[]{Integer.toString(id)});
        database.setTransactionSuccessful();
        database.endTransaction();
    }


    public static void deleteAll(SQLiteDatabase database, String tableName) {
        database.beginTransaction();
        database.delete(tableName, null, null);
        database.setTransactionSuccessful();
        database.endTransaction();
    }


    public static void deleteAllPendingMessage(SQLiteDatabase database) {
        database.beginTransaction();
        database.delete(tbl_unsent_messages, null, null);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public static void deletePendingMessage(SQLiteDatabase database, String message) {
        database.beginTransaction();
        database.delete(tbl_unsent_messages, DAO.message + " = ?", new String[]{message});
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public static void deleteAllMessages(SQLiteDatabase database) {
        database.beginTransaction();
        database.delete(tbl_sent_messages, null, null);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public static void deleteNotification(SQLiteDatabase database, Integer classroomId, Integer student_id_) {
        try {
            database.beginTransaction();
            database.delete(tbl_notifications, null, null);

            database.delete(tbl_notifications, student_id + " = ? AND " + classroom_id + " = ?", new String[]{Integer.toString(student_id_), Integer.toString(classroomId)});
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void deleteAllNotifications(SQLiteDatabase database) {
        try {
            database.beginTransaction();
            database.delete(tbl_notifications, null, null);

//            database.delete(tbl_notifications, student_id + " = ? AND " + classroom_id + " = ?", new String[]{Integer.toString(student_id_), Integer.toString(classroomId)});
            database.setTransactionSuccessful();
            database.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //////////////////////
    public static void addMessage(SQLiteDatabase database, Integer schoolId, String sender, String dateTime, String type, String message, Integer senderId, Integer studentId) {
        ArrayList<MessageHistoryObject> messagesList = new ArrayList<>();
        messagesList.add(new MessageHistoryObject(schoolId, sender, dateTime, type, message, senderId));
        addMessages(database, messagesList, studentId);
    }

    public static void addMessages(SQLiteDatabase database, ArrayList<MessageHistoryObject> messagesList, int studentId) {
        database.beginTransaction();
        for (int i = 0; i < messagesList.size(); i++) {
            ContentValues values = new ContentValues();
            CursorUtil.putObject(values, student_id, studentId);
            CursorUtil.putObject(values, school_id, messagesList.get(i).getSchoolId());
            CursorUtil.putObject(values, sender, messagesList.get(i).getSender());
            CursorUtil.putObject(values, date_time, messagesList.get(i).getDateTime());
            CursorUtil.putObject(values, type, messagesList.get(i).getType());
            CursorUtil.putObject(values, message, messagesList.get(i).getMessage());
            CursorUtil.putObject(values, sender_id, messagesList.get(i).getSenderId());
            /**/
            database.insert(tbl_sent_messages, null, values);
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }


    public static ArrayList<MessageHistoryObject> getMessages(SQLiteDatabase database, int studentId) {
        Cursor cursor = database.rawQuery("select * FROM " + tbl_sent_messages + " where " + student_id + " =" + studentId + " ORDER BY " + date_time, null);
        ArrayList<MessageHistoryObject> reminderObjectList = new ArrayList<MessageHistoryObject>();
        if (cursor.moveToFirst()) {
            do {
//                Integer student_id_ = cursor.getInt(cursor.getColumnIndex(school_id));
                int school_id_ = cursor.getInt(cursor.getColumnIndex(school_id));
                String sender_ = cursor.getString(cursor.getColumnIndex(sender));
                String date_time_ = cursor.getString(cursor.getColumnIndex(date_time));
                String type_ = cursor.getString(cursor.getColumnIndex(type));
                String message_ = cursor.getString(cursor.getColumnIndex(message));
                int sender_id_ = cursor.getInt(cursor.getColumnIndex(sender_id));

                reminderObjectList.add(new MessageHistoryObject(school_id_, sender_, date_time_, type_, message_, sender_id_));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return reminderObjectList;

    }

    public static void addPendingMessage(SQLiteDatabase database, MessageHistoryObject pendingMessage, int studentId) {
        database.beginTransaction();
        ContentValues values = new ContentValues();
        CursorUtil.putObject(values, student_id, studentId);
//        CursorUtil.putObject(values, pending_message, GsonInstance.getGson().toJson(pendingMessage));


        CursorUtil.putObject(values, student_id, studentId);
        CursorUtil.putObject(values, school_id, pendingMessage.getSchoolId());
        CursorUtil.putObject(values, sender, pendingMessage.getSender());
        CursorUtil.putObject(values, date_time, DateTools.Formats.DATE_FORMAT_GMT.format(new Date().getTime()));
        CursorUtil.putObject(values, type, pendingMessage.getType());
        CursorUtil.putObject(values, message, pendingMessage.getMessage());
        CursorUtil.putObject(values, sender_id, pendingMessage.getSenderId());


        database.insert(tbl_unsent_messages, null, values);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public static ArrayList<MessageHistoryObject> getPendingMessages(SQLiteDatabase database, int studentId) {
        Cursor cursor = database.rawQuery("select * FROM " + tbl_unsent_messages + " where " + student_id + " =" + studentId + " ORDER BY " + date_time, null);
        ArrayList<MessageHistoryObject> pendingMessages = new ArrayList<MessageHistoryObject>();
        if (cursor.moveToFirst()) {
            do {
//

                Integer student_id_ = cursor.getInt(cursor.getColumnIndex(school_id));
                int school_id_ = cursor.getInt(cursor.getColumnIndex(school_id));
                String sender_ = cursor.getString(cursor.getColumnIndex(sender));
                String date_time_ = cursor.getString(cursor.getColumnIndex(date_time));
                String type_ = cursor.getString(cursor.getColumnIndex(type));
                String message_ = cursor.getString(cursor.getColumnIndex(message));
                int sender_id_ = cursor.getInt(cursor.getColumnIndex(sender_id));

                pendingMessages.add(new MessageHistoryObject(school_id_, sender_, date_time_, type_, message_, sender_id_));


            } while (cursor.moveToNext());
        }
        cursor.close();
        return pendingMessages;

    }


    public static void addNotification(SQLiteDatabase database, int classroomId, int studentId) {
        ContentValues values = new ContentValues();
        CursorUtil.putObject(values, classroom_id, classroomId);
        CursorUtil.putObject(values, student_id, studentId);
        database.beginTransaction();
        database.insert(tbl_notifications, null, values);
        database.setTransactionSuccessful();
        database.endTransaction();
    }

//    public static int haseNotification(SQLiteDatabase database, Integer classroom_id_, Integer student_id_) {
//        try {
//            String q = "select * FROM " + tbl_notifications + " where " + student_id + " =" + student_id_.intValue();
//            if (classroom_id_ != null) {
//                q = q + " AND " + classroom_id + " =" + classroom_id_.intValue();
//            }
//            Log.v("rawQuery = ", q);
//            Cursor cursor = database.rawQuery(q, null);
//            return cursor.getCount();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//
//        }
//
//
//    }

    public static int haseNotification(SQLiteDatabase database) {
        try {
            String q = "select * FROM " + tbl_notifications ;
            Cursor cursor = database.rawQuery(q, null);
            return cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;

        }


    }


}