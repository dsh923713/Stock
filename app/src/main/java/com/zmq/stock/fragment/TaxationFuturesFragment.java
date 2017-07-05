package com.zmq.stock.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.zmq.stock.R;
import com.zmq.stock.adapter.TaxationFuturesAdapter;
import com.zmq.stock.base.BaseFragment;
import com.zmq.stock.bean.TaxationFuturesBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 期货
 * Created by Administrator on 2017/6/22.
 */

public class TaxationFuturesFragment extends BaseFragment {
    private static TaxationFuturesFragment taxationFuturesFragment;

    @BindView(R.id.rv_futures)
    RecyclerView rvFutures; //期货列表
    @BindView(R.id.et_futures_name)
    EditText etFuturesName; //期货名称
    @BindView(R.id.tv_futures_search)
    TextView tvFuturesSearch; //查询
    @BindView(R.id.ib_subtract)
    ImageButton ibSubtract; //减
    @BindView(R.id.et_price)
    EditText etPrice; //价格
    @BindView(R.id.ib_add)
    ImageButton ibAdd; //加
    @BindView(R.id.rg_direction)
    RadioGroup rgDirection;
    @BindView(R.id.rb_more)
    RadioButton rbMore; //做多
    @BindView(R.id.rb_empty)
    RadioButton rbEmpty; //做空
    @BindView(R.id.cb_one_tenth)
    CheckBox cbOneTenth; //1/10
    @BindView(R.id.cb_one_fifth)
    CheckBox cbOneFifth;//1/5
    @BindView(R.id.cb_one_four)
    CheckBox cbOneFour;//1/4
    @BindView(R.id.cb_one_third)
    CheckBox cbOneThird;//1/3
    @BindView(R.id.cb_one_half)
    CheckBox cbOneHalf;//1/2
    @BindView(R.id.cb_hundred_percentage)
    CheckBox cbHundredPercentage; //100%
    @BindView(R.id.tv_sure)
    TextView tvSure; //确定


    private MyHandler myHandler = new MyHandler(this);
    private boolean HasValue; //是否选择了仓位
    private String shipSpace;//仓位数值
    private String direction;
    private float price = 1; //股票价格
    private static final int HASVALUE = 1001;

    private List<TaxationFuturesBean> mData;

    /**
     * 单例模式
     *
     * @return
     */
    public static TaxationFuturesFragment getInstance() {
        if (taxationFuturesFragment == null) {
            taxationFuturesFragment = new TaxationFuturesFragment();
        }
        return taxationFuturesFragment;
    }

    @Override
    protected View initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_taxation_futures, container, false);
        return view;
    }

    @Override
    protected void initView(View view) {
        rgDirection.check(rbMore.getId());
        direction = rbMore.getText().toString();
        setmData();
        rvFutures.setLayoutManager(new LinearLayoutManager(activity));
        rvFutures.setAdapter(new TaxationFuturesAdapter(mData));
        setEtFuturesTextChange();
    }

    /**
     * 监听期货名称文字长度变化来判断查询是否可点击 默认不可点击
     */
    private void setEtFuturesTextChange() {
        tvFuturesSearch.setClickable(false);
        etFuturesName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    tvFuturesSearch.setClickable(true);
                    tvFuturesSearch.setTextColor(ContextCompat.getColor(activity, R.color.red));
                    tvFuturesSearch.setBackgroundResource(R.drawable.taxation_line_red_shape);
                } else {
                    tvFuturesSearch.setClickable(false);
                    tvFuturesSearch.setTextColor(ContextCompat.getColor(activity, R.color.grey));
                    tvFuturesSearch.setBackgroundResource(R.drawable.taxation_line_grey_shape);
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
                if (s.length() < 1) {
                    etPrice.setText("0.00");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 模拟数据
     */
    private void setmData() {
        mData = new ArrayList<>();
        mData.add(new TaxationFuturesBean("白糖1472", 6524l, -32l, 68514l));
        mData.add(new TaxationFuturesBean("花生糖4421", 1234l, -58l, 68514l));
        mData.add(new TaxationFuturesBean("石油167", 52674l, 322l, 12344l));
        mData.add(new TaxationFuturesBean("天然气851", 5824l, 77l, 68214l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
        mData.add(new TaxationFuturesBean("煤矿433", 63434l, -92l, 45114l));
    }

    /**
     * 绑定点击事件
     *
     * @param v
     */
    @OnClick({R.id.tv_futures_search, R.id.ib_subtract, R.id.ib_add, R.id.rb_more, R.id.rb_empty, R.id.cb_one_tenth, R.id
            .cb_one_fifth, R.id.cb_one_four, R.id.cb_one_third, R.id.cb_one_half, R.id.cb_hundred_percentage, R.id.tv_sure})
    void onClick(View v) {
        price = Float.parseFloat(etPrice.getText().toString());
        switch (v.getId()) {
            case R.id.tv_futures_search: //查询
                showShortToast(etFuturesName.getText().toString());
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
            case R.id.rb_more: //做多
                direction = rbMore.getText().toString();
                break;
            case R.id.rb_empty: //做空
                direction = rbEmpty.getText().toString();
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
                showShortToast(direction + "---" + shipSpace +"---"+price);
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
        private final WeakReference<TaxationFuturesFragment> mFragment;

        public MyHandler(TaxationFuturesFragment fragment) {
            mFragment = new WeakReference<TaxationFuturesFragment>(fragment);
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
