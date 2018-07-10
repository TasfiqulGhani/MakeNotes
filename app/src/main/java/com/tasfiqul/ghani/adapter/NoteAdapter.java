package com.tasfiqul.ghani.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import com.tasfiqul.ghani.R;
import com.tasfiqul.ghani.adapter.template.ModelAdapter;
import com.tasfiqul.ghani.model.Note;
import com.tasfiqul.ghani.widget.NoteViewHolder;

public class NoteAdapter extends ModelAdapter<Note, NoteViewHolder> {
	public NoteAdapter(ArrayList<Note> items, ArrayList<Note> selected, ClickListener<Note> listener) {
		super(items, selected, listener);
	}

	@Override
	public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new NoteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
	}
}