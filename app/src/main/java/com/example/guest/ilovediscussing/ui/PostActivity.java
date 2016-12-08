package com.example.guest.ilovediscussing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.ilovediscussing.Constants;
import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.adapters.FirebasePostViewHolder;
import com.example.guest.ilovediscussing.models.Category;
import com.example.guest.ilovediscussing.models.Post;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.categoryName) TextView mCategoryName;
    @Bind(R.id.addPostImage) ImageView mAddPost;
    @Bind(R.id.postList) RecyclerView mPostList;

    private DatabaseReference mPostReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private String mCategoryPushId;
    private Category mCategory;
    private ValueEventListener mPostReferenceListener;

    public ArrayList mPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

       mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));
        mCategoryPushId = mCategory.getPushId();
        mCategoryName.setText(mCategory.getName());

        mPostReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_POSTS)
                .child(mCategoryPushId);

        setUpFirebaseAdapter();

        mPostReferenceListener = mPostReference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            mPosts.add(snapshot.getValue(Post.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        mAddPost.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onClick(View view) {
      if (view == mAddPost){
          Intent intent = new Intent(PostActivity.this, NewPostActivity.class);
          intent.putExtra("category", Parcels.wrap(mCategory));
          startActivity(intent);
      }
    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Post, FirebasePostViewHolder>(Post.class, R.layout.post_list_item, FirebasePostViewHolder.class, mPostReference) {
            @Override
            protected void populateViewHolder(FirebasePostViewHolder viewHolder, Post model, int position) {
                viewHolder.bindPost(model);
            }
        };
        mPostList.setHasFixedSize(true);
        mPostList.setLayoutManager(new LinearLayoutManager(this));
        mPostList.setAdapter(mFirebaseAdapter);

    }
}
