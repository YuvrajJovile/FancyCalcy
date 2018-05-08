package yuvi.com.fancycalcy.presenter;

import yuvi.com.fancycalcy.presenter.iPresenter.IPresenter;
import yuvi.com.fancycalcy.view.iView.IView;

public abstract class BasePresenter implements IPresenter {

    protected String TAG = getClass().getSimpleName();

    private IView  iView;

    public BasePresenter(IView iView) {
        this.iView = iView;
        iView.bindPresenter(this);
    }

    public String getString(int pStringData){
       return iView.getCodeSnippet().getString(pStringData);
    }

    public void showMessage(String pMessage){
       iView.showMessage(pMessage);
    }
}
