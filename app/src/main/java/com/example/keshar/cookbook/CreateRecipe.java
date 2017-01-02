package com.example.keshar.cookbook;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mukul on 12/30/16.
 * This will walk user through creating the recipie
 * notes:
 * 1) user will list ingredients required
 * 2) user will list cookware required
 * 3) user will list time required and serving information (calorie count estimate?)
 * 4) user will type in steps and will be able to attach photos (hopefully inline)
 * 5) user clicks publish
 */


public class CreateRecipe extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

/*
        //code for step 1 here:

        //create array list for ingredients list

        ListView list = (ListView) findViewById(R.id.list_step1);
        Button btn = (Button) findViewById(R.id.btn_step1_add);

        //String input = txt.getText().toString();

        btn.setOnClickListener(new View.OnClickListener()
                               {
                                   public void onClick(View v)
                                   {

                                       ArrayList<String> addArray = new ArrayList<String>();
                                       EditText txt = (EditText) findViewById(R.id.txt_step1_input);

                                       if (1 != 2) {
                                          // addArray.add(txt.getText().toString().trim());
                                          // Log.v("Test", addArray.toString());
                                       }


                                   }
                               }
        );

*/
    }




    //create the array list to store our ingridints
    ArrayList<String> addArray = new ArrayList<String>();

    //method invoked by onlick via the XML
    public void add_too_list(View v) {
        Log.v("test","test");

        ListView list = (ListView) findViewById(R.id.list_step1);
        Button btn = (Button) findViewById(R.id.btn_step1_add);

        EditText txt = (EditText) findViewById(R.id.txt_step1_input);

        //we don't like blank inputs!
        if (!txt.getText().toString().equals("")) {
            //throw a toast message error
            if(addArray.contains(txt.getText().toString()) ){
                Toast.makeText(CreateRecipe.this,
                        "ERROR Already In List!, Try Something Else!", Toast.LENGTH_LONG).show();
                txt.setText("");

            }
            //finally add it to array list
            else{
                addArray.add(txt.getText().toString().trim());

                //clear txt field for next input
                txt.setText("");
            }


            Log.v("Test", addArray.toString());
        }

        //set contents of arraylist into the list view so user can see the list
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addArray));

    }



        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_recipe, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    //the steps (each a fragment)
    // i'm trying not to use third party libraries so this is the best nataive way I could think of to do this

    public static class FragmentStep_1 extends Fragment {

        public FragmentStep_1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step1, container, false);

            return rootView;

            }



    }






    public static class FragmentStep_2 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public FragmentStep_2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step2, container, false);
            return rootView;
        }
    }


    public static class FragmentStep_3 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public FragmentStep_3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step3, container, false);
            return rootView;
        }
    }


    //end fragments



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            //using a switch statement to switch btw our two tabs


            switch (position) {
                case 0:
                    //page 1
                    return new CreateRecipe.FragmentStep_1();
                case 1:
                    //page 2
                    return new CreateRecipe.FragmentStep_2();
                case 2:
                    //page 2
                    return new CreateRecipe.FragmentStep_3();

            }
            //nothing
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Step 1";
                case 1:
                    return "Step 2";
                case 2:
                    return "Step 3";
            }
            return null;
        }
    }
}
