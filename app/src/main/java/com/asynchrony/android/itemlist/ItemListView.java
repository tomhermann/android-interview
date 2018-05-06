package com.asynchrony.android.itemlist;

import android.view.View;

import java.util.List;

interface ItemListView {
    void setAddButtonClickListener(View.OnClickListener listener);

    String getNewItemText();

    void clearItemInput();

    void updateList(List<String> itemList);
}
