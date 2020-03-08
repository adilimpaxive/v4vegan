package com.oranjeclick.veganapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.oranjeclick.veganapp.R;

public class Splash extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000); //3 seconds pause time.
				} catch (InterruptedException e) {
					Toast.makeText(Splash.this, "Exception is : " + e.toString(), Toast.LENGTH_SHORT).show();
				}
				// login Activity class is called
				Intent intent = new Intent(Splash.this, MainPage.class);
				startActivity(intent);
				finish();
			}
		}).start();
	}
}
