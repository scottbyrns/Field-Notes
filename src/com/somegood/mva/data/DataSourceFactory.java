package com.somegood.mva.data;

import android.app.Activity;
import com.somegood.mva.data.DataSource;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.data.source.*;

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
 * Created 7/13/12 6:54 PM
 */
public class DataSourceFactory
{

    /**
     * Produce a data source.
     *
     * @param dataSources The make of the data source to produce.
     * @return The data source of the specified make.
     */
    public static DataSource produce (DataSources dataSources, Activity context) {
        switch (dataSources) {
            case CREATE_KINVEY_USER:
                return new LoginDataSource(context);
            case KINVEY_USERS:
                return new KinveyUsers(context);
            case ANDROID_SHARED_PREFS:
                return new AndroidSharedPreferences(context);
            case FIELD_NOTES_LIST:
                return new FieldNotesDataSource(context);
            case FIELD_NOTE_DETAIL:
                return new FieldNoteDataSource(context);
            case ADD_FIELD_NOTE:
                return new AddFieldNoteDataSource(context);
            case EDIT_FIELD_NOTE:
                return new EditFieldNoteDataSource(context);
        }

        return null;
    }
}
