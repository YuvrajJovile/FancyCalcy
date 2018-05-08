package yuvi.com.fancycalcy.view.iView;

import yuvi.com.fancycalcy.presenter.BasePresenter;
import yuvi.com.fancycalcy.presenter.iPresenter.IPresenter;
import yuvi.com.fancycalcy.utils.CodeSnippet;

public interface IView {


    void bindPresenter(IPresenter pIPresenter);

    void showMessage(String pMessage);

    CodeSnippet getCodeSnippet();


}
