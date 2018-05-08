package yuvi.com.fancycalcy.presenter;

import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import yuvi.com.fancycalcy.R;
import yuvi.com.fancycalcy.presenter.iPresenter.IMainActivityPresenter;
import yuvi.com.fancycalcy.utils.Log;
import yuvi.com.fancycalcy.view.iView.IMainActivityView;

import static yuvi.com.fancycalcy.utils.Constants.actionTags.ADD;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.BACKSPACE;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.CLEAR_ALL;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.DIVIDE;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.DOT;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.EQUALS;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.MULTIPLY;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.PERCENTAGE;
import static yuvi.com.fancycalcy.utils.Constants.actionTags.SUBRACT;

public class MainActivityPresenter extends BasePresenter implements IMainActivityPresenter {

    private IMainActivityView mIMainActivityView;

    private String mData = "";
    private StringBuffer mDataBuffer = new StringBuffer();
    private boolean equalsIsPressed = false;


    public MainActivityPresenter(IMainActivityView iView) {
        super(iView);
        this.mIMainActivityView = iView;
    }

    @Override
    public void onCreatePresenter(Bundle pBundle) {

    }

    // handles all the user input events
    @Override
    public void handleActionEvent(String pAction) {


        if (pAction.equals(BACKSPACE)) {
            if (mData.length() > 0) {

                if (mDataBuffer.length() > 0) {
                    Log.d(TAG, "char at last ==" + mDataBuffer.charAt(mDataBuffer.length() - 1));
                    String temp = mDataBuffer.toString();
                    mDataBuffer = new StringBuffer();
                    mDataBuffer.append(temp.substring(0, temp.length() - 1));
                }

                if (mData.equalsIgnoreCase("error") || mData.equalsIgnoreCase("0.")) {
                    mData = "";
                } else {
                    mData = mData.substring(0, mData.length() - 1);
                }
            }
        } else if (pAction.equals(CLEAR_ALL)) {
            mData = "";
            mDataBuffer = new StringBuffer();
            Log.d(TAG, "clear all buffer = = " + mDataBuffer.toString());

        } else if (pAction.equals(SUBRACT)) {
            if (validate(SUBRACT) && mData.length() > 0) {
                mDataBuffer = new StringBuffer();
                mData += getString(R.string.string_subraction);
            }

        } else if (pAction.equals(EQUALS)) {
            if (mData.length() > 0) {
                String lastchar = mData.charAt(mData.length() - 1) + "";
                Log.d(TAG, "lastChar==" + lastchar.equals(R.string.string_modulus));
                if ((validateData(lastchar) ||
                        lastchar.equals(getString(R.string.string_modulus)))
                        && !lastchar.equals(getString(R.string.string_dot))) {
                    Log.d(TAG, "final data = " + mData);
                    mData = solveEquation(mData);
                    mDataBuffer = new StringBuffer();
                    mIMainActivityView.setDisplayMessage(mData);
                    equalsIsPressed = true;
                }
            }
        } else if (mData.length() < 20) {
            switch (pAction) {

                case ADD:
                    if (validate(ADD) && mData.length() > 0) {
                        mData += getString(R.string.string_addition);
                        mDataBuffer = new StringBuffer();
                    }
                    break;
                case MULTIPLY:
                    if (validate(MULTIPLY) && mData.length() > 0) {
                        mDataBuffer = new StringBuffer();
                        mData += getString(R.string.string_multiply);
                    }
                    break;
                case DIVIDE:
                    if (validate(DIVIDE) && mData.length() > 0) {
                        mDataBuffer = new StringBuffer();
                        mData += getString(R.string.string_divide);
                    }
                    break;
                case PERCENTAGE: {
                    String temp = mDataBuffer.toString();
                    if (!temp.isEmpty() &&
                            !temp.contains(getString(R.string.string_modulus)) && validate(PERCENTAGE) && mData.length() > 0) {
                        Log.d(TAG, "buffer==" + mDataBuffer);
                        mDataBuffer.append(getString(R.string.string_modulus));
                        mData += getString(R.string.string_modulus);
                        mDataBuffer = new StringBuffer();
                    }
                }
                break;
                case DOT:
                    String temp = mDataBuffer.toString();

                    if (!temp.contains(getString(R.string.string_dot))) {

                        boolean dotRestrictionFlag = true;
                        if (equalsIsPressed) {
                            temp = mDataBuffer.append(mData).toString();
                            equalsIsPressed = false;
                            if (!validateForOperatorOccurance(mData) && mData.contains(getString(R.string.string_dot))) {
                                dotRestrictionFlag = false;
                            }
                        }
                        if (temp.length() == 0) {
                            mData += "0";
                        }
                        if (dotRestrictionFlag) {
                            mData += getString(R.string.string_dot);
                            mDataBuffer.append(getString(R.string.string_dot));
                        }
                    }
                    break;
                default:


                    if (equalsIsPressed && !validateForOperatorOccurance(mData) && !mDataBuffer.toString().contains(getString(R.string.string_dot))) {
                        mData = "";
                        equalsIsPressed = false;
                    }


                    mData += pAction;
                    mDataBuffer.append(pAction);
                    break;
            }
        }

        mIMainActivityView.setDisplayMessage(mData);
    }


