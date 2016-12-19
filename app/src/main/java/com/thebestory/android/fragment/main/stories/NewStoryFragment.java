/*
 * The Bestory Project
 */

package com.thebestory.android.fragment.main.stories;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thebestory.android.R;
import com.thebestory.android.activity.MainActivity;
import com.thebestory.android.api.ApiMethods;
import com.thebestory.android.api.LoaderResult;
import com.thebestory.android.model.Story;

import java.util.List;

/**
 * Fragment for New story screen.
 * TODO: Maybe work... It is need to decorate this fragment
 * Use the {@link NewStoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewStoryFragment extends Fragment implements LoaderManager.
        LoaderCallbacks<LoaderResult<Story>> {
    private View view;
    private TextView editStory;
    private AppCompatActivity activity;

    public NewStoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewStoryFragment.
     */
    public static NewStoryFragment newInstance() {
        return new NewStoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_stories_new, container, false);
        activity = (MainActivity) getActivity();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.main_stories_new_toolbar);
        editStory = (TextView) view.findViewById(R.id.editText);

        toolbar.setTitle(R.string.main_stories_new_toolbar_title);
        activity.setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onBackPressed();
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_stories_new, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.main_stories_new_toolbar_action_send: {
                String textStory = editStory.getText().toString();
                Bundle tempBundle = new Bundle();
                tempBundle.putString("text_story", textStory);
                getLoaderManager().restartLoader(5, tempBundle, this);
                editStory.setText("");
                break;
            }
//            case R.id.main_stories_toolbar_action_search:
//                // TODO: Stories search
//                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<LoaderResult<Story>> onCreateLoader(int id, Bundle args) {
        return ApiMethods.getInstance().postStory(getContext(), args.getString("text_story"));
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<Story>> loader, LoaderResult<Story> data) {
        switch (data.status) {
            case OK: {
                Log.w("NewStory", "Done!");
                break;
            }
            case ERROR: {
                Log.w("NewStory", "Error!");
                break;
            }
            case WARNING: {
                //TODO: Try to write this)))
                break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<Story>> loader) {

    }
}
