package com.example.guest.ilovediscussing.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.guest.ilovediscussing.Constants;
import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.adapters.FirebaseCategoryViewHolder;
import com.example.guest.ilovediscussing.models.Category;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CategoryListActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.categoryList) RecyclerView mCategoryList;
    @Bind(R.id.addCategoryButton) Button mAddCategoryButton;
    @Bind(R.id.categoryName) EditText mCategoryName;
    private DatabaseReference mCategoryReference;
    private ValueEventListener mCategoryReferenceListener;
    private FirebaseRecyclerAdapter mFirebaseAdapter;


    public ArrayList mCategories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        ButterKnife.bind(this);

//        Intent from Main Activity
        Intent intent = getIntent();


        mCategoryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_CATEGORIES);

        setUpFirebaseAdapter();

        mCategoryReferenceListener = mCategoryReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    mCategories.add(snapshot.getValue(Category.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAddCategoryButton.setOnClickListener(this);

    }

    private void setUpFirebaseAdapter(){
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Category, FirebaseCategoryViewHolder>(Category.class, R.layout.category_list_item, FirebaseCategoryViewHolder.class, mCategoryReference){
            @Override
            protected void populateViewHolder(FirebaseCategoryViewHolder viewHolder, Category model, int position){
                viewHolder.bindCategory(model);
            }
        };
        mCategoryList.setHasFixedSize(true);
        mCategoryList.setLayoutManager(new LinearLayoutManager(this));
        mCategoryList.setAdapter(mFirebaseAdapter);

    }

    @Override
    public void onClick(View view) {
        if (view == mAddCategoryButton){
            String name = mCategoryName.getText().toString();

            if (name.equals("")){
                mCategoryName.setError("Please enter a category name");
                return;
            }

            Category category = new Category(name);
            DatabaseReference pushRef = mCategoryReference.push();
            String pushId = pushRef.getKey();
            category.setPushId(pushId);
            pushRef.setValue(category);


            startActivity(getIntent());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_logout){
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(CategoryListActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
