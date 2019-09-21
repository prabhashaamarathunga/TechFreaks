package com.example.mobilemania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class payment extends AppCompatActivity{

    EditText Name, MobileNo,Address,Email;
    Button BtnPay;
    String Namevp, MobileNovp, Addressvp, Emailvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);



                Name = (EditText) findViewById(R.id.namein);
                MobileNo = (EditText) findViewById(R.id.mobileno);
                Address = (EditText) findViewById(R.id.addressin);
                Email = (EditText) findViewById(R.id.cemail);


                BtnPay = (Button)findViewById(R.id.pay);

                BtnPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(payment.this, ViewPayment.class);
                        Namevp = Name.getText().toString();
                        MobileNovp = MobileNo.getText().toString();
                        Addressvp = Address.getText().toString();
                        Emailvp = Email.getText().toString();

                        intent.putExtra("vpName",Namevp);
                        intent.putExtra("vpMobileNo",MobileNovp);
                        intent.putExtra("vpAddress",Addressvp);
                        intent.putExtra("vpEmail",Emailvp);
                        startActivity(intent);
                        finish();
                    }
                });

        getIntent();
    }


}
