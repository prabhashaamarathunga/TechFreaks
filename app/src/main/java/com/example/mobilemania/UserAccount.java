package com.example.mobilemania;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mobilemania.Database.DBHelper;

public class UserAccount extends AppCompatActivity {
    DBHelper dbHelper;
    EditText fnamedis, lnamedis, unamedis, addressdis, contactNumberdis;
    Button btnUpdate, btnDelete;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        dbHelper = new DBHelper(this);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);


        fnamedis = findViewById(R.id.fnamedis);
        lnamedis = findViewById(R.id.lnamedis);
        unamedis = findViewById(R.id.unamedis);
        addressdis = findViewById(R.id.password);
        contactNumberdis = findViewById(R.id.address);

        sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);
        String uName = sharedPreferences.getString("username", null);

        Cursor result = dbHelper.getUserData(uName);
        while (result.moveToNext())
            {
                fnamedis.setText(result.getString(1));
                lnamedis.setText(result.getString(2));
                unamedis.setText(result.getString(3));
                addressdis.setText(result.getString(6));
                contactNumberdis.setText(result.getString(7));
            }
    }

    @Override
    protected void onStart() {

        sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);
        String uName = sharedPreferences.getString("username", null);

        Cursor result = dbHelper.getUserData(uName);
        while (result.moveToNext())
        {
            fnamedis.setText(result.getString(1));
            lnamedis.setText(result.getString(2));
            unamedis.setText(result.getString(3));
            addressdis.setText(result.getString(6));
            contactNumberdis.setText(result.getString(7));
        }

        super.onStart();
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(UserAccount.this, UserMain.class);
        startActivity(intent);
    }

    public void UpdateDetails(View view)
    {
        fnamedis = findViewById(R.id.fnamedis);
        lnamedis = findViewById(R.id.lnamedis);
        unamedis = findViewById(R.id.unamedis);
        addressdis = findViewById(R.id.password);
        contactNumberdis = findViewById(R.id.address);

        String fname = fnamedis.getText().toString();
        String lname = lnamedis.getText().toString();
        String uname = unamedis.getText().toString();
        String address = addressdis.getText().toString();
        String cnum = contactNumberdis.getText().toString();

        boolean result = dbHelper.updateInfo(fname, lname, uname, address, cnum);

        if (result)
        {
            Toast.makeText(this, "Data Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, "Data Cannot Be Updated! Please Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAccount(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Delete Account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean result = dbHelper.deleteInfo(unamedis.getText().toString());
                if (result)
                {
                    Intent intent = new Intent(UserAccount.this, Login.class);
                    startActivity(intent);
                    Toast.makeText(UserAccount.this, "Account Deleted Successfully!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(UserAccount.this, "Account Can't Be Deleted!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }

    public void logout(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sharedPreferences = getSharedPreferences("MobileMania", MODE_PRIVATE);
                sharedPreferences.edit().remove("logged").apply();
                sharedPreferences.edit().remove("username").apply();
                Intent intent = new Intent(UserAccount.this, Login.class);
                startActivity(intent);
                Toast.makeText(UserAccount.this, "Logged out!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}