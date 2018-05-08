package yuvi.com.fancycalcy.presenter;

import android.os.Bundle;

import yuvi.com.fancycalcy.presenter.iPresenter.IDisplayFragmentPresenter;
import yuvi.com.fancycalcy.view.iView.IDisplayFragmentView;
import yuvi.com.fancycalcy.view.iView.IView;

public class DisplayFragmentPresenter extends BasePresenter implements IDisplayFragmentPresenter {

    private IDisplayFragmentView mIDisplayFragmentView;

    public DisplayFragmentPresenter(IDisplayFragmentView iView) {
        super(iView);
        this.mIDisplayFragmentView = iView;
    }

    @Override
    public void onCreatePresenter(Bundle pBundle) {

    }
}
