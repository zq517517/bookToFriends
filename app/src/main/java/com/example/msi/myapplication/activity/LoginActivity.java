package com.example.msi.myapplication.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msi.myapplication.R;
import com.example.msi.myapplication.http.operation.Operaton;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText mMobileText;
    EditText mPasswordText;
    Button mLoginButton;
    TextView mSignupLink;
    ProgressDialog mProgressDialog;

    String mobile ;
    String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        mLoginButton.setOnClickListener(new LoginOnclick());

        mSignupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }
    private void init()
    {
        mMobileText =(EditText) findViewById(R.id.input_mobile);
        mPasswordText = (EditText) findViewById(R.id.input_password);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mSignupLink = (TextView) findViewById(R.id.link_signup);

        mProgressDialog=new ProgressDialog(LoginActivity.this);
        mProgressDialog.setTitle("登录中");
        mProgressDialog.setMessage("登录中，马上就好");
    }

    private class LoginOnclick implements View.OnClickListener
    {
        public void onClick(View arg0) {

            if (!validate()) {
                onLoginFailed();
                return;
            }

            mProgressDialog.show();

            new Thread(new Runnable() {
                public void run() {
                    Operaton operaton=new Operaton();
                    String result=operaton.login("Login", mobile, password);
                    Message msg=new Message();
                    msg.obj=result;
                    if(result.equals("登录成功")){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    handler.sendMessage(msg);
                }
            }).start();

        }
    }
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String string=(String) msg.obj;
            mProgressDialog.dismiss();
            Toast.makeText(LoginActivity.this, string,Toast.LENGTH_SHORT).show();
            super.handleMessage(msg);
        }
    };




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        mLoginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        mLoginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        mobile = mMobileText.getText().toString().trim();
        password = mPasswordText.getText().toString().trim();

        if (mobile.isEmpty() || mobile.length()!=11) {
            mMobileText.setError("enter a valid mobile");
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
        return valid;
    }
}

