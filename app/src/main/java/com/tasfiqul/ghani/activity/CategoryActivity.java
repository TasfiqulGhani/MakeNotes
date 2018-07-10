package com.tasfiqul.ghani.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.tasfiqul.ghani.R;
import com.tasfiqul.ghani.db.OpenHelper;
import com.tasfiqul.ghani.fragment.CategoryFragment;
import com.tasfiqul.ghani.fragment.template.RecyclerFragment;
import com.tasfiqul.ghani.inner.Animator;
import com.tasfiqul.ghani.model.Category;

public class CategoryActivity extends AppCompatActivity implements RecyclerFragment.Callbacks {
	public static final int REQUEST_CODE = 1;
	public static final int RESULT_CHANGE = 101;
	private Toolbar toolbar;
	private CategoryFragment fragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		setTheme(Category.getStyle(getIntent().getIntExtra(OpenHelper.COLUMN_THEME, Category.THEME_GREEN)));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);

		AdView adView = (AdView)findViewById(R.id.adView);

		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.build();

		adView.loadAd(adRequest);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		try {
			//noinspection ConstantConditions
			getSupportActionBar().setDisplayShowTitleEnabled(false);
		} catch (Exception ignored) {
		}

		toolbar.findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});

		if (savedInstanceState == null) {
			fragment = new CategoryFragment();

			getSupportFragmentManager().beginTransaction()
				.add(R.id.container, fragment)
				.commit();
		}
	}

	@Override
	public void onChangeSelection(boolean state) {
		if (state) {
			Animator.create(getApplicationContext())
				.on(toolbar)
				.setEndVisibility(View.INVISIBLE)
				.animate(R.anim.fade_out);
		} else {
			Animator.create(getApplicationContext())
				.on(toolbar)
				.setStartVisibility(View.VISIBLE)
				.animate(R.anim.fade_in);
		}
	}

	@Override
	public void toggleOneSelection(boolean state) {
	}

	@Override
	public void onBackPressed() {
		if (fragment.isFabOpen) {
			fragment.toggleFab(true);
			return;
		}

		if (fragment.selectionState) {
			fragment.toggleSelection(false);
			return;
		}

		Intent data = new Intent();
		data.putExtra("position", fragment.categoryPosition);
		data.putExtra(OpenHelper.COLUMN_COUNTER, fragment.items.size());
		setResult(RESULT_CHANGE, data);
		finish();
	}
}
