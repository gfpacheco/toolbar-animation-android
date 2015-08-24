package com.gfpacheco.toolbaranimation;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gfpacheco.toolbaranimation.dummy.DummyContent;

public class ItemsFragment extends ListFragment implements AbsListView.OnScrollListener {

    private OnFragmentInteractionListener mListener;
    private int mHeaderHeight;
    private int mMinHeaderTranslation;
    private int mToolbarHeight;
    private View mHeaderView;
    private View mHeaderBackground;
    private View mBackgroundPicture;
    private View mFavoriteButton;
    private View mSecondaryInfo;
    private View mName;
    private int mNameTranslationY;
    private int mNameTranslationX;

    public ItemsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_selectable_list_item, android.R.id.text1, DummyContent.ITEMS));

        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mToolbarHeight = getResources().getDimensionPixelSize(R.dimen.toolbar_height);
        mMinHeaderTranslation = mToolbarHeight - mHeaderHeight;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_items, null);

        Toolbar mToolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);

        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        listView.setOnScrollListener(this);

        mHeaderView = rootView.findViewById(R.id.header);
        mHeaderBackground = mHeaderView.findViewById(R.id.header_background);
        mBackgroundPicture = mHeaderView.findViewById(R.id.picture_parse_image_view);
        mFavoriteButton = mHeaderView.findViewById(R.id.favorite_button);
        mSecondaryInfo = mHeaderView.findViewById(R.id.secondary_info);
        mName = mHeaderView.findViewById(R.id.name_text_view);
        ViewGroup.MarginLayoutParams nameLayoutParams = (ViewGroup.MarginLayoutParams) mName.getLayoutParams();
        ViewGroup.MarginLayoutParams favoriteButtonLayoutParams = (ViewGroup.MarginLayoutParams) mFavoriteButton.getLayoutParams();
        mNameTranslationX = favoriteButtonLayoutParams.leftMargin - nameLayoutParams.leftMargin;
        mNameTranslationY = nameLayoutParams.bottomMargin - favoriteButtonLayoutParams.bottomMargin;

        return rootView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (null == mHeaderView) {
            return;
        }

        int headerTranslation = Math.min(0, Math.max(mMinHeaderTranslation, -getScrollY(view)));
        float progress = (float) headerTranslation / mMinHeaderTranslation;

        mHeaderView.setTranslationY(headerTranslation);
        mBackgroundPicture.setTranslationY(-headerTranslation / 2);
        mHeaderBackground.getBackground().setAlpha((int) (progress * 255));
        mFavoriteButton.setAlpha(1 - progress);
        mSecondaryInfo.setAlpha(1 - progress);
        mName.setTranslationX(progress * mNameTranslationX);
        mName.setTranslationY(progress * mNameTranslationY);
    }

    public int getScrollY(AbsListView view) {
        View c = view.getChildAt(0);

        if (c == null)
            return 0;

        int firstVisiblePosition = view.getFirstVisiblePosition();
        int top = c.getTop();

        return -top + firstVisiblePosition * c.getHeight() + view.getListPaddingTop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(String id);
    }

}
