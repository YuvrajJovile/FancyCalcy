package yuvi.com.fancycalcy.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import yuvi.com.fancycalcy.presenter.iPresenter.IPresenter;
import yuvi.com.fancycalcy.utils.CodeSnippet;
import yuvi.com.fancycalcy.view.iView.IView;

public abstract class BaseActivity extends AppCompatActivity implements IView {

    protected String TAG = getClass().getSimpleName();

    private View mParentView;

    private IPresenter mIPresenter;

    private CodeSnippet mCodeSnippet;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
    }

    protected abstract int getLayoutId();

    @Override
    public void bindPresenter(IPresenter pIPresenter) {
        this.mIPresenter = pIPresenter;
    }

    @Override
    public void showMessage(String pMessage) {
        Toast.makeText(getApplicationContext(), pMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public CodeSnippet getCodeSnippet() {

        if(mCodeSnippet==null)
            mCodeSnippet = new CodeSnippet(this);
        return mCodeSnippet;
    }
}
