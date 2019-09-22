package com.example.mobilemania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListCreditorAdaptor extends ArrayAdapter {

    List list = new ArrayList();

    public ListCreditorAdaptor(@NonNull Context context, int resource) {
        super(context, resource);
    }


    static class LayoutHandler {
        TextView CNAME, CPHONE, CAMOUNT, CDATE;
    }


    @Override
    public void add(@Nullable Object object) {
        super.add(object);

        list.add(object);
   }

    @Override
    public int getCount() {
        return list.size();
    }


    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;

        if(row == null){

            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.crow_layout,parent,false);
            layoutHandler = new LayoutHandler();

            layoutHandler.CNAME     = row.findViewById(R.id.txt_cname);
            layoutHandler.CPHONE    = row.findViewById(R.id.txt_cphone);
            layoutHandler.CAMOUNT   = row.findViewById(R.id.txt_camount);
            layoutHandler.CDATE     = row.findViewById(R.id.txt_cdate);

            row.setTag(layoutHandler);

        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();
        }

        CreditorsProvider creditorsProvider = (CreditorsProvider) this.getItem(position);
        layoutHandler.CNAME.setText(creditorsProvider.getName());
        layoutHandler.CPHONE.setText(creditorsProvider.getPhone());
        layoutHandler.CAMOUNT.setText(creditorsProvider.getAmount());
        layoutHandler.CDATE.setText(creditorsProvider.getDate());

        return row;
    }
}
