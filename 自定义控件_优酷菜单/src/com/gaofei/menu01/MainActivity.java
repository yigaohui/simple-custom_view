package com.gaofei.menu01;

import android.os.Bundle;
import android.app.Activity;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{

    private Button mMiddle;
	private Button mMenu;
	private boolean isMiddleShow=true;
	private boolean isTopShow=true;
	private RelativeLayout Rel_mtop;
	private RelativeLayout mRel_middle;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        initView();
    }

	private void initView() {
		mMiddle = (Button) findViewById(R.id.btn_middle);
		mMenu = (Button) findViewById(R.id.btn_menu);
		Rel_mtop = (RelativeLayout) findViewById(R.id.rel_top);
		mRel_middle = (RelativeLayout) findViewById(R.id.rel_middle);
		mMiddle.setOnClickListener(this);
		mMenu.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_middle) {
			if(Tools.isShow()) {
				return;
			}
			
			if(isTopShow) {
				//����м�İ�ť,topִ�ж���,���top��ʾ,��ִ����ʧ����.
				Tools.hide(Rel_mtop,0);
			} else {
				//���top����ʾ,ִ����ʾ����
				Tools.show(Rel_mtop);
			}
			isTopShow=!isTopShow;
		} else if(v.getId()==R.id.btn_menu) {
			if(Tools.isShow()) {
				return;
			}
			if(isTopShow) {
				//����˵���ť,���top����,middle����,top��middleִ����ʧ����
				Tools.hide(Rel_mtop,0);
				Tools.hide(mRel_middle,300);
				isTopShow=!isTopShow;
			} else if(isMiddleShow){
				Tools.hide(mRel_middle,0);
				//���top������,middle����,ִ��middle��ʧ����
			} else {
				//���middle������,ִ��middle��ʾ����
				Tools.show(mRel_middle);
			}
			
			isMiddleShow=!isMiddleShow;
			
		}
	}
}
