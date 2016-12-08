package com.example.guest.ilovediscussing.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.guest.ilovediscussing.R;
import com.example.guest.ilovediscussing.models.Comment;

/**
 * Created by joannaanderson on 12/8/16.
 */

public class FirebaseCommentViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
    private Comment mComment;

    public FirebaseCommentViewHolder(View itemView){
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
//        itemView.setOnClickListener(this);
    }

    public void bindComment(Comment comment){
        mComment = comment;
        TextView commentTextView = (TextView) mView.findViewById(R.id.commentTextView);
        commentTextView.setText(comment.getContent());
    }
}

