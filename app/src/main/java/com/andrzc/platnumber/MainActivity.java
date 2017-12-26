package com.andrzc.platnumber;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements OnKeyboardChangeListener.OnChangeListener {
    //
    @BindView(R.id.tv_city)
    TextView tv_city;

    @BindView(R.id.edTxt_code1)
    EditText edTxt_code1;

    @BindView(R.id.edTxt_code2)
    EditText edTxt_code2;

    @BindView(R.id.edTxt_code3)
    EditText edTxt_code3;

    @BindView(R.id.edTxt_code4)
    EditText edTxt_code4;

    @BindView(R.id.edTxt_code5)
    EditText edTxt_code5;

    @BindView(R.id.edTxt_code6)
    EditText edTxt_code6;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.btn_done)
    Button btn_done;

    @BindView(R.id.tv_zero)
    TextView tv_zero;

    @BindView(R.id.tv_one)
    TextView tv_one;

    @BindView(R.id.tv_two)
    TextView tv_two;

    @BindView(R.id.tv_three)
    TextView tv_three;

    @BindView(R.id.tv_four)
    TextView tv_four;

    @BindView(R.id.tv_five)
    TextView tv_five;

    @BindView(R.id.tv_six)
    TextView tv_six;

    @BindView(R.id.tv_seven)
    TextView tv_seven;

    @BindView(R.id.tv_eight)
    TextView tv_eight;

    @BindView(R.id.tv_nine)
    TextView tv_nine;

    @BindView(R.id.rv_city)
    RecyclerView rv_city;

    @BindView(R.id.ll_number)
    LinearLayout ll_number;

    @BindView(R.id.ll_carnumber)
    LinearLayout ll_carnumber;

    CityAdapter cityAdapter;
    boolean isAutoNext = true;//会自动跳转到一位

    String strCarNumber = "";//输入的车牌号码

    //车所属地
    String[] citys = new String[]{"京", "津", "沪", "渝", "冀", "晋", "辽", "吉", "黑", "苏", "浙", "皖", "闽", "赣", "鲁", "豫", "鄂", "湘"
            , "粤", "琼", "川", "贵", "云", "陕", "甘", "青", "桂", "蒙", "藏", "宁", "新"};
    List<String> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    OnEditFocusChangeListener onEditFocusChangeListener;

    private void initData() {
        cityList = Arrays.asList(citys);
        cityAdapter = new CityAdapter(MainActivity.this, cityList);
        LinearLayoutManager manager = new GridLayoutManager(MainActivity.this, 9);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_city.setLayoutManager(manager);
        rv_city.setAdapter(cityAdapter);
        cityAdapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                tv_city.setText(cityList.get(pos));
            }
        });

        onEditFocusChangeListener = new OnEditFocusChangeListener();

        edTxt_code1.setOnFocusChangeListener(onEditFocusChangeListener);
        edTxt_code2.setOnFocusChangeListener(onEditFocusChangeListener);
        edTxt_code3.setOnFocusChangeListener(onEditFocusChangeListener);
        edTxt_code4.setOnFocusChangeListener(onEditFocusChangeListener);
        edTxt_code5.setOnFocusChangeListener(onEditFocusChangeListener);
        edTxt_code6.setOnFocusChangeListener(onEditFocusChangeListener);

        edTxt_code1.addTextChangedListener(new OnEditTextChangedListener(edTxt_code1));
        edTxt_code2.addTextChangedListener(new OnEditTextChangedListener(edTxt_code2));
        edTxt_code3.addTextChangedListener(new OnEditTextChangedListener(edTxt_code3));
        edTxt_code4.addTextChangedListener(new OnEditTextChangedListener(edTxt_code4));
        edTxt_code5.addTextChangedListener(new OnEditTextChangedListener(edTxt_code5));
        edTxt_code6.addTextChangedListener(new OnEditTextChangedListener(edTxt_code6));

    }

    int posCurrentEd = 1;

    private class OnEditFocusChangeListener implements View.OnFocusChangeListener {
        @Override
        public void onFocusChange(View view, boolean b) {

            if (b) {
                rv_city.setVisibility(View.GONE);
                switch (view.getId()) {
                    case R.id.edTxt_code1:
                        edTxt_code1.setText("");
                        posCurrentEd = 1;
                        break;

                    case R.id.edTxt_code2:
                        edTxt_code2.setText("");
                        posCurrentEd = 2;
                        break;

                    case R.id.edTxt_code3:
                        edTxt_code3.setText("");
                        posCurrentEd = 3;
                        break;

                    case R.id.edTxt_code4:
                        edTxt_code4.setText("");
                        posCurrentEd = 4;
                        break;

                    case R.id.edTxt_code5:
                        edTxt_code5.setText("");
                        posCurrentEd = 5;
                        break;

                    case R.id.edTxt_code6:
                        edTxt_code6.setText("");
                        posCurrentEd = 6;
                        break;

                    default:
                        break;
                }
            }


        }
    }

    private class OnEditTextChangedListener implements TextWatcher {
        EditText ed;

        OnEditTextChangedListener(EditText editText) {
            this.ed = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!TextUtils.isEmpty(editable.toString()) && editable.toString().length() > 0) {
                String s = editable.toString().substring(0, 1);
//                setTextValue(posCurrentEd, s.toUpperCase());

                ed.removeTextChangedListener(this);
//                    ed.setText(s.toUpperCase());
                setTextValue(posCurrentEd, s.toUpperCase());
                ed.addTextChangedListener(this);

            }

        }
    }


    @OnClick({R.id.btn_cancel, R.id.btn_done, R.id.tv_city, R.id.tv_zero, R.id.tv_one, R.id.tv_two, R.id.tv_three, R.id.tv_four, R.id.tv_five, R.id.tv_six, R.id.tv_seven, R.id.tv_eight, R.id.tv_nine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                finish();
                break;

            case R.id.btn_done:
                String strCity = tv_city.getText().toString();
                String strCode1 = edTxt_code1.getText().toString();
                String strCode2 = edTxt_code2.getText().toString();
                String strCode3 = edTxt_code3.getText().toString();
                String strCode4 = edTxt_code4.getText().toString();
                String strCode5 = edTxt_code5.getText().toString();
                String strCode6 = edTxt_code6.getText().toString();

                if (TextUtils.isEmpty(strCity) || TextUtils.isEmpty(strCode1) || TextUtils.isEmpty(strCode2) ||
                        TextUtils.isEmpty(strCode3) || TextUtils.isEmpty(strCode3) || TextUtils.isEmpty(strCode4) || TextUtils.isEmpty(strCode5) || TextUtils.isEmpty(strCode6)) {
                    showToast(getResources().getString(R.string.input_carnumber_error));
                } else {
                    strCarNumber = strCity + strCode1 + strCode2 + strCode3 + strCode4 + strCode5 + strCode6;
                    showToast(strCarNumber);
                    finish();
                }

                break;

            case R.id.tv_city:
                hideKeyboard();
                rv_city.setVisibility(View.VISIBLE);
                break;

            case R.id.tv_zero:
                setTextValue(posCurrentEd, "0");
                break;

            case R.id.tv_one:
                setTextValue(posCurrentEd, "1");
                break;

            case R.id.tv_two:
                setTextValue(posCurrentEd, "2");
                break;

            case R.id.tv_three:
                setTextValue(posCurrentEd, "3");
                break;

            case R.id.tv_four:
                setTextValue(posCurrentEd, "4");
                break;

            case R.id.tv_five:
                setTextValue(posCurrentEd, "5");
                break;

            case R.id.tv_six:
                setTextValue(posCurrentEd, "6");

                break;

            case R.id.tv_seven:
                setTextValue(posCurrentEd, "7");
                break;

            case R.id.tv_eight:
                setTextValue(posCurrentEd, "8");
                break;

            case R.id.tv_nine:
                setTextValue(posCurrentEd, "9");
                break;

        }
    }


    private void setTextValue(int pos, String data) {
        switch (pos) {
            case 1:
                edTxt_code1.setText(data);
                if (isAutoNext) {
                    edTxt_code2.requestFocus();
                }
                break;
            case 2:
                edTxt_code2.setText(data);
                if (isAutoNext) {
                    edTxt_code3.requestFocus();
                }
                break;
            case 3:
                edTxt_code3.setText(data);
                if (isAutoNext) {
                    edTxt_code4.requestFocus();
                }
                break;
            case 4:
                edTxt_code4.setText(data);
                if (isAutoNext) {
                    edTxt_code5.requestFocus();
                }
                break;
            case 5:
                edTxt_code5.setText(data);
                if (isAutoNext) {
                    edTxt_code6.requestFocus();
                }
                break;
            case 6:
                edTxt_code6.setText(data);
                if (isAutoNext) {
                    isAutoNext = false;
                }
                break;
        }
    }

    //软键盘是否显示
    boolean isSoftWindowOpen = false;


    @Override
    public void onKeyboardShow() {
        isSoftWindowOpen = true;
        ll_number.setVisibility(View.VISIBLE);
//        rv_city.setVisibility(View.GONE);
    }

    @Override
    public void onKeyboardHidden() {
        isSoftWindowOpen = false;
        ll_number.setVisibility(View.GONE);
    }

    private void hideKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
//        ll_number.setVisibility(View.GONE);
    }

    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