    //validates the data contains *,%,/,+ and -
    private boolean validateForOperatorOccurance(String mData) {

        return (mData.contains(getString(R.string.string_modulus)) ||
                mData.contains(getString(R.string.string_divide)) ||
                mData.contains(getString(R.string.string_multiply)) ||
                mData.contains(getString(R.string.string_subraction)) ||
                mData.contains(getString(R.string.string_addition))) ? true : false;

    }


    // validates the input with operator eg: 2+5 is correct but 2+x is wrong
    private boolean validate(String pOpertator) {


        if (mData.length() > 0 && mData.charAt(mData.length() - 1) != pOpertator.charAt(0)) {

            String temp = mData.charAt(mData.length() - 1) + "";


            if (temp.equals(getString(R.string.string_modulus))) {
                return true;
            }
            if (temp.equals(getString(R.string.string_dot))) {
                return false;
            }
            if (temp.equals(getString(R.string.string_addition))) {
                return false;
            }
            if (temp.equals(getString(R.string.string_subraction))) {
                return false;
            }
            if (temp.equals(getString(R.string.string_multiply))) {
                return false;
            }
            if (temp.equals(getString(R.string.string_divide))) {
                return false;
            }

        }
        return true;

    }


    // solves the equation when equals is triggered
    private String solveEquation(String pData) {

        String resultTemp = "";
        if (validateEquation(pData)) {
            final List<String> dataList = organizeDataIntoList(pData);
            dataList.forEach((v) -> Log.d(TAG, "DataList == " + v + "\n"));

            divideAndConquer(PERCENTAGE, dataList);
            dataList.forEach((v) -> Log.d(TAG, "% solving == " + v + "\n"));

            divideAndConquer(MULTIPLY, dataList);
            dataList.forEach((v) -> Log.d(TAG, "X solving == " + v + "\n"));


            divideAndConquer(DIVIDE, dataList);
            dataList.forEach((v) -> Log.d(TAG, "/ solving == " + v + "\n"));

            divideAndConquer(ADD, dataList);
            dataList.forEach((v) -> Log.d(TAG, "+ solving == " + v + "\n"));

            divideAndConquer(SUBRACT, dataList);
            dataList.forEach((v) -> Log.d(TAG, "- solving == " + v + "\n"));


            try {
                if (dataList.size() == 1) {
                    //showMessage(dataList.get(0));
                    resultTemp = roundOffData(dataList.get(0));
                }
            } catch (Exception e) {
                e.printStackTrace();
                resultTemp = "Error";
            }
        } else {
            resultTemp = "Error";
        }


        return resultTemp;
    }

    private boolean validateEquation(String pData) {

        boolean checkFlag = true;
        for (int i = 0; i < pData.length(); i++) {

            String temp = pData.charAt(i) + "";
            if (temp.charAt(0) >= '0' && temp.charAt(0) <= '9' || validateForOperatorOccurance(temp) || temp.equals(getString(R.string.string_dot))) {
                checkFlag = true;
            } else {
                checkFlag = false;
                break;
            }
        }

        return checkFlag;
    }


    //final round of data if the result has no decimal data
    private String roundOffData(String pData) {

        if (pData.contains("E") || pData.contains("e")) {
            return pData;
        }

        Double temp = Double.parseDouble(pData);
        int tempInt = temp.intValue();
        Double decimalLessDouble = Double.parseDouble(String.valueOf(tempInt));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(5);

        Log.d(TAG, "difference==" + (temp - decimalLessDouble));

        if (Math.abs(temp - decimalLessDouble) > 0) {
            return df.format(temp);
        } else {
            return tempInt + "";
        }

        //return ((temp - decimalLessDouble) > 0) ? df.format(temp) + "" : tempInt + "";
    }


