package com.tasfiqul.ghani.fragment;

import android.support.v4.content.ContextCompat;
import android.view.View;

import com.tasfiqul.ghani.R;
import com.tasfiqul.ghani.fragment.template.NoteFragment;
import com.tasfiqul.ghani.model.DatabaseModel;
import jp.wasabeef.richeditor.RichEditor;

public class SimpleNoteFragment extends NoteFragment {
	private RichEditor body;

	public SimpleNoteFragment() {}

	@Override
	public int getLayout() {
		return R.layout.fragment_simple_note;
	}

	@Override
	public void saveNote(final SaveListener listener) {
		super.saveNote(listener);
		note.body = body.getHtml();

		new Thread() {
			@Override
			public void run() {
				long id = note.save();
				if (note.id == DatabaseModel.NEW_MODEL_ID) {
					note.id = id;
				}
				listener.onSave();
				interrupt();
			}
		}.start();
	}

	@Override
	public void init(View view) {
		body = (RichEditor) view.findViewById(R.id.editor);
		body.setPlaceholder("Note");
		body.setEditorBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg));

		view.findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				body.setBold();
			}
		});

		view.findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				body.setItalic();
			}
		});

		view.findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				body.setUnderline();
			}
		});

		body.setHtml(note.body);
	}
}
