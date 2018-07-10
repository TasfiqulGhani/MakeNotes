package com.tasfiqul.ghani.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.tasfiqul.ghani.R;
import com.tasfiqul.ghani.adapter.template.ModelAdapter;
import com.tasfiqul.ghani.model.Category;
import com.tasfiqul.ghani.widget.CategoryViewHolder;

public class CategoryAdapter extends ModelAdapter<Category, CategoryViewHolder> {
	public CategoryAdapter(ArrayList<Category> items, ArrayList<Category> selected, ClickListener<Category> listener) {
		super(items, selected, listener);
	}

	@Override
	public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
	}
}
