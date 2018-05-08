package yuvi.com.fancycalcy.presenter;

import android.os.Bundle;

import yuvi.com.fancycalcy.presenter.iPresenter.IPresenter;
import yuvi.com.fancycalcy.presenter.iPresenter.IWorkspaceFragmentPresenter;
import yuvi.com.fancycalcy.view.iView.IView;
import yuvi.com.fancycalcy.view.iView.IWorkspaceView;

public class WorkspaceFragmentPresenter extends BasePresenter implements IWorkspaceFragmentPresenter {

    private IWorkspaceView mIWorkspaceView;

    public WorkspaceFragmentPresenter(IWorkspaceView iView) {
        super(iView);
        this.mIWorkspaceView = iView;
    }

    @Override
    public void onCreatePresenter(Bundle pBundle) {

    }
}
