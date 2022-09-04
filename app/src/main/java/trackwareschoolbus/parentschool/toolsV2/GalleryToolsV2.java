package trackwareschoolbus.parentschool.toolsV2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.theartofdev.edmodo.cropper.CropImage;

import trackwareschoolbus.parentschool.R;
import trackwareschoolbus.parentschool.interfaceParent.OnActionDoneListener;

public class GalleryToolsV2 {


    public static void openGalary(Activity activity, String title) {

        CropImage.activity().setFixAspectRatio(true)
//                .setFixAspectRatio(true)
//                .setMaxCropResultSize(365, 510)
                .setRequestedSize(365, 510)
                .setAspectRatio(2, 3)
                .setCropMenuCropButtonTitle(activity.getString(R.string.done))
                .start(activity);
//        EasyImage.configuration(activity).setAllowMultiplePickInGallery(false);
//        EasyImage.openChooserWithDocuments(activity, title, 0);
    }


    public static void handleOnActivityResult(Activity activity, int requestCode, int resultCode, Intent data, OnActionDoneListener<Uri> onImagesPicked) {

        try {
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
            {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == activity.RESULT_OK) {
                    Uri resultUri = result.getUri();
                    if (onImagesPicked!=null){
                        onImagesPicked.OnActionDone(resultUri);
                    }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            }

        } catch (Exception e) {
        }
    }
}


//        EasyImage.handleActivityResult(requestCode, resultCode, data, activity, new DefaultCallback() {
//            @Override
//            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
//                if (onImagesPicked!=null)
//                    onImagesPicked.OnActionDone(imageFiles);
////                onPhotosReturned(imageFiles);
//            }
//
//            @Override
//            public void onCanceled(EasyImage.ImageSource source, int type) {
//                if (source == EasyImage.ImageSource.CAMERA) {
//                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(activity);
//                    if (photoFile != null) photoFile.delete();
//                }
//            }
//        });
//    }
//}


//    public static void handleOnActivityResult(Activity activity, int requestCode, int resultCode, Intent data, OnActionDoneListener onImagesPicked) {
//
//        EasyImage.handleActivityResult(requestCode, resultCode, data, activity, new DefaultCallback() {
//            @Override
//            public void onImagePickerError(Exception e, EasyImage.ImageSource source, int type) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onImagesPicked(List<File> imageFiles, EasyImage.ImageSource source, int type) {
//                if (onImagesPicked!=null)
//                    onImagesPicked.OnActionDone(imageFiles);
////                onPhotosReturned(imageFiles);
//            }
//
//            @Override
//            public void onCanceled(EasyImage.ImageSource source, int type) {
//                if (source == EasyImage.ImageSource.CAMERA) {
//                    File photoFile = EasyImage.lastlyTakenButCanceledPhoto(activity);
//                    if (photoFile != null) photoFile.delete();
//                }
//            }
//        });
//    }
//}
