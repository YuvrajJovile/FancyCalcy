package yuvi.com.fancycalcy.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import yuvi.com.fancycalcy.presenter.iPresenter.IPresenter;
import yuvi.com.fancycalcy.utils.CodeSnippet;
import yuvi.com.fancycalcy.view.iView.IView;

public abstract class BaseFragment extends Fragment implements IView {


    protected String TAG = getClass().getSimpleName();
    private CodeSnippet mCodeSnippet;
    private IPresenter mIpresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }


    protected abstract int getLayoutId();

    @Override
    public void bindPresenter(IPresenter pIPresenter) {
        this.mIpresenter = pIPresenter;
    }

    @Override
    public CodeSnippet getCodeSnippet() {
        return ((IView)getActivity()).getCodeSnippet();
    }

    @Override
    public void showMessage(String pMessage) {
        ((IView)getActivity()).showMessage(pMessage);
    }
}
