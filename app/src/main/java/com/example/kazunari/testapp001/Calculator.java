package com.example.kazunari.testapp001;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by Kazunari on 2016/02/23.
 */
public class Calculator {
    // 入力中の文字列
    private StringBuilder mInputNumber = new StringBuilder();
    // 入力中の演算子
    private String mOperator;
    // 計算結果
    private double mResult = 0;

    public Calculator() {
        reset();
    };

    private boolean isNumber(String key) {
       try {
            Double.parseDouble(key);
            return true;
        } catch(NumberFormatException e) {
           if(key.equals(".")) {
               return true;
           }
        }
        return false;
    }

    private boolean isSupportedOperator(String key) {
        boolean ret = false;

        switch (key) {
            case "＋":
            case "－":
            case "×":
            case "÷":
            case "＝":
            case "%":
            case "+/-":
                ret = true;
                break;
            default:
                break;
        }

        return ret;
    }

    private void doCalculation(String ope) {
        if(mInputNumber.length() == 0) {
            return;
        }

        BigDecimal tmp_num1 = new BigDecimal(String.valueOf(mResult));
        BigDecimal tmp_num2 = new BigDecimal(mInputNumber.toString());

        if(ope.equals("＋")) {
            mResult = tmp_num1.add(tmp_num2).doubleValue();
        }
        else if(ope.equals("－")) {
            mResult = tmp_num1.subtract(tmp_num2).doubleValue();
        }
        else if(ope.equals("×")) {
            mResult = tmp_num1.multiply(tmp_num2).doubleValue();
        }
        else if(ope.equals("÷")) {
            try {
                // 小数第8位で四捨五入
                mResult = tmp_num1.divide(tmp_num2,7,BigDecimal.ROUND_HALF_UP).doubleValue();
            } catch (ArithmeticException e) {
                ;
            }
        }
        else {
            ;
        }

        mInputNumber.setLength(0);
    }

    public void reset() {
        mOperator = null;
        mResult = 0;
        mInputNumber.setLength(0);
    }

    public String putInput(String key) {
        String ret = null;

        // 数値の場合
        if (isNumber(key)) {
            // 入力桁数チェック
            if(mInputNumber.length() < 9) {
                // 入力中の値が0の場合、小数点しか入力を受け付けない
                if (mInputNumber.toString().equals("0") && !key.equals(".")) {
                    ;
                } else {
                    // 入力値が小数点の場合
                    if (key.equals(".")) {
                        // 入力中の値に小数点が含まれている場合、小数点の入力を受け付けない
                        if (mInputNumber.indexOf(".") != -1) {
                            ;
                        }
                        // 未入力の場合
                        else if (mInputNumber.length() == 0) {
                            mInputNumber.append("0" + key);
                            ret = mInputNumber.toString();
                        } else {
                            // 次の入力を待つ
                            mInputNumber.append(key);
                            ret = mInputNumber.toString();
                        }
                    }
                    // 入力値が小数点以外の場合
                    else {
                        // 次の入力を待つ
                        mInputNumber.append(key);
                        ret = mInputNumber.toString();
                    }

                }
            }
        }
        // サポートしている演算子の場合、入力中の数値を基に演算を行う
        else if (isSupportedOperator(key)) {
            // =なら演算を行い結果を返却する
            if (key.equals("＝")) {
                // 演算子入力済みの場合
                if (mOperator != null) {
                    // 計算を行う
                    doCalculation(mOperator);
                    mOperator = null;
                    ret = adjustResut(mResult);
                }
            }
            else if (key.equals("+/-")) {
                // 計算結果が0以外の場合
                if(mResult != 0) {
                    //　計算結果の符号を反転する
                    mResult = -mResult;
                    ret = adjustResut(mResult);
                }
                // 入力値がある場合
                else if(mInputNumber.length() > 0) {
                    // 入力値の符号を反転する
                    Double tmp = Double.parseDouble(mInputNumber.toString());
                    tmp = -tmp;
                    mInputNumber.setLength(0);
                    mInputNumber.append(tmp);
                    ret = adjustResut(tmp);
                }
                else {
                    ;
                }
            }
            else if(key.equals("%")) {
                ret = calcParsent();
            }
            else {
                // 演算子入力済みの場合
                if (mOperator != null) {
                    doCalculation(mOperator);
                    mOperator = null;
                }
                // 入力値が存在する場合
                else if (mInputNumber.length() > 0) {
                    mResult = Double.parseDouble(mInputNumber.toString());
                    mInputNumber.setLength(0);
                }
                else {
                    ;
                }
                mOperator = key;
                ret =  mOperator;
            }
        }

        return ret;
    }

    // 計算結果のフォーマットを整える
    private String adjustResut(double inputNum) {
        DecimalFormat format = new DecimalFormat("0.########");
        return format.format(inputNum);
    }

    // %ボタン押下時の計算
    private String calcParsent() {
        String ret = null;
        BigDecimal tmp_num2 = new BigDecimal(0.01);
        BigDecimal tmp_num3 = new BigDecimal(mResult);

        try {
            // 百分率の計算
            BigDecimal tmp_num1 = new BigDecimal(mInputNumber.toString());

            try {
                // 加算の場合
                if (mOperator.equals("＋")) {
                    tmp_num1 = tmp_num1.multiply(tmp_num2);
                    mResult = tmp_num3.multiply(tmp_num1).add(tmp_num3).doubleValue();
                }
                // 減算の場合
                else if (mOperator.equals("－")) {
                    tmp_num1 = tmp_num1.multiply(tmp_num2);
                    mResult = tmp_num3.subtract(tmp_num3.multiply(tmp_num1)).doubleValue();
                }
                //　乗算の場合
                else if (mOperator.equals("×") || mOperator.equals("÷")) {
                    mResult = tmp_num3.multiply(tmp_num1.multiply(tmp_num2)).doubleValue();
                }
                // 除算の場合
                else if (mOperator.equals("÷")) {
                    try {
                        // 小数第8位で四捨五入
                        mResult = tmp_num3.divide(tmp_num1.multiply(tmp_num2), 7, BigDecimal.ROUND_HALF_UP).doubleValue();
                    } catch (ArithmeticException e) {
                        ;
                    }
                }
                else {
                    ;
                }
            }
            // 演算子未入力の場合
            catch(NullPointerException e) {
                mResult = tmp_num1.multiply(tmp_num2).doubleValue();
            }
        }
        // 入力値なしの場合
        catch (NumberFormatException e) {
            mResult = tmp_num3.multiply(tmp_num2).doubleValue();
        }

        mInputNumber.setLength(0);
        ret = adjustResut(mResult);
        mOperator = null;

        return ret;
    }
}
