package yuvi.com.fancycalcy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import yuvi.com.fancycalcy.R;
import yuvi.com.fancycalcy.presenter.MainActivityPresenter;
import yuvi.com.fancycalcy.presenter.iPresenter.IMainActivityPresenter;
import yuvi.com.fancycalcy.utils.Log;
import yuvi.com.fancycalcy.view.fragment.DisplayFragment;
import yuvi.com.fancycalcy.view.fragment.WorkspaceFragment;
import yuvi.com.fancycalcy.view.iView.IDisplayFragmentView;
import yuvi.com.fancycalcy.view.iView.IMainActivityView;
import yuvi.com.fancycalcy.view.iView.IWorkspaceView;

import static yuvi.com.fancycalcy.utils.Constants.fragmentTags.DISPLAY_FRAG;
import static yuvi.com.fancycalcy.utils.Constants.fragmentTags.WORKSPACE_FRAG;

public class MainActivity extends BaseActivity implements IMainActivityView {


    private IMainActivityPresenter mIMainActivityPresenter;

    private FragmentManager mFragmentManager;

    private WorkspaceFragment mWorkspaceFragment;
    private DisplayFragment mDisplayFragment;

    private IWorkspaceView mIWorkspaceView;
    private IDisplayFragmentView mIDisplayFragmentView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIMainActivityPresenter = new MainActivityPresenter(this);
        mIMainActivityPresenter.onCreatePresenter(getIntent().getExtras());

        init();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initializeView(IDisplayFragmentView pIDisplayFragmentView) {
        this.mIDisplayFragmentView = pIDisplayFragmentView;
    }



    private void init() {

        if (mFragmentManager == null)
            mFragmentManager = getSupportFragmentManager();
        mWorkspaceFragment = (WorkspaceFragment) mFragmentManager.findFragmentById(R.id.v_container_workspace);
        if (mWorkspaceFragment == null) {
            mWorkspaceFragment = new WorkspaceFragment();
            mFragmentManager.beginTransaction().add(R.id.v_container_workspace, mWorkspaceFragment, WORKSPACE_FRAG).commit();
        }
        mDisplayFragment = (DisplayFragment) mFragmentManager.findFragmentById(R.id.v_container_display);
        if (mDisplayFragment == null) {
            mDisplayFragment = new DisplayFragment();
            mFragmentManager.beginTransaction().add(R.id.v_container_display, mDisplayFragment, DISPLAY_FRAG).commit();
        }
    }

    @Override
    public void sendActionEvent(String pAction) {
        Log.d(TAG, "key Event==" + pAction);
        mIMainActivityPresenter.handleActionEvent(pAction);

    }

    @Override
    public void setDisplayMessage(String mData) {
        if(mIDisplayFragmentView!=null){
            mIDisplayFragmentView.setDisplayMessage(mData);
        }
    }



}
