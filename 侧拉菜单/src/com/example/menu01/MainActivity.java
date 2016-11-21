package com.example.menu01;

import com.gaofe.view.MySlidingMenu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private MySlidingMenu mySlidingmenu;
	private ImageView mBack;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        intiView();
    }
    
    private void intiView() {
    	mySlidingmenu = (MySlidingMenu) findViewById(R.id.myslidingmenu);
    	mBack = (ImageView) findViewById(R.id.back);
    	mBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mySlidingmenu.toggle();
			}
		});
	}

	public void showtext(View view) {
    	TextView textView=(TextView)view;
    	Toast.makeText(this, textView.getText().toString(), 0).show();
    }
    
    
}
