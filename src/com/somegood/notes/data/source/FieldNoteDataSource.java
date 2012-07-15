package com.somegood.notes.data.source;

import android.app.Activity;
import com.somegood.mva.data.BaseDataSource;
import com.somegood.notes.activities.FieldNoteDetailActivity;
import com.somegood.notes.entities.FieldNote;
import com.somegood.notes.entities.FieldNotes;

/**
 * Manage a single field note.
 *
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
 * Created 7/14/12 4:37 PM
 */
public class FieldNoteDataSource extends BaseKinveyDataSource<FieldNote>
{
    public FieldNoteDataSource(Activity context)
    {
        super(context);
    }

    @Override
    protected void loadEntity() throws Throwable
    {
        FieldNote fieldNote = FieldNoteDetailActivity.fieldNote;
        setEntity(fieldNote);
    }

    @Override
    protected void saveEntity() throws Throwable
    {
        if (getEntity().getData().equals("delete-this-note")) {
            getKinveyClient().mappeddata("Notes").delete(getEntity().getId());
        }
    }
}
