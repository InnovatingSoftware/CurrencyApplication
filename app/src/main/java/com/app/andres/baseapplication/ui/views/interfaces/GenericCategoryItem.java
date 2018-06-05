package com.app.andres.baseapplication.ui.views.interfaces;

public interface GenericCategoryItem<T> extends GenericItem<T> {

    String getCategoryName();

    int compareTo(GenericCategoryItem categoryItem);

}
