package com.example.guest.ilovediscussing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.ilovediscussing.Constants;
import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Category;
import com.example.guest.ilovediscussing.models.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.postTitle) EditText mPostTitle;
    @Bind(R.id.postContent) EditText mPostContent;
    @Bind(R.id.addPostButton) Button mAddPostButton;

    private DatabaseReference mPostReference;
    private DatabaseReference mCategoryReference;
    private Category mCategory;
    private String mCategoryPushId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);

        mAddPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mAddPostButton){

            mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));
            mCategoryPushId = mCategory.getPushId();

            String title = mPostTitle.getText().toString();
            String content = mPostContent.getText().toString();

            if (title.equals("")){
                mPostTitle.setError("Please enter a title");
                return;
            }
            if (content.equals("")){
                mPostContent.setError("Please enter details about the post");
                return;
            }

            Post post = new Post(title, content, mCategoryPushId);

            mPostReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_POSTS)
                    .child(mCategoryPushId);

            DatabaseReference pushRef = mPostReference.push();
            String pushId = pushRef.getKey();
            post.setPushId(pushId);
            pushRef.setValue(post);

            Intent intent = new Intent(NewPostActivity.this, PostActivity.class);
            intent.putExtra("category", Parcels.wrap(mCategory));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }

}
