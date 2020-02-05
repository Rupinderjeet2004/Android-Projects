package in.thekites.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    String val1,val2,operation,str;
    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.text);
    }
    public void getNumber(View view)
    {
        Button b = (Button) view;
        editText.setText(editText.getText().toString()+""+b.getText().toString());
    }
    public void operation(View view)
    {
        Button v = (Button) view;
        val1 = editText.getText().toString();
        operation = v.getText().toString();
        editText.setText("");
    }
    public void result(View v)
    {
        val2 = editText.getText().toString();
        float var1 = Float.valueOf(val1);
        float var2 = Float.valueOf(val2);
        float res = 0.0f;
        switch(operation)
        {
            case "+":
                res = var1 + var2;
                break;
            case "-":
                res = var1 - var2;
                break;
            case "*":
                res = var1 * var2;
                break;
            case "/":
                res = var1 / var2;
        }
        editText.setText(String.valueOf(res));
    }
    public void clear(View v)
    {
        editText.setText("");
    }

}
