/*
 * The Bestory Project
 */

package com.thebestory.android.fragment.main.stories;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thebestory.android.R;
import com.thebestory.android.adapter.main.StoriesAdapter;
import com.thebestory.android.api.ApiMethods;
import com.thebestory.android.api.LoaderResult;
import com.thebestory.android.api.LoaderStatus;
import com.thebestory.android.api.urlCollection.TypeOfCollection;
import com.thebestory.android.loader.main.RandomStoriesData;
import com.thebestory.android.model.Story;

import java.util.List;

/**
 * Fragment for Random tab on Stories screen.
 * TODO: Work, but we have one problem with repeating stories
 * Use the {@link RandomTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RandomTabFragment extends Fragment implements LoaderManager.LoaderCallbacks<LoaderResult<List<Story>>>  {
    private View view;
    final RandomTabFragment thisFragment = this;

    private RecyclerView rv;
    private TextView errorTextView;
    private ProgressBar progressView;

    private boolean flagForLoader;

    @Nullable
    private StoriesAdapter adapter;
    private RandomStoriesData randomStoriesData;
    public RandomTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RandomTabFragment.
     */
    public static RandomTabFragment newInstance() {
        return new RandomTabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_stories_random_tab, container, false);

        FragmentManager fm = getFragmentManager();

        progressView = (ProgressBar) view.findViewById(R.id.progress);
        errorTextView = (TextView) view.findViewById(R.id.error_text);

        adapter = new StoriesAdapter(getActivity());
        randomStoriesData = (RandomStoriesData) fm.findFragmentByTag(RandomStoriesData.TAG);


        rv = (RecyclerView) view.findViewById(R.id.rv_stories_random_tab);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rv.addItemDecoration(new RecylcerDividersDecorator(R.color.colorPrimaryDark));
        rv.setAdapter(adapter);

        if (randomStoriesData == null) {
            randomStoriesData = new RandomStoriesData();
            fm.beginTransaction().add(randomStoriesData, RandomStoriesData.TAG).commit();
        }

        errorTextView.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);

        if (savedInstanceState != null) {
            displayNonEmptyData(randomStoriesData.getCurrentStories());
        } else {
            flagForLoader = true;
            //Log.e("onCreateView: ", "i am here");
            getLoaderManager().initLoader(3, null, this);
        }

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (flagForLoader) {
                    return;
                }
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (llm.findLastVisibleItemPosition() + 3 >= adapter.getItemCount()) {
                    flagForLoader = true;
                    getLoaderManager().restartLoader(3, null, thisFragment);
                }
            }
        });

        return view;
    }

    @Override
    public Loader<LoaderResult<List<Story>>> onCreateLoader(int id, Bundle args) {
        //String currentId = randomStoriesData.getLastId();
        Loader<LoaderResult<List<Story>>> temp;
        temp = ApiMethods.getInstance().getRandomStories(getActivity(), TypeOfCollection.NONE, null, 10);
        /*if (currentId.equals("0")) {
            temp = ApiMethods.getInstance().getRandomStories(getActivity(), TypeOfCollection.NONE, null, 10);
        } else {
            temp = ApiMethods.getInstance().getRandomStories(getActivity(), TypeOfCollection.BEFORE, null, 10);
        }*/
        temp.forceLoad();
        return temp;
    }

    @Override
    public void onLoadFinished(Loader<LoaderResult<List<Story>>> loader, LoaderResult<List<Story>> result) {
        flagForLoader = result.data.isEmpty();

        if (result.status == LoaderStatus.OK) {
            if (!result.data.isEmpty()) {
                displayNonEmptyData(result.data);
                randomStoriesData.getCurrentStories().addAll(result.data);
            } else if (randomStoriesData.getCurrentStories().isEmpty()) {
                displayEmptyData();
            }
        } else {
            displayError(result.status);
        }
    }

    @Override
    public void onLoaderReset(Loader<LoaderResult<List<Story>>> loader) {
        displayEmptyData();
    }


    private void displayEmptyData() {
        progressView.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(R.string.stories_not_found);
    }

    private void displayNonEmptyData(List<Story> stories) {
        if (adapter != null) {
            adapter.addStories(stories);
        }
        progressView.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
    }

    private void displayError(LoaderStatus resultType) {
        progressView.setVisibility(View.GONE);
        rv.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        final int messageResId;
        if (resultType == LoaderStatus.ERROR) { //TODO: Add in LoaderStatus NO_INTERNET
            messageResId = R.string.no_internet;
        } else {
            messageResId = R.string.error;
        }
        errorTextView.setText(messageResId);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

}
