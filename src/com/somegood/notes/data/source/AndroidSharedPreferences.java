package com.somegood.notes.data.source;

import android.app.Activity;
import android.content.SharedPreferences;
import com.somegood.mva.data.AndroidPersistence;

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
 * Created 7/13/12 7:41 PM
 */
public class AndroidSharedPreferences extends AndroidPersistence<SharedPreferences>
{

    public AndroidSharedPreferences(Activity context)
    {
        super(context);
    }

    @Override
    protected void loadEntity() throws Throwable
    {
        SharedPreferences preferences = getContext().getSharedPreferences("PREFERENCES", 0);

        setEntity(preferences);
    }

    @Override
    protected void saveEntity() throws Throwable
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
