package yuvi.com.fancycalcy.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import yuvi.com.fancycalcy.R;
import yuvi.com.fancycalcy.presenter.DisplayFragmentPresenter;
import yuvi.com.fancycalcy.presenter.iPresenter.IDisplayFragmentPresenter;
import yuvi.com.fancycalcy.utils.Log;
import yuvi.com.fancycalcy.view.iView.IDisplayFragmentView;
import yuvi.com.fancycalcy.view.iView.IMainActivityView;

public class DisplayFragment extends BaseFragment implements IDisplayFragmentView {

    private IDisplayFragmentPresenter mIDisplayFragmentPresenter;

    private IMainActivityView pIMainActivityView;
    private TextView mDisplayText;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIDisplayFragmentPresenter = new DisplayFragmentPresenter(this);
        mIDisplayFragmentPresenter.onCreatePresenter(getArguments());

        mDisplayText = (TextView) view.findViewById(R.id.tv_display_text);

        init();
    }

    private void init() {
        ((IMainActivityView) getActivity()).initializeView(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_display;
    }


    @Override
    public void setDisplayMessage(String pMessage) {

        Log.e(TAG, pMessage);
        if (pMessage != null)
            mDisplayText.setText(pMessage);
    }

}
