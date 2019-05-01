package com.wentsingnee.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    public native void setExpcalretToTextView(String expression, Object target);

    @Override
    public void onClick(View v) {
        Log.e("abcd", "abcdef");
        final int[] digitButtonIDs = {
                R.id.button0,
                R.id.button1,
                R.id.button2,
                R.id.button3,
                R.id.button4,
                R.id.button5,
                R.id.button6,
                R.id.button7,
                R.id.button8,
                R.id.button9,
        };

        TextView expressionInputBox = findViewById(R.id.expressionInputBox);

        int btnID = v.getId();
        switch (btnID) {
            case R.id.buttonClear: {
                expressionInputBox.setText("");
                TextView tv = findViewById(R.id.sample_text);
                tv.setText("");
                break;
            }
            case R.id.buttonBackspce: {
                String preExpression = expressionInputBox.getText().toString();
                if (preExpression.length() != 0) {
                    preExpression = preExpression.substring(0, preExpression.length() - 1);
                }
                expressionInputBox.setText(preExpression);
                break;
            }
            case R.id.buttonLeftparentheses: {
                expressionInputBox.append("(");
                break;
            }
            case R.id.buttonRightparentheses: {
                expressionInputBox.append(")");
                break;
            }
            case R.id.buttonCal: {
                // Example of a call to a native method
                TextView tv = findViewById(R.id.sample_text);
                setExpcalretToTextView(expressionInputBox.getText().toString(), tv);
                break;
            }
            case R.id.buttonAdd: {
                expressionInputBox.append("+");
                break;
            }
            case R.id.buttonMin: {
                expressionInputBox.append("-");
                break;
            }
            case R.id.buttonMul: {
                expressionInputBox.append("*");
                break;
            }
            case R.id.buttonDiv: {
                expressionInputBox.append("/");
                break;
            }
            case R.id.buttonMod: {
                expressionInputBox.append("%");
                break;
            }
            case R.id.buttonPow: {
                expressionInputBox.append("^");
                break;
            }
            default: {
                for (int i = 0; i < digitButtonIDs.length; ++i) {
                    if (btnID == digitButtonIDs[i]) {
                        expressionInputBox.append(String.valueOf(i));
                        break;
                    }
                }
                break;
            }
        }
    }
}
