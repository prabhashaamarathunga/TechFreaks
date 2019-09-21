package com.example.mobilemania;


        import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;
        import com.example.mobilemania.Database.DBHelper;
        import java.util.ArrayList;

public class checkout extends AppCompatActivity{

    DBHelper dbHelper;
    ListView cartView;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        dbHelper = new DBHelper(this);
        cartView = findViewById(R.id.cart_view);
        listItem = new ArrayList<>();

        viewCheck();

        cartView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = cartView.getItemAtPosition(position).toString();
                Toast.makeText(checkout.this,""+text,Toast.LENGTH_SHORT).show();
            }
        });
        getIntent();
    }

    private void viewCheck() {
        Cursor cursor = dbHelper.viewCheckout();

        if(cursor.getCount() == 0)
            Toast.makeText(this,"No Items in Cart",Toast.LENGTH_LONG).show();
        else
            while(cursor.moveToNext()){
                listItem.add(cursor.getString(1));

            }
            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, listItem);
            cartView.setAdapter(adapter);
    }



    public void onClickcheckout(View view){
        Intent intent = new Intent(this, payment.class);
        startActivity(intent);
    }
}
