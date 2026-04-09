package com.example.ej3_b.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ej3_b.R;
import com.example.ej3_b.activities.DetailActivity;
import com.example.ej3_b.model.Product;

import java.util.List;

public class ProductsAdapter extends BaseAdapter {
    List<Product> products;
    Context context;
    TextView txvProducts;
    Button btnView;

    public ProductsAdapter(List<Product> products, Context context) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.product_list, parent, false);
        }
        txvProducts = convertView.findViewById(R.id.txvProducts);
        txvProducts.setText(products.get(position).getName());
        btnView = convertView.findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callDetail(products.get(position).getId());
            }
        });

        return convertView;
    }

    private void callDetail(int id){
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("id", id);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
