package com.example.pulltorefreshlistview;

import java.util.ArrayList;
import java.util.List;

import com.example.pulltorefreshlistview.PulltoreFreshLisViewt.FreshListener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class MainActivity extends Activity {

	private int  j=0;
	private int q=0;
    private PulltoreFreshLisViewt mListView;
    private List<String> list=new ArrayList<String>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 10; i++) {
        	list.add("德玛西亚"+i+"区");
		}
        initView();
    }

	private void initView() {
		mListView = (PulltoreFreshLisViewt) findViewById(R.id.pulltorefreshlistview);
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		mListView.setAdapter(adapter);
		mListView.setFreshListener(new FreshListener() {
			
			@Override
			public void fresh() {
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						list.add(0,"艾欧尼亚"+j+"区");
						j++;
						adapter.notifyDataSetChanged();
						mListView.finish();
					}
				}, 1000);
				
			}

			@Override
			public void loadMore() {
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						list.add("黑色玫瑰"+q+"区");
						q++;
						mListView.finish();
					}
				}, 1000);
				
			}
		});
	}


  
    
}
