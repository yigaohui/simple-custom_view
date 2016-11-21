package com.gaofei.menu01;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;

public class Tools {

	private static boolean isShow;
	public static boolean isShow(){
		return isShow;
	};
	public static void show(View menu){
		setAnimation(menu,-180f,0f,0);
		setClickable(menu,true);
	}
	public static void hide(View menu,long startOffset){
		setAnimation(menu, 0f, -180f,startOffset);
		setClickable(menu,false);
	}

	private static void setClickable(View menu,boolean enable ) {
		menu.setEnabled(enable);
		if(menu instanceof ViewGroup) {
			ViewGroup group=(ViewGroup)menu;
			
			for (int i = 0; i < group.getChildCount(); i++) {
				View view = group.getChildAt(i);
				view.setEnabled(enable);
			}
		}
	}
	private static void setAnimation(View menu, float fromDegrees, float toDegrees,long startOffset) {
		RotateAnimation animation=new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,1.0f);
		animation.setDuration(500);
		animation.setFillAfter(true);
		menu.startAnimation(animation);
		animation.setStartOffset(startOffset);
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				isShow=true;
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				isShow=false;
			}
		});
	}

	
}
