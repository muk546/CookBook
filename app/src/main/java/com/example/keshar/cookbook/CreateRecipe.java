package com.example.keshar.cookbook;
import android.app.Dialog;
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

import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.valueOf;

/**
 * Created by mukul on 12/30/16.
 * This will walk user through creating the recipe
 * notes:
 * 1) user will list ingredients required
 * 2) user will list cookware required
 * 3) user will list time required and serving information (calorie count estimate?)
 * 4) user will type in steps and will be able to attach photos (hopefully inline)
 * 5) user clicks publish
 */


public class CreateRecipe extends AppCompatActivity implements NumberPicker.OnValueChangeListener{
    private static TextView tv_time_step3;
    private static EditText txt_serve_step3;
    static Dialog d ;
    int input_hours;
    int input_mins;
    int input_serve;


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
        //keep those tabs alive!
        mViewPager.setOffscreenPageLimit(10);

        tv_time_step3 = (TextView) findViewById(R.id.tv_time_step3);



    }
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.v("value is",""+newVal);

    }

    //had to define onclick in XML because reasons...
    //step 3 part 1 begin
    public void show(View view)
    {

        final Dialog d = new Dialog(CreateRecipe.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog);
        Button btn_done1 = (Button) d.findViewById(R.id.btn_done_main);
        NumberPicker np_hours = (NumberPicker) d.findViewById(R.id.np_hours);
        NumberPicker np_mins = (NumberPicker) d.findViewById(R.id.np_mins);

        np_hours.setMaxValue(24);
        np_hours.setMinValue(0);
        np_hours.setWrapSelectorWheel(false);
        np_hours.setOnValueChangedListener(this);

        np_mins.setMaxValue(59);
        np_mins.setMinValue(0);
        np_mins.setWrapSelectorWheel(false);
        np_mins.setOnValueChangedListener(this);

        String output_time = "";


        np_hours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                input_hours = newVal;
            }
        });

        np_mins.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                input_mins = newVal;
            }
        });

        btn_done1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                //display our time so user can double check
                TextView tv_time_step3 = (TextView) findViewById(R.id.tv_time_step3);
                tv_time_step3.setText(input_hours + " Hour(s) and " + input_mins + " Minute(s) ");

                //display our time so user can double check

                //removed (for now)


                d.dismiss();
            }
        });
        d.show();


    }
    //step 3 part 1 end



    //step 3 part e begin
    //had to define onclick in XML because reasons...
    public void show2(View view)
    {

        final Dialog d = new Dialog(CreateRecipe.this);
        d.setTitle("NumberPicker");
        d.setContentView(R.layout.dialog2);
        Button btn_done2 = (Button) d.findViewById(R.id.btn_done2);
        NumberPicker np_serve = (NumberPicker) d.findViewById(R.id.np_serve);

        np_serve.setMaxValue(100);
        np_serve.setMinValue(0);
        np_serve.setWrapSelectorWheel(false);

        np_serve.setOnValueChangedListener(this);



        np_serve.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){

                input_serve = newVal;

            }
        });


        btn_done2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                //display our num so user can double check
                //tv_serve_step3
                TextView tv_serve_step3 = (TextView) findViewById(R.id.tv_serve_step3);
                tv_serve_step3.setText("" + input_serve);


                d.dismiss();
            }
        });
        d.show();


    }
    //step 3 part 2 end

    //lets next an previous buttons work (eventually want to switch to img view arrows

    public void next_btn(View v) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);

    }

    public void prev_btn(View v) {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1, true);
    }

    /*
    Here lies the code for the steps of the user input. Getting this data and trying to catch errors
    is important during input, I will use toast to alert users of any input mistakes (such as
    duplicates).
     */


//step 1 code

    //create the array list to store our ingridints
    ArrayList<String> addArray = new ArrayList<String>();

    //method invoked by onlick via the XML
    public void add_too_list(View v) {
        Log.v("test", "test");

        ListView list = (ListView) findViewById(R.id.list_step1);
        Button btn = (Button) findViewById(R.id.btn_step1_add);
        EditText txt = (EditText) findViewById(R.id.txt_step1_input);

        //we don't like blank inputs!
        if (!txt.getText().toString().equals("")) {
            //throw a toast message error
            if (addArray.contains(txt.getText().toString())) {
                Toast.makeText(CreateRecipe.this,
                        "ERROR Already In List!, Try Something Else!", Toast.LENGTH_LONG).show();
                txt.setText("");

            }
            //finally add it to array list
            else {
                addArray.add(txt.getText().toString().trim());

                //clear txt field for next input
                txt.setText("");
            }


            Log.v("Step 1", addArray.toString());
        }

        //set contents of arraylist into the list view so user can see the list
        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, addArray));

    }