    // organises the user data which is a string into a list for easier calculation
    private List<String> organizeDataIntoList(String pData) {


        char[] mDataArray = pData.toCharArray();
        final List<String> dataList = new ArrayList<>();


        try {

            StringBuffer tempBuffer = new StringBuffer();
            for (int i = 0; i < mDataArray.length; i++) {

                String temp = mDataArray[i] + "";


                if (temp.equals(getString(R.string.string_addition)) ||
                        temp.equals(getString(R.string.string_subraction)) ||
                        temp.equals(getString(R.string.string_multiply)) ||
                        temp.equals(getString(R.string.string_divide)) ||
                        (temp.equals(getString(R.string.string_modulus)))) {

                    if (!tempBuffer.toString().isEmpty())
                        dataList.add(tempBuffer.toString());
                    dataList.add(temp);
                    tempBuffer = new StringBuffer();
                } else if (i == mDataArray.length - 1) {
                    if (temp.equals(getString(R.string.string_modulus))) {
                        dataList.add(temp);
                    } else {
                        tempBuffer.append(temp);
                        dataList.add(tempBuffer.toString());
                    }
                } else {
                    tempBuffer.append(temp);
                }


            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return dataList;
    }


    /* to execute the operations in the equation one by one modulus, division, multiplication, subraction and addition
    and the final result will be stored in the zeroth position of the list
    */
    private List<String> divideAndConquer(String pParam, List<String> pDataList) {


        String operatorString = "";
        switch (pParam) {
            case PERCENTAGE:
                operatorString = getString(R.string.string_modulus);
                break;
            case DIVIDE:
                operatorString = getString(R.string.string_divide);
                break;
            case MULTIPLY:
                operatorString = getString(R.string.string_multiply);
                break;
            case SUBRACT:
                operatorString = getString(R.string.string_subraction);
                break;
            case ADD:
                operatorString = getString(R.string.string_addition);
                break;
        }

        try {
            for (int i = 0; i < pDataList.size(); i++) {


                String currentData = pDataList.get(i);


                if (i > 0) {
                    if (pParam.equals(PERCENTAGE) &&
                            (currentData.equals(getString(R.string.string_modulus)))) {
                        double data = 0;
                        data = Double.parseDouble(pDataList.get(i - 1)) / 100;
                        pDataList.set(i - 1, data + "");
                        if (i < pDataList.size() - 1 && (validateData(pDataList.get(i + 1)))) {
                            pDataList.set(i, getString(R.string.string_multiply));
                        } else {
                            pDataList.remove(i);
                        }
                    } else {
                        int continueLooping = 0;
                        for (int j = 0; j < pDataList.size(); j++) {
                            if (pDataList.get(i).equals(operatorString)) {
                                continueLooping++;
                            }
                        }
                        if (i > 0 && i < pDataList.size()) {
                            if (validateData(pDataList.get(i - 1)) && validateData(pDataList.get(i + 1))) {
                                double data = 0;


                                if (currentData.equals(getString(R.string.string_multiply))) {
                                    data = Double.parseDouble((pDataList.get(i - 1))) * Double.parseDouble(pDataList.get(i + 1));

                                } else if (currentData.equals(getString(R.string.string_divide))) {
                                    data = Double.parseDouble(pDataList.get(i - 1)) / Double.parseDouble(pDataList.get(i + 1));

                                } else if (currentData.equals(getString(R.string.string_addition)) ||
                                        currentData.equals(getString(R.string.string_subraction))) {
                                    if (currentData.equals(getString(R.string.string_addition))) {
                                        data = Double.parseDouble(pDataList.get(i - 1)) + Double.parseDouble(pDataList.get(i + 1));
                                    } else if (currentData.equals(getString(R.string.string_subraction))) {
                                        data = Double.parseDouble(pDataList.get(i - 1)) - Double.parseDouble(pDataList.get(i + 1));
                                    }
                                }

                                pDataList.set(i - 1, data + "");
                                pDataList.remove(i + 1);
                                pDataList.remove(i);
                                i = 0;
                                if (continueLooping == 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return pDataList;
    }

    //validates the data is operator or operand
    private boolean validateData(String pData) {

        if (pData.equals(getString(R.string.string_modulus))) {
            return false;
        } else if (pData.equals(getString(R.string.string_divide))) {
            return false;
        } else if (pData.equals(getString(R.string.string_multiply))) {
            return false;
        } else if (pData.equals(getString(R.string.string_subraction))) {
            return false;
        } else if (pData.equals(getString(R.string.string_addition))) {
            return false;
        }

        return true;
    }
}
