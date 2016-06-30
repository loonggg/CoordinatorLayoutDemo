package com.loonggg.coordinatorlayoutdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.library.StatusBarUtil;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MainActivity extends AppCompatActivity {
    private LinearLayout head_layout;
    private TabLayout toolbar_tab;
    private ViewPager main_vp_container;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private CoordinatorLayout root_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppBarLayout app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        head_layout = (LinearLayout) findViewById(R.id.head_layout);
        root_layout = (CoordinatorLayout) findViewById(R.id.root_layout);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id
                .collapsing_toolbar_layout);
        app_bar_layout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -head_layout.getHeight() / 2) {
                    mCollapsingToolbarLayout.setTitle("涩郎");
                } else {
                    mCollapsingToolbarLayout.setTitle(" ");
                }
            }
        });
        toolbar_tab = (TabLayout) findViewById(R.id.toolbar_tab);
        main_vp_container = (ViewPager) findViewById(R.id.main_vp_container);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
        main_vp_container.setAdapter(vpAdapter);
        main_vp_container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener
                (toolbar_tab));
        toolbar_tab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener
                (main_vp_container));
        //tablayout和viewpager建立联系为什么不用下面这个方法呢？自己去研究一下，可能收获更多
        //toolbar_tab.setupWithViewPager(main_vp_container);
        loadBlurAndSetStatusBar();

        ImageView head_iv = (ImageView) findViewById(R.id.head_iv);
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new RoundedCornersTransformation(this,
                90, 0)).into(head_iv);
    }

    /**
     * 设置毛玻璃效果和沉浸状态栏
     */
    private void loadBlurAndSetStatusBar() {
        StatusBarUtil.setTranslucent(MainActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                    GlideDrawable> glideAnimation) {
                head_layout.setBackground(resource);
                root_layout.setBackground(resource);
            }
        });

        Glide.with(this).load(R.mipmap.bg).bitmapTransform(new BlurTransformation(this, 100))
                .into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super
                    GlideDrawable> glideAnimation) {
                mCollapsingToolbarLayout.setContentScrim(resource);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId()) {
            case R.id.webview:
                msg += "博客跳转";
                break;
            case R.id.weibo:
                msg += "微博跳转";
                break;
            case R.id.action_settings:
                msg += "设置";
                break;
        }
        if (!msg.equals("")) {
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
