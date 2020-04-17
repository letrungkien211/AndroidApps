package learningprogramming.academy;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

final class OperationButtonClickListener implements View.OnClickListener {
    private static final String TAG = "OperationButtonClickListener";
    private final EditText result;
    private final EditText newNumber;
    private final TextView pendingOp;

    OperationButtonClickListener(EditText result, EditText newNumber, TextView pendingOp) {
        this.result = result;
        this.newNumber = newNumber;
        this.pendingOp = pendingOp;
    }

    @Override
    public void onClick(View v) {
        String pendingOperand = pendingOp.getText().toString();
        pendingOp.setText(((Button) v).getText().toString());

        String value = newNumber.getText().toString();
        try {
            Double op2 = Double.parseDouble(value);
            String op1Text = result.getText().toString();
            Double op1;
            if (op1Text.isEmpty()) {
                op1 = op2;
            } else {
                op1 = Double.parseDouble(op1Text);
                switch (pendingOperand) {
                    case "=":
                        op1 = op2;
                        break;
                    case "+":
                        op1 += op2;
                        break;
                    case "-":
                        op1 -= op2;
                        break;
                    case "*":
                        op1 *= op2;
                        break;
                    case "/":
                        op1 = op2 == 0 ? 0 : op1 / op2;
                        break;
                    default:
                        break;
                }

            }
            result.setText(op1.toString());
        } catch (NumberFormatException e) {
            Log.d(TAG, e.toString());
        }

        newNumber.setText("");
    }
}
