package coshx.com.rewards;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import coshx.com.rewards.model.Offer;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Offer> offers;

    public MyItemRecyclerViewAdapter(List<Offer> items) {
        offers = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Context context = holder.mIdView.getContext();
        Offer offer = offers.get(position);
        Drawable ad_gradient = context.getDrawable(R.drawable.ad_gradient);
        Drawable reward_gradient = context.getDrawable(R.drawable.reward_gradient);
        Drawable progress_gradient = context.getDrawable(R.drawable.progress_gradient);

        holder.gradient.setBackground(position % 3 == 0 ? reward_gradient : position % 3 == 1 ? ad_gradient : progress_gradient);
//        if (position % 3 == 0)
        Glide.with(context).load("https://slack-imgs.com/?c=1&url=http%3A%2F%2Fmedia2.giphy.com%2Fmedia%2FvER8rSZTjKpag%2Fgiphy.gif").into(holder.iv_gif);



        holder.mItem = offers.get(position);
        holder.mIdView.setText(offers.get(position).merchantName);
        holder.mContentView.setText(offers.get(position).amount);
        Typeface font = Typeface.createFromAsset(holder.mIdView.getContext().getAssets(), "sf-pro-display-regular.otf");
        holder.mIdView.setTypeface(font);

        holder.mView.setOnClickListener(v -> {
            Toast.makeText(holder.mIdView.getContext(), "onClick " + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(v.getContext(), DealDetailsActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final CardView cardView;
        public final ImageView iv_gif;
        public final View gradient;
        public Offer mItem;
        public LinearLayout ll;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            gradient = view.findViewById(R.id.card_gradient);
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
            cardView = view.findViewById(R.id.card_view);
            iv_gif = view.findViewById(R.id.iv_gif);
            ll = (LinearLayout) view;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
