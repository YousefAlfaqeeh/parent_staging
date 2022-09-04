package trackwareschoolbus.parentschool.api_v3

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import trackwareschoolbus.parentschool.R
import java.net.URI
import java.util.*

fun addImageBaseUrl(imageUrl: String? = ""): String {
    try {


    } catch (ignore: Exception) {
    }
    return ""
}


fun imageLoader(
    imageView: ImageView?,
    with: Context? = null,
    url: String? = "-",
    uriString: String? = "-",
    uri: Uri? = null,
    @DrawableRes errorResId: Int = R.drawable.img_error,
    @DrawableRes resId: Int = -1,
    fitCenter: Boolean = false,
    fitXY: Boolean = false,
    centerCrop: Boolean = false,
    circleCrop: Boolean = false,
    centerInside: Boolean = false,

    ) =
    imageView?.apply {
        try {
            val requestManager = with?.let { Glide.with(with) } ?: Glide.with(imageView)
            var viewTarget = requestManager.asDrawable()

            if (errorResId != -1) {
                viewTarget = viewTarget.error(errorResId)
            }
            if (!url.equals("-")) {
                viewTarget = viewTarget.load(url)
            }

            if (!uriString.equals("-")) {
                viewTarget = viewTarget.load(URI(uriString))
            }
            if (uri != null) {
                viewTarget = viewTarget.load(uri)
            }

            if (resId != -1) {
                viewTarget = viewTarget.load(resId)
            }
            when {
                fitCenter -> viewTarget.centerInside()
                centerCrop -> viewTarget.centerCrop()
                circleCrop -> viewTarget.circleCrop()
                centerInside -> viewTarget.centerInside()
                fitXY -> {/*do nothing it will taked from layout */
                }
                else -> viewTarget.centerCrop()
            }
//        viewTarget.skipMemoryCache(true)
//        viewTarget.diskCacheStrategy(DiskCacheStrategy.NONE)
            viewTarget = viewTarget.placeholder(createCircularProgressDrawable(this.context))


            viewTarget.into(this)
        } catch (ignore: Exception) {

        }

    }

fun getTintedDrawable(
    context: Context,
    @DrawableRes drawableResId: Int = 0,
    colorString: String = "",
    colorResId: Int = R.color.colorAccent,
    drawableObject: Drawable? = null,
): Drawable? {
    var mWrappedDrawable: Drawable? = null

    try {
        val drawableColor = if (!colorString.isEmpty()) {
            Color.parseColor(colorString)
        } else {
            colorResId
        }
        val drawable = drawableObject ?: ContextCompat.getDrawable(context, drawableResId)

        mWrappedDrawable = Objects.requireNonNull<Drawable>(drawable).mutate()
        mWrappedDrawable = DrawableCompat.wrap(mWrappedDrawable!!)
        DrawableCompat.setTint(mWrappedDrawable!!, drawableColor)
        DrawableCompat.setTintMode(mWrappedDrawable, PorterDuff.Mode.SRC_IN)
    } catch (e: Exception) {
        e.printStackTrace()

    }

    return mWrappedDrawable
}

private fun createCircularProgressDrawable(context: Context?): CircularProgressDrawable? {
    context?.let {
        val circularProgressDrawable = CircularProgressDrawable(it)
        circularProgressDrawable.setStyle(CircularProgressDrawable.DEFAULT)
        circularProgressDrawable.strokeWidth = 6f
        circularProgressDrawable.centerRadius = 33f
        circularProgressDrawable.alpha = 50
        circularProgressDrawable.setColorSchemeColors(Color.parseColor("#44C3CF"))
//            getTintedDrawable(context,circularProgressDrawable,R.color.purple)
//            circularProgressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.purple), PorterDuff.Mode.MULTIPLY)
//                    alpha = 100
        circularProgressDrawable.start();
        return circularProgressDrawable;
    } ?: return null


}

