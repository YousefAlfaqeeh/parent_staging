package trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces;


public interface OnApiComplete<Type> {

    void onSuccess(Type type);

    void onError(int errorCode, String errorMessage);

}
