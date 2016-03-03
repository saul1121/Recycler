package com.mobileappscompany.training.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView myRecyclerView;
    private PeopleAdapter mPeopleAdapter;
    private List<Person> people;
    private List<Person> updatedPeople;
    private SwipeRefreshLayout mySwipeRefresh;
    private Parcelable myParcelable;

/*

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState!= null){

            Parcelable par =  savedInstanceState.getParcelable("savedState");
            myRecyclerView.getLayoutManager().onRestoreInstanceState(par);

        }
    }

*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*

        if(savedInstanceState != null){

            Parcelable par =  savedInstanceState.getParcelable("savedState");

            myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


           myRecyclerView.getLayoutManager().onRestoreInstanceState(par);

        }else{

*/
            myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            myRecyclerView.setLayoutManager(layoutManager);


//       }


        mySwipeRefresh = (SwipeRefreshLayout) findViewById(R.id.activity_refresh_layout);
        mySwipeRefresh.setColorSchemeColors(R.color.blue);

        initializaData();
        initializaMoreData();

        List<Person> pep = Person.listAll(Person.class);


        mPeopleAdapter = new PeopleAdapter(pep);
        myRecyclerView.setAdapter(mPeopleAdapter);

        mySwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshAllContent();


            }
        });


        findViewById(R.id.action_settings);

/*


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


*/



    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState != null){

            myParcelable = savedInstanceState.getParcelable("recycle");

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

       // outState.putParcelable("savedState", myRecyclerView.getLayoutManager().onSaveInstanceState());
        myParcelable= myRecyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable("recycle", myParcelable);



    }




    private void refreshAllContent(){


      final  List<Person> pep = Person.listAll(Person.class);




        initializaMoreData();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                mPeopleAdapter = new PeopleAdapter(pep);  //getPeopleUpdated());
                myRecyclerView.setAdapter(mPeopleAdapter);
                mySwipeRefresh.setRefreshing(false);

            }
        }, 2000);



    }



    private List<Person> getPeopleUpdated() {
        List<Person> newUpdates = new ArrayList<Person>();

        // Create an array
        Integer[] array = new Integer[]{0,1,2,3,4,5,6,7};

// Shuffle the elements in the array
        Collections.shuffle(Arrays.asList(array));


        for (int i = 0; i < array.length; i++) {
            //int randomCatNameIndex = new Random().nextInt((7 - 1) + 1)+1;
            newUpdates.add(updatedPeople.get(array[i]));



        }
        return newUpdates;
    }




    public void initializaData(){
        people = new ArrayList<Person>();
        people.add(new Person("Paty", "Feeling cool", R.drawable.anime1, randomDate()));
        people.add(new Person("Jen", "IM hungry", R.drawable.anime2,randomDate()));
        people.add(new Person("Lui", "Going out tonigh", R.drawable.anime3,randomDate()));
        people.add(new Person("Jocelyn", "Miss me?  ", R.drawable.anime4,randomDate()));
        people.add(new Person("Rachel", "Everithin ok", R.drawable.anime5, randomDate()));
        people.add(new Person("Yissel", "add meeeee", R.drawable.anime6,randomDate()));
        people.add(new Person("Monica", "Not so good", R.drawable.anime7,randomDate()));
        people.add(new Person("Yadira", "Im your doll", R.drawable.anime8,randomDate()));


    }


    public void initializaMoreData(){
        updatedPeople = new ArrayList<Person>();
        updatedPeople.add(new Person("Rachel", "Everithin ok", R.drawable.anime5, randomDate()));
        updatedPeople.add(new Person("Yissel", "add meeeee", R.drawable.anime6,randomDate()));
        updatedPeople.add(new Person("Monica", "Not so good", R.drawable.anime7,randomDate()));
        updatedPeople.add(new Person("Yadira", "Im your doll", R.drawable.anime8,randomDate()));
        updatedPeople.add(new Person("Paty", "Feeling cool", R.drawable.anime1,randomDate()));
        updatedPeople.add(new Person("Jen", "IM hungry", R.drawable.anime2,randomDate()));
        updatedPeople.add(new Person("Lui", "Going out tonigh", R.drawable.anime3,randomDate()));
        updatedPeople.add(new Person("Jocelyn", "Miss me?  ", R.drawable.anime4,randomDate()));


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }else if(id==R.id.action_create){

            Intent createIntent = new Intent(this, CreateActivity.class);
            startActivity(createIntent);



        }

        return super.onOptionsItemSelected(item);
    }



    public String randomDate(){

        GregorianCalendar gc = new GregorianCalendar();

        int year = randBetween(2000, 2016);

        gc.set(gc.YEAR, year);

        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

        gc.set(gc.DAY_OF_YEAR, dayOfYear);

        return  (gc.get(gc.MONTH) + 1) + "-"  + gc.get(gc.DAY_OF_MONTH) + "-" +  gc.get(gc.YEAR)  ;


    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }



}
