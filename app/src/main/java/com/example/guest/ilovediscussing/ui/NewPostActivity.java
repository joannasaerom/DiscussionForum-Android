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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        ButterKnife.bind(this);

        mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));

//        mCategoryReference = FirebaseDatabase
//                .getInstance()
//                .getReference()
//                .child(Constants.FIREBASE_CHILD_CATEGORIES);




        mAddPostButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mAddPostButton){

            String title = mPostTitle.getText().toString();
            String content = mPostContent.getText().toString();
            String categoryId = mCategory.getPushId();
            Post newPost = new Post(title, content, categoryId);

            mPostReference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_POSTS);


            DatabaseReference pushRef = mPostReference.push();
            String pushId = pushRef.getKey();
            newPost.setPushId(pushId);
            pushRef.setValue(newPost);


            Intent intent = new Intent(NewPostActivity.this, PostActivity.class);
            intent.putExtra("category", Parcels.wrap(mCategory));

            startActivity(intent);
        }

    }

}
