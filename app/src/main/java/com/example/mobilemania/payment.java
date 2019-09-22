package com.example.mobilemania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class payment extends AppCompatActivity{

    private TextInputLayout Name;
    private TextInputLayout MobileNo;
    private TextInputLayout Email;
    private TextInputLayout CardNo;
    private TextInputLayout Address;
    Button BtnPay, BtnClear;
    String Namevp, MobileNovp, Addressvp, Emailvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

                Name = findViewById(R.id.namein);
                MobileNo = findViewById(R.id.mobileno);
                Email = findViewById(R.id.cemail);
                Address = findViewById(R.id.addressin);
                CardNo = findViewById(R.id.cardno);

                BtnPay = (Button)findViewById(R.id.pay);
                BtnClear = (Button) findViewById(R.id.clear);

               BtnPay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       //if (!validateName() | !validatePhone() | !validateEmail() | !validateCard() | !validateAddress()){
                          // return;}
                        Intent intent = new Intent(payment.this, ViewPayment.class);
                        Namevp = Name.getEditText().getText().toString();
                        MobileNovp = MobileNo.getEditText().getText().toString();
                        Addressvp = Address.getEditText().getText().toString();
                        Emailvp = Email.getEditText().getText().toString();

                        intent.putExtra("vpName",Namevp);
                        intent.putExtra("vpMobileNo",MobileNovp);
                        intent.putExtra("vpAddress",Addressvp);
                        intent.putExtra("vpEmail",Emailvp);
                        startActivity(intent);
                        finish();
                    }
                });

                BtnClear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Name.getEditText().getText().clear();
                        MobileNo.getEditText().getText().clear();
                        Address.getEditText().getText().clear();
                        Email.getEditText().getText().clear();
                        CardNo.getEditText().getText().clear();

            }
        });
        getIntent();
    }

    private boolean validateName() {

        String NameInput = Name.getEditText().getText().toString().trim();
        String NamePattern = "^[a-zA-Z\\s]*$";

        if(NameInput.isEmpty()){
           Name.setError("Field can't be empty");
            return false;
        }
        else if(!NameInput.matches(NamePattern)){
            Name.setError("Incorrect Name");
            return false;
        }
        else {
            Name.setError(null);
            return true;
        }
    }

    private boolean validateAddress(){
        String addressInput = Address.getEditText().getText().toString().trim();

        if(addressInput.isEmpty()){
            Address.setError("Field can't be empty");
            return false;
        }
        else {
            Address.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = Email.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

        if(emailInput.isEmpty()){
            Email.setError("Field can't be empty");
            return false;
        }
        else if(!emailInput.matches(emailPattern)){
            Email.setError("Incorrect Email");
            return false;
        }
        else {
            Email.setError(null);
            return true;
        }
    }

    private boolean validatePhone(){
        String phoneInput = MobileNo.getEditText().getText().toString().trim();

        if(phoneInput.isEmpty()){
            MobileNo.setError("Field can't be empty");
            return false;
        }
        else if(phoneInput.length() < 10){
            MobileNo.setError("Incorrect Mobile Number");
            return false;
        }
        else {
            MobileNo.setError(null);
            return true;
        }
    }

    private boolean validateCard(){
        String cardInput = CardNo.getEditText().getText().toString().trim();

        if(cardInput.isEmpty()){
            CardNo.setError("Field can't be empty");
            return false;
        }
        else if(cardInput.length() < 16){
            CardNo.setError("Invalid Credit Card");
            return false;
        }
        else {
            CardNo.setError(null);
            return true;
        }
    }


}
