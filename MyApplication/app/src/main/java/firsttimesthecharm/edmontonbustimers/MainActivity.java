package firsttimesthecharm.edmontonbustimers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private userRoutes myRoutes;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView)findViewById(R.id.listView_activebusList);

        myRoutes = new userRoutes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launch_addIntent();
            }
        });

<<<<<<< HEAD
        newBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launch_addIntent();
            }
        });
=======
        final ArrayList<Result> my_results = new ArrayList<Result>();

        final DataEdmontonRequestHandler DERH_handler = new DataEdmontonRequestHandler();
        // final CustomAdapter adapter = new CustomAdapter(this, new ArrayList<Result>());
        final CustomAdapter adapter = new CustomAdapter(this, my_results);
        lv.setAdapter(adapter);
>>>>>>> 691b52336cde1df2cd2a791cb461d7dab8d46303

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressWarnings("unchecked")
                    public void run() {
                        try {
                            DERH_handler.GetDataModels(new IObserver<ArrayList<Result>>() {
                                @Override
                                public void callback(ArrayList<Result> list) {
                                    System.out.println("[Callback]: List size: " + list.size());
                                    my_results.clear();
                                    for(Result r : findNearestResult(list)) {
                                        my_results.add(r);
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            }, myRoutes.get_routes());
                        }
                        catch (Exception e) {
                            //TODO: catch block
                        }
                    }
                });
            }
        };
<<<<<<< HEAD
        //timer.schedule(doAsynchronousTask, 0, 5000);
=======
//        timer.schedule(doAsynchronousTask, 0, 5000*20);
        timer.schedule(doAsynchronousTask, 0, 5000);
>>>>>>> 691b52336cde1df2cd2a791cb461d7dab8d46303
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void showToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
 private void launch_addIntent(){
     Intent intent = new Intent(this, newBus_Activity.class);
     startActivityForResult(intent, 1);
 }

<<<<<<< HEAD
=======
    private void launch_addIntent() {
        Intent intent = new Intent(this, newBus_Activity.class);
        startActivityForResult(intent, 1);
    }

>>>>>>> 691b52336cde1df2cd2a791cb461d7dab8d46303
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1 && data != null) {
            int busStop = data.getIntExtra("busStop", 0);
            int busNum = data.getIntExtra("busNum", 0);
<<<<<<< HEAD
            showToast(String.valueOf(busStop) + " " + String.valueOf(busNum));
        }
    }
=======
            myRoutes.addRoute(new Route(busStop, busNum));
        }
    }

    private ArrayList<Result> findNearestResult(ArrayList<Result> results) {
        ArrayList<Result> finished = new ArrayList<Result>();

        for(Route ur : myRoutes.get_routes()) {

            ArrayList<Result> temp = new ArrayList<Result>();

            for(Result r : results) {

//                System.out.println(ur.get_busNum() + " " + ur.get_busStop() + " " + r.get_BusStop() + " " + r.get_BusNum());
                if(ur.get_busStop().equals(r.get_BusStop()) &&
                        ur.get_busNum().equals(r.get_BusNum())) {
                    temp.add(r);
                }
            }

            Collections.sort(temp, new Comparator<Result>() {
                @Override
                public int compare(Result result, Result t1) {
                    if(result.get_time().before(t1.get_time())) {
                        return -1;
                    } else if (result.get_time().equals(t1.get_time())) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });

            if (temp.size() > 0) {
                Date currentTime = new Date(System.currentTimeMillis());
                System.err.println("[EBT Tag]: ASDF");

                for (Result result : temp) {
//                    System.err.println(result.get_time());

                    Date nextDate = result.get_time();
                    nextDate.setYear(currentTime.getYear());
                    nextDate.setDate(currentTime.getDate());
                    nextDate.setMonth(currentTime.getMonth());

                    if (nextDate.after(currentTime)) {
                        finished.add(result);
                        break;
                    }
                }

                System.err.println("[EBT Tag]: No time found!!!!!");
            } else {
                System.err.println("[EBT Tag]: Result list size is zero");
            }
        }

        return finished;
    }

>>>>>>> 691b52336cde1df2cd2a791cb461d7dab8d46303
}

