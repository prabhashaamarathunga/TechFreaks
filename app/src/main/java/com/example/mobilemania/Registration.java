package com.example.mobilemania;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemania.Database.DBHelper;

public class Registration extends AppCompatActivity implements View.OnClickListener{
    Button createAccount;
    TextView fname, lname, uname, pass, cpass, address, cNumber;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper = new DBHelper(this);

        createAccount = (Button)findViewById(R.id.btncreateAcc);

        fname = (TextView)findViewById(R.id.firstName);
        lname = (TextView)findViewById(R.id.lastName);
        uname = (TextView)findViewById(R.id.UserName);
        pass = (TextView)findViewById(R.id.password);
        cpass = (TextView)findViewById(R.id.cpassword);
        address = (TextView)findViewById(R.id.address);
        cNumber  = (TextView)findViewById(R.id.cNumber);

        createAccount.setOnClickListener(this);
        Toast t = Toast.makeText(getApplicationContext(), "User Added! ", Toast.LENGTH_LONG);

        getIntent();
    }
    @Override
    public void onClick(View view) {
        addUser();

    }



    private void addUser(){
        String fname1 = fname.getText().toString();
        String lname1 = lname.getText().toString();
        String uname1 = uname.getText().toString();
        String pass1 = pass.getText().toString();
        String cpass1 = cpass.getText().toString();
        String address1 = address.getText().toString();
        String cNumber1 = cNumber.getText().toString();

        int numberCheck = cNumber1.length();

        if(!fname1.equals("") && !lname1.equals("") && !uname1.equals("") && !pass1.equals("") && !cpass1.equals("") && !address1.equals("") && !cNumber1.equals("")){
            if(!pass1.equals(cpass1)){
                Toast t = Toast.makeText(getApplicationContext(), "Passwords Are Not Matching!", Toast.LENGTH_LONG);
                t.show();
            }
            else {
                if (dbHelper.addUsrDetails(fname.getText().toString(), lname.getText().toString(), uname.getText().toString(), pass.getText().toString(), cpass.getText().toString(), address.getText().toString(), cNumber.getText().toString())) {
                    Toast t = Toast.makeText(getApplicationContext(), "You Have Registered! Please Log In!", Toast.LENGTH_LONG);
                    t.show();
                    Intent intent = new Intent(this, Login.class);
                    startActivity(intent);
                } else {
                    Toast t = Toast.makeText(getApplicationContext(), "Can't Insert The User!", Toast.LENGTH_LONG);
                    t.show();
                }
            }
        }
        else if(numberCheck != 10){
            Toast.makeText(this, "Please Enter A Valid Phone Number!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Empty Fields!", Toast.LENGTH_LONG);
            toast.show();
        }
        //String sqLiteDatabase = DBHelper.DATABASE_NAME;
        //int insertResult = sqLiteDatabase.addUsrDetails(fname.getText().toString(), lname.getText().toString(), uname.getText().toString(), pass.getText().toString(), cpass.getText().toString(), address.getText().toString(), cNumber.getText().toString()));
        //Existing username found
        //if(insertResult == 0)
        //{

            //getEmail.setError(getString(R.string.this_email_is_already_in_our_database_please_provide_another_email_address));
            //return;
        //}


    }


}
