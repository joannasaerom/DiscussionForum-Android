package com.example.guest.ilovediscussing.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.ilovediscussing.Constants;
import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.adapters.FirebaseCommentViewHolder;
import com.example.guest.ilovediscussing.models.Comment;
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

public class PostDetailActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.postTitle) TextView mPostTitle;
    @Bind(R.id.postDetails) TextView mPostDetails;
    @Bind(R.id.commentList) RecyclerView mCommentList;
    @Bind(R.id.commentEditText) EditText mCommentEditText;
    @Bind(R.id.addComment) Button mAddComment;

    private DatabaseReference mCommentReference;
    private ValueEventListener mCommentReferenceListener;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    private Post mPost;
    public ArrayList<Comment> mComments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        mPost = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        mPostTitle.setText(mPost.getTitle());
        mPostDetails.setText(mPost.getContent());

        mCommentReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_COMMENTS)
                .child(mPost.getCategoryId())
                .child(mPost.getPushId());

        setUpFirebaseAdapter();

        mCommentReferenceListener = mCommentReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            mComments.add(snapshot.getValue(Comment.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        mAddComment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        if (v == mAddComment){
            String comment = mCommentEditText.getText().toString();

            if (comment.equals("")){
                mCommentEditText.setError("Please enter a comment");
                return;
            }
            String postId = mPost.getPushId();
            Comment newComment = new Comment(comment, postId);

            DatabaseReference pushRef = mCommentReference.push();
            String pushId = pushRef.getKey();
            newComment.setPushId(pushId);
            pushRef.setValue(newComment);

            startActivity(getIntent());
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Comment, FirebaseCommentViewHolder>(Comment.class, R.layout.comment_list_item, FirebaseCommentViewHolder.class, mCommentReference) {
            @Override
            protected void populateViewHolder(FirebaseCommentViewHolder viewHolder, Comment model, int position) {
                viewHolder.bindComment(model);
            }
        };
        mCommentList.setHasFixedSize(true);
        mCommentList.setLayoutManager(new LinearLayoutManager(this));
        mCommentList.setAdapter(mFirebaseAdapter);
    }
}
