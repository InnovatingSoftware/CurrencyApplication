package com.app.andres.baseapplication.ui.views.generics;

import com.app.andres.baseapplication.ui.views.interfaces.GenericItem;

public class GenericItemAbstract implements GenericItem<Object> {

    private Object data;
    private int type;

    public GenericItemAbstract(Object data) {
        this.data = data;
    }

    public GenericItemAbstract(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public int getType() {
        return type;
    }
}
