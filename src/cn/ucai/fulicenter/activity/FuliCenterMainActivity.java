package cn.ucai.fulicenter.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import cn.ucai.fulicenter.DemoHXSDKHelper;
import cn.ucai.fulicenter.R;
import cn.ucai.fulicenter.utils.Utils;

/**
 * Created by clawpo on 16/8/1.
 */
public class FuliCenterMainActivity extends BaseActivity {
    private final static String TAG = FuliCenterMainActivity.class.getSimpleName();
    RadioButton rbNowGood;
    RadioButton rbBoutique;
    RadioButton rbCategory;
    RadioButton rbCart;
    RadioButton rbPersonalCenter;
    TextView tvCartHint;
    RadioButton[] mrbTabs;

    NewGoodFragment mNewGoodFragment;
    BoutiqueFragment mBoutiqueFragment;
    CategoryFragment mCategoryFragment;
    PersonalCenterFragment mPersonalCenterFragment;
    CartFragment mCartFragment;
    Fragment[] mFragment;

    int index;
    int currentIndex;

    public static final int ACTION_LOGIN_PERSONAL = 100;
    public static final int ACTION_LOGIN_CART = 200;

    updateCartNumReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulicetner_main);
        initView();
        initFragment();
        setListener();
        // 添加显示第一个fragment
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, mNewGoodFragment)
                .add(R.id.fragment_container,mBoutiqueFragment)
                .add(R.id.fragment_container,mCategoryFragment)
                .hide(mBoutiqueFragment).hide(mCategoryFragment)
                .show(mNewGoodFragment)
                .commit();
    }

    private void setListener() {
        setUpdateCartCountListener();
    }

    private void initFragment() {
        mNewGoodFragment = new NewGoodFragment();
        mBoutiqueFragment = new BoutiqueFragment();
        mCategoryFragment = new CategoryFragment();
        mCartFragment = new CartFragment();
        mPersonalCenterFragment = new PersonalCenterFragment();
        mFragment = new Fragment[5];
        mFragment[0] = mNewGoodFragment;
        mFragment[1] = mBoutiqueFragment;
        mFragment[2] = mCategoryFragment;
        mFragment[3] = mCartFragment;
        mFragment[4] = mPersonalCenterFragment;
    }

    private void initView() {
        rbNowGood = (RadioButton) findViewById(R.id.layout_new_good);
        rbBoutique = (RadioButton) findViewById(R.id.layout_boutique);
        rbCategory = (RadioButton) findViewById(R.id.layout_category);
        rbCart = (RadioButton) findViewById(R.id.layout_cart);
        rbPersonalCenter = (RadioButton) findViewById(R.id.layout_personal_center);
        tvCartHint = (TextView) findViewById(R.id.tvCartHint);
        mrbTabs = new RadioButton[5];
        mrbTabs[0] = rbNowGood;
        mrbTabs[1] = rbBoutique;
        mrbTabs[2] = rbCategory;
        mrbTabs[3] = rbCart;
        mrbTabs[4] = rbPersonalCenter;

    }

    public void onCheckedChange(View view){
        switch (view.getId()){
            case R.id.layout_new_good:
                index = 0;
                break;
            case R.id.layout_boutique:
                index = 1;
                break;
            case R.id.layout_category:
                index = 2;
                break;
            case R.id.layout_cart:
                if(DemoHXSDKHelper.getInstance().isLogined()) {
                    index = 3;
                }else{
                    gotoLogin(ACTION_LOGIN_CART);
                }
                break;
            case R.id.layout_personal_center:
                if(DemoHXSDKHelper.getInstance().isLogined()) {
                    index = 4;
                }else{
                    gotoLogin(ACTION_LOGIN_PERSONAL);
                }
                break;
        }
        Log.e(TAG,"index="+index+",currentIndex="+currentIndex);
        setFragment();
    }

    private void setFragment(){
        Log.e(TAG,"setFragment,index="+index+",currentIndex="+currentIndex);
        if(index!=currentIndex){
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(mFragment[currentIndex]);
            if (!mFragment[index].isAdded()) {
                trx.add(R.id.fragment_container, mFragment[index]);
            }
            trx.show(mFragment[index]).commit();
            setRadioButtonStatus(index);
            currentIndex = index;
        }
    }

    private void gotoLogin(int action) {
        startActivityForResult(new Intent(this,LoginActivity.class),action);
    }

    private void setRadioButtonStatus(int index) {
        for(int i=0;i<mrbTabs.length;i++){
            if(index==i){
                mrbTabs[i].setChecked(true);
            }else{
                mrbTabs[i].setChecked(false);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG,"onActivityResult");
        if(DemoHXSDKHelper.getInstance().isLogined()) {
            if(requestCode==ACTION_LOGIN_PERSONAL){
                index = 4;
            }
            if(requestCode==ACTION_LOGIN_CART){
                index = 3;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
        if(!DemoHXSDKHelper.getInstance().isLogined() && index == 4) {
            index = 0;
        }
        setFragment();
        setRadioButtonStatus(currentIndex);
        updateCartNum();
    }

    class updateCartNumReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            updateCartNum();
        }
    }

    private void setUpdateCartCountListener(){
        mReceiver = new updateCartNumReceiver();
        IntentFilter filter = new IntentFilter("update_cart_list");
        filter.addAction("update_user");
        registerReceiver(mReceiver,filter);
    }

    private void updateCartNum() {
        int count = Utils.sumCartCount();
        Log.e(TAG,"count="+count);
        if(!DemoHXSDKHelper.getInstance().isLogined() || count ==0){
            tvCartHint.setText(String.valueOf(0));
            tvCartHint.setVisibility(View.GONE);
        }else{
            tvCartHint.setText(String.valueOf(count));
            tvCartHint.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mReceiver!=null){
            unregisterReceiver(mReceiver);
        }
    }
}
