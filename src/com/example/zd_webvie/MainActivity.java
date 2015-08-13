package com.example.zd_webvie;

import java.io.File;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 支持进度条显示
		getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		selectItem("http://www.wander-moon.com");
	}

	private void selectItem(String url) {
		// update the main content by replacing fragments
		Fragment fragment = new WebviewFragment(url);
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.frame, fragment)
				.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(0, 1, 0, "刷新")
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(0, 2, 0, "分享")
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getTitle() == "刷新") {
			WebviewFragment.web.reload();
		} else if (item.getTitle() == "分享") {
			Intent intent = new Intent(Intent.ACTION_SEND);
			File f = new File("/sdcard/Koala.jpg");
			Uri u = Uri.fromFile(f);
			intent.setType("text/plain");
			intent.setType("image/*");
			intent.putExtra(Intent.EXTRA_STREAM, u);
			intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
			intent.putExtra(Intent.EXTRA_TEXT,WebviewFragment.web.getUrl().trim());
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			this.startActivity(Intent.createChooser(intent, "分享"));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if ((keyCode == KeyEvent.KEYCODE_BACK)
				&& WebviewFragment.web.canGoBack()) {
			WebviewFragment.web.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
