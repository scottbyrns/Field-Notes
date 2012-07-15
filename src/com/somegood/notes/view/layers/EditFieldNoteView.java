package com.somegood.notes.view.layers;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
 * Created 7/14/12 10:46 PM
 */
public class EditFieldNoteView extends ViewLayer
{
    private Button   addPhotoButton;
    private Button saveNoteButton;
    private EditText noteTextArea;
    private ImageView fieldNoteImage;

    @Override
    public int getViewId()
    {
        return R.layout.edit_field_note;
    }

    @Override
    public void viewDidRender()
    {
        setSaveNoteButton((Button)getContext().findViewById(R.id.field_note_save));
        setAddPhotoButton((Button)getContext().findViewById(R.id.field_note_add_photo));
        setNoteTextArea((EditText)getContext().findViewById(R.id.field_note_textarea));
        setFieldNoteImage((ImageView)getContext().findViewById(R.id.field_note_image));
    }

    public Button getAddPhotoButton()
    {
        return addPhotoButton;
    }

    public void setAddPhotoButton(Button addPhotoButton)
    {
        this.addPhotoButton = addPhotoButton;
    }

    public Button getSaveNoteButton()
    {
        return saveNoteButton;
    }

    public void setSaveNoteButton(Button saveNoteButton)
    {
        this.saveNoteButton = saveNoteButton;
    }

    public EditText getNoteTextArea()
    {
        return noteTextArea;
    }

    public void setNoteTextArea(EditText noteTextArea)
    {
        this.noteTextArea = noteTextArea;
    }

    public ImageView getFieldNoteImage()
    {
        return fieldNoteImage;
    }

    public void setFieldNoteImage(ImageView fieldNoteImage)
    {
        this.fieldNoteImage = fieldNoteImage;
    }
}
