package com.somegood.notes.data.source;

import android.app.Activity;
import android.content.SharedPreferences;
import com.kinvey.KinveyUser;
import com.somegood.notes.entities.UserAuthentication;

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
 * Created 7/13/12 3:53 PM
 */
public class KinveyUsers extends BaseKinveyDataSource<KinveyUser>
{

    public KinveyUsers(Activity context)
    {
        super(context);
    }

    private SharedPreferences sharedPreferences;
    private UserAuthentication userAuthentication;

    public SharedPreferences getSharedPreferences()
    {
        return sharedPreferences;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences)
    {
        this.sharedPreferences = sharedPreferences;
    }

    public UserAuthentication getUserAuthentication()
    {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthentication userAuthentication)
    {
        this.userAuthentication = userAuthentication;
    }

    @Override
    protected void beforeEntityLoad()
    {

    }

    @Override
    protected void loadEntity()
    {

        setEntity(
                getKinveyClient().loginWithUsername("scott", "starnut")
                 );
    }

    @Override
    protected void saveEntity()
    {

    }
}
