package com.example.pulltorefreshlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PulltoreFreshLisViewt extends ListView implements OnScrollListener {
	private static int REFRESHING = 3;
	private static int PULL_DOWN = 1;
	private static int REFRESH = 2;
	private static int CURRENTSTATE = PULL_DOWN;
	private FreshListener listener;
	private View footerView;
	private int measuredHeight2;
	private View headView;
	private float downY;
	private int measuredHeight;
	private ImageView mArrow;
	private RotateAnimation up;
	private RotateAnimation down;
	private TextView mTv;
	private ProgressBar mProgress;
	boolean isLoadMore = false;

	public PulltoreFreshLisViewt(Context context) {
		this(context, null);
	}

	public PulltoreFreshLisViewt(Context context, AttributeSet attrs) {
		this(context, attrs, -1);
	}

	public PulltoreFreshLisViewt(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		addHead();
		addFooter();
		// 初始化动画

		setAnimation();
		setOnScrollListener(this);

	}

	private void addFooter() {
		footerView = View.inflate(getContext(), R.layout.footer, null);
		footerView.measure(0, 0);
		measuredHeight2 = footerView.getMeasuredHeight();
		footerView.setPadding(0, 0, 0, -measuredHeight2);
		addFooterView(footerView);
	}

	private void initView() {

		mArrow = (ImageView) headView.findViewById(R.id.arrow);
		mTv = (TextView) headView.findViewById(R.id.tv);
		mProgress = (ProgressBar) headView.findViewById(R.id.progressbar);

	}

	private void setAnimation() {
		up = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		up.setDuration(500);
		up.setFillAfter(true);

		down = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		down.setDuration(500);
		down.setFillAfter(true);
	}

	private void addHead() {

		headView = View
				.inflate(getContext(), R.layout.llistview_headitem, null);
		headView.measure(0, 0);
		measuredHeight = headView.getMeasuredHeight();
		headView.setPadding(0, -measuredHeight, 0, 0);
		this.addHeaderView(headView);
		initView();

	}

	private void setChange() {

		if (CURRENTSTATE == REFRESH) {
			// 当前处于刷新状态,箭头执行向上的动画,textview变成松开刷新
			System.out.println("执行动画");
			mArrow.startAnimation(up);
			mTv.setText("松开刷新");

		} else if (CURRENTSTATE == PULL_DOWN) {
			// 当前处于下拉状态,箭头执行向下的动画,textview变成下拉刷新
			mArrow.startAnimation(down);
			mTv.setText("下拉刷新");
		} else if (CURRENTSTATE == REFRESHING) {
			mArrow.clearAnimation();
			mProgress.setVisibility(View.VISIBLE);
			mArrow.setVisibility(View.GONE);
		}
	}

	// 刷新完成调用的方法,隐藏刷新头
	public void finish() {
		if (CURRENTSTATE == REFRESHING) {
			CURRENTSTATE = PULL_DOWN;
			mTv.setText("下拉刷新");
			headView.setPadding(0, -measuredHeight, 0, 0);
			mProgress.setVisibility(View.GONE);
			mArrow.setVisibility(View.VISIBLE);
		}
		if (isLoadMore) {
			footerView.setPadding(0, 0, 0, -measuredHeight2);
			isLoadMore = false;
		}

	}

	public interface FreshListener {
		void fresh();

		void loadMore();
	}

	public void setFreshListener(FreshListener listener) {
		this.listener = listener;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float moveY = ev.getY();
			int distence = (int) (moveY - downY);
			int padding = distence - measuredHeight;
			// 下拉,显示刷新头,如果下拉距离大于刷新头的高度,此时松开就会刷新,所以要改变刷新头中的文本内容和箭头的动画
			if (this.getFirstVisiblePosition() == 0 && distence > 0) {
				headView.setPadding(0, padding, 0, 0);
				if (padding > 0 && CURRENTSTATE == PULL_DOWN) {
					CURRENTSTATE = REFRESH;
					setChange();
				}
				if (padding < 0 && CURRENTSTATE == REFRESH) {
					CURRENTSTATE = PULL_DOWN;
					setChange();
				}
				return true;
			}

			break;

		case MotionEvent.ACTION_UP:
			if (CURRENTSTATE == REFRESH) {
				headView.setPadding(0, 0, 0, 0);
				CURRENTSTATE = REFRESHING;
				setChange();
				if (listener != null) {
					listener.fresh();
				}

			}
			if (CURRENTSTATE == PULL_DOWN) {
				headView.setPadding(0, -measuredHeight, 0, 0);
			}
			break;

		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		System.out.println(1111);

		if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
				&& getLastVisiblePosition() == getAdapter().getCount() - 1
				&& isLoadMore == false) {
			isLoadMore = true;
			System.out.println("显示加载条目");
			footerView.setPadding(0, 0, 0, 0);
			setSelection(getAdapter().getCount() - 1);// 定位选择哪个条目position：条目的索引

			if (listener != null) {
				listener.loadMore();

			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub

	}

}
