package trackwareschoolbus.parentschool.screens_v2;

import androidx.lifecycle.ViewModel;

import java.util.List;


public class BaseViewModelV2 extends ViewModel {
    /**/

    public BaseViewModelV2() {
    }


    public boolean isNull(Object... objects) {
        try {
            for (Object object : objects) {
                if (object == null)
                    return true;
            }
        } catch (Exception e) {

        }

        return false;
    }

    public boolean isEmpty(List<?>... objectsLists) {
        for (List<?> objectsList : objectsLists) {
            if (objectsList.size() <= 0)
                return true;
        }
        return false;
    }


}
