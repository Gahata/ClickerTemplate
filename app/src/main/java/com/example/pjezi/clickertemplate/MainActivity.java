package com.example.pjezi.clickertemplate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.pjezi.clickertemplate.R.id.bottom_nav_bar;
import static com.example.pjezi.clickertemplate.R.id.popup_fullscreen;
import static com.example.pjezi.clickertemplate.R.string.building1_name;

public class MainActivity extends AppCompatActivity
        implements BuildingsFragment.OnFragmentInteractionListener, ClickFragment.OnFragmentInteractionListener, UpgradesFragment.OnFragmentInteractionListener{

    //button for notification view opening
    private ImageButton notificationButton;

    View navigation_button_click;

    //booleans for settings in overflow menu
    private boolean fullscreen = false;
    private boolean sound = true;
    private boolean music = true;

    //expander for total bar
    ImageView totalExpandArrow;

    //textViews for currency values
    TextView bankText;
    TextView totalText;
    TextView perSecondText;

    //currency values and production data
    public static double bankValue;
    public static double totalValue;
    public static double perSecondValue;

    //number of taps
    static int clicksAmount;

    //buildings class instance;
    building building;

    //arraylist for storing all buildings with their values
    public static ArrayList<building> buildings = new ArrayList<>();
    public static ArrayList<Building> buildings2 = new ArrayList<>();


    private ViewPager pager;
    FragmentPagerAdapter adapterViewPager;

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        private MyPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
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
    public void onFragmentInteraction(Uri uri){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //making bottomnavbar work
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(bottom_nav_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //making toolbar (actionbar) work
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Fragment> fragments = getFragments();

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
        navigation_button_click = navigation.findViewById(R.id.navigation_click);

        //initializing notification button and making a toast
        //TODO implement notification view
        notificationButton = (ImageButton) findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Notification button successful",Toast.LENGTH_SHORT).show();
            }

        });

        totalText = (TextView) findViewById(R.id.total_currency_textview);
        bankText = (TextView) findViewById(R.id.bank_textview);
        perSecondText = (TextView) findViewById(R.id.currency_per_second_textview);

        perSecondText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),String.valueOf(perSecondValue),Toast.LENGTH_SHORT).show();
            }
        });

        //total currency produced view expansion arrow and code for animated rotation on click
        totalExpandArrow = (ImageView) findViewById(R.id.total_expand_arrow);
        totalExpandArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalText.getVisibility() == View.GONE) {
                    totalText.setVisibility(View.VISIBLE);
                    totalExpandArrow.animate().rotation(180f).start();
                } else {
                    totalText.setVisibility(View.GONE);
                    totalExpandArrow.animate().rotation(0f).start();
                }
            }
        });
    }

    //method initializing gameplay
    void play() {
        //create buildings
        building = new building();
        building.main();
        //initialize currency per second update process
        textViewRefresher();
    }

    //initializator for saveData() method
    protected void onStop(){
        super.onStop();
        //calling method to save data when app goes offscreen
        saveData();
    }
    //initializator for loadData() method
    protected void onStart() {
        super.onStart();
        //calling method to load data when app starts
        loadData();
        //navigating to click page when app starts
        navigation_button_click.performClick();
    }

    //method to save all data before app is killed
    void saveData() {
        SharedPreferences.Editor editor = getSharedPreferences("savedData", MODE_PRIVATE).edit();

        //checking if array of buildings is full, ie created
        if (buildings.size() == 15) {
            editor.putInt("building1amount", buildings.get(0).amount);
            editor.putInt("building1level", buildings.get(0).level);
            editor.putLong("building1cost", Double.doubleToRawLongBits(buildings.get(0).cost));
            editor.putInt("building2amount", buildings.get(1).amount);
            editor.putInt("building2level", buildings.get(0).level);
            editor.putLong("building2cost", Double.doubleToRawLongBits(buildings.get(1).cost));
            editor.putInt("building3amount", buildings.get(2).amount);
            editor.putInt("building3level", buildings.get(0).level);
            editor.putLong("building3cost", Double.doubleToRawLongBits(buildings.get(2).cost));
            editor.putInt("building4amount", buildings.get(3).amount);
            editor.putInt("building4level", buildings.get(0).level);
            editor.putLong("building4cost", Double.doubleToRawLongBits(buildings.get(3).cost));
            editor.putInt("building5amount", buildings.get(4).amount);
            editor.putInt("building5level", buildings.get(0).level);
            editor.putLong("building5cost", Double.doubleToRawLongBits(buildings.get(4).cost));
            editor.putInt("building6amount", buildings.get(5).amount);
            editor.putInt("building6level", buildings.get(0).level);
            editor.putLong("building6cost", Double.doubleToRawLongBits(buildings.get(5).cost));
            editor.putInt("building7amount", buildings.get(6).amount);
            editor.putInt("building7level", buildings.get(0).level);
            editor.putLong("building7cost", Double.doubleToRawLongBits(buildings.get(6).cost));
            editor.putInt("building8amount", buildings.get(7).amount);
            editor.putInt("building8level", buildings.get(0).level);
            editor.putLong("building8cost", Double.doubleToRawLongBits(buildings.get(7).cost));
            editor.putInt("building9amount", buildings.get(8).amount);
            editor.putInt("building9level", buildings.get(0).level);
            editor.putLong("building9cost", Double.doubleToRawLongBits(buildings.get(8).cost));
            editor.putInt("building10amount", buildings.get(9).amount);
            editor.putInt("building10level", buildings.get(0).level);
            editor.putLong("building10cost", Double.doubleToRawLongBits(buildings.get(9).cost));
            editor.putInt("building11amount", buildings.get(10).amount);
            editor.putInt("building11level", buildings.get(0).level);
            editor.putLong("building11cost", Double.doubleToRawLongBits(buildings.get(10).cost));
            editor.putInt("building12amount", buildings.get(11).amount);
            editor.putInt("building12level", buildings.get(0).level);
            editor.putLong("building12cost", Double.doubleToRawLongBits(buildings.get(11).cost));
            editor.putInt("building13amount", buildings.get(12).amount);
            editor.putInt("building13level", buildings.get(0).level);
            editor.putLong("building13cost", Double.doubleToRawLongBits(buildings.get(12).cost));
            editor.putInt("building14amount", buildings.get(13).amount);
            editor.putInt("building14level", buildings.get(0).level);
            editor.putLong("building14cost", Double.doubleToRawLongBits(buildings.get(13).cost));
            editor.putInt("building15amount", buildings.get(14).amount);
            editor.putInt("building15level", buildings.get(0).level);
            editor.putLong("building15cost", Double.doubleToRawLongBits(buildings.get(14).cost));
        }
        long tStart = System.nanoTime();
        editor.putLong("tStart", tStart);
        editor.putInt("clicksAmount", clicksAmount);
        editor.putLong("bankValue", Double.doubleToRawLongBits(bankValue));
        editor.putLong("totalValue", Double.doubleToRawLongBits(totalValue));
        editor.putLong("perSecondValue", Double.doubleToRawLongBits(perSecondValue));


        editor.apply();

    }
    //method to load all data after app is restarted
    void loadData() {
        play();
        SharedPreferences prefs = getSharedPreferences("savedData", MODE_PRIVATE);
        //checking if one value exists in sharedPrefs, ie if sharedPrefs have been saved
        if (prefs.contains("building1cost")) {

            DecimalFormat format = new DecimalFormat("#");
            format.setDecimalSeparatorAlwaysShown(false);

            buildings.get(0).cost = Double.longBitsToDouble(prefs.getLong("building1cost", 0));
            buildings.get(0).amount = prefs.getInt("building1amount", 0);
            buildings.get(0).level = prefs.getInt("building1level", 0);
            buildings.get(1).cost = Double.longBitsToDouble(prefs.getLong("building2cost", 0));
            buildings.get(1).amount = prefs.getInt("building2amount", 0);
            buildings.get(1).level = prefs.getInt("building2level", 0);
            buildings.get(2).cost = Double.longBitsToDouble(prefs.getLong("building3cost", 0));
            buildings.get(2).amount = prefs.getInt("building3amount", 0);
            buildings.get(2).level = prefs.getInt("building3level", 0);
            buildings.get(3).cost = Double.longBitsToDouble(prefs.getLong("building4cost", 0));
            buildings.get(3).amount = prefs.getInt("building4amount", 0);
            buildings.get(3).level = prefs.getInt("building4level", 0);
            buildings.get(4).cost = Double.longBitsToDouble(prefs.getLong("building5cost", 0));
            buildings.get(4).amount = prefs.getInt("building5amount", 0);
            buildings.get(4).level = prefs.getInt("building5level", 0);
            buildings.get(5).cost = Double.longBitsToDouble(prefs.getLong("building6cost", 0));
            buildings.get(5).amount = prefs.getInt("building6amount", 0);
            buildings.get(5).level = prefs.getInt("building6level", 0);
            buildings.get(6).cost = Double.longBitsToDouble(prefs.getLong("building7cost", 0));
            buildings.get(6).amount = prefs.getInt("building7amount", 0);
            buildings.get(6).level = prefs.getInt("building7level", 0);
            buildings.get(7).cost = Double.longBitsToDouble(prefs.getLong("building8cost", 0));
            buildings.get(7).amount = prefs.getInt("building8amount", 0);
            buildings.get(7).level = prefs.getInt("building8level", 0);
            buildings.get(8).cost = Double.longBitsToDouble(prefs.getLong("building9cost", 0));
            buildings.get(8).amount = prefs.getInt("building9amount", 0);
            buildings.get(8).level = prefs.getInt("building9level", 0);
            buildings.get(9).cost = Double.longBitsToDouble(prefs.getLong("building10cost", 0));
            buildings.get(9).amount = prefs.getInt("building10amount", 0);
            buildings.get(9).level = prefs.getInt("building10level", 0);
            buildings.get(10).cost = Double.longBitsToDouble(prefs.getLong("building11cost", 0));
            buildings.get(10).amount = prefs.getInt("building11amount", 0);
            buildings.get(10).level = prefs.getInt("building11level", 0);
            buildings.get(11).cost = Double.longBitsToDouble(prefs.getLong("building12cost", 0));
            buildings.get(11).amount = prefs.getInt("building12amount", 0);
            buildings.get(11).level = prefs.getInt("building12level", 0);
            buildings.get(12).cost = Double.longBitsToDouble(prefs.getLong("building13cost", 0));
            buildings.get(12).amount = prefs.getInt("building13amount", 0);
            buildings.get(12).level = prefs.getInt("building13level", 0);
            buildings.get(13).cost = Double.longBitsToDouble(prefs.getLong("building14cost", 0));
            buildings.get(13).amount = prefs.getInt("building14amount", 0);
            buildings.get(13).level = prefs.getInt("building14level", 0);
            buildings.get(14).cost = Double.longBitsToDouble(prefs.getLong("building15cost", 0));
            buildings.get(14).amount = prefs.getInt("building15amount", 0);
            buildings.get(14).level = prefs.getInt("building15level", 0);

            bankValue = numberToLetter(Double.longBitsToDouble(prefs.getLong("bankValue", 0)));
            totalValue = numberToLetter(Double.longBitsToDouble(prefs.getLong("totalValue", 0)));
            //perSecondValue = numberToLetterPerSecond(Double.longBitsToDouble(prefs.getLong("perSecondValue", 0)));

            updatePerSecondValue();

            backgroundProduction(prefs.getLong("tStart", 0), perSecondValue);
        }

        String bankString = numberToLetterFromDouble(bankValue);
        bankText.setText(bankString);

        String totalString = getString(R.string.total) + " " + numberToLetterFromDouble(totalValue);
        totalText.setText(totalString);

        String perSecondString = getString(R.string.per_second) + " " + numberToLetterFromDoublePerSecond(perSecondValue);
        perSecondText.setText(perSecondString);


    }
    //method for calculating backgound production between saveData and loadData timers
    void backgroundProduction(long tStart, double perSecond) {
        long tElapsed = System.nanoTime() - tStart;
        long tSeconds = TimeUnit.SECONDS.convert(tElapsed, TimeUnit.NANOSECONDS);
        double backgroundGain = tSeconds * perSecond;
        bankValue = bankValue + backgroundGain;
        totalValue = totalValue + backgroundGain;
    }

    //class with all generic building information
    class building {
        String name;
        int amount;
        //upgrade level defining production speed multiplier
        int level;
        //base cost is a cost of first building
        double baseCost;
        //cost is cost in currency of next building
        double cost;
        //production is currency/second value of a single building
        double production;

        TextView nameTextView;
        TextView amountTextView;
        TextView costTextView;
        Button purchaseButton;
        Button sellButton;
        Button expandButton;
        LinearLayout expandedLayout;

        private void main() {

            building building1 = new building();
            building1.name = getResources().getString(building1_name);
            building1.amount = 0;
            building1.level = 1;
            building1.baseCost = 15;
            building1.cost = building1.baseCost;
            building1.production = 0.1;
            buildings.add(building1);

            building building2 = new building();
            building2.name = "building2";
            building2.amount = 0;
            building2.level = 1;
            building2.baseCost = 100;
            building2.cost = building2.baseCost;
            building2.production = 1;
            buildings.add(building2);

            building building3 = new building();
            building3.name = "building3";
            building3.amount = 0;
            building3.level = 1;
            building3.baseCost = 1100;
            building3.cost = building3.baseCost;
            building3.production = 8;
            buildings.add(building3);

            building building4 = new building();
            building4.name = "building4";
            building4.amount = 0;
            building4.level = 1;
            building4.baseCost = 12000;
            building4.cost = building4.baseCost;
            building4.production = 47;
            buildings.add(building4);

            building building5 = new building();
            building5.name = "building5";
            building5.amount = 0;
            building5.level = 1;
            building5.baseCost = 130000;
            building5.cost = building5.baseCost;
            building5.production = 260;
            buildings.add(building5);

            building building6 = new building();
            building6.name = "building6";
            building6.amount = 0;
            building6.level = 1;
            building6.baseCost = 1400000;
            building6.cost = building6.baseCost;
            building6.production = 1400;
            buildings.add(building6);

            building building7 = new building();
            building7.name = "building7";
            building7.amount = 0;
            building7.level = 1;
            building7.baseCost = 20000000;
            building7.cost = building7.baseCost;
            building7.production = 7800;
            buildings.add(building7);

            building building8 = new building();
            building8.name = "building8";
            building8.amount = 0;
            building8.level = 1;
            building8.baseCost = 330000000;
            building8.cost = building8.baseCost;
            building8.production = 44000;
            buildings.add(building8);

            building building9 = new building();
            building9.name = "building9";
            building9.amount = 0;
            building9.level = 1;
            building9.baseCost = 5100000000d;
            building9.cost = building9.baseCost;
            building9.production = 260000;
            buildings.add(building9);

            building building10 = new building();
            building10.name = "building10";
            building10.amount = 0;
            building10.level = 1;
            building10.baseCost = 75000000000d;
            building10.cost = building10.baseCost;
            building10.production = 1600000;
            buildings.add(building10);

            building building11 = new building();
            building11.name = "building11";
            building11.amount = 0;
            building11.level = 1;
            building11.baseCost = 1000000000000d;
            building11.cost = building11.baseCost;
            building11.production = 10000000;
            buildings.add(building11);

            building building12 = new building();
            building12.name = "building12";
            building12.amount = 0;
            building12.level = 1;
            building12.baseCost = 14000000000000d;
            building12.cost = building12.baseCost;
            building12.production = 65000000;
            buildings.add(building12);


            //TODO set numerical values that are anyhow proper for buildings 13-15
            building building13 = new building();
            building13.name = "building13";
            building13.amount = 0;
            building13.level = 1;
            building13.baseCost = 14000000000000d;
            building13.cost = building13.baseCost;
            building13.production = 65000000;
            buildings.add(building13);

            building building14 = new building();
            building14.name = "building14";
            building14.amount = 0;
            building14.level = 1;
            building14.baseCost = 14000000000000d;
            building14.cost = building14.baseCost;
            building14.production = 65000000;
            buildings.add(building14);

            building building15 = new building();
            building15.name = "building15";
            building15.amount = 0;
            building15.level = 1;
            building15.baseCost = 14000000000000d;
            building15.cost = building15.baseCost;
            building15.production = 65000000;
            buildings.add(building15);
        }
    }

    //thread for currency production
    void textViewRefresher() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        //running the code every 1/10th of a second
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                bankValue = bankValue + 0.1 * perSecondValue;
                                String truncatedBank = numberToLetterFromDouble(bankValue);
                                bankText.setText(truncatedBank);

                                totalValue = totalValue + 0.1 * perSecondValue;
                                String truncatedTotal = numberToLetterFromDouble(totalValue);
                                String totalString = getString(R.string.total)+ " " + truncatedTotal;
                                totalText.setText(totalString);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
    }

    static void updatePerSecondValue() {
        for (building building : buildings) {
            perSecondValue = perSecondValue + building.amount*building.production*building.level;
        }
    }

    //method converting big numbers inside double to smaller ones with letters
    static double numberToLetter(double value) {
        DecimalFormat format = new DecimalFormat("#");
        format.setDecimalSeparatorAlwaysShown(false);
        String truncatedValue = format.format(value);

        //m=million, b=billion, etc
        if (value >= 1000000 && value < 1000000000)
            truncatedValue = String.format("%.2fm", value/ 1000000.0);
        if (value >= 1000000000 && value < 1000000000000d)
            truncatedValue = String.format("%.2fb", value/ 1000000000.0);
        if (value >= 1000000000000d && value < 1000000000000000d)
            truncatedValue = String.format("%.2ft", value/ 1000000000000.0);
        if (value >= 1000000000000000d && value < 1000000000000000000d)
            truncatedValue = String.format("%.2fqd", value/ 1000000000000000.0);
        if (value >= 1000000000000000000d && value < 1000000000000000000000d)
            truncatedValue = String.format("%.2fqn", value/ 1000000000000000000.0);
        if (value >= 1000000000000000000000d && value < 1000000000000000000000000d)
            truncatedValue = String.format("%.2fsx", value/ 1000000000000000000000.0);
        if (value >= 1000000000000000000000000d && value < 1000000000000000000000000000d)
            truncatedValue = String.format("%.2fsp", value/ 1000000000000000000000000.0);
        if (value >= 1000000000000000000000000000d && value < 1000000000000000000000000000000d)
            truncatedValue = String.format("%.2fo", value/ 1000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000d && value < 1000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fn", value/ 1000000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000000d && value < 1000000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fd", value/ 1000000000000000000000000000000000.0);

        value = Double.parseDouble(truncatedValue);
        return value;
    }
    //method converting big numbers inside double to smaller ones with letters
    static double numberToLetterPerSecond(double value) {
        DecimalFormat format = new DecimalFormat("#.#");
        format.setDecimalSeparatorAlwaysShown(false);
        String truncatedValue = format.format(value);

        //m=million, b=billion, etc
        if (value >= 1000000 && value < 1000000000)
            truncatedValue = String.format("%.2fm", value/ 1000000.0);
        if (value >= 1000000000 && value < 1000000000000d)
            truncatedValue = String.format("%.2fb", value/ 1000000000.0);
        if (value >= 1000000000000d && value < 1000000000000000d)
            truncatedValue = String.format("%.2ft", value/ 1000000000000.0);
        if (value >= 1000000000000000d && value < 1000000000000000000d)
            truncatedValue = String.format("%.2fqd", value/ 1000000000000000.0);
        if (value >= 1000000000000000000d && value < 1000000000000000000000d)
            truncatedValue = String.format("%.2fqn", value/ 1000000000000000000.0);
        if (value >= 1000000000000000000000d && value < 1000000000000000000000000d)
            truncatedValue = String.format("%.2fsx", value/ 1000000000000000000000.0);
        if (value >= 1000000000000000000000000d && value < 1000000000000000000000000000d)
            truncatedValue = String.format("%.2fsp", value/ 1000000000000000000000000.0);
        if (value >= 1000000000000000000000000000d && value < 1000000000000000000000000000000d)
            truncatedValue = String.format("%.2fo", value/ 1000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000d && value < 1000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fn", value/ 1000000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000000d && value < 1000000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fd", value/ 1000000000000000000000000000000000.0);

        value = Double.parseDouble(truncatedValue);
        return value;
    }
    //method converting double to string while replacing big numbers with smaller ones with letters
    static String numberToLetterFromDouble(double value) {
        DecimalFormat format = new DecimalFormat("#");
        format.setDecimalSeparatorAlwaysShown(false);
        String truncatedValue = format.format(value);

        value = Double.parseDouble(truncatedValue);

        //m=million, b=billion, etc
        if (value >= 1000000 && value < 1000000000)
            truncatedValue = String.format("%.2fm", value/ 1000000.0);
        if (value >= 1000000000 && value < 1000000000000d)
            truncatedValue = String.format("%.2fb", value/ 1000000000.0);
        if (value >= 1000000000000d && value < 1000000000000000d)
            truncatedValue = String.format("%.2ft", value/ 1000000000000.0);
        if (value >= 1000000000000000d && value < 1000000000000000000d)
            truncatedValue = String.format("%.2fqd", value/ 1000000000000000.0);
        if (value >= 1000000000000000000d && value < 1000000000000000000000d)
            truncatedValue = String.format("%.2fqn", value/ 1000000000000000000.0);
        if (value >= 1000000000000000000000d && value < 1000000000000000000000000d)
            truncatedValue = String.format("%.2fsx", value/ 1000000000000000000000.0);
        if (value >= 1000000000000000000000000d && value < 1000000000000000000000000000d)
            truncatedValue = String.format("%.2fsp", value/ 1000000000000000000000000.0);
        if (value >= 1000000000000000000000000000d && value < 1000000000000000000000000000000d)
            truncatedValue = String.format("%.2fq", value/ 1000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000d && value < 1000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fq", value/ 1000000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000000d && value < 1000000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fd", value/ 1000000000000000000000000000000000.0);

        return truncatedValue;
    }
    //method converting double to string while replacing big numbers with smaller ones with letters and keeping one decimal
    static String numberToLetterFromDoublePerSecond(double value) {
        DecimalFormat format = new DecimalFormat("#.#");
        format.setDecimalSeparatorAlwaysShown(false);
        String truncatedValue = format.format(value);

        value = Double.parseDouble(truncatedValue);

        //m=million, b=billion, etc
        if (value >= 1000000 && value < 1000000000)
            truncatedValue = String.format("%.2fm", value/ 1000000.0);
        if (value >= 1000000000 && value < 1000000000000d)
            truncatedValue = String.format("%.2fb", value/ 1000000000.0);
        if (value >= 1000000000000d && value < 1000000000000000d)
            truncatedValue = String.format("%.2ft", value/ 1000000000000.0);
        if (value >= 1000000000000000d && value < 1000000000000000000d)
            truncatedValue = String.format("%.2fqd", value/ 1000000000000000.0);
        if (value >= 1000000000000000000d && value < 1000000000000000000000d)
            truncatedValue = String.format("%.2fqn", value/ 1000000000000000000.0);
        if (value >= 1000000000000000000000d && value < 1000000000000000000000000d)
            truncatedValue = String.format("%.2fsx", value/ 1000000000000000000000.0);
        if (value >= 1000000000000000000000000d && value < 1000000000000000000000000000d)
            truncatedValue = String.format("%.2fsp", value/ 1000000000000000000000000.0);
        if (value >= 1000000000000000000000000000d && value < 1000000000000000000000000000000d)
            truncatedValue = String.format("%.2fq", value/ 1000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000d && value < 1000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fq", value/ 1000000000000000000000000000000.0);
        if (value >= 1000000000000000000000000000000000d && value < 1000000000000000000000000000000000000d)
            truncatedValue = String.format("%.2fd", value/ 1000000000000000000000000000000000.0);

        return truncatedValue;
    }

    //starting About activity, used as a menu option in menu toolbar overflow
    private void launchAboutActivity() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

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
                launchAboutActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //TODO write mute_sound and unmute_sound methods (hard without having any sound)
    public void mute_sound() {
    }
    public void unmute_sound() {
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
}