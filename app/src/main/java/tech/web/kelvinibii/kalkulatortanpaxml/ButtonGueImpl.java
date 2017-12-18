package tech.web.kelvinibii.kalkulatortanpaxml;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * Created by KelvinDu on 12/12/2017.
 */

public class ButtonGueImpl implements ButtonGue {
    private Button button[];
    private EditText editText;
    private RelativeLayout.LayoutParams params[] = new RelativeLayout.LayoutParams[18];
    private boolean solved = false;
    private double firstValue;
    private double lastValue;
    private int operation;

    private final int OPERATOR_PLUS = 11;
    private final int OPERATOR_MIN = 22;
    private final int OPERATOR_MULTI = 33;
    private final int OPERATOR_DIV = 44;

    ButtonGueImpl(Button button[], String symbols[], EditText editText, Context context){



        for (int i = 0; i < symbols.length; i++){
            params[i] =
                    new RelativeLayout.LayoutParams(300, 200);
            params[i].setMargins(10,10,10, 10);
            int j = i+1;
            button[i] = new Button(context);
            button[i].setText(symbols[i]);
            button[i].setId(j);
            if (i == 0){
                params[i].addRule(RelativeLayout.BELOW, editText.getId());
                params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else if (i < 3){
                params[i].addRule(RelativeLayout.BELOW, editText.getId());
                params[i].addRule(RelativeLayout.RIGHT_OF, button[i-1].getId());
            } else if (i == 3){
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else if (i < 6) {
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.RIGHT_OF, button[i-1].getId());
            } else if (i == 6){
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else if (i < 9) {
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.RIGHT_OF, button[i-1].getId());
            } else if (i == 9) {
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else if (i < 12) {
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.RIGHT_OF, button[i-1].getId());
            } else if (i == 12) {
                params[i].width = 220;
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else if (i < 16) {
                params[i].width = 220;
                params[i].addRule(RelativeLayout.BELOW, button[i-4].getId());
                params[i].addRule(RelativeLayout.RIGHT_OF, button[i-1].getId());
            } else if (i == 16) {
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            } else {
                params[i].addRule(RelativeLayout.BELOW, button[i-3].getId());
                params[i].addRule(RelativeLayout.RIGHT_OF, button[i-1].getId());
            }
        }

        for (int i = 0; i < button.length; i++){
            if (i < 9) {
                final int j = i+1;
                button[i].setOnClickListener( (v)->{
                    if (solved){
                        editText.setText(String.valueOf(j));
                        solved = false;
                    }
                    else
                        editText.append(String.valueOf(j));
                });
            } else if (i == 9) {
                button[i].setOnClickListener( (v)->{
                    if (solved){
                        editText.setText(String.valueOf(0));
                        solved = false;
                    }
                    else
                        editText.append(String.valueOf(0));
                });
            } else if (i == 10) {
                button[i].setOnClickListener( (v)->{
                    editText.setText("");
                    firstValue = 0;
                    operation = 0;
                });
            } else if (i == 11) {
                button[i].setOnClickListener( (v)->{
                    String currentInput = editText.getText().toString();
                    currentInput = currentInput.substring(0, currentInput.length()-1);
                    editText.setText(currentInput);
                });
            } else if (i < 16) {
                final int o = i;
                button[i].setOnClickListener( (v)->{
                    firstValue = Double.parseDouble(editText.getText().toString());
                    editText.setText("");
                    switch (o){
                        case 12:
                            operation = OPERATOR_PLUS;
                            break;
                        case 13:
                            operation = OPERATOR_MIN;
                            break;
                        case 14:
                            operation = OPERATOR_MULTI;
                            break;
                        case 15:
                            operation = OPERATOR_DIV;
                            break;
                    }
                });
            } else if (i == 16){
                button[i].setOnClickListener( (vieu) ->{
                    firstValue = Double.parseDouble(editText.getText().toString());
                    firstValue = firstValue / 100;
                    editText.setText(String.valueOf(firstValue));
                    solved = true;
                });
            } else if (i == 17) {
                button[i].setOnClickListener( (v)->{
                    double newValue;
                    lastValue = Double.parseDouble(editText.getText().toString());
                    switch (operation){
                        case OPERATOR_PLUS:
                            newValue = firstValue + lastValue;
                            firstValue = newValue;
                            break;
                        case OPERATOR_MIN:
                            newValue = firstValue - lastValue;
                            break;
                        case OPERATOR_MULTI:
                            newValue = firstValue * lastValue;
                            firstValue = newValue;
                            break;
                        case OPERATOR_DIV:
                            newValue = firstValue / lastValue;
                            firstValue = newValue;
                            break;
                        default:
                            newValue = Double.parseDouble(editText.getText().toString());
                            break;
                    }
                    editText.setText(String.valueOf(newValue));
                    solved = true;
                });
            }
        }
        this.button = button;
        this.editText = editText;
    }

    @Override
    public void addButtonToView(RelativeLayout r) {
        Log.i("Total buttons:", String.valueOf(button.length));
        for (int i=0; i<button.length; i++){
            r.addView(button[i], params[i]);
        }
    }
}
