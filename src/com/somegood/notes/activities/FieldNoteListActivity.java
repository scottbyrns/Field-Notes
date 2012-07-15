package com.somegood.notes.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.somegood.mva.ModelViewActivity;

import com.somegood.notes.adapters.FieldNoteAdapter;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.data.source.FieldNotesDataSource;
import com.somegood.notes.entities.FieldNote;
import com.somegood.notes.entities.FieldNotes;
import com.somegood.mva.reflection.ModelViewConfiguration;
import com.somegood.notes.entities.Note;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.layers.FieldNoteListView;

import java.io.File;
import java.io.IOException;

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
 * Created 7/13/12 8:53 PM
 */
@ModelViewConfiguration(
        dataSource = DataSources.FIELD_NOTES_LIST,
        view = ViewLayers.FIELD_NOTES_LIST)
public class FieldNoteListActivity extends ModelViewActivity implements AdapterView.OnItemClickListener, View.OnClickListener
{

    private FieldNoteAdapter adapter;

    private FieldNoteListView getFieldNoteListView () {
        return ((FieldNoteListView) getViewLayer());
    }

    @Override
    public void dataDidLoad()
    {
        adapter = new FieldNoteAdapter();
        adapter.setFieldNotes((FieldNotes) getDataSource().getEntity());
        adapter.setContext(this);

        getFieldNoteListView().getListView().setAdapter(adapter);

        getFieldNoteListView().getAddFieldNoteButton().setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent addIntent = new Intent(FieldNoteListActivity.this, AddFieldNoteActivity.class);
                startActivity(addIntent);
//                showUploadImageOptions();
            }
        });

        getFieldNoteListView().getListView().setItemsCanFocus(true);
//        getFieldNoteListView().getListView().setOnItemClickListener(this);

    }

    @Override
    public void dataDidFailToLoad()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dataDidSave()
    {

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

    @Override
    protected void onResume()
    {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.

        getDataSource().load();
        getFieldNoteListView().getListView().invalidateViews();
    }

    public void onClick(View view)
    {

        Note note = ((FieldNotesDataSource) getDataSource()).getEntity().getNotes().get(view.getId());
        FieldNote fieldNote = new FieldNote();
        fieldNote.setData(note.getData());
        fieldNote.setId(note.getId());
        fieldNote.setImage(note.getImage());


        Intent itemIntent = new Intent(this, FieldNoteDetailActivity.class);
        FieldNoteDetailActivity.fieldNote = fieldNote;

        startActivity(itemIntent);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        Note note = ((FieldNotesDataSource) getDataSource()).getEntity().getNotes().get(i);
        FieldNote fieldNote = new FieldNote();
        fieldNote.setData(note.getData());
        fieldNote.setId(note.getId());
        fieldNote.setImage(note.getImage());


        Intent itemIntent = new Intent(FieldNoteListActivity.this, FieldNoteDetailActivity.class);

        itemIntent.putExtra("field_note",
                           fieldNote);
        startActivity(itemIntent);
    }
}