//step 1 code end

    //step 2 code (copy paste from step 1)

    //create the array list to store our cookware
    ArrayList<String> cookArray = new ArrayList<String>();

    //method invoked by onlick via the XML
    public void cook_too_list(View v) {

        //rename vars
        ListView list2 = (ListView) findViewById(R.id.list_step2);
        Button btn2 = (Button) findViewById(R.id.btn_step2_add);
        EditText txt2 = (EditText) findViewById(R.id.txt_step2_input);

        // we use the same checks as step 1

        //we don't like blank inputs!
        if (!txt2.getText().toString().equals("")) {
            //throw a toast message error
            if (cookArray.contains(txt2.getText().toString())) {
                Toast.makeText(CreateRecipe.this,
                        "ERROR Already In List!, Try Something Else!", Toast.LENGTH_LONG).show();
                txt2.setText("");

            }
            //finally add it to array list
            else {
                cookArray.add(txt2.getText().toString().trim());

                //clear txt field for next input
                txt2.setText("");
            }


            Log.v("Step 2", cookArray.toString());
        }

        //set contents of arraylist into the list view so user can see the list
        list2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cookArray));

    }
    //step 2 code end

    //step 4 code begin
    //very simliar to step 1 and 2 but with a new UI

    ArrayList<String> FinalArray = new ArrayList<String>();
    //our step var
    int increment = 0;
    String Pure_input;
    //method invoked by onlick via the XML
    public void final_list(View v) {



        //rename vars

        //btn_input_step4
        ListView list_step4 = (ListView) findViewById(R.id.list_step4);
        Button btn_input_step4 = (Button) findViewById(R.id.btn_input_step4);
        EditText txt_input_step4 = (EditText) findViewById(R.id.txt_input_step4);

        // we use the same checks as step 1 & 2

        //we don't like blank inputs!
        if (!txt_input_step4.getText().toString().equals("")) {
            //throw a toast message error
            if (FinalArray.contains(txt_input_step4.getText().toString())) {
                Toast.makeText(CreateRecipe.this,
                        "ERROR Already In List!, Try Something Else!", Toast.LENGTH_LONG).show();
                txt_input_step4.setText("");

            }
            //finally add it to array list
            else {
                //captures the vanilla input without the step string (for later use)
                Pure_input = txt_input_step4.getText().toString().trim();
                //raise the step counter by 1 each time
                increment++;
                FinalArray.add("Step "+ increment + " : " +txt_input_step4.getText().toString().trim());

                //clear txt field for next input
                txt_input_step4.setText("");
            }


            Log.v("Step 4", FinalArray.toString());
        }

        //set contents of arraylist into the list view so user can see the list
        list_step4.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, FinalArray));

    }
    //end step 4

    /*
    This will be the code for step 5 which is the most interesting step
    Since android API 19 we can generate PDF's nativily
    So we will use this to put all our data togeather and gernerate a shareable recipie document
    This document will be viewable on ALL PLATFORMS which is the advantage of PDF.

   In the background there will be a database that will store the raw user input allowing user's to
   go back and edit thier old recipies and this is what will be visible in the My Recipie tab from the main activity

   Step 5 will have a preview of their Recpie (PDF) and the user will be able to save it (so it will go into the database under my recpies) and the PDF copy will be saved as well.
   The user will be able to eventually share the PDF through various services starting with the easist (email)

     */


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

        return false;
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

            public FragmentStep_3() {
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {

                //hide keyboard (not needed in this frag)
                getActivity().getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                View rootView = inflater.inflate(R.layout.fragment_step3, container, false);
                return rootView;


            }
        }





    public static class FragmentStep_4 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public FragmentStep_4() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step4, container, false);
            return rootView;
        }
    }

    public static class FragmentStep_5 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public FragmentStep_5() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step5, container, false);
            return rootView;
        }
    }

    public static class FragmentStep_6 extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public FragmentStep_6() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_step6, container, false);
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
            //using a switch statement to switch btw our  tabs


            switch (position) {
                case 0:
                    //page 1
                    return new CreateRecipe.FragmentStep_1();
                case 1:
                    //page 2
                    return new CreateRecipe.FragmentStep_2();
                case 2:
                    //page 3
                    return new CreateRecipe.FragmentStep_3();
                case 3:
                    //page 4
                    return new CreateRecipe.FragmentStep_4();
                case 4:
                    //page 5
                    return new CreateRecipe.FragmentStep_5();
                case 5:
                    //page 6
                    return new CreateRecipe.FragmentStep_6();



            }


            //nothing
            return null;
        }


        @Override
        public int getCount() {
            // Show 6 total pages.
            return 6;
        }

        //displaying information here is redundant so it's blank to allow for the bar to do the work
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
                case 2:
                    return "";
                case 3:
                    return "";
                case 4:
                    return "";
                case 5:
                    return "";
            }
            return null;
        }


    }
}


