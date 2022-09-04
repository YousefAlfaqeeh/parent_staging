package trackwareschoolbus.parentschool.API.interfaceApi;

/**
 * Created by  on 1/8/17.
 */

public interface IRestCallPhoenix {


    void sendSocketMessage(String response, int usedId);
    void receivedSocketMessage(String response, int usedId);
    void newLocation(double lat, double lng, int usedId);
    void errorConnect(String response);
    void ignoreConnect(String response);
    void closeConnect(String response);

    void socketConnected(int busID);

    /*
        When socket& channel are ready to be used
        We implement the code here
     */
    void onReadyToPushMessage();
}
