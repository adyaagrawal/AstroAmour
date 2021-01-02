package com.example.astroamour;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private Context context;
    private List<Model> pics;
    private ItemClickListener itemClickListener;

    public ItemAdapter(Context context, List<Model> pics) {
        this.context = context;
        this.pics = pics;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_pic,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Model pic=pics.get(position);
        holder.title.setText(pic.getTitle());
        Picasso.with(context).load(pic.getUrl()).into(holder.pic);

        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,ItemDetails.class);
                intent.putExtra("image_url", pics.get(position).getUrl());
                intent.putExtra("image_title", pics.get(position).getTitle());
                intent.putExtra("image_date", pics.get(position).getDate());
                intent.putExtra("image_des", pics.get(position).getExplanation());
                intent.putExtra("image_copy", pics.get(position).getCopyright());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView pic;
        TextView title;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.itempic);
            title=itemView.findViewById(R.id.itemtitle);

        }
    }
}
