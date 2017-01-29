package firsttimesthecharm.edmontonbustimers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class newBus_Activity extends AppCompatActivity {

    private Button commitBus;
    private EditText userBus;
    private int busNumber;
    private int stopNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        commitBus = (Button) findViewById(R.id.button_commitBus);
        userBus = (EditText) findViewById(R.id.editText_userBus);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        commitBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        userBus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionID == EditorInfo.IME_ACTION_DONE) {
                    busNumber = Integer.valueOf(userBus.getText().toString());
                    return true;
                }
                return false;
            }
        });

    }
}