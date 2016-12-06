package com.example.guest.ilovediscussing.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Category;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity {
    @Bind(R.id.categoryName) TextView mCategoryName;
    private Category mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
       mCategory = Parcels.unwrap(getIntent().getParcelableExtra("category"));
        ButterKnife.bind(this);
        mCategoryName.setText(mCategory.getName());
    }
}
