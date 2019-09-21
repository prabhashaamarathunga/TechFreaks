package com.example.mobilemania;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilemania.Database.DBHelper;

public class yourOrders extends AppCompatActivity {

    DBHelper dbHelper;
    Button btnviewOrders;
    Button btnupdateOrders;
    Button btndeleteOrders;
    EditText editname, editphone, editaddress, editid, editemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_orders);

        dbHelper = new DBHelper(this);

        editid = (EditText) findViewById(R.id.id);
        editname = (EditText) findViewById(R.id.name);
        editphone = (EditText) findViewById(R.id.phone);
        editaddress = (EditText) findViewById(R.id.address);
        editemail = (EditText) findViewById(R.id.email);

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
                        boolean isUpdate = dbHelper.updateOrders(editid.getText().toString(),
                                editname.getText().toString(),
                                editphone.getText().toString(),
                                editaddress.getText().toString(),
                                editemail.getText().toString());

                        if(isUpdate == true)
                            Toast.makeText(yourOrders.this,"Updated Succesful",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(yourOrders.this,"Update Failed",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteOrder(){
        btndeleteOrders.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = dbHelper.deleteOrders(editid.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(yourOrders.this,"Deleted Succesful",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(yourOrders.this,"Deleting Failed",Toast.LENGTH_LONG).show();
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

}
