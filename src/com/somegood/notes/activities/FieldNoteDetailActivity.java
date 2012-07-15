package com.somegood.notes.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.somegood.mva.ModelViewActivity;
import com.somegood.mva.reflection.ModelViewConfiguration;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.data.source.FieldNoteDataSource;
import com.somegood.notes.entities.FieldNote;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.layers.FieldNoteDetailView;

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
 * Created 7/14/12 4:27 PM
 */

@ModelViewConfiguration(
        dataSource = DataSources.FIELD_NOTE_DETAIL,
        view = ViewLayers.FIELD_NOTE_DETAILS)
public class FieldNoteDetailActivity extends ModelViewActivity implements View.OnClickListener
{

    public static FieldNote fieldNote;

    private FieldNoteDataSource getFieldNoteDataSource()
    {
        return (FieldNoteDataSource) getDataSource();
    }

    private FieldNoteDetailView getFieldNoteDetailView()
    {
        return (FieldNoteDetailView) getViewLayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getFieldNoteDataSource().load();
    }

    @Override
    public void dataDidLoad()
    {
        getFieldNoteDetailView().getFieldNoteText().setText(getFieldNoteDataSource().getEntity().getData());

        if (null != getFieldNoteDataSource().getEntity().getImage())
        {
            getFieldNoteDetailView().getFieldNoteImage().setImageBitmap(getFieldNoteDataSource().getEntity().getImage());
        }

        getFieldNoteDetailView().getEditButton().setOnClickListener(this);
        getFieldNoteDetailView().getDeleteButton().setOnClickListener(this);
    }

    public void onClick(View view)
    {
        if (view.equals(getFieldNoteDetailView().getEditButton())) {
            Intent startNewActivityOpen = new Intent(FieldNoteDetailActivity.this,
                                                     EditFieldNoteActivity.class);
            startActivityForResult(startNewActivityOpen,
                                   0);
        }
        else if (view.equals(getFieldNoteDetailView().getDeleteButton())) {
            getFieldNoteDataSource().getEntity().setData("delete-this-note");
            save();
        }
    }

    @Override
    public void dataDidFailToLoad()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dataDidSave()
    {
        finish();
    }

    @Override
    public void dataDidFailToSave()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void viewDidLoad()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
