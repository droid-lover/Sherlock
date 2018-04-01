package com.sherlock.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CommonItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private boolean verticalOrientation = true;
    private int space = 5;

    public CommonItemSpaceDecoration(int value, boolean verticalOrientation) {
        this.space = value;
        this.verticalOrientation = verticalOrientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        //skip first item in the list
        if (parent.getChildAdapterPosition(view) != 0) {
            if (verticalOrientation) {
                outRect.set(space, 0, 0, 0);
            } else if (!verticalOrientation) {
                outRect.set(0, space, 0, 0);
            }
        }
    }
}