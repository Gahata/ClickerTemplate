package com.example.pjezi.clickertemplate;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.pjezi.clickertemplate.R.id.checkbox;
import static com.example.pjezi.clickertemplate.R.id.popup_fullscreen;

public class MainActivity extends AppCompatActivity
        implements BuildingsFragment.OnFragmentInteractionListener, ClickFragment.OnFragmentInteractionListener, UpgradesFragment.OnFragmentInteractionListener{

    //button for notification view opening
    private ImageButton notification_button;

    //booleans for settings in overflow menu
    private boolean fullscreen = false;
    private boolean sound = true;
    private boolean music = true;

    //expander for total bar
    ImageView totalExpandArrow;

    TextView totalValue;

    private ViewPager pager;
    FragmentPagerAdapter adapterViewPager;

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
            super(fragmentManager);
            this.fragments = fragments;
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return this.fragments.size();
        }

        // Returns the fragment to display for that page
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }


    private List<Fragment> getFragments(){
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(BuildingsFragment.newInstance());
        fragmentList.add(ClickFragment.newInstance());
        fragmentList.add(UpgradesFragment.newInstance());
        return fragmentList;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Fragment> fragments = getFragments();

        //making bottomnavbar work
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //making toolbar (actionbar) work
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        notification_button = (ImageButton) findViewById(R.id.notification_button);
        notification_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Notification button successful",Toast.LENGTH_SHORT).show();
            }

        });

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(adapterViewPager);

        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_buildings);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_click);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_upgrades);
                        break;
                    default:
                        break;
                }
            }
        };
        pager.addOnPageChangeListener(pageChangeListener);

        //navigation to click panel at app start
        View view = navigation.findViewById(R.id.navigation_click);
        view.performClick();

        totalValue = (TextView) findViewById(R.id.total_currency_textview);

        totalExpandArrow = (ImageView) findViewById(R.id.total_expand_arrow);

        totalExpandArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalValue.getVisibility() == View.GONE) {
                    totalValue.setVisibility(View.VISIBLE);
                    totalExpandArrow.animate().rotation(180f).start();
                } else {
                    totalValue.setVisibility(View.GONE);
                    totalExpandArrow.animate().rotation(0f).start();
                }
            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri){
    }

    //standard android bottomnavigationview code, nothing much happens here so far
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_buildings:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_click:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_upgrades:
                    pager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    //setting correct checkboxes for settings, called before opening menu every time
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (sound) {
            menu.findItem(R.id.popup_sound).setChecked(true);
        } else {
            menu.findItem(R.id.popup_sound).setChecked(false);
        }
        if (music) {
            menu.findItem(R.id.popup_music).setChecked(true);
        } else {
            menu.findItem(R.id.popup_music).setChecked(false);
        }
        if (fullscreen) {
            menu.findItem(R.id.popup_fullscreen).setChecked(true);
        } else {
            menu.findItem(R.id.popup_fullscreen).setChecked(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popup_sound:
                if(sound) {
                    mute_sound();
                } else {
                    unmute_sound();
                }
                return true;
            case R.id.popup_music:
                if(music) {
                    mute_music();
                } else {
                    unmute_music();
                }
                return true;
            case popup_fullscreen:
                if(fullscreen) {
                    showDecor();
                } else {
                    hideDecor();
                }
                return true;
            case R.id.popup_about:
                Toast.makeText(getApplicationContext(),"Popup about successful",Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO write mute_sound and unmute_sound methods (hard without having any sound)
    public void mute_sound() {
        sound = false;
    }
    public void unmute_sound() {
        sound = true;
    }

    //TODO write mute_sound and unmute_sound methods (hard without having any music)
    public void mute_music() {
        music = false;
    }
    public void unmute_music() {
        music = true;
    }

    //methods to hide navigation and statusbar programmatically, taken mostly from Slide
    public void hideDecor(){
        final View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            decorView.setOnSystemUiVisibilityChangeListener(
                    new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility) == 0) {
                                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
                            } else {
                                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
                            }
                        }
                    });
        fullscreen = true;
    }
    public void showDecor() {
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(null);
        decorView.setSystemUiVisibility(0);
        fullscreen = false;
    }

}