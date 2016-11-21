package com.gaofei.ViewPager01;

import android.os.Bundle;
import android.os.Handler;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int[] imageResIds = { R.drawable.a, R.drawable.b, R.drawable.c,
			R.drawable.d, R.drawable.e, };

	private String[] descs = { "巩俐不低俗，我就不能低俗", "扑树又回来啦！再唱经典老歌引万人大合唱",
			"揭秘北京电影如何升级", "乐视网TV版大派送", "热血屌丝的反杀", };
	private ImageView[] Images;
	private View[] dots;
	private ViewPager mViewPager;

	private TextView mTv;
	private View currentselectveiw;
	private LinearLayout mLl_dots;

	private int mAXdaptercount;

	private Handler handler=new Handler() {
		public void handleMessage(android.os.Message msg) {
			if(msg.what==0) {
				moveViewPager();
			}
		}

		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Images = new ImageView[imageResIds.length];
		dots = new View[imageResIds.length];
		mAXdaptercount=imageResIds.length*1000*100;
		initView();
		
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mTv = (TextView) findViewById(R.id.tv);
		mLl_dots = (LinearLayout) findViewById(R.id.ll_dots);

		for (int i = 0; i < imageResIds.length; i++) {
			createImageView(i);
			createDots(i);
		}

		viewChangeListener();
		change(0);
	}
	private void moveViewPager() {
		int currentItem = mViewPager.getCurrentItem();
		currentItem++;
		mViewPager.setCurrentItem(currentItem);
		handler.sendEmptyMessageDelayed(0, 3000);
	};
	private void createDots(int i) {
		dots[i] = new View(this);
		LayoutParams params = new LayoutParams(5, 5);
		params.rightMargin = 5;
		dots[i].setBackgroundResource(R.drawable.selector_dot);
		mLl_dots.addView(dots[i], params);

	}


	private void viewChangeListener() {
		mViewPager.setAdapter(new MyAdapter());
		mViewPager.setCurrentItem(mAXdaptercount/2);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				change(position);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				if(state==ViewPager.SCROLL_STATE_IDLE) {
					System.out.println("11111111");
					handler.sendEmptyMessageDelayed(0, 3000);
					//ViewPager.SCROLL_STATE_SETTLING
				} else {
					handler.removeMessages(0);
					
				}
			}
		});
	}

	protected void change(int position) {
		position=position%5;
		mTv.setText(descs[position]);
		if(currentselectveiw!=null) {
			currentselectveiw.setSelected(false);
		}
		dots[position].setSelected(true);
		currentselectveiw=dots[position];
	}

	private void createImageView(int i) {
		Images[i] = new ImageView(this);
		Images[i].setBackgroundResource(imageResIds[i]);
	}

	private class MyAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mAXdaptercount;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position=position %5;
			ImageView imageview = Images[position];
			container.addView(imageview);
			return imageview;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			// super.destroyItem(container, position, object);
			container.removeView((View) object);
		}

	}
	@Override
	protected void onStart() {
		handler.sendEmptyMessageDelayed(0, 3000);
		super.onStart();
	}

	@Override
	protected void onStop() {
		handler.removeMessages(0);
		super.onStop();
	}
}
