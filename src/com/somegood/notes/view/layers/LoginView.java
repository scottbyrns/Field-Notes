package com.somegood.notes.view.layers;

import android.widget.Button;
import android.widget.EditText;

import com.somegood.mva.view.ViewLayer;
import com.somegood.notes.R;

/**
 * Copyright (C) 2012 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 7/13/12 7:59 PM
 */
public class LoginView extends ViewLayer
{

    private Button signUpButton;
    private Button loginButton;
    private EditText username, password;


    @Override
    public int getViewId()
    {
        return R.layout.login;
    }

    @Override
    public void viewDidRender()
    {
        setSignUpButton((Button) getContext().findViewById(R.id.loginscreen_signupButton));
        setLoginButton((Button) getContext().findViewById(R.id.loginscreen_signinButton));
        setUsername((EditText)getContext().findViewById(R.id.loginscreen_usernameEditText));
        setPassword((EditText)getContext().findViewById(R.id.loginscreen_passwordEditText));
    }

    public Button getSignUpButton()
    {
        return signUpButton;
    }

    public void setSignUpButton(Button signUpButton)
    {
        this.signUpButton = signUpButton;
    }

    public Button getLoginButton()
    {
        return loginButton;
    }

    public void setLoginButton(Button loginButton)
    {
        this.loginButton = loginButton;
    }

    public EditText getUsername()
    {
        return username;
    }

    public void setUsername(EditText username)
    {
        this.username = username;
    }

    public EditText getPassword()
    {
        return password;
    }

    public void setPassword(EditText password)
    {
        this.password = password;
    }
}
