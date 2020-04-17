package learningprogramming.academy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String PENDING_OP_KEY = "PENDING_OP_KEY";
    private TextView pendingOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText result = findViewById(R.id.result);
        EditText newNumber = findViewById(R.id.newNumber);
        Button negateButton = findViewById(R.id.buttonNeg);
        pendingOperation = findViewById(R.id.operation);
        pendingOperation.setText("=");

        View.OnClickListener numberClickListener = new NumberButtonClickListener(newNumber);
        View.OnClickListener operationClickListener = new OperationButtonClickListener(result, newNumber, pendingOperation);
        View.OnClickListener negateClickListener = new NegateButtonClickListener(newNumber);
        for (int i = 0; i <= 9; i++) {
            int viewId = getResources().getIdentifier("button" + i, "id", getPackageName());
            findViewById(viewId).setOnClickListener(numberClickListener);
        }

        for (String opName : Arrays.asList("Dot", "Equal", "Divide", "Multiply", "Minus", "Plus")) {
            int viewId = getResources().getIdentifier("button" + opName, "id", getPackageName());
            findViewById(viewId).setOnClickListener(operationClickListener);
        }

        negateButton.setOnClickListener(negateClickListener);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation.setText(savedInstanceState.getString(PENDING_OP_KEY));
        Log.i(TAG, "Restore: " + pendingOperation.getText().toString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG, "Save: " + pendingOperation.getText().toString());
        outState.putString(PENDING_OP_KEY, pendingOperation.getText().toString());
        Log.i(TAG, "Save: " + pendingOperation.getText().toString());
        super.onSaveInstanceState(outState);
    }
}