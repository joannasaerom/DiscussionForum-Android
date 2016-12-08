package com.example.guest.ilovediscussing.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.database.DatabaseUtilsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ilovediscussing.Constants;
import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Post;
import com.example.guest.ilovediscussing.ui.PostActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;


public class FirebasePostViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebasePostViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPost(Post post){
        TextView postTitleTextView = (TextView) mView.findViewById(R.id.postTitleTextView);
        TextView postPreviewTextView = (TextView) mView.findViewById(R.id.postPreviewTextView);

        postTitleTextView.setText(post.getTitle());
        postPreviewTextView.setText(post.getContent());

    }

    @Override
    public void onClick(View view){
        final ArrayList<Post> posts = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POSTS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    posts.add(snapshot.getValue(Post.class));
                }
                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, PostActivity.class);
//                intent.putExtra("post", Parcels.wrap(posts.get(itemPosition)));
//                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
