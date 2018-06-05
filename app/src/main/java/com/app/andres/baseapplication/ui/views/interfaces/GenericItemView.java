package com.app.andres.baseapplication.ui.views.interfaces;

public interface GenericItemView<T> {

    void bind(T item);

    T getData();

    void setSelected(boolean isSelected);

}
