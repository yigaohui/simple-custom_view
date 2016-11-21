package com.gaofei.ToggleButton;

import com.gaofei.ToggleButton.view.MyToggleButton;
import com.gaofei.ToggleButton.view.MyToggleButton.OnToggleOnListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MyToggleButton mToggleButton;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
    	MyToggleButton mToggleButton = (MyToggleButton) findViewById(R.id.mytogglebutton);
    	//mToggleButton.setBackgroundResource(R.drawable.slide_background);
    	mToggleButton.setBackgroundAndIcon(R.drawable.slide_background,R.drawable.slide_icon);
    	mToggleButton.setState(true);
    	mToggleButton.setOnToggleOnListener(new OnToggleOnListener() {
			
			@Override
			public void isToggleOn(boolean istoggleon) {
				Toast.makeText(getApplicationContext(), istoggleon ? "¿ªÆô" :"¹Ø±Õ", 0).show();
			}
		});
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }
	
    
}
