package com.somegood.notes.adapters;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.somegood.notes.R;
import com.somegood.notes.activities.FieldNoteListActivity;
import com.somegood.notes.entities.FieldNotes;
import com.somegood.notes.entities.Note;

import java.util.List;

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
 * Created 7/14/12 7:00 AM
 */
public class FieldNoteAdapter implements ListAdapter
{

    private FieldNotes fieldNotes;
    private Activity context;

    public Activity getContext()
    {
        return context;
    }

    public void setContext(Activity context)
    {
        this.context = context;
    }

    public FieldNotes getFieldNotes()
    {
        return fieldNotes;
    }

    public void setFieldNotes(FieldNotes fieldNotes)
    {
        this.fieldNotes = fieldNotes;
    }

    public boolean areAllItemsEnabled()
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEnabled(int i)
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getCount()
    {
        if (isEmpty()) {
            return 0;
        }
        return getFieldNotes().getNotes().size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getItem(int i)
    {
        if (isEmpty()) {
            return null;
        }
        return getFieldNotes().getNotes().get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }

    public long getItemId(int i)
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasStableIds()
    {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = getContext().getLayoutInflater().inflate(R.layout.fieldnoteitem, null);
        ImageView imageView = (ImageView)view.findViewById(R.id.userimage);

        TextView itemDetail = (TextView)view.findViewById(R.id.field_note);
        itemDetail.setText(
                ((Note)getItem(i)).getData()
                          );
        if (null != ((Note)getItem(i)).getImage()) {

            imageView.setImageBitmap(
                    ((Note)getItem(i)).getImage()
                                    );
        }

        view.setOnClickListener((FieldNoteListActivity)getContext());
        view.setId(i);
        return view;
    }

    public int getItemViewType(int i)
    {
        return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getViewTypeCount()
    {
        return 1;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isEmpty()
    {
        if (null == getFieldNotes()) {
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
