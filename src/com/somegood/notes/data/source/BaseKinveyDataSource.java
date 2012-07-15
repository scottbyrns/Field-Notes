package com.somegood.notes.data.source;

import android.app.Activity;
import com.kinvey.KCSClient;
import com.somegood.mva.data.BaseDataSource;

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
 * Created 7/13/12 4:19 PM
 */
public abstract class BaseKinveyDataSource<Entity> extends BaseDataSource<Entity>
{

    protected BaseKinveyDataSource(Activity context)
    {
        super(context);
    }

    private static KCSClient kinveyClient;

    public static KCSClient getKinveyClient()
    {
        return kinveyClient;
    }

    public static void setKinveyClient(KCSClient kinveyClient)
    {
        BaseKinveyDataSource.kinveyClient = kinveyClient;
    }
}
