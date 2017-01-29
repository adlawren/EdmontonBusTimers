package firsttimesthecharm.edmontonbustimers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button jsonTest;
    private Button backendTest;
    private Button addBus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jsonTest = (Button)findViewById(R.id.button_jsonTest);
        backendTest = (Button)findViewById(R.id.button_backendTest);
        addBus = (Button)findViewById(R.id.button_addBus);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        jsonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launch_jsonTest();
            }
        });

        backendTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launch_backend();
            }
        });

        addBus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });
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

    private void launch_jsonTest() {
        Intent intent = new Intent(this, jsonTestingActivity.class);
        startActivity(intent);
    }

    private void launch_backend() {
        Intent intent = new Intent(this, backendActivity.class);
        startActivity(intent);
    }
    
}
