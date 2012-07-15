package com.somegood.notes.data.source;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.kinvey.KCSClient;
import com.kinvey.KinveyUser;
import com.kinvey.exception.KinveyException;
import com.kinvey.persistence.mapping.MappedEntity;
import com.kinvey.util.ScalarCallback;
import com.somegood.mva.data.DataSourceFactory;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.entities.FieldNote;
import com.somegood.notes.entities.Note;

import java.io.ByteArrayOutputStream;
import java.security.KeyException;

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
 * Created 7/14/12 6:19 PM
 */
public class AddFieldNoteDataSource extends BaseKinveyDataSource<FieldNote>
{
    public AddFieldNoteDataSource(Activity context)
    {
        super(context);
    }

    @Override
    protected void loadEntity() throws Throwable
    {
        setEntity(new FieldNote());

    }

    @Override
    protected void saveEntity() throws Throwable
    {


        Note note = new Note();
        note.setData(getEntity().getData());
        note.setImage(getEntity().getImage());

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
        String image = Base64.encodeToString(b, Base64.DEFAULT);
        note.setEncodedImage(image);

        setKinveyClient(KCSClient.getInstance(getContext(), getKinveyClient().settings()));

        note.setOwner(getKinveyClient().getCurrentUser().getUsername());

        try {
            AndroidSharedPreferences preferences = (AndroidSharedPreferences) DataSourceFactory.produce(DataSources.ANDROID_SHARED_PREFS,
                                                                               getContext());
            preferences.loadEntity();
            String username = preferences.getEntity().getString("username",
                                                                "null");
            String password = preferences.getEntity().getString("password",
                                                                "null");

            getKinveyClient().getCurrentUser().setAttribute("password", password);
            getKinveyClient().mappeddata("Notes").save(note);

        }
        catch (KinveyException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
