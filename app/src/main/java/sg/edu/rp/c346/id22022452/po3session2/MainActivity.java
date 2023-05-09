package sg.edu.rp.c346.id22022452.po3session2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText enteramount;
    EditText numofpax;
    EditText discount;
    RadioGroup PaymentMethod;
    RadioButton cash;
    ToggleButton gst;
    ToggleButton svc;
    TextView totalbill;
    TextView eachpay;
    TextView via;
    Button split;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteramount =  findViewById(R.id.amount);
        numofpax = findViewById(R.id.pax);
        discount = findViewById(R.id.discount);
        PaymentMethod = findViewById(R.id.PM);
        svc = findViewById(R.id.svc);
        gst = findViewById(R.id.gst);
        totalbill = findViewById(R.id.totalview);
        eachpay = findViewById(R.id.eachpayview);
        via = findViewById(R.id.via);
        split = findViewById(R.id.split);
        reset = findViewById(R.id.reset);
        cash = findViewById(R.id.pmCash);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amt1= enteramount.getText().toString();
                double amt =Integer.parseInt(amt1);
                String px1= numofpax.getText().toString();
                int px =Integer.parseInt(px1);
                String dc1= discount.getText().toString();
                double dc =Integer.parseInt(dc1);

                double disc = amt -(amt*(dc/100));

                if (svc.isChecked() && (!gst.isChecked()))
                {
                    disc+= (disc*0.1);
                } else if (svc.isChecked() && gst.isChecked())
                {
                    disc+= (disc*0.1) + (disc*0.07);
                } else if (gst.isChecked() && (!svc.isChecked()))
                {
                    disc+= (disc*0.07);
                }

                double calc = disc/px;

                eachpay.setText(String.format("AMOUNT PAYABLE PER PAX            $%.2f",calc));
                totalbill.setText(String.format("TOTAL BILL                                        $%.2f",disc));

                int checkedRadioId = PaymentMethod.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.pmPaynow){

                    cash.setChecked(false);
                    via.setText("VIA PAYNOW @ 81237658");

                }
                else if (checkedRadioId == R.id.pmCash){
                    cash.setChecked(true);
                    via.setText("VIA CASH");

                }

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteramount.setText("");
                numofpax.setText("");
                discount.setText("");
                svc.setChecked(false);
                gst.setChecked(false);
                cash.setChecked(true);

                eachpay.setText("AMOUNT PAYABLE PER PAX");
                totalbill.setText("TOTAL BILL");
                via.setText("VIA");
            }
        });
    }
}
