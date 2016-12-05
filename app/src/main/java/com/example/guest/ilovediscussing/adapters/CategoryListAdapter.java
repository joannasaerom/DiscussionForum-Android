package com.example.guest.ilovediscussing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Category;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/5/16.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder> {
    private ArrayList<Category> mCategories = new ArrayList<>();
    private Context mContext;

    public CategoryListAdapter(Context context, ArrayList<Category> categories){
        mContext = context;
        mCategories = categories;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.categoryTextView) TextView mCategoryTextView;

        private Context mContext;

        public CategoryViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindCategory(Category category){
            mCategoryTextView.setText(category.getName());
        }
    }
}
