package com.somegood.notes.data.source;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.somegood.notes.activities.FieldNoteDetailActivity;
import com.somegood.notes.entities.FieldNote;
import com.somegood.notes.entities.Note;

import java.io.ByteArrayOutputStream;

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
 * Created 7/14/12 10:45 PM
 */
public class EditFieldNoteDataSource extends BaseKinveyDataSource<FieldNote>
{

    public EditFieldNoteDataSource(Activity context)
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
        if (null == getEntity().getId()) {
            throw new Exception("Can not edit FieldNote with out an ID");
        }

        FieldNoteDetailActivity.fieldNote = getEntity();

        Note note = new Note();
        note.setData(getEntity().getData());
        note.setId(getEntity().getId());

        Bitmap bm = getEntity().getImage();


        int h = bm.getHeight(); // height in pixels
        int w = bm.getWidth(); // width in pixels
//        if (h > 300) {
            double ratio = h / w;
            h = 300;
            w = (int)(ratio * 300);
//        }
        bm = Bitmap.createScaledBitmap(bm, h, w, true);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String image = Base64.encodeToString(b,
                                             Base64.DEFAULT);
        note.setEncodedImage(image);

        note.setOwner(getKinveyClient().getCurrentUser().getUsername());

        getKinveyClient().mappeddata("Notes").save(note);
    }
}
