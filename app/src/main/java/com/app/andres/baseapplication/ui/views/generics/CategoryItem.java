package com.app.andres.baseapplication.ui.views.generics;

import com.app.andres.baseapplication.ui.factories.GenericAdapterFactory;
import com.app.andres.baseapplication.ui.views.interfaces.GenericCategoryItem;

public class CategoryItem implements GenericCategoryItem<String> {

    public String category;

    public CategoryItem(String category) {
        this.category = category;
    }

    @Override
    public String getData() {
        return category;
    }

    @Override
    public int getType() {
        return GenericAdapterFactory.TYPE_CATEGORY;
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
