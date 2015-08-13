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
		// ����WebView֧��JS
		web.getSettings().setJavaScriptEnabled(true);
		// ���õ�ǰWebView�ı���
		web.getSettings().setDefaultTextEncodingName("utf-8");
		// WebView��������ת
		web.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				// ��ǰwebview�ڷ���url
				view.loadUrl(url);
				// ����true��ʾͣ���ڱ�WebView������ת��ϵͳ���������
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				super.onPageFinished(view, url);
			}

		});
		// ��������ʾ
		web.setWebChromeClient(new WebChromeClient() {

			public void onProgressChanged(WebView view, int newProgress) {
				// activity�Ľ�����0 to 10000 (both inclusive),����Ҫ*100
				getActivity().setProgress(newProgress * 100);
				if (newProgress == 100) {
					// ���õ�ǰActivityActionBar
					getActivity().setTitle(view.getTitle());
				} else {
					// ���õ�ǰActivityActionBar
					getActivity().setTitle("������...");
				}
			}
		});
		//����DOM �洢
		web.getSettings().setDomStorageEnabled(true);
		// ����Webviewʱ���ص�Url
		web.loadUrl(Url);
		return rootView;
	}

}
