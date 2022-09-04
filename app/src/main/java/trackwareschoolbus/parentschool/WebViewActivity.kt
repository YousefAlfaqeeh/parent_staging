package trackwareschoolbus.parentschool

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.*
import android.view.MenuItem
import android.view.View
import android.webkit.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import kotlinx.android.synthetic.main.web_view_pae.*
import trackwareschoolbus.parentschool.api_v3.GetKidsResp
import trackwareschoolbus.parentschool.basePage.BaseActivity


internal class WebViewActivity : BaseActivity() {

    var student: GetKidsResp.Student? = null
    var selected_feature: GetKidsResp.Student.FeaturesListItem? = null
    var page_title: String? = null
    var page_icon_url: String? = null

    fun setWebviewSettings(webView: WebView?) {
//        webView?.setInitialScale(1)
        webView?.isScrollbarFadingEnabled = false

//            webView?.settings?.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL;
//        webView?.settings?.setGeolocationEnabled(true)

        try {
            webView?.getSettings()?.allowFileAccess = true;
            webView?.getSettings()?.allowContentAccess = true;
            CookieManager.allowFileSchemeCookies()
        } catch (e: Exception) {

        }


        try {


            (webView as? AdvancedWebView)?.setMixedContentAllowed(true)
//            (webView as? AdvancedWebView)?.setGeolocationEnabled(true);
            (webView as? AdvancedWebView)?.setDesktopMode(false);
            (webView as? AdvancedWebView)?.setThirdPartyCookiesEnabled(true);
            (webView as? AdvancedWebView)?.setCookiesEnabled(true);
            (webView as? AdvancedWebView)?.setNetworkAvailable(true);
            (webView as? AdvancedWebView)?.setUploadableFileTypes("*/*");
            AdvancedWebView.isFileUploadAvailable(true);
            (webView as? AdvancedWebView)?.getSettings()?.allowFileAccess = true;
            (webView as? AdvancedWebView)?.getSettings()?.allowContentAccess = true
            (webView as? AdvancedWebView)?.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        } catch (e: Exception) {

        }


        webView?.settings?.databaseEnabled = true;
        webView?.settings?.domStorageEnabled = true;
        webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView?.settings?.builtInZoomControls = false;
        webView?.settings?.displayZoomControls = false;
        webView?.settings?.setSupportZoom(false);
        webView?.settings?.allowContentAccess = true;
        webView?.settings?.blockNetworkImage = false;
        webView?.settings?.blockNetworkLoads = false;
        webView?.isScrollbarFadingEnabled = false;
        webView?.settings?.defaultTextEncodingName = "UTF-8"
        webView?.settings?.mediaPlaybackRequiresUserGesture = true
        webView?.settings?.loadsImagesAutomatically = true
            webView?.settings?.cacheMode= WebSettings.LOAD_DEFAULT
        webView?.settings?.javaScriptCanOpenWindowsAutomatically = true
        webView?.settings?.javaScriptEnabled = true
        webView?.getSettings()?.setJavaScriptEnabled(true);
        webView?.settings?.allowFileAccess = true;
        webView?.settings?.useWideViewPort = false
        webView?.settings?.loadWithOverviewMode = false


        try {
            webView?.settings?.setRenderPriority(WebSettings.RenderPriority.HIGH)
            webView?.settings?.allowUniversalAccessFromFileURLs = true
            webView?.settings?.allowFileAccessFromFileURLs = true;
            webView?.settings?.saveFormData = true
            webView?.settings?.savePassword = true
        } catch (e: Exception) {

        }


        try {
//                webView?.settings?.javaScriptEnabled = true
//                webView?.getSettings()?.setJavaScriptEnabled(true);

        } catch (e: Exception) {

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdvancedWebView.isFileUploadAvailable(true)
        setContentView(R.layout.web_view_pae)
        advancedWebView.visibility = View.VISIBLE
        progressBarContent.visibility = View.GONE
        student = intent?.extras?.getParcelable("KIDBEAN") as? GetKidsResp.Student?

        selected_feature =
            intent?.extras?.getParcelable("SELECTED_FEATURE") as? GetKidsResp.Student.FeaturesListItem?

        try {
//selected_feature?.url="http://school-staging.trackware.com/web/login?db=tst";

            page_title = intent?.extras?.getString("PAGE_TITLE", "")
            page_icon_url = intent?.extras?.getString("PAGE_ICON_URL", "")

            supportActionBar?.title = page_title
//            getSupportActionBar()?.setDisplayShowHomeEnabled(true);
            supportActionBar?.setDisplayUseLogoEnabled(false);
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

//            supportActionBar?.setHomeAsUpIndicator(ContextCompat.getDrawable(this,R.drawable.img_back))
            supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.blue_background)));

            Glide.with(this).asDrawable()
                .apply(RequestOptions().error(R.drawable.school_outline).centerInside())
                .load("")
                .into(object : CustomTarget<Drawable?>() {
                    override fun onLoadCleared(placeholder: Drawable?) {}
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: com.bumptech.glide.request.transition.Transition<in Drawable?>?
                    ) {

//

                        getSupportActionBar()?.setIcon(resource)
                    }
                })


        } catch (e: Exception) {

        }

        advancedWebView.webChromeClient = object : WebChromeClient() {
            override fun onCreateWindow(
                view: WebView,
                isDialog: Boolean,
                isUserGesture: Boolean,
                resultMsg: Message
            ): Boolean {
                // myParentLayout.addView(newWebView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                val transport: WebView.WebViewTransport? =
                    resultMsg.obj as? WebView.WebViewTransport
                transport?.webView = AdvancedWebView(this@WebViewActivity).apply {
                    setWebviewSettings(this)
                    addHeadersAndData(this)
                }

                resultMsg.sendToTarget();
                return@onCreateWindow true;
            }

        }


