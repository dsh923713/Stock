package com.zmq.stock.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.zmq.stock.R;
import com.zmq.stock.base.BaseActivity;
import com.zmq.stock.fragment.DirectPlayFragment;
import com.zmq.stock.fragment.InquireFragment;
import com.zmq.stock.widget.CustomPopWindow;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/3.
 */

public class PersonInformationActivity extends BaseActivity {

    @BindView(R.id.riv_head)
    RoundedImageView rivHead;//头像
    @BindView(R.id.tv_name)
    TextView tvName; //昵称
    @BindView(R.id.tv_remark)
    TextView tvRemark; //个人签名
    @BindView(R.id.tv_hot)
    TextView tvHot; //热度
    @BindView(R.id.tv_fans)
    TextView tvFans;//粉丝
    @BindView(R.id.tv_buy)
    TextView tvBuy; //购买
    @BindView(R.id.rb_play)
    RadioButton rbPlay; //直播
    @BindView(R.id.rb_inquire)
    RadioButton rbInquire; //问答
    private DirectPlayFragment directPlayFragment;
    private InquireFragment inquireFragment;

    public PersonInformationActivity() {
        super(R.layout.activity_person_information);
    }

    @Override
    protected void initView() {
        setLeftIcon(R.mipmap.ic_back, "返回", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setRightIcon(R.mipmap.ic_more, "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPopupWindow();
            }
        });
        directPlayFragment = new DirectPlayFragment();
        inquireFragment = new InquireFragment();
        replaceFragment(R.id.fl_content, DirectPlayFragment.getInstance());//默认显示直播
    }

    @OnClick({R.id.tv_follow, R.id.rb_play, R.id.rb_inquire})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_follow:
                showShortToast("关注");
                break;
            case R.id.rb_play:
                rbPlay.setTextColor(ContextCompat.getColor(this, R.color.blue));
                rbPlay.setCompoundDrawables(null, null, null, getDrawable());
                rbInquire.setCompoundDrawables(null, null, null, null);
                rbInquire.setTextColor(ContextCompat.getColor(this, R.color.gary));
                replaceFragment(R.id.fl_content, DirectPlayFragment.getInstance());
                break;
            case R.id.rb_inquire:
                rbInquire.setTextColor(ContextCompat.getColor(this, R.color.blue));
                rbInquire.setCompoundDrawables(null, null, null, getDrawable());
                rbPlay.setCompoundDrawables(null, null, null, null);
                rbPlay.setTextColor(ContextCompat.getColor(this, R.color.gary));
                replaceFragment(R.id.fl_content, inquireFragment);
                break;
            default:
                break;
        }
    }

    private Drawable getDrawable() {
        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_line);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    private CustomPopWindow mCustomPopWindow;//顶部更多弹窗

    private void setPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_person_information_menu, null);
        //处理popWindow 显示内容及点击事件
        handleLogic(view);
        //创建并显示popupwindow
        mCustomPopWindow = new CustomPopWindow.PopupWindowBuilder(this).setView(view).create().showAsDropDown(tv_right, 0, 20);
    }

    /**
     * 处理弹出显示内容、点击事件等逻辑
     *
     * @param contentView
     */
    private void handleLogic(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCustomPopWindow != null) {
                    mCustomPopWindow.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.tv_shape: //分享
                        showShortToast("分享");
                        break;
                    case R.id.tv_price: //
                        showShortToast("价格");
                        break;
                    case R.id.tv_search: //搜索
                        showShortToast("搜索");
                        break;
                    default:
                        break;
                }
            }
        };
        contentView.findViewById(R.id.tv_shape).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_price).setOnClickListener(listener);
        contentView.findViewById(R.id.tv_search).setOnClickListener(listener);
    }
}
