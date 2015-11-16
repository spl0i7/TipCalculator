package me.ketansingh.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.NumberFormat;
public class MainActivity extends AppCompatActivity {
    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat =
            NumberFormat.getPercentInstance();
    private double billAmount=0.0;
    private double customPercent=0.18;
    private TextView amountDisplatTextView;
    private TextView percentCustomTextView;
    private TextView tip15TextView;
    private TextView total15TextView;
    private TextView tipCustomTextView;
    private TextView totalCustomTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountDisplatTextView = (TextView) findViewById(R.id.amountDisplayTextView);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);
        amountDisplatTextView.setText(currencyFormat.format(billAmount));
        updateStandard();
        updateCustom();
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);
        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setProgress(18);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarListener);
    }
    void updateStandard(){
        double fifteenPercenTip=0.15*billAmount;
        double fifteenPercentTotal=billAmount+fifteenPercenTip;
        tip15TextView.setText(currencyFormat.format(fifteenPercenTip));
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));

    }
    void updateCustom(){
        double customTip=customPercent*billAmount;
        double customTotal=billAmount+customTip;
        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));
        String outputCustomPercent=(int)(customPercent*100)+" %";
        percentCustomTextView.setText(outputCustomPercent);


    }
    private SeekBar.OnSeekBarChangeListener customSeekBarListener =
            new SeekBar.OnSeekBarChangeListener()
            {
                // update customPercent, then call updateCustom
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser)
                {
                    // sets customPercent to position of the SeekBar's thumb
                    customPercent = progress / 100.0;
                    updateCustom(); // update the custom tip TextViews
                } // end method onProgressChanged

                @Override
                public void onStartTrackingTouch(SeekBar seekBar)
                {
                } // end method onStartTrackingTouch

                @Override
                public void onStopTrackingTouch(SeekBar seekBar)
                {
                } // end method onStopTrackingTouch
            }; // end OnSeekBarChangeListener
    private TextWatcher amountEditTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        try {
            billAmount=Double.parseDouble(s.toString());
            billAmount=billAmount/100;
        }
        catch (NumberFormatException e){
            billAmount=0;
        }
            amountDisplatTextView.setText(currencyFormat.format(billAmount));
            updateCustom();
            updateStandard();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
