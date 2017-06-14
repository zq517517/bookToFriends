package com.example.msi.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnFocusChangeListener;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.entity.User;
import com.example.msi.myapplication.http.operation.Operaton;
import com.example.msi.myapplication.json.WriteJson;

import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText mNameText;
    EditText mMobileText;
    EditText mPasswordText;
    EditText mReEnterPasswordText;
    Button mSignupButton;
    TextView mLoginLink;

    String name;
    // String email = mMobileText.getText().toString();
    String mobile ;
    String password ;
    String reEnterPassword;
    String jsonString=null;

    ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        // mMobileText.setOnFocusChangeListener(new EtusernameOnFocusChange());
        mSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        mLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    private void init(){
        mNameText = (EditText) findViewById(R.id.input_name);
        mMobileText = (EditText) findViewById(R.id.input_mobile);
        mPasswordText = (EditText) findViewById(R.id.input_password);
        mReEnterPasswordText = (EditText) findViewById(R.id.input_reEnterPassword);
        mSignupButton = (Button) findViewById(R.id.btn_signup);
        mLoginLink = (TextView) findViewById(R.id.link_login);

        mProgressDialog=new ProgressDialog(SignupActivity.this);
        mProgressDialog.setTitle("上传数据中");
        mProgressDialog.setMessage("请稍等...");
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

       // mSignupButton.setEnabled(false);

        mProgressDialog.show();
        new Thread(new Runnable() {
            public void run() {
                Operaton operaton=new Operaton();
                User user=new User();
                user.setMobile(mobile);
                user.setNickname(name);
                user.setPassword(password);
                //构造一个user对象
                List<User> list=new ArrayList<User>();
                list.add(user);
                WriteJson writeJson=new WriteJson();
                //将user对象写出json形式字符串
                jsonString= writeJson.getJsonData(list);
                System.out.println(jsonString);
                String result= operaton.UpData("Register", jsonString);
                Message msg=new Message();
                System.out.println("result---->"+result);
                msg.obj=result;
                handler1.sendMessage(msg);
            }
        }).start();
    }
    Handler handler1=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            mProgressDialog.dismiss();
            String msgobj=msg.obj.toString();
            if(msgobj.equals("t"))
            {
                Toast.makeText(SignupActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.setClass(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(SignupActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
            super.handleMessage(msg);
        }
    };

    public void onSignupSuccess() {
        mSignupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        mSignupButton.setEnabled(true);
    }

    private class EtusernameOnFocusChange implements OnFocusChangeListener
    {
        public void onFocusChange(View v, boolean hasFocus) {
            if (!mMobileText.hasFocus()) {
                mobile =mMobileText.getText().toString().trim();
                if (!mobile.isEmpty() && mobile.length()!=11) {
                    new Thread(new Runnable() {
                        //如果用户名不为空，那么将用户名提交到服务器上进行验证，看用户名是否存在，就像JavaEE中利用
                        //ajax一样，虽然你看不到但是它却偷偷摸摸做了很多
                        public void run() {
                            Operaton operaton=new Operaton();
                            String result= operaton.checkusername("Check", mobile);
                            System.out.println("result:"+result);
                            Message message=new Message();
                            message.obj=result;
                            handler.sendMessage(message);
                        }
                    }).start();
                }
            }
        }
    }
    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            String msgobj=msg.obj.toString();
            System.out.println(msgobj);
            System.out.println(msgobj.length());

            if (msgobj.equals("t")) {
                mMobileText.requestFocus();
                mMobileText.setError("用户名"+mobile+"已存在");
            } else {
                mMobileText.requestFocus();
            }
            super.handleMessage(msg);
        }
    };

    public boolean validate() {
        boolean valid = true;

        name = mNameText.getText().toString().trim();
//      email = mMobileText.getText().toString().trim();
        mobile = mMobileText.getText().toString().trim();
        password = mPasswordText.getText().toString().trim();
        reEnterPassword = mReEnterPasswordText.getText().toString().trim();

        if (name.isEmpty() || name.length() < 3) {
            mNameText.setError("at least 3 characters");
            valid = false;
        } else {
            mNameText.setError(null);
        }

//        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            mMobileText.setError("enter a valid email address");
//            valid = false;
//        } else {
//            mMobileText.setError(null);
//        }

        if (mobile.isEmpty() || mobile.length()!=11) {
            mMobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            mMobileText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mPasswordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            mPasswordText.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            mReEnterPasswordText.setError("Password Do not match");
            valid = false;
        } else {
            mReEnterPasswordText.setError(null);
        }
        return valid;
    }
}
