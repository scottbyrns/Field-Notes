package com.somegood.notes.data.source;

import android.app.Activity;
import android.content.SharedPreferences;
import com.kinvey.KinveyUser;
import com.somegood.mva.data.DataSourceFactory;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.entities.UserAuthentication;
import com.kinvey.util.KinveyCallback;
import com.somegood.notes.data.DataSources;

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
 * Created 7/13/12 7:39 PM
 */
public class LoginDataSource extends BaseKinveyDataSource<UserAuthentication>
{

    public LoginDataSource(Activity context)
    {
        super(context);
    }

    private AndroidSharedPreferences preferences;

    @Override
    protected void loadEntity() throws Throwable
    {

        preferences = (AndroidSharedPreferences) DataSourceFactory.produce(DataSources.ANDROID_SHARED_PREFS,
                                                                           getContext());
        preferences.loadEntity();
        String username = preferences.getEntity().getString("username",
                                                            "null");
        String password = preferences.getEntity().getString("password",
                                                            "null");

        setEntity(new UserAuthentication(username,
                                         password));
    }


    @Override
    protected void saveEntity() throws Throwable
    {
        if (getEntity().isNew())
        {
            getKinveyClient().userWithUsername(getEntity().getUsername(),
                                               getEntity().getPassword());
        }

        KinveyUser kinveyUser = getKinveyClient().loginWithUsername(getEntity().getUsername(),
                                                                    getEntity().getPassword());

        getKinveyClient().getCurrentUser().setAttribute("password", getEntity().getPassword());

        //If the credentials were wrong and the login failed
        //kinveyUser.isCurrentUser();
        //returns true despite not being true.

        if (null == getKinveyClient().getCurrentUser())
        {
            throw new Exception("Invalid user.");
        }
        else {
            SharedPreferences.Editor editor = preferences.getEntity().edit();
            editor.putString("username",
                             getEntity().getUsername());
            editor.putString("password",
                             getEntity().getPassword());
            editor.commit();
        }
    }
}
