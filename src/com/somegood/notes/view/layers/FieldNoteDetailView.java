package com.somegood.notes.view.layers;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.somegood.mva.view.ViewLayer;
import com.somegood.notes.R;

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
 * Created 7/14/12 4:23 PM
 */
public class FieldNoteDetailView extends ViewLayer
{

    private ImageView fieldNoteImage;
    private TextView fieldNoteText;
    private Button editButton, deleteButton;

    @Override
    public int getViewId()
    {
        return R.layout.field_note_detail_view;
    }

    @Override
    public void viewDidRender()
    {
        setFieldNoteImage((ImageView)getContext().findViewById(R.id.field_note_image));
        setFieldNoteText((TextView)getContext().findViewById(R.id.field_note));
        setEditButton((Button)getContext().findViewById(R.id.edit_note));
        setDeleteButton((Button)getContext().findViewById(R.id.delete_note));
    }

    public ImageView getFieldNoteImage()
    {
        return fieldNoteImage;
    }

    public void setFieldNoteImage(ImageView fieldNoteImage)
    {
        this.fieldNoteImage = fieldNoteImage;
    }

    public TextView getFieldNoteText()
    {
        return fieldNoteText;
    }

    public void setFieldNoteText(TextView fieldNoteText)
    {
        this.fieldNoteText = fieldNoteText;
    }

    public Button getEditButton()
    {
        return editButton;
    }

    public void setEditButton(Button editButton)
    {
        this.editButton = editButton;
    }

    public Button getDeleteButton()
    {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton)
    {
        this.deleteButton = deleteButton;
    }
}
