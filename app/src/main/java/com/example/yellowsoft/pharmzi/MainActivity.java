package com.example.yellowsoft.pharmzi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar.LayoutParams;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.OnNavigationPositionListener;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.OnTabSelectedListener;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation.TitleState;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation.OnMenuItemSelectionListener;

public class MainActivity extends AppCompatActivity implements OnMenuItemSelectionListener {
    private static long back_pressed;
    String act;
    String area_id;
    ImageView back_btn;
    ImageView cart;
    String cart_;
    ImageView change_city;
    String delivery;
    ImageView lan;
    private AHBottomNavigation mBottomNavigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    public ViewPager mViewPager;
    TextView page_title;
    String pre = "0";
    boolean previous_page;
    int previous_postion = 0;
    ImageView search;
    private TabsAdapter tabsAdapter;

    class C04051 implements OnClickListener {
        C04051() {
        }

        public void onClick(View view) {
            MainActivity.this.mViewPager.setCurrentItem(0);
        }
    }

    class C04062 implements OnClickListener {
        C04062() {
        }

        public void onClick(View view) {
            MainActivity.this.startActivity(new Intent(MainActivity.this, LanguageActivity.class));
            MainActivity.this.finish();
        }
    }

    class C04073 implements OnClickListener {
        C04073() {
        }

        public void onClick(View view) {
            MainActivity.this.startActivityForResult(new Intent(MainActivity.this, CityActivity.class), 1);
            MainActivity.this.finish();
        }
    }

    class C07674 implements OnTabSelectedListener {
        C07674() {
        }

        public boolean onTabSelected(int position, boolean wasSelected) {
            MainActivity.this.mViewPager.setCurrentItem(position);
            return true;
        }
    }

    class C07685 implements OnNavigationPositionListener {
        C07685() {
        }

        public void onPositionChange(int y) {
        }
    }

