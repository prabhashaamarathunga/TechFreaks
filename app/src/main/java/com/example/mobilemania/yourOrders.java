package com.example.mobilemania;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobilemania.Database.DBHelper;
import com.google.android.material.textfield.TextInputLayout;

public class yourOrders extends AppCompatActivity {

    DBHelper dbHelper;
    Button btnviewOrders;
    Button btnupdateOrders;
    Button btndeleteOrders;
    private TextInputLayout editname;
    private TextInputLayout editphone;
    private TextInputLayout editaddress;
    private TextInputLayout editid;
    private TextInputLayout editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        dbHelper = new DBHelper(this);

        editid = findViewById(R.id.id);
        editname = findViewById(R.id.name);
        editphone = findViewById(R.id.phone);
        editaddress = findViewById(R.id.address);
        editemail = findViewById(R.id.email);

        btnviewOrders = (Button)findViewById(R.id.view_orders);
        btnupdateOrders = (Button)findViewById(R.id.update_orders);
        btndeleteOrders = (Button)findViewById(R.id.delete_orders);
        viewOrder();
        updateOrder();
        deleteOrder();

    }


    public void viewOrder(){
        btnviewOrders.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Cursor res = dbHelper.viewOrders();
                        if(res.getCount() == 0){
                            showMessage("Error","No Data Found!");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID :"+res.getString(0)+"\n");
                            buffer.append("Name :"+res.getString(1)+"\n");
                            buffer.append("Phone :"+res.getString(2)+"\n");
                            buffer.append("Address :"+res.getString(3)+"\n");
                            buffer.append("Email :"+res.getString(4)+"\n");
                            buffer.append("Item :"+res.getString(5)+"\n\n");
                        }
                        //Show All Orders
                        showMessage("Your Orders",buffer.toString());
                    }
                }
        );
    }

    public void updateOrder(){
        btnupdateOrders.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!validateName() | !validatePhone() | !validateEmail() | !validateAddress()){
                            return;}
                        boolean isUpdate = dbHelper.updateOrders(editid.getEditText().getText().toString(),
                                editname.getEditText().getText().toString(),
                                editphone.getEditText().getText().toString(),
                                editaddress.getEditText().getText().toString(),
                                editemail.getEditText().getText().toString());

                        if(isUpdate == true)
                            Toast.makeText(yourOrders.this,"Order Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(yourOrders.this,"Operation Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteOrder(){
        btndeleteOrders.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = dbHelper.deleteOrders(editid.getEditText().getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(yourOrders.this,"Order Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(yourOrders.this,"Operation Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }



    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    private boolean validateName() {

        String NameInput = editname.getEditText().getText().toString().trim();
        String NamePattern = "^[a-zA-Z\\s]*$";

        if(NameInput.isEmpty()){
            editname.setError("Field can't be empty");
            return false;
        }
        else if(!NameInput.matches(NamePattern)){
            editname.setError("Incorrect Name");
            return false;
        }
        else {
            editname.setError(null);
            return true;
        }
    }

    private boolean validateAddress(){
        String addressInput = editaddress.getEditText().getText().toString().trim();

        if(addressInput.isEmpty()){
            editaddress.setError("Field can't be empty");
            return false;
        }
        else {
            editaddress.setError(null);
            return true;
        }
    }

    private boolean validateEmail(){
        String emailInput = editemail.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

        if(emailInput.isEmpty()){
            editemail.setError("Field can't be empty");
            return false;
        }
        else if(!emailInput.matches(emailPattern)){
            editemail.setError("Incorrect Email");
            return false;
        }
        else {
            editemail.setError(null);
            return true;
        }
    }

    private boolean validatePhone(){
        String phoneInput = editphone.getEditText().getText().toString().trim();

        if(phoneInput.isEmpty()){
            editphone.setError("Field can't be empty");
            return false;
        }
        else if(phoneInput.length() < 10){
            editphone.setError("Incorrect Mobile Number");
            return false;
        }
        else {
            editphone.setError(null);
            return true;
        }
    }
    public void onClickView(View v) {

        switch (v.getId()){
            case R.id.view_orders: viewOrder();
                break;
        }
    }
    public void onClickUpdate(View v) {

        switch (v.getId()){
            case R.id.update_orders: updateOrder();
                break;
        }
    }

    public void onClickDelete(View v) {

        switch (v.getId()){
            case R.id.delete_orders: deleteOrder();
                break;
        }
    }

    public void onClickHome(View view){
        Intent intent = new Intent(this, UserMain.class);
        startActivity(intent);
    }

}
