package com.aritraroy.rxmagnetodemo;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aritraroy.rxmagnetodemo.domain.FeatureModel;

import java.util.List;

/**
 * Created by aritraroy on 18/02/17.
 */

public class FeaturesRecyclerAdapter extends RecyclerView.Adapter<FeaturesRecyclerAdapter.FeaturesViewHolder> {

    public int mSelectedItem = 0;

    private List<FeatureModel> mFeatureModelsList;
    private OnClickListener mOnClickListener;

    public FeaturesRecyclerAdapter(List<FeatureModel> featureList) {
        this.mFeatureModelsList = featureList;
    }

    @Override
    public FeaturesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycler_item, parent, false);
        return new FeaturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeaturesViewHolder holder, int position) {
        FeatureModel featureModel = getItemForPosition(position);
        if (featureModel != null) {
            holder.mFeatureTitle.setText(featureModel.getTitle());
            holder.mFeatureSelector.setTag(featureModel.getId());
            holder.mFeatureSelector.setChecked(position == mSelectedItem);
        }
    }

    @Nullable
    private FeatureModel getItemForPosition(int position) {
        return mFeatureModelsList != null ? mFeatureModelsList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return mFeatureModelsList != null ? mFeatureModelsList.size() : 0;
    }

    public OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.mOnClickListener = listener;
    }

    class FeaturesViewHolder extends RecyclerView.ViewHolder {

        private TextView mFeatureTitle;
        private RadioButton mFeatureSelector;

        public FeaturesViewHolder(View itemView) {
            super(itemView);
            mFeatureTitle = (TextView) itemView.findViewById(R.id.feature_title);
            mFeatureSelector = (RadioButton) itemView.findViewById(R.id.feature_selector);

            mFeatureSelector.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = getAdapterPosition();
                    notifyItemRangeChanged(0, mFeatureModelsList.size());

                    if (mOnClickListener != null) {
                        mOnClickListener.onClick((Integer) v.getTag());
                    }
                }
            });
        }
    }

    public interface OnClickListener {
        void onClick(int featureId);
    }
}
