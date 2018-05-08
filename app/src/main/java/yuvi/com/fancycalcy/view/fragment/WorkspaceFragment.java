package yuvi.com.fancycalcy.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import yuvi.com.fancycalcy.R;
import yuvi.com.fancycalcy.presenter.WorkspaceFragmentPresenter;
import yuvi.com.fancycalcy.presenter.iPresenter.IWorkspaceFragmentPresenter;
import yuvi.com.fancycalcy.view.iView.IMainActivityView;
import yuvi.com.fancycalcy.view.iView.IWorkspaceView;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.*;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.ADD;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.BACKSPACE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.CLEAR_ALL;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.DIVIDE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.DOT;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.EIGHT;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.EQUALS;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.FIVE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.FOUR;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.MULTIPLY;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.NINE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.ONE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.PERCENTAGE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.SEVEN;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.SIX;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.SUBRACT;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.THREE;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.TWO;
//import static yuvi.com.fancycalcy.utils.Constants.actionTags.ZERO;

public class WorkspaceFragment extends BaseFragment implements IWorkspaceView, View.OnClickListener {


    private IWorkspaceFragmentPresenter mIWorkspaceFragmentPresenter;

    private IMainActivityView mIMainActivityView;

    private Button mBtClearAll,
            mBtPercentage,
            mBtDivide,
            mBtBackSpace,
            mBtSeven,
            mBtEight,
            mBtNine,
            mBtMultiply,
            mBtFour,
            mBtFive,
            mBtSix,
            mBtSubract,
            mBtOne,
            mBtTwo,
            mBtThree,
            mBtZero,
            mBtDot,
            mBtEquals,
            mBtAdd;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIWorkspaceFragmentPresenter = new WorkspaceFragmentPresenter(this);
        mIWorkspaceFragmentPresenter.onCreatePresenter(getArguments());

        mBtClearAll =  view.findViewById(R.id.bt_clear_all);
        mBtPercentage = (Button) view.findViewById(R.id.bt_percentage);
        mBtDivide = (Button) view.findViewById(R.id.bt_divide);
        mBtBackSpace = (Button) view.findViewById(R.id.bt_backspace);
        mBtSeven = (Button) view.findViewById(R.id.bt_seven);
        mBtEight = (Button) view.findViewById(R.id.bt_eight);
        mBtNine = (Button) view.findViewById(R.id.bt_nine);
        mBtMultiply = (Button) view.findViewById(R.id.bt_multiply);
        mBtFour = (Button) view.findViewById(R.id.bt_four);
        mBtFive = (Button) view.findViewById(R.id.bt_five);
        mBtSix = (Button) view.findViewById(R.id.bt_six);
        mBtSubract = (Button) view.findViewById(R.id.bt_subract);
        mBtOne = (Button) view.findViewById(R.id.bt_one);
        mBtTwo = (Button) view.findViewById(R.id.bt_two);
        mBtThree = (Button) view.findViewById(R.id.bt_three);
        mBtZero = (Button) view.findViewById(R.id.bt_zero);
        mBtDot = (Button) view.findViewById(R.id.bt_dot);
        mBtEquals = (Button) view.findViewById(R.id.bt_equals);
        mBtAdd = (Button) view.findViewById(R.id.bt_add);

        mBtClearAll.setOnClickListener(this);
        mBtPercentage.setOnClickListener(this);
        mBtDivide.setOnClickListener(this);
        mBtBackSpace.setOnClickListener(this);
        mBtSeven.setOnClickListener(this);
        mBtEight.setOnClickListener(this);
        mBtNine.setOnClickListener(this);
        mBtMultiply.setOnClickListener(this);
        mBtFour.setOnClickListener(this);
        mBtFive.setOnClickListener(this);
        mBtSix.setOnClickListener(this);
        mBtSubract.setOnClickListener(this);
        mBtOne.setOnClickListener(this);
        mBtTwo.setOnClickListener(this);
        mBtThree.setOnClickListener(this);
        mBtZero.setOnClickListener(this);
        mBtDot.setOnClickListener(this);
        mBtEquals.setOnClickListener(this);
        mBtAdd.setOnClickListener(this);


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_work_space;
    }


    @Override
    public void onClick(View pView) {

        String action = "";


        switch (pView.getId()) {


            case R.id.bt_clear_all:
                action = CLEAR_ALL;
                break;
            case R.id.bt_percentage:
                action = PERCENTAGE;
                break;
            case R.id.bt_divide:
                action = DIVIDE;
                break;
            case R.id.bt_backspace:
                action = BACKSPACE;
                break;


            case R.id.bt_seven:
                action = SEVEN;
                break;
            case R.id.bt_eight:
                action = EIGHT;
                break;
            case R.id.bt_nine:
                action = NINE;
                break;
            case R.id.bt_multiply:
                action = MULTIPLY;
                break;

            case R.id.bt_four:
                action = FOUR;
                break;
            case R.id.bt_five:
                action = FIVE;
                break;
            case R.id.bt_six:
                action = SIX;
                break;
            case R.id.bt_subract:
                action = SUBRACT;
                break;


            case R.id.bt_one:
                action = ONE;
                break;
            case R.id.bt_two:
                action = TWO;
                break;
            case R.id.bt_three:
                action = THREE;
                break;
            case R.id.bt_add:
                action = ADD;
                break;

            case R.id.bt_zero:
                action = ZERO;
                break;
            case R.id.bt_dot:
                action = DOT;
                break;
            case R.id.bt_equals:
                action = EQUALS;
                break;


        }

        ((IMainActivityView) getActivity()).sendActionEvent(action);

    }


}
