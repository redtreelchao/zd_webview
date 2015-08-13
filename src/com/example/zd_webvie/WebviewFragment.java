package com.example.zd_webvie;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint("ValidFragment")
public class WebviewFragment extends Fragment {

	public static WebView web;
	private String Url;

	public WebviewFragment() {
	}

	public WebviewFragment(String url) {
		this.Url = url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		// file:///android_asset/index.html
		View rootView = inflater.inflate(R.layout.webviewxml, container, false);
		web = (WebView) rootView.findViewById(R.id.webView);
		// 设置WebView支持JS
		web.getSettings().setJavaScriptEnabled(true);
		// 设置当前WebView的编码
		web.getSettings().setDefaultTextEncodingName("utf-8");
		// WebView内链接跳转
		web.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				// 当前webview内访问url
				view.loadUrl(url);
				// 返回true表示停留在本WebView（不跳转到系统的浏览器）
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

		});
		// 进度条显示
		web.setWebChromeClient(new WebChromeClient() {

			public void onProgressChanged(WebView view, int newProgress) {
				// activity的进度是0 to 10000 (both inclusive),所以要*100
				getActivity().setProgress(newProgress * 100);
				if (newProgress == 100) {
					// 设置当前ActivityActionBar
					getActivity().setTitle(view.getTitle());
				} else {
					// 设置当前ActivityActionBar
					getActivity().setTitle("加载中...");
				}
			}
		});
		//开启DOM 存储
		web.getSettings().setDomStorageEnabled(true);
		// 启动Webview时加载的Url
		web.loadUrl(Url);
		return rootView;
	}

}
