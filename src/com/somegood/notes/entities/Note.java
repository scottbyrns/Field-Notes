package com.somegood.notes.entities;

import android.graphics.Bitmap;
import com.kinvey.persistence.mapping.MappedEntity;
import com.kinvey.persistence.mapping.MappedField;
import org.json.JSONArray;

import java.util.Arrays;
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
 * Created 7/12/12 10:38 PM
 */
public class Note implements MappedEntity {
    private JSONArray components;
    private String data;
    private String id;
    private String encodedImage;
    private Bitmap image;
    private String owner;

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap image)
    {
        this.image = image;
    }

    public List<MappedField> getMapping() {
        return Arrays.asList(new MappedField[]{
                new MappedField("encodedImage", "image"),
                new MappedField("owner", "owner"),
                new MappedField("data",
                                "data"), new MappedField("id",
                                                         "_id")
        });
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public JSONArray getComponents()
    {
        return components;
    }

    public void setComponents(JSONArray components)
    {
        this.components = components;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getEncodedImage()
    {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage)
    {
        this.encodedImage = encodedImage;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }
}