//        setWebviewSettings(advancedWebView)


//        val assetLoader: WebViewAssetLoader = WebViewAssetLoader.Builder()
//            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
//            .build()


        advancedWebView.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    setWebviewSettings(view)
                    addHeadersAndData(view)
                    view.loadUrl(url)
                } catch (e: Exception) {

                }

                return false
            }
//            override fun shouldInterceptRequest(
//                view: WebView?,
//                url: String?
//            ): WebResourceResponse? {
//                setWebviewSettings(view)
//                return assetLoader.shouldInterceptRequest(url?.toUri()!!);
//
//            }
//            override fun shouldInterceptRequest(
//                view: WebView?,
//                request: WebResourceRequest?
//            ): WebResourceResponse? {
////                setWebviewSettings(view)
//                return assetLoader.shouldInterceptRequest(request?.url!!);
//
//            }
        })

//        advancedWebView.setListener(
//            this@WebViewActivity,
//            object : AdvancedWebView.Listener {
//
//                override fun onPageStarted(url: String?, favicon: Bitmap?) {
//                    Log.v("onPageStarted", url.toString());
//                    url?.let {
//                        if (!url.contains("login")) {
//                            Handler(Looper.getMainLooper()).postDelayed({
//                                advancedWebView.visibility = View.VISIBLE
//                                progressBarContent.visibility = View.GONE
//                            }, 100)
//
//                        }
//
//                    }
//
//                }
//
//                override fun onPageFinished(url: String?) {
//                    Log.v("onPageFinished", url.toString());
//                    url?.let {
//                        if (url.contains("login")) {
//                            advancedWebView.clearHistory();
//                            addHeadersAndData(advancedWebView, false)
//                            selected_feature?.getUrlWithLangAndDB(student?.schoolsListItem?.dbName)?.let {
//                                advancedWebView.loadUrl(
//                                    selected_feature?.getUrlWithLangAndDB(student?.schoolsListItem?.dbName)!!,
//                                    false
//                                )
//                            }
//                        }
//
//                    }
//                }
//
//                override fun onPageError(
//                    errorCode: Int,
//                    description: String?,
//                    failingUrl: String?
//                ) {
//                    Log.v("onPageError", failingUrl.toString());
//
//                }
//
//                override fun onDownloadRequested(
//                    url: String?,
//                    suggestedFilename: String?,
//                    mimeType: String?,
//                    contentLength: Long,
//                    contentDisposition: String?,
//                    userAgent: String?
//                ) {
//                    Log.v("onDownloadRequested", url.toString());
//
//                }
//
//                override fun onExternalPageRequest(url: String?) {
//                    Log.v("onExternalPageRequest", url.toString());
//
//                }
//
//            }
//        )






        setWebviewSettings(advancedWebView)
        addHeadersAndData(advancedWebView)

        selected_feature?.getUrlWithLang()?.let { advancedWebView.loadUrl(it) }

//        var fullUrl: String = student?.schoolsListItem?.url ?: ""
//
//        if (fullUrl.endsWith("/")) {
//            fullUrl += "web/login"
//        } else {
//            fullUrl += "/web/login"
//        }
//        fullUrl += fullUrl + "?db=" + student?.schoolsListItem?.dbName
//
//        advancedWebView.loadUrl(fullUrl, false)

//        Handler(Looper.getMainLooper()).postDelayed({

//        },10000);


        // ...
    }


    fun addHeadersAndData(_webView: WebView) {
//        advancedWebView.removeHttpHeader("session_id")
//        advancedWebView.removeHttpHeader("X-Openerp-Session-Id")

        CookieManager.getInstance().acceptThirdPartyCookies(advancedWebView)
        CookieManager.getInstance().setAcceptCookie(true)
//        CookieManager.getInstance().setCookie("X-Openerp-Session-Id", student?.sessionId)
//        CookieManager.getInstance().setCookie("session_id", student?.sessionId)


//        advancedWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        if (!isLogin)
            (_webView as AdvancedWebView).addHttpHeader("X-Openerp-Session-Id", student?.sessionId);
//        advancedWebView.addHttpHeader("Content-Type", "application/json");
//        advancedWebView.addHttpHeader("frontend_lang", "en_US");
        (_webView as AdvancedWebView).addHttpHeader("session_id", student?.sessionId);

//        _advancedWebView.addPermittedHostname("http://school-staging.trackware.com")


    }

    override fun onResume() {
        super.onResume()
        advancedWebView.onResume()
        // ...
    }

    override fun onPause() {
        advancedWebView.onPause()
        // ...
        super.onPause()
    }

    override fun onDestroy() {
        advancedWebView.onDestroy()
        // ...
        super.onDestroy()
    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
//        advancedWebView.onActivityResult(requestCode, resultCode, intent)
//        this.onActivityResult(requestCode = requestCode, resultCode = resultCode, intent = intent)
//        // ...
//    }

    override fun onBackPressed() {
        if (!advancedWebView.onBackPressed()) {
            return
        }
        // ...
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() //OR finish();
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}