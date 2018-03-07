package com.droidverine.adcrimereporter.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.droidverine.adcrimereporter.Acitivities.InfoActivity;
import com.droidverine.adcrimereporter.Models.OthersModel;
import com.droidverine.adcrimereporter.R;

import java.util.List;

/**
 * Created by DELL on 04-03-2018.
 */

public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.customadapter> {
    Context context;
    List<OthersModel> othersModelList;

    public OtherAdapter(Context context, List<OthersModel> othersModelList) {
        this.context = context;
        this.othersModelList = othersModelList;
    }

    @NonNull
    @Override
    public OtherAdapter.customadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        OtherAdapter.customadapter customholder = new OtherAdapter.customadapter(view);
        return customholder;
    }

    @Override
    public void onBindViewHolder(final OtherAdapter.customadapter holder, final int position) {
        holder.victimname.append(othersModelList.get(position).getName());
        holder.Location.append(othersModelList.get(position).getAddress());
        String url=othersModelList.get(position).getUrl();
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        Glide.with(context).load(url)
                .listener(new RequestListener<Drawable>()
                {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource)
                    {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource)
                    {
                        Log.d("Glide", "resource ready");
                        holder.victimimg.setBackgroundResource(0);
                        return false;
                    }

                })
                .apply(options)
                .into(holder.victimimg);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, InfoActivity.class);
                intent.putExtra("name",othersModelList.get(position).getName());
                intent.putExtra("location",othersModelList.get(position).getAddress());
                intent.putExtra("otherinfo",othersModelList.get(position).getOtherinfo());
                intent.putExtra("url",othersModelList.get(position).getUrl());
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return othersModelList.size();
    }
    public  class  customadapter extends  RecyclerView.ViewHolder
    {
        TextView victimname,Location;
        ImageView victimimg;
        LinearLayout item;
        public customadapter(View itemView) {
            super(itemView);
            victimimg=(ImageView)itemView.findViewById(R.id.victimimg);
            victimname=(TextView) itemView.findViewById(R.id.victimname);
            Location=(TextView)itemView.findViewById(R.id.victimlocation);
            item=(LinearLayout)itemView.findViewById(R.id.item_list);

        }
    }
}
