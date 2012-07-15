package com.somegood.notes.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import com.somegood.mva.ModelViewActivity;
import com.somegood.mva.data.DataCallback;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.entities.UserAuthentication;
import com.somegood.mva.reflection.ModelViewConfiguration;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.layers.LoginView;

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
 * Created 7/13/12 6:52 PM
 */
@ModelViewConfiguration(
        dataSource = DataSources.CREATE_KINVEY_USER,
        view = ViewLayers.LOGIN)
public class LoginActivity extends ModelViewActivity
{

    @Override
    public void dataDidLoad()
    {
        if (!getUserAuthentication().getUsername().equals("null"))
        {
            getView().getUsername().setText(getUserAuthentication().getUsername());
        }

        if (!getUserAuthentication().getPassword().equals("null"))
        {
            getView().getPassword().setText(getUserAuthentication().getPassword());
        }
    }

    @Override
    public void dataDidFailToLoad()
    {
    }

    @Override
    public void dataDidSave()
    {
    }

    @Override
    public void dataDidFailToSave()
    {
    }

    @Override
    public void viewDidLoad()
    {

        final LoginActivity activity = this;

        getView().getSignUpButton().setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                if (getView().getUsername().getText().toString().length() < 1 && getView().getPassword().getText().toString().length() < 1) {
                    showAlert(activity,
                              "Hint",
                              "Enter your desired username and password above.");
                }
                else if (getView().getUsername().getText().toString().length() < 1) {
                    showAlert(activity,
                              "Registration Error",
                              "Please provide a username for your account.");
                }
                else if (getView().getPassword().getText().toString().length() < 1) {
                    showAlert(activity,
                              "Registration Error",
                              "Please provide a password for your account.");
                }

                if (getView().getUsername().getText().length() > 0 && getView().getPassword().getText().length() > 0)
                {
                    getUserAuthentication().setUsername(getView().getUsername().getText().toString());
                    getUserAuthentication().setPassword(getView().getPassword().getText().toString());
                    getUserAuthentication().setNew(true);

                    getDataSource().setDataSaveSuccessCallback(new DataCallback()
                    {
                        public void execute()
                        {

                            Intent startNewActivityOpen = new Intent(LoginActivity.this,
                                                                     FieldNoteListActivity.class);
                            startActivityForResult(startNewActivityOpen,
                                                   0);
                        }
                    });

                    getDataSource().setDataSaveFailureCallback(new DataCallback()
                    {
                        public void execute()
                        {
                                showAlert(activity,
                                          "Registration Error",
                                          "The username you selected is already in use. Please select another username for your account.");

                        }
                    });



                    getDataSource().save();
                }
            }
        });



        getView().getLoginButton().setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View view)
            {

                if (getView().getUsername().getText().toString().length() < 1 && getView().getPassword().getText().toString().length() < 1) {
                    showAlert(activity,
                              "Hint",
                              "Enter your username and password above.");
                }
                else if (getView().getUsername().getText().toString().length() < 1) {
                    showAlert(activity,
                              "Registration Error",
                              "Please provide your username to sign in.");
                }
                else if (getView().getPassword().getText().toString().length() < 1) {
                    showAlert(activity,
                              "Registration Error",
                              "Please provide your password to sign in.");
                }

                if (getView().getUsername().getText().length() > 0 && getView().getPassword().getText().length() > 0)
                {
                    getUserAuthentication().setUsername(getView().getUsername().getText().toString());
                    getUserAuthentication().setPassword(getView().getPassword().getText().toString());


                    getDataSource().setDataSaveSuccessCallback(new DataCallback()
                    {
                        public void execute()
                        {
                            Intent startNewActivityOpen = new Intent(LoginActivity.this,
                                                                     FieldNoteListActivity.class);
                            startActivityForResult(startNewActivityOpen,
                                                   0);
                        }
                    });

                    getDataSource().setDataSaveFailureCallback(new DataCallback()
                    {
                        public void execute()
                        {
                            showAlert(activity,
                                      "Login Error",
                                      "Invalid login. Try again.");
                        }
                    });

                    getDataSource().save();


                }
            }
        });
    }

    private LoginView getView()
    {
        return (LoginView) getViewLayer();
    }

    private UserAuthentication getUserAuthentication()
    {
        return ((UserAuthentication) getDataSource().getEntity());
    }
}
