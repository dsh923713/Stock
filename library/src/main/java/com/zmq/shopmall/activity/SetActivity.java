package com.zmq.shopmall.activity;

import android.view.View;
import android.widget.TextView;

import com.zmq.shopmall.R;
import com.zmq.shopmall.R2;
import com.zmq.shopmall.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/19.
 */

public class SetActivity extends BaseActivity {

    @BindView(R2.id.tv_user_login)
    TextView tvUserLogin;
    @BindView(R2.id.tv_vip_clup)
    TextView tvVipClup;
    @BindView(R2.id.tv_plus_vip)
    TextView tvPlusVip;
    @BindView(R2.id.tv_address_administration)
    TextView tvAddressAdministration;
    @BindView(R2.id.tv_real_name_authentication)
    TextView tvRealNameAuthentication;
    @BindView(R2.id.tv_account_security)
    TextView tvAccountSecurity;
    @BindView(R2.id.tv_connected_account)
    TextView tvConnectedAccount;

    public SetActivity() {
        super(R.layout.activity_set_library);
    }

    @Override
    protected void initView() {
        setTitle("设置");
        setLeftIcon(R.mipmap.ic_back_library, "", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R2.id.tv_user_login, R2.id.tv_vip_clup, R2.id.tv_plus_vip, R2.id.tv_address_administration, R2.id
            .tv_real_name_authentication, R2.id.tv_account_security, R2.id.tv_connected_account})
    void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_user_login) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_vip_clup) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_plus_vip) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_address_administration) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_real_name_authentication) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_account_security) {
            startActivity(LoginActivity.class);

        } else if (i == R.id.tv_connected_account) {
            startActivity(LoginActivity.class);

        } else {
        }
    }
}
