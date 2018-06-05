package com.app.andres.baseapplication.ui.views.generics;

import com.app.andres.baseapplication.ui.views.interfaces.GenericCategoryItem;

public class GenericCategoryItemAbstract implements GenericCategoryItem<Object> {

    private Object data;
    private int type;
    private String category;

    public GenericCategoryItemAbstract(Object data, String category) {
        this.data = data;
        this.category = category;
    }

    public GenericCategoryItemAbstract(Object data, int type, String category) {
        this.data = data;
        this.type = type;
        this.category = category;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public String getCategoryName() {
        return category;
    }

    @Override
    public int compareTo(GenericCategoryItem categoryItem) {
        return category.compareTo(categoryItem.getCategoryName());
    }
}