    class C07696 implements OnPageChangeListener {
        C07696() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public void onPageSelected(int position) {
            if (position == 0) {
                try {
                    MainActivity.this.mBottomNavigation.setCurrentItem(position, false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (position == 1) {
                MainActivity.this.mBottomNavigation.setCurrentItem(position, false);
            } else if (position == 2) {
                MainActivity.this.mBottomNavigation.setCurrentItem(position, false);
            } else if (position == 3) {
                MainActivity.this.mBottomNavigation.setCurrentItem(position, false);
            } else if (position == 4) {
                MainActivity.this.mBottomNavigation.setCurrentItem(position, false);
            }
            MainActivity.this.setHeader(position);
        }

        public void onPageScrollStateChanged(int state) {
        }
    }

    private void setupActionBar() {
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        View v = getLayoutInflater().inflate(R.layout.action_bar, null);
        this.page_title = (TextView) v.findViewById(R.id.page_title);
        this.back_btn = (ImageView) v.findViewById(R.id.page_back);
        this.search = (ImageView) v.findViewById(R.id.search);
        this.lan = (ImageView) v.findViewById(R.id.lan);
        this.change_city = (ImageView) v.findViewById(R.id.change_city);
        this.cart = (ImageView) v.findViewById(R.id.cart);
        this.back_btn.setOnClickListener(new C04051());
        this.lan.setOnClickListener(new C04062());
        this.change_city.setOnClickListener(new C04073());
        getSupportActionBar().setCustomView(v, layoutParams);
        ((Toolbar) v.getParent()).setContentInsetsAbsolute(0, 0);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.mBottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        if (getIntent() != null && getIntent().hasExtra("id")) {
            this.area_id = getIntent().getStringExtra("id");
        }
        this.act = getIntent().getStringExtra("act");
        this.cart_ = getIntent().getStringExtra("cart");
        this.delivery = getIntent().getStringExtra(Session.delivery);
        Log.e(Session.delivery, this.delivery);
        Session.SetDelivery(this, this.delivery);
        this.mBottomNavigation.addItem(new AHBottomNavigationItem("Home", (int) R.drawable.home));
        this.mBottomNavigation.addItem(new AHBottomNavigationItem("Pharmacies", (int) R.drawable.shop_gray));
        this.mBottomNavigation.addItem(new AHBottomNavigationItem("Categories", (int) R.drawable.category_gray));
        this.mBottomNavigation.addItem(new AHBottomNavigationItem("Account", (int) R.drawable.user_gray));
        this.mBottomNavigation.addItem(new AHBottomNavigationItem("Cart", (int) R.drawable.cart_gray));
        this.mBottomNavigation.setTitleState(TitleState.ALWAYS_SHOW);
        this.mBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#f6f5f0"));
        this.mBottomNavigation.setAccentColor(Color.parseColor("#00bbb2"));
        this.mBottomNavigation.setInactiveColor(Color.parseColor("#616161"));
        this.mBottomNavigation.setForceTint(true);
        this.mBottomNavigation.setCurrentItem(0);
        this.mBottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        this.mBottomNavigation.setOnTabSelectedListener(new C07674());
        this.mBottomNavigation.setOnNavigationPositionListener(new C07685());
        this.tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        this.mViewPager = (ViewPager) findViewById(R.id.viewPager);
        this.mViewPager.setAdapter(this.tabsAdapter);
        this.mViewPager.addOnPageChangeListener(new C07696());
        setSupportActionBar((Toolbar) findViewById(R.id.my_awesome_toolbar));
        setupActionBar();
        setHeader(0);
        if (this.act.equals("1")) {
            this.mViewPager.setCurrentItem(4);
        } else if (this.act.equals("0")) {
            this.mViewPager.setCurrentItem(0);
        }
        RelativeLayout mainView = (RelativeLayout) findViewById(R.id.mainView);
    }

    public void onBackPressed() {
        Log.e("pp_pp_back", String.valueOf(this.previous_postion));
        if (this.mViewPager.getCurrentItem() == 0) {
            finish();
        } else if (this.mViewPager.getCurrentItem() == 1) {
            this.mViewPager.setCurrentItem(this.previous_postion, false);
        } else if (this.mViewPager.getCurrentItem() == 2) {
            this.mViewPager.setCurrentItem(this.previous_postion, false);
        } else if (this.mViewPager.getCurrentItem() == 3) {
            this.mViewPager.setCurrentItem(this.previous_postion, false);
        } else if (this.mViewPager.getCurrentItem() == 4) {
            this.mViewPager.setCurrentItem(this.previous_postion, false);
        } else {
            this.mViewPager.setCurrentItem(0, false);
        }
    }

    public void onMenuItemSelect(@IdRes int i, int i1, boolean b) {
    }

    public void onMenuItemReselect(@IdRes int i, int i1, boolean b) {
        if (i1 == 2 && !this.previous_page && this.mViewPager.getCurrentItem() == 3) {
            this.previous_page = false;
        } else {
            this.previous_page = false;
        }
    }

    private void setHeader(int pos) {
        this.page_title.setVisibility(View.GONE);
        switch (pos) {
            case 0:
                this.back_btn.setVisibility(View.GONE);
                this.search.setVisibility(View.GONE);
                this.cart.setVisibility(View.GONE);
                this.lan.setVisibility(View.VISIBLE);
                this.change_city.setVisibility(View.VISIBLE);
                this.page_title.setVisibility(View.VISIBLE);
                if (Session.GetLang(MainActivity.this).equals("en")) {
                    this.page_title.setText(Session.GetAreaTitle(this));
                    Log.e("area_title",Session.GetAreaTitle(this));
                }else {
                    this.page_title.setText(Session.GetAreaTitleAr(this));
                    Log.e("area_title_ar",Session.GetAreaTitleAr(this));
                }
                return;
            case 1:
                this.lan.setVisibility(View.GONE);
                this.change_city.setVisibility(View.GONE);
                this.search.setVisibility(View.VISIBLE);
                this.back_btn.setVisibility(View.VISIBLE);
                this.page_title.setVisibility(View.VISIBLE);
                this.page_title.setText("Pharmacies");
                return;
            case 2:
                this.lan.setVisibility(View.GONE);
                this.change_city.setVisibility(View.GONE);
                this.cart.setVisibility(View.GONE);
                this.search.setVisibility(View.GONE);
                this.back_btn.setVisibility(View.GONE);
                this.page_title.setVisibility(View.VISIBLE);
                this.page_title.setText("Categories");
                return;
            case 3:
                this.lan.setVisibility(View.GONE);
                this.change_city.setVisibility(View.GONE);
                this.cart.setVisibility(View.GONE);
                this.search.setVisibility(View.GONE);
                this.back_btn.setVisibility(View.GONE);
                this.page_title.setVisibility(View.VISIBLE);
                this.page_title.setText("Account");
                return;
            case 4:
                this.lan.setVisibility(View.GONE);
                this.change_city.setVisibility(View.GONE);
                this.cart.setVisibility(View.GONE);
                this.search.setVisibility(View.GONE);
                this.back_btn.setVisibility(View.GONE);
                this.page_title.setVisibility(View.VISIBLE);
                this.page_title.setText("Cart");
                return;
            default:
                return;
        }
    }

    public void onResume() {
        super.onResume();
        try {
            if (this.pre.equals("1")) {
                this.mBottomNavigation.setCurrentItem(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
