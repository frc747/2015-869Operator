package com.frc869.robot.operator2015;

import com.frc869.robot.operator2015.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class Operator extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_operator);

		final View operatorView = findViewById(R.id.Operator_control);
		final View zone1_Next_v = findViewById(R.id.Zone1_Next);
		Button zone1_Next = (Button) zone1_Next_v;
		
		zone1_Next.setText("Next");
		
		zone1_Next.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});

	}

}
