package firsttimesthecharm.edmontonbustimers;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

public class newBus_Activity extends AppCompatActivity {

    private Button commitBus;
    private EditText userBus;
    private EditText userStop;
    private int busNumber = 0;
    private int stopNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bus_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        commitBus = (Button) findViewById(R.id.button_commitBus);
        userBus = (EditText) findViewById(R.id.editText_userBus);
        userStop = (EditText) findViewById(R.id.editText_userStop);

        commitBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(busNumber == 0 || stopNumber == 0) {
                    showToast("Field is empty.");
                } else {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("busNum", busNumber);
                    returnIntent.putExtra("busStop", stopNumber);
                    setResult(RESULT_OK, returnIntent);
                    finish();
                }
            }
        });

        userBus.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionID == EditorInfo.IME_ACTION_NEXT) {
                    busNumber = Integer.valueOf(userBus.getText().toString());
                    return false;
                }
                return false;
            }
        });

        userStop.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                boolean handled = false;
                if (actionID == EditorInfo.IME_ACTION_DONE) {
                    stopNumber = Integer.valueOf(userStop.getText().toString());
                    return false;
                }
                return false;
            }
        });

    }
    public void showToast(String msg) {
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}