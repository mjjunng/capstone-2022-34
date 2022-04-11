package com.capstone.momomeal

import android.content.Intent
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.capstone.momomeal.databinding.ActivityMyaddressBinding


class MyAddressActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMyaddressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //activity view binding
        binding = ActivityMyaddressBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val webView = binding.activityMyaddressWebview
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(BridgeInterface(), "Android")
        webView.webViewClient = (object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                //android -> Javascript 함수 호출!
                // html의 fun : sample2_execDaumPostcode을 호출하면 마지막에 window.Android.processDATA(최종주소) 실행
                webView.loadUrl("javascript:sample2_execDaumPostcode()")
            }
        })
        //최초 웹뷰 로드
        webView.loadUrl("https://momomeal-15dcf.web.app")
    }
    private inner class BridgeInterface {
        //sample2_execDaumPostcode에서 사용할 함수를 지정
        @JavascriptInterface
        fun processDATA(data: String) {
            //다음 주소 검색(api) 결과값이 브릿지 통로를 통해 전달받는다 (from javascript)
            val result = Intent()
            result.putExtra("data", data)
            setResult(RESULT_OK, result)
            finish()
        }
    }
}