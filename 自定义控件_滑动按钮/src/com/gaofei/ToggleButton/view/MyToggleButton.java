package com.gaofei.ToggleButton.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MyToggleButton extends View {

	private static final String NAMESPACE = "http://schemas.android.com/apk/res/com.gaofei.ToggleButton";
	private Bitmap background;
	private Bitmap icon;
	private float leftLocation;
	private int maxRightLocation;
	OnToggleOnListener listener;
	private boolean isUp;
	private boolean istoggle;
	private int backgroundid;
	private int iconid;
	public MyToggleButton(Context context) {
		this(context, null);
	}

	
	public MyToggleButton(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public MyToggleButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		istoggle = attrs.getAttributeBooleanValue(NAMESPACE, "toggleon", true);
		backgroundid = attrs.getAttributeResourceValue(NAMESPACE, "mybackground", -1);
		iconid = attrs.getAttributeResourceValue(NAMESPACE, "myicon", -1);
		
		if(backgroundid!=-1 &&iconid!=-1) {
			setBackgroundAndIcon(backgroundid,iconid);
		}
		// setBackgroundAndIcon();
		maxRightLocation = background.getWidth() - icon.getWidth();
		setState(istoggle);
	}

	public void setBackgroundAndIcon(int backgroundid, int iconid) {
		background = BitmapFactory.decodeResource(getResources(), backgroundid);
		icon = BitmapFactory.decodeResource(getResources(), iconid);
	}

	public interface OnToggleOnListener {
		void isToggleOn(boolean istoggleon);
	}
	
	
	public void setOnToggleOnListener(OnToggleOnListener listener) {
		this.listener=listener;
	}
	// 测量控件的宽高
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(background.getWidth(), background.getHeight());

	}

	// 设置控件的位置
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	// 画出控件
	@Override
	protected void onDraw(Canvas canvas) {
		//System.out.println("111111");
		
		canvas.drawBitmap(background, 0, 0, null);

		if (leftLocation < 0) {
			leftLocation = 0;
			
		} else if (leftLocation > maxRightLocation) {
			leftLocation=maxRightLocation;	
		}
		if(isUp) {
			if(listener!=null) {
				listener.isToggleOn(leftLocation>0);
				isUp=false;
			}
		}
		
		canvas.drawBitmap(icon, leftLocation, 0, null);
		super.onDraw(canvas);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			leftLocation = event.getX() - icon.getWidth() / 2;
			break;
		case MotionEvent.ACTION_MOVE:
			leftLocation = event.getX() - icon.getWidth() / 2;
			break;
		case MotionEvent.ACTION_UP:
			
			isUp = true;
			if(event.getX()<background.getWidth()/2) {
				leftLocation=0;
			} else if(event.getX()>background.getWidth()/2){
				leftLocation=maxRightLocation;
			}
			break;

		default:
			break;
		}

		invalidate();
		return true;
	}
	public void setState (boolean istoggle) {
		isUp=true;
		System.out.println(istoggle);
		if(istoggle){
			leftLocation=maxRightLocation;
		} else {
			leftLocation=0;
		}
		
		invalidate();
	}

}
