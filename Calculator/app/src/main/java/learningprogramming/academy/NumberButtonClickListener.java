package learningprogramming.academy;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

final class NumberButtonClickListener implements View.OnClickListener {

    private final EditText newNumber;

    NumberButtonClickListener(EditText newNumber){
        this.newNumber = newNumber;
    }

    @Override
    public void onClick(View v) {
        newNumber.append(((Button) v).getText().toString());
    }
}
