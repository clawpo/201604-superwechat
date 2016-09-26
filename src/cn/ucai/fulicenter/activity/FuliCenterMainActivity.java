package cn.ucai.fulicenter.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import cn.ucai.fulicenter.R;

/**
 * Created by clawpo on 16/8/1.
 */
public class FuliCenterMainActivity extends BaseActivity {
    private final static String TAG = FuliCenterMainActivity.class.getSimpleName();
    RadioButton rbNowGood;
    RadioButton rbBoutique;
    RadioButton rbCategory;
    RadioButton[] mrbTabs;

    NewGoodFragment mNewGoodFragment;
    BoutiqueFragment mBoutiqueFragment;
    CategoryFragment mCategoryFragment;
    Fragment[] mFragment;

    int index;
    int currentIndex;

    public static final int ACTION_LOGIN_PERSONAL = 100;
    public static final int ACTION_LOGIN_CART = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulicetner_main);
        initView();
        initFragment();
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

    private void initFragment() {
        mNewGoodFragment = new NewGoodFragment();
        mBoutiqueFragment = new BoutiqueFragment();
        mCategoryFragment = new CategoryFragment();
        mFragment = new Fragment[3];
        mFragment[0] = mNewGoodFragment;
        mFragment[1] = mBoutiqueFragment;
        mFragment[2] = mCategoryFragment;
    }

    private void initView() {
        rbNowGood = (RadioButton) findViewById(R.id.layout_new_good);
        rbBoutique = (RadioButton) findViewById(R.id.layout_boutique);
        rbCategory = (RadioButton) findViewById(R.id.layout_category);
        mrbTabs = new RadioButton[3];
        mrbTabs[0] = rbNowGood;
        mrbTabs[1] = rbBoutique;
        mrbTabs[2] = rbCategory;

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
    protected void onResume() {
        super.onResume();
        setFragment();
        setRadioButtonStatus(currentIndex);
    }
}
