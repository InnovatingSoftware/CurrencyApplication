package com.app.andres.baseapplication.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.app.andres.baseapplication.ui.factories.GenericAdapterFactory;
import com.app.andres.baseapplication.ui.views.generics.CategoryItem;
import com.app.andres.baseapplication.ui.views.interfaces.GenericCategoryItem;
import com.app.andres.baseapplication.ui.views.interfaces.GenericItem;
import com.app.andres.baseapplication.ui.views.interfaces.GenericItemView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.app.andres.baseapplication.ui.factories.GenericAdapterFactory.TYPE_CATEGORY;
import static com.app.andres.baseapplication.ui.factories.GenericAdapterFactory.TYPE_FOOTER;
import static com.app.andres.baseapplication.ui.factories.GenericAdapterFactory.TYPE_HEADER;

public class GenericAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String defaultCategory = "Otro";

    protected List<GenericItem> items;

    protected GenericAdapterFactory factory;

    protected boolean categoryEnable = false;

    private String other;

    public GenericAdapter(GenericAdapterFactory factory) {
        this.factory = factory;
        this.items = new ArrayList<>();
        this.other = defaultCategory;
    }

    public GenericAdapter(GenericAdapterFactory factory, boolean categoryenable) {
        this(factory);
        this.categoryEnable = categoryenable;
    }

    public GenericAdapter(GenericAdapterFactory factory, String other) {
        this.factory = factory;
        this.items = new ArrayList<>();
        this.other = other;
        this.categoryEnable = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = (View) this.factory.onCreateViewHolder(parent, viewType);

        return new RecyclerView.ViewHolder(view) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GenericItemView genericItemView = (GenericItemView) holder.itemView;
        genericItemView.bind(getItem(position).getData());
    }

    protected GenericItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setCategoryEnable(boolean categoryEnable) {
        this.categoryEnable = categoryEnable;
        notifyDataSetChanged();
    }

    public void setItems(List<? extends GenericItem> items) {

        this.items = new ArrayList<>();

        if (items != null) {

            for (int i = 0, size = items.size(); i < size; i++) {

                GenericItem genericItem = items.get(i);

                addNewItem(genericItem);
            }

        } else {
            this.items = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    private void addNewItem(GenericItem genericItem) {
        if (categoryEnable && genericItem instanceof GenericCategoryItem) {
            GenericCategoryItem categoryItem = (GenericCategoryItem) genericItem;
            addCategories(categoryItem);
        } else {
            this.items.add(genericItem);
        }
    }


    private void addCategories(GenericCategoryItem item) {

        final String category = TextUtils.isEmpty(item.getCategoryName()) ? other : item.getCategoryName();

        int categoryIndex = indexCategoryOf(category);

        if (categoryIndex != -1) {

            this.items.add(categoryIndex + 1, item);

        } else {

            GenericCategoryItem newCategory = getCategoryItem(category);

            int indexNewCategory = getNewCategoryIndex(newCategory);

            this.items.add(indexNewCategory, newCategory);

            this.items.add(indexNewCategory + 1, item);
        }
    }

    protected CategoryItem getCategoryItem(String categoryName) {
        return new CategoryItem(categoryName);
    }

    private int indexCategoryOf(String category) {
        for (int i = 0, size = items.size(); i < size; i++) {
            GenericItem item = getItem(i);
            if (item.getType() == TYPE_CATEGORY) {
                GenericCategoryItem categoryItem = (GenericCategoryItem) item;
                if (categoryItem.getData().equals(category)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getNewCategoryIndex(GenericCategoryItem newCategory) {
        int itemSize = items.size();
        for (int i = 0; i < itemSize; i++) {
            GenericItem item = getItem(i);
            if (item.getType() == TYPE_CATEGORY) {
                GenericCategoryItem categoryItem = (GenericCategoryItem) item;
                if (categoryItem.compareTo(newCategory) >= 0) {
                    return i;
                }
            }
        }
        return itemSize;
    }

    public void updateCategories() {
        removeCategories();
        setItems(new ArrayList<>(items));
    }

    private void removeCategories() {
        Iterator<GenericItem> itemIterator = items.iterator();
        while (itemIterator.hasNext()) {
            GenericItem item = itemIterator.next();
            if (item.getType() == TYPE_CATEGORY) {
                itemIterator.remove();
            }
        }
    }

    public void update(GenericItem item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.set(index, item);
            notifyItemChanged(index);
        }
    }

    public void remove(GenericItem item) {
        int index = items.indexOf(item);
        if (index != -1) {
            items.remove(index);
            notifyItemRemoved(index);
            removeEmptyCategory(index);
        }
    }


    private void removeEmptyCategory(int index) {
        if (categoryEnable) {

            GenericItem prevItem = null, nextItem = null;
            if (index < items.size()) {
                nextItem = items.get(index);
            }
            if (index > 0) {
                prevItem = items.get(index - 1);
            }

            if (prevItem != null && prevItem instanceof CategoryItem && (nextItem == null || nextItem instanceof CategoryItem)) {
                items.remove(index - 1);
                notifyItemRemoved(index - 1);
            }
        }
    }

    public void removeItemAtPosition(int position) {
        if (position >= 0 && position < items.size()) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void removeItemsAtPosition(int position, int itemCount) {
        if (position >= 0 && position < items.size()) {
            for (int i = 0; i < itemCount; i++) {
                items.remove(position);
            }
            notifyItemRangeRemoved(position, itemCount);
        }
    }

    public void addItem(GenericItem item) {
        addNewItem(item);
        notifyDataSetChanged();
    }

    public void addItems(List<? extends GenericItem> items) {
        if (items != null) {
            for (int i = 0, size = items.size(); i < size; i++) {
                addNewItem(items.get(i));
            }
            notifyDataSetChanged();
        }
    }

    public void addItems(int index, List<? extends GenericItem> itemsToAdd) {
        if (itemsToAdd == null) {
            itemsToAdd = new ArrayList<>();
        }
        items.addAll(index, itemsToAdd);
        notifyItemInserted(index);
    }

    public void addItem(int index, GenericItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(index, item);
        notifyItemInserted(index);
    }

    public void clearAll() {
        if (items != null) {
            items.clear();
            notifyDataSetChanged();
        }
    }

    public void clear() {
        if (items != null) {
            Iterator<GenericItem> i = items.iterator();
            while (i.hasNext()) {
                GenericItem itemView = i.next();
                if (itemView.getType() != TYPE_HEADER && itemView.getType() != TYPE_FOOTER) {
                    i.remove();
                }
            }
            notifyDataSetChanged();
        }
    }

    public int getItemPosition(GenericItem item) {
        int itemPosition = -1;
        if (items != null && !items.isEmpty()) {
            itemPosition = items.indexOf(item);
        }
        return itemPosition;
    }

    public GenericItem getItemAtPosition(int position) {
        GenericItem genericItem = null;
        if (items != null && items.size() > position) {
            genericItem = getItem(position);
        }
        return genericItem;
    }

    public void notifyItemWithDataChanged(Object data) {
        GenericItem genericItem = getItemWithData(data);
        if (genericItem != null) {
            notifyItemChanged(getItemPosition(genericItem));
        }
    }

    public GenericItem getItemWithData(Object data) {
        if (items != null) {
            Iterator<GenericItem> i = items.iterator();
            while (i.hasNext()) {
                GenericItem itemView = i.next();
                if (itemView.getData() == data) {
                    return itemView;
                }
            }
        }
        return null;
    }

}