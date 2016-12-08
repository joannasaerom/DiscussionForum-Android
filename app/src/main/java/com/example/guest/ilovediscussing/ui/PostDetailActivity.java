package com.example.guest.ilovediscussing.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Post;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity {
    @Bind(R.id.postTitle) TextView mPostTitle;
    @Bind(R.id.postDetails) TextView mPostDetails;

    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        mPost = Parcels.unwrap(getIntent().getParcelableExtra("post"));
        Log.d("postDetail", "title " + mPost.getTitle());
        Log.d("postDetail", "content " + mPost.getContent());
        mPostTitle.setText(mPost.getTitle());
        mPostDetails.setText(mPost.getContent());
    }
}
