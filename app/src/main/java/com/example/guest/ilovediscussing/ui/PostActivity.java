package com.example.guest.ilovediscussing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Category;
import com.example.guest.ilovediscussing.models.Post;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.categoryName) TextView mCategoryName;
    @Bind(R.id.postList) RecyclerView mPostList;
    @Bind(R.id.addPostImage) ImageView mAddPost;


    public ArrayList<Post> mPosts = new ArrayList<>();
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

       mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));
        mCategoryName.setText(mCategory.getName());

        mAddPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if (view == mAddPost){
          Intent intent = new Intent(PostActivity.this, NewPostActivity.class);
          intent.putExtra("category", Parcels.wrap(mCategory));
          startActivity(intent);
      }
    }
}
