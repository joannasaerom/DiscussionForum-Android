package com.example.guest.ilovediscussing.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ilovediscussing.Constants;
import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Category;
import com.example.guest.ilovediscussing.ui.CategoryListActivity;
import com.example.guest.ilovediscussing.ui.PostActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FirebaseCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View mView;
    Context mContext;

    public FirebaseCategoryViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);


    }

    public void bindCategory(Category category){
        TextView mCategoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        mCategoryTextView.setText(category.getName());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Category> categories = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_CATEGORIES);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    categories.add(snapshot.getValue(Category.class));
                }

                int itemPosition = getLayoutPosition();


                Intent intent = new Intent(mContext, PostActivity.class);
             //   intent.putExtra("position", itemPosition + "");
           //     intent.putExtra("position", itemPosition);
                intent.putExtra("category", Parcels.wrap(categories.get(itemPosition)));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
