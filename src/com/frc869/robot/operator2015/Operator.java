package com.frc869.robot.operator2015;

import com.frc869.robot.operator2015.server.Server;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.VelocityTrackerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Space;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Operator extends Activity {
	private static final String DEBUG_TAG = "Velocity";
	Server server;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		this.server = new Server(5809);

		if (Build.VERSION.SDK_INT < 16) {
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}

		View decorView = getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
				| View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
				| View.SYSTEM_UI_FLAG_IMMERSIVE;
		decorView.setSystemUiVisibility(uiOptions);
		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		ActionBar actionBar = getActionBar();
		actionBar.hide();

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_operator);

		final View main = findViewById(R.id.Activity_main);
		main.setBackgroundColor(Color.RED);

		final View operatorView = findViewById(R.id.Operator_control);
		final View zone1_Next_v = findViewById(R.id.Zone1_Next);
		final View zone1_Prev_v = findViewById(R.id.Zone1_Prev);
		final View zone1_Touch_v = findViewById(R.id.Zone1_Touch);
		final View zone2_Next_v = findViewById(R.id.Zone2_Next);
		final View zone2_Prev_v = findViewById(R.id.Zone2_Prev);
		final View zone2_Touch_v = findViewById(R.id.Zone2_Touch);
		final View zone3_Next_v = findViewById(R.id.Zone3_Next);
		final View zone3_Prev_v = findViewById(R.id.Zone3_Prev);
		final View zone3_Touch_v = findViewById(R.id.Zone3_Touch);
		Button zone1_Next = (Button) zone1_Next_v;
		Button zone1_Prev = (Button) zone1_Prev_v;
		Button zone2_Next = (Button) zone2_Next_v;
		Button zone2_Prev = (Button) zone2_Prev_v;
		Button zone3_Next = (Button) zone3_Next_v;
		Button zone3_Prev = (Button) zone3_Prev_v;

		Space zone1_Touch = (Space) zone1_Touch_v;
		Space zone2_Touch = (Space) zone2_Touch_v;
		Space zone3_Touch = (Space) zone3_Touch_v;

		zone1_Next.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				server.sendMessage("TUGGERLEFT NEXT");
				return false;
			}
		});

		zone1_Prev.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				server.sendMessage("TUGGERLEFT PREV");
				return false;
			}
		});
		zone2_Next.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				server.sendMessage("TUGGERRIGHT NEXT");
				return false;
			}
		});
		zone2_Prev.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				server.sendMessage("TUGGERRIGHT PREV");
				return false;
			}
		});

		zone3_Next.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				server.sendMessage("LIFT NEXTUP");
				return false;
			}
		});

		zone3_Prev.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				server.sendMessage("LIFT NEXTDOWN");
				return false;
			}
		});

		zone1_Touch.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					if (event.getY() > v.getHeight() / 2) {
						server.sendMessage("TUGGERLEFT UP");
					} else {
						server.sendMessage("TUGGERLEFT DOWN");
					}
					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					server.sendMessage("TUGGERLEFT STOP");
				default:
					break;
				}

				return false;
			}

		});
		zone1_Touch.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					if (event.getY() > v.getHeight() / 2) {
						server.sendMessage("TUGGERRIGHT UP");
					} else {
						server.sendMessage("TUGGERRIGHT DOWN");
					}
					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					server.sendMessage("TUGGERRIGHT STOP");
				default:
					break;
				}

				return false;
			}

		});
		zone1_Touch.setOnTouchListener(new View.OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getActionMasked()) {
				case MotionEvent.ACTION_DOWN:
				case MotionEvent.ACTION_MOVE:
					if (event.getY() > v.getHeight() / 2) {
						server.sendMessage("LIFT UP");
					} else {
						server.sendMessage("LIFT DOWN");
					}
					break;
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					server.sendMessage("LIFT STOP");
				default:
					break;
				}

				return false;
			}

		});
	}
	
	public void onResume(){
		super.onResume();
		
		View decorView = getWindow().getDecorView();
		// Hide the status bar.
		int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
	            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
	            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
	            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
	            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
	            | View.SYSTEM_UI_FLAG_IMMERSIVE;
		decorView.setSystemUiVisibility(uiOptions);
		// Remember that you should never show the action bar if the
		// status bar is hidden, so hide that too if necessary.
		ActionBar actionBar = getActionBar();
		actionBar.hide();
	}

}
