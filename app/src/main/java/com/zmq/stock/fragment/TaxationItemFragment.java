package com.zmq.stock.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zmq.stock.R;
import com.zmq.stock.base.BaseFragment;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 报单
 * Created by Administrator on 2017/6/28.
 */

public class TaxationItemFragment extends BaseFragment {
    private static TaxationItemFragment taxationItemFragment;
    @BindView(R.id.et_stock_code)
    EditText etStockCode;//股票代码
    @BindView(R.id.tv_taxation_search)
    TextView tvTaxationSearch;//查询
    @BindView(R.id.tv_stock_name)
    TextView tvStockName; //股票名称
    @BindView(R.id.ib_subtract)
    ImageButton ibSubtract; //减
    @BindView(R.id.et_price)
    EditText etPrice; //股票金额
    @BindView(R.id.ib_add)
    ImageButton ibAdd; //加
    @BindView(R.id.cb_one_tenth)
    CheckBox cbOneTenth; //1/10
    @BindView(R.id.cb_one_fifth)
    CheckBox cbOneFifth; //1/5
    @BindView(R.id.cb_one_four)
    CheckBox cbOneFour; //1/4
    @BindView(R.id.cb_one_third)
    CheckBox cbOneThird; //1/3
    @BindView(R.id.cb_one_half)
    CheckBox cbOneHalf; //1/2
    @BindView(R.id.cb_hundred_percentage)
    CheckBox cbHundredPercentage; //100%
    @BindView(R.id.tv_sure)
    TextView tvSure; //确定


    private boolean HasValue; //是否选择了仓位
    private String shipSpace;//仓位数值
    private float price = 1; //股票价格
    private static final int HASVALUE = 1001;
    private MyHandler myHandler = new MyHandler(this);

    /**
     * 单例模式
     *
     * @return
     */
    public static TaxationItemFragment getInstance() {
        if (taxationItemFragment == null) {
            taxationItemFragment = new TaxationItemFragment();
        }
        return taxationItemFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item_taxation, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        tvSure.setClickable(false);
        tvTaxationSearch.setClickable(false);
        setEtTextChange();
    }

    /**
     * 设置editview的文本长度监听
     */
    private void setEtTextChange() {
        etStockCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    tvTaxationSearch.setClickable(true);
                    tvTaxationSearch.setTextColor(ContextCompat.getColor(activity, R.color.red));
                    tvTaxationSearch.setBackgroundResource(R.drawable.taxation_line_red_shape);
                } else {
                    tvTaxationSearch.setClickable(false);
                    tvTaxationSearch.setTextColor(ContextCompat.getColor(activity, R.color.grey));
                    tvTaxationSearch.setBackgroundResource(R.drawable.taxation_line_grey_shape);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 1){
                    etPrice.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.tv_taxation_search, R.id.ib_subtract, R.id.ib_add, R.id.cb_one_tenth, R.id.cb_one_fifth, R.id.cb_one_four, R
            .id.cb_one_third, R.id.cb_one_half, R.id.cb_hundred_percentage, R.id.tv_sure})
    void onClick(View v) {
        price = Float.parseFloat(etPrice.getText().toString());
        switch (v.getId()) {
            case R.id.tv_taxation_search: //查询
                showShortToast(etStockCode.getText().toString());
                break;
            case R.id.ib_subtract: //减
                if (price < 0.01) {
                    price = 0;
                } else {
                    price -= 0.01;
                }
                etPrice.setText(String.format("%.2f", price) + "");
                etPrice.setSelection(etPrice.length());
                break;
            case R.id.ib_add: //加
                price += 0.01;
                etPrice.setText(String.format("%.2f", price) + "");
                etPrice.setSelection(etPrice.length());
                break;
            case R.id.cb_one_tenth: //十分之一
                cbOneFifth.setChecked(false);
                cbOneFour.setChecked(false);
                cbOneThird.setChecked(false);
                cbOneHalf.setChecked(false);
                cbHundredPercentage.setChecked(false);
                break;
            case R.id.cb_one_fifth: //五分之一
                cbOneTenth.setChecked(false);
                cbOneFour.setChecked(false);
                cbOneThird.setChecked(false);
                cbOneHalf.setChecked(false);
                cbHundredPercentage.setChecked(false);
                break;
            case R.id.cb_one_four: //四分之一
                cbOneTenth.setChecked(false);
                cbOneFifth.setChecked(false);
                cbOneThird.setChecked(false);
                cbOneHalf.setChecked(false);
                cbHundredPercentage.setChecked(false);
                break;
            case R.id.cb_one_third: //三分之一
                cbOneTenth.setChecked(false);
                cbOneFifth.setChecked(false);
                cbOneFour.setChecked(false);
                cbOneHalf.setChecked(false);
                cbHundredPercentage.setChecked(false);
                break;
            case R.id.cb_one_half: //二分之一
                cbOneTenth.setChecked(false);
                cbOneFifth.setChecked(false);
                cbOneFour.setChecked(false);
                cbOneThird.setChecked(false);
                cbHundredPercentage.setChecked(false);
                break;
            case R.id.cb_hundred_percentage: //百分百
                cbOneTenth.setChecked(false);
                cbOneFifth.setChecked(false);
                cbOneFour.setChecked(false);
                cbOneThird.setChecked(false);
                cbOneHalf.setChecked(false);
                break;
            case R.id.tv_sure: //确定
                showShortToast(shipSpace +"---"+ price);
                break;
            default:
                break;
        }
        //发送消息队列
        myHandler.sendEmptyMessage(HASVALUE);
    }

    /**
     * 是否获取到值
     */
    public void setShipSpace() {
        if (cbOneTenth.isChecked()) {
            HasValue = true;
            shipSpace = cbOneTenth.getText().toString();
            return;
        } else if (cbOneFifth.isChecked()) {
            HasValue = true;
            shipSpace = cbOneFifth.getText().toString();
            return;
        } else if (cbOneFour.isChecked()) {
            HasValue = true;
            shipSpace = cbOneFour.getText().toString();
            return;
        } else if (cbOneThird.isChecked()) {
            HasValue = true;
            shipSpace = cbOneThird.getText().toString();
            return;
        } else if (cbOneHalf.isChecked()) {
            HasValue = true;
            shipSpace = cbOneHalf.getText().toString();
            return;
        } else if (cbHundredPercentage.isChecked()) {
            HasValue = true;
            shipSpace = cbHundredPercentage.getText().toString();
            return;
        } else {
            HasValue = false;
            shipSpace = "";
        }
    }

    /**
     * 是否已选择仓位
     */
    public void setHasValue() {
        if (HasValue) {
            tvSure.setClickable(true);
            tvSure.setBackgroundResource(R.drawable.taxation_red);
        } else {
            tvSure.setClickable(false);
            tvSure.setBackgroundResource(R.drawable.taxation_grey);
        }
    }

    /**
     * handler 软引用  防止内存泄漏
     */
    class MyHandler extends Handler {
        private final WeakReference<TaxationItemFragment> mFragment;

        public MyHandler(TaxationItemFragment fragment) {
            mFragment = new WeakReference<TaxationItemFragment>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mFragment.get() == null) {
                return;
            }
            if (msg.what == HASVALUE) {
                mFragment.get().setShipSpace();
                mFragment.get().setHasValue();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        myHandler.removeCallbacksAndMessages(null);//移除handler中的消息队列
    }
}

