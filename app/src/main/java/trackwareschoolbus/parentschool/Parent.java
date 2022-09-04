package trackwareschoolbus.parentschool;

import trackwareschoolbus.parentschool.API.interfaceApi.IRestCallPhoenix;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


/**
 * Created by  on 6/14/2017.
 */

public class Parent extends User  {


     int countRecieved;

    public Parent()  {

        super(
            "parent",
                UtilityParent.getStringShared(UtilityParent.AUTH),
             null,
                null,
               null
             );

    }


//    @Override
//    public void newLocation(double lat, double lng, int usedId) {
//        setChannelState();
//        countRecieved++;
//
//        iRestCallPhoenixMap.newLocation(lat,lng,usedId);
////        if (MainActivity.labParentRecievedMessages!=null){
////            new Handler(Looper.getMainLooper()).post(new Runnable() {
////                @Override
////                public void run() {
////                    MainActivity.labParentRecievedMessages.setText(countRecieved+"");
////
////                }
////            });
////        }
//
//    }



//    @Override
//    public void socketConnected(int busID) {
//        super.socketConnected(busID);
//
//
//        try {
//            phoenixPlug.joinBus((IRestCallPhoenix) this,busID);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }

//    @Override
//    public void onReadyToPushMessage() {
//
//        phoenixPlug.receivedMessage(this);
//    }
}
