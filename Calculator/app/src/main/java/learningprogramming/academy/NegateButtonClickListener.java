package learningprogramming.academy;

import android.util.Log;
import android.view.View;
import android.widget.EditText;

final class NegateButtonClickListener implements View.OnClickListener {
    private static final String TAG = "NegateButtonClickListener";
    private final EditText newNumber;

    NegateButtonClickListener(EditText newNumber){

        this.newNumber = newNumber;
    }
    @Override
    public void onClick(View v) {
        try{
            String valueText = newNumber.getText().toString();
            Double value = Double.parseDouble(valueText);
            value = -value;
            newNumber.setText(value.toString());
        }
        catch(NumberFormatException ex){
            Log.d(TAG, ex.toString());
        }
    }
}
