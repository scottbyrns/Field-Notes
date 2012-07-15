package com.somegood.notes.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import com.somegood.mva.ModelViewActivity;
import com.somegood.mva.reflection.ModelViewConfiguration;
import com.somegood.notes.R;
import com.somegood.notes.data.DataSources;
import com.somegood.notes.data.source.EditFieldNoteDataSource;
import com.somegood.notes.entities.FieldNote;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.layers.EditFieldNoteView;

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
 * Created 7/14/12 8:35 PM
 */
@ModelViewConfiguration(
        dataSource = DataSources.EDIT_FIELD_NOTE,
        view = ViewLayers.EDIT_FIELD_NOTE
)
public class EditFieldNoteActivity extends ModelViewActivity implements View.OnClickListener
{

    private Uri outputFileUri;

    private EditFieldNoteView getEditFieldNoteView () {
        return (EditFieldNoteView)getViewLayer();
    }

    private EditFieldNoteDataSource getEditFieldNoteDataSource () {
        return (EditFieldNoteDataSource) getDataSource();
    }

    public void onClick(View view)
    {
        if (view.equals(getEditFieldNoteView().getSaveNoteButton())) {
            getEditFieldNoteDataSource().getEntity().setData(
                    getEditFieldNoteView().getNoteTextArea().getText().toString()
                                                            );
            save();
        }
        else if (view.equals(getEditFieldNoteView().getAddPhotoButton())) {
            showUploadImageOptions();
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,
                               resultCode,
                               data);


        if ((requestCode == 1003 || requestCode == 1004) && resultCode == RESULT_OK)
        {

            if (outputFileUri != null)
            {
                Uri selectedImageUri = outputFileUri;
                if (selectedImageUri == null)
                {
                    selectedImageUri = data.getData();
                }
                if (selectedImageUri != null)
                {
                    try
                    {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                                                          selectedImageUri);
                        ((FieldNote) getDataSource().getEntity()).setImage(bitmap);
                    }
                    catch (IOException e)
                    {

                    }

                }
            }
            else
            {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null)
                {
                    try
                    {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                                                                          selectedImageUri);
                        ((FieldNote) getDataSource().getEntity()).setImage(bitmap);
                    }
                    catch (IOException e)
                    {

                    }

                }
            }

            getEditFieldNoteView().getFieldNoteImage().setImageBitmap(
                    getEditFieldNoteDataSource().getEntity().getImage()
                                                                     );

        }
    }

    @Override
    public void dataDidLoad()
    {
        getEditFieldNoteView().getNoteTextArea().setText(
                getEditFieldNoteDataSource().getEntity().getData()
                                                        );


        getEditFieldNoteView().getFieldNoteImage().setImageBitmap(getEditFieldNoteDataSource().getEntity().getImage());

        getEditFieldNoteView().getSaveNoteButton().setOnClickListener(this);
        getEditFieldNoteView().getAddPhotoButton().setOnClickListener(this);
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

    /**
     * Show options when a user has chosen to upload a new progress pic
     */
    public void showUploadImageOptions()
    {

        new AlertDialog.Builder(EditFieldNoteActivity.this).setTitle(R.string.add_photo).setItems(R.array.image_upload_options,
                                                                                                 new DialogInterface.OnClickListener()
                                                                                                 {

                                                                                                     public void onClick(DialogInterface dialog, int which)
                                                                                                     {

                                                                                                         if (which == 0)
                                                                                                         {
                                                                                                             String path = Environment.getExternalStorageDirectory() + "/" + (System.currentTimeMillis() / 1000) + ".jpg";
                                                                                                             File file = new File(path);

                                                                                                             Intent camIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                                                                                             outputFileUri = Uri.fromFile(file);
                                                                                                             camIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                                                                                                                                outputFileUri);
                                                                                                             startActivityForResult(camIntent,
                                                                                                                                    1003);

                                                                                                         }
                                                                                                         else if (which == 1)
                                                                                                         {

                                                                                                             Intent i = new Intent(Intent.ACTION_PICK,
                                                                                                                                   android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                                                                                             i.setType("image/*");
                                                                                                             startActivityForResult(i,
                                                                                                                                    1004);
                                                                                                         }
                                                                                                     }
                                                                                                 }).setNeutralButton(R.string.cancel,
                                                                                                                     null).create().show();
    }

}
