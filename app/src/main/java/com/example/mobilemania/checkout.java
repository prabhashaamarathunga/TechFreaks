package com.example.mobilemania;
        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.example.mobilemania.Database.DBHelper;
        import java.util.ArrayList;

public class checkout extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        getIntent();
    }






    public void onClickcheckout(View view){
        Intent intent = new Intent(this, payment.class);
        startActivity(intent);
    }

    public void onClickkeepshopping(View view){
        Intent intent = new Intent(this, LoginItemList.class);
        startActivity(intent);
    }
}
