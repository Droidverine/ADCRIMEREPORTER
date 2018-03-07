package com.droidverine.adcrimereporter.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.droidverine.adcrimereporter.Models.MissingModel;
import com.droidverine.adcrimereporter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 04-03-2018.
 */

public class MissingAdapter extends RecyclerView.Adapter<MissingAdapter.msgholder> {
    List<MissingModel> missingModelList;
    Context context;

    public MissingAdapter() {
    }

    public MissingAdapter(List<MissingModel> missingModelList, Context context) {
        this.missingModelList = missingModelList;
        this.context = context;
    }

    @Override
    public MissingAdapter.msgholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        msgholder msgholder = new msgholder(view);
        return msgholder;
    }

    @Override
    public void onBindViewHolder( final MissingAdapter.msgholder holder, final int position) {
        holder.victimname.append(missingModelList.get(position).getName());
        String url=missingModelList.get(position).getUrl();
        holder.Location.append(missingModelList.get(position).getAddress());
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
                intent.putExtra("name",missingModelList.get(position).getName());
                intent.putExtra("location",missingModelList.get(position).getAddress());
                intent.putExtra("otherinfo",missingModelList.get(position).getOtherinfo());
                intent.putExtra("url",missingModelList.get(position).getUrl());
                context.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return missingModelList.size();
    }
    public  class  msgholder extends RecyclerView.ViewHolder
    {
       TextView victimname,Location;
        ImageView victimimg;
        LinearLayout item;
        public msgholder(View itemView) {
            super(itemView);
            victimimg=(ImageView)itemView.findViewById(R.id.victimimg);
            victimname=(TextView) itemView.findViewById(R.id.victimname);
            Location=(TextView)itemView.findViewById(R.id.victimlocation);

            item=(LinearLayout)itemView.findViewById(R.id.item_list);


        }
    }
}