//class DrawableTools {
//
//
//    private fun base64ToBitmap(b64: String): Bitmap {
//        val imageAsBytes = Base64.decode(b64.toByteArray(), Base64.DEFAULT)
//        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
//    }
//
//    private fun bitmapToBase64(bitmap: Bitmap): String {
//        val byteArrayOutputStream = ByteArrayOutputStream()
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
//        val byteArray = byteArrayOutputStream.toByteArray()
//        return Base64.encodeToString(byteArray, Base64.DEFAULT)
//    }
//
//    companion object {
//
//        var GLIDE_SCALE_CENTER_INSIDE = RequestOptions.centerInsideTransform()
//        var GLIDE_SCALE_CIRCLE_CROP = RequestOptions.circleCropTransform()
//        var GLIDE_SCALE_CENTER_CROP = RequestOptions.centerCropTransform()
//        var GLIDE_SCALE_CENTER_FIT = RequestOptions.fitCenterTransform()
//
//        public fun createCircularProgressDrawable(context: Context): CircularProgressDrawable {
//            val circularProgressDrawable = CircularProgressDrawable(context)
//            circularProgressDrawable.setStyle(CircularProgressDrawable.DEFAULT)
//            circularProgressDrawable.strokeWidth = 6f
//            circularProgressDrawable.centerRadius = 33f
//            circularProgressDrawable.alpha = 50
//            circularProgressDrawable.setColorSchemeColors(Color.parseColor("#44C3CF"))
////            getTintedDrawable(context,circularProgressDrawable,R.color.purple)
////            circularProgressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.purple), PorterDuff.Mode.MULTIPLY)
////                    alpha = 100
//            circularProgressDrawable.start();
//            return circularProgressDrawable;
//        }
//
//        public fun createCircularProgressDrawable(view: View): CircularProgressDrawable {
//            val circularProgressDrawable = CircularProgressDrawable(view.context)
//            circularProgressDrawable.setStyle(CircularProgressDrawable.DEFAULT)
//            circularProgressDrawable.strokeWidth = 6f
//            circularProgressDrawable.centerRadius = 33f
//            circularProgressDrawable.alpha = 50
//            circularProgressDrawable.setColorSchemeColors(Color.BLACK)
//            //        circularProgressDrawable.setBackgroundColor(Color.parseColor("#FFFFFF"));
////        circularProgressDrawable.setColorFilter(ContextCompat.getColor(context, android.R.color.holo_blue_bright), PorterDuff.Mode.SRC_IN);
////        circularProgressDrawable.setColorFilter(ContextCompat.getColor(context,  android.R.color.white), PorterDuff.Mode.MULTIPLY);
////            circularProgressDrawable.colorFilter= ColorFilter.No(Color.parseColor(("#A0000000"), PorterDuff.Mode.SRC_IN)
//
////            getTintedDrawable(context,circularProgressDrawable,R.color.purple)
////            circularProgressDrawable.setColorFilter(ContextCompat.getColor(context, R.color.purple), PorterDuff.Mode.MULTIPLY)
////                    alpha = 100
////            circularProgressDrawable.start();
//            return circularProgressDrawable;
//        }
//
//        fun <Y : ImageView> loadGif(
//            @RawRes @DrawableRes gifResId: Int,
//            loopCount: Int,
//            imageView: Y?,
//            afterGifLoadListener: OnActionDoneListener<View>?
//        ) {
//            imageView?.let { imageView ->
//                Glide.with(imageView.context).asGif()
//                    .placeholder(createCircularProgressDrawable(imageView.context)).load(gifResId)
//                    .optionalFitCenter()
//                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
//                    .listener(object : RequestListener<GifDrawable> {
//                        override fun onLoadFailed(
//                            e: GlideException?,
//                            model: Any,
//                            target: Target<GifDrawable>,
//                            isFirstResource: Boolean
//                        ): Boolean {
//                            afterGifLoadListener?.onAction(null)
//                            return false
//                        }
//
//                        override fun onResourceReady(
//                            resource: GifDrawable,
//                            model: Any,
//                            target: Target<GifDrawable>,
//                            dataSource: DataSource,
//                            isFirstResource: Boolean
//                        ): Boolean {
//                            resource.setLoopCount(loopCount)
//                            //////
//                            resource.registerAnimationCallback(object :
//                                Animatable2Compat.AnimationCallback() {
//                                override fun onAnimationEnd(drawable: Drawable?) {
//                                    afterGifLoadListener?.onAction(imageView)
//                                }
//                            })
//                            return false
//                        }
//                    }).into(imageView)
//            }
//
//        }
//
//        fun filePathToBase64(filePath: String?): String? {
//            var bmp: Bitmap? = null
//            var bos: ByteArrayOutputStream? = null
//            var bt: ByteArray? = null
//            var encodeString: String? = null
//            try {
//                bmp = BitmapFactory.decodeFile(filePath)
//                bos = ByteArrayOutputStream()
//                bmp?.compress(Bitmap.CompressFormat.JPEG, 100, bos)
//                bt = bos.toByteArray()
//                encodeString = Base64.encodeToString(bt, Base64.DEFAULT)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//            return encodeString
//        }
//
//        fun getTintedDrawable(
//            context: Context,
//            @DrawableRes drawableResId: Int,
//            @ColorRes color: Int
//        ): Drawable? {
//            val drawable = ContextCompat.getDrawable(context, drawableResId)
//            return getTintedDrawable(context, drawable, color)
//        }
//
//
//        fun getTintedDrawable(
//            context: Context,
//            drawable: Drawable?,
//            @ColorRes color: Int
//        ): Drawable? {
//            var mWrappedDrawable: Drawable? = null
//
//            try {
//                val drawableColor = ContextCompat.getColor(context, color)
//                mWrappedDrawable = Objects.requireNonNull<Drawable>(drawable).mutate()
//                mWrappedDrawable = DrawableCompat.wrap(mWrappedDrawable!!)
//                DrawableCompat.setTint(mWrappedDrawable!!, drawableColor)
//                DrawableCompat.setTintMode(mWrappedDrawable, PorterDuff.Mode.SRC_IN)
//            } catch (e: Exception) {
//                e.printStackTrace()
//
//            }
//
//            return mWrappedDrawable
//        }
//
//
//
//
////        fun loadProfileImage(imageView: ImageView, userId: Int): DrawableLoader<*> {
////            return DrawableLoader.initWith(imageView).withUrl(UserProfileImage.getInstance().getUserProfileImage(userId))
////        }
////
////        fun loadProfileImage(imageView: ImageView, url: String): DrawableLoader<*> {
////            return DrawableLoader.initWith(imageView).withUrl(url)
////        }
////
////        fun loadGroupImage(imageView: ImageView, url: String): DrawableLoader<*> {
////            return DrawableLoader.initWith(imageView).withUrl(url).withError(R.drawable.ic_vector_story_error).withError(R.drawable.test_error).withOptions(RequestOptions.overrideOf(200,600))
////
////        }
////
////        fun loadPostImage(imageView: ImageView, url: String): DrawableLoader<*> {
////
////            return DrawableLoader.initWith(imageView).withUrl(url).withError(R.drawable.test_error).withOptions(GLIDE_SCALE_CENTER_CROP)
////
////        }
//    }
//
//
//}
