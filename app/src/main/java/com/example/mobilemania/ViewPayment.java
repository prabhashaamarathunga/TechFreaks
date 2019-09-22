package com.example.mobilemania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobilemania.Database.DBHelper;

public class ViewPayment extends AppCompatActivity {

        DBHelper dbHelper;
        TextView Name,Address,Phone,Email,Item;
        String Namevp, MobileNovp, Addressvp, Emailvp;
        Button btnAddDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_payment);

        dbHelper = new DBHelper(this);

        Name = findViewById(R.id.vname);
        Address = findViewById(R.id.vaddress);
        Phone = findViewById(R.id.vphone);
        Email = findViewById(R.id.vemail);
        Item = findViewById(R.id.vitems);

        btnAddDetails = (Button) findViewById(R.id.cconfirm);

        Namevp = getIntent().getExtras().getString("vpName");
        MobileNovp = getIntent().getExtras().getString("vpMobileNo");
        Addressvp = getIntent().getExtras().getString("vpAddress");
        Emailvp = getIntent().getExtras().getString("vpEmail");

        Name.setText(Namevp);
        Address.setText(Addressvp);
        Phone.setText(MobileNovp);
        Email.setText(Emailvp);

        addData();
    }


    public void addData(){
        btnAddDetails.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                          boolean isInserted =   dbHelper.addOrders(
                                    Name.getText().toString(),
                                    Phone.getText().toString(),
                                    Address.getText().toString(),
                                    Email.getText().toString(),
                                    Item.getText().toString() );
                          if(isInserted = true)
                              Toast.makeText(ViewPayment.this,"Inserting Succesful",Toast.LENGTH_LONG).show();
                          else
                              Toast.makeText(ViewPayment.this,"Inserting Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void onClickAdd(View v) {

        switch (v.getId()){
            case R.id.cconfirm: addData();
                break;
        }
    }

    public void onClickManageOrders(View view){
        Intent intent = new Intent(this, yourOrders.class);
        startActivity(intent);
    }

    public void onClickkeepshopping(View view){
        Intent intent = new Intent(this, LoginItemList.class);
        startActivity(intent);
    }
}
