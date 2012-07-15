package com.somegood.notes.view.layers;

import android.widget.Button;
import android.widget.ListView;
import com.somegood.notes.R;
import com.somegood.mva.view.ViewLayer;

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
 * Created 7/14/12 6:42 AM
 */
public class FieldNoteListView extends ViewLayer
{

    private ListView listView;
    private Button addFieldNoteButton;

    public Button getAddFieldNoteButton()
    {
        return addFieldNoteButton;
    }

    public void setAddFieldNoteButton(Button addFieldNoteButton)
    {
        this.addFieldNoteButton = addFieldNoteButton;
    }

    public ListView getListView()
    {
        return listView;
    }

    public void setListView(ListView listView)
    {
        this.listView = listView;
    }

    @Override
    public int getViewId()
    {
        return R.layout.fieldnotelist;
    }

    @Override
    public void viewDidRender()
    {
        setListView((ListView)getContext().findViewById(R.id.fieldnotelist));
        setAddFieldNoteButton((Button)getContext().findViewById(R.id.add_field_note));
    }
}
