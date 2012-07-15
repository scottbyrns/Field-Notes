package com.somegood.notes.data.source;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.kinvey.KinveyUser;
import com.kinvey.MappedAppdata;
import com.kinvey.persistence.EntityCollection;
import com.kinvey.persistence.EntityDict;
import com.somegood.notes.entities.FieldNotes;
import com.somegood.notes.entities.Note;
import com.kinvey.util.ListCallback;
import com.somegood.notes.entities.FieldNotes;
import com.somegood.notes.entities.Note;

import java.util.ArrayList;
import java.util.Iterator;
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
 * Created 7/14/12 6:48 AM
 */
public class FieldNotesDataSource extends BaseKinveyDataSource<FieldNotes>
{

    public FieldNotesDataSource(Activity context)
    {
        super(context);
    }

    @Override
    protected void loadEntity() throws Throwable
    {
        EntityCollection collection = getKinveyClient().collection("Notes");

        collection.addFilterCriteria("owner",
                                        "==",
                                        getKinveyClient().getCurrentUser().getUsername());
        List<EntityDict> list = collection.fetch();

        FieldNotes fieldNotes = new FieldNotes();
        List<Note> notes = new ArrayList<Note>();

        Iterator<EntityDict> iterator = list.iterator();
        EntityDict dict = null;
        while (iterator.hasNext()) {
            dict = iterator.next();
            Note note = new Note();
            note.setData(dict.getProperty("data").toString());
            note.setId(dict.getProperty("_id").toString());
            try {
                byte[] image = Base64.decode(dict.getProperty("image").toString(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(image, 0, image.length);
                note.setImage(decodedByte);
            }
            catch (Throwable e) {
                // No image?
            }
            notes.add(note);

        }

        fieldNotes.setNotes(notes);
        setEntity(fieldNotes);

    }

    @Override
    protected void saveEntity() throws Throwable
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
