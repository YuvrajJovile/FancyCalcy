package yuvi.com.fancycalcy.view.iView;

import yuvi.com.fancycalcy.view.fragment.DisplayFragment;

public interface IMainActivityView  extends IView{
    void sendActionEvent(String action);

    void initializeView(IDisplayFragmentView iDisplayFragmentView);

    void setDisplayMessage(String mData);
}
