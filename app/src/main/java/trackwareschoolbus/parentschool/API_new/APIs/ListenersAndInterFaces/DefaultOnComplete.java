package trackwareschoolbus.parentschool.API_new.APIs.ListenersAndInterFaces;

import android.view.View;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.utilityParent.StaticValue;
import trackwareschoolbus.parentschool.utilityParent.UtilDialogs;
import trackwareschoolbus.parentschool.utilityParent.UtilityParent;


public abstract class DefaultOnComplete<Type> implements OnApiComplete<Type> {

    public static final int SHOW_ERROR_MESSAGE_CODE = 500;
    public static final int NULL_RESPONSE = 501;
    private View view;

    public DefaultOnComplete(View view) {
        this.view = view;
    }

    @Override
    public void onError(int errorCode, String errorMessage) {
        if (view == null) {
            return;
        }
        switch (errorCode) {
            case ServiceResponseErrorListener.NETWORK_IS_UNREACHABLE:
            case ServiceResponseErrorListener.CONNECTION_REFUSED:
            case ServiceResponseErrorListener.UNKNOWN_HOST:
//                UtilityParent.showMessageDialog(view.getContext(), view.getContext().getString(R.string.internet_connection), errorMessage);
                UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.internet_connection);
                break;
            case 404:
                UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.api_send_error);
//                UtilityParent.showMessageDialog(view.getContext(), view.getContext().getString(R.string.api_send_error), errorMessage );

                break;
            case SHOW_ERROR_MESSAGE_CODE:
                UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.api_send_error);
//                UtilityParent.showMessageDialog(view.getContext(), view.getContext().getString(R.string.api_send_error),  errorMessage);

                break;
            case NULL_RESPONSE:
//                UtilityParent.showMessageDialog(view.getContext(), view.getContext().getString(R.string.api_send_error), errorMessage );
                UtilDialogs.showGeneralErrorDialog(StaticValue.mActivity).setDialogeMessage(R.string.api_send_error);

                break;
        }
    }
}
