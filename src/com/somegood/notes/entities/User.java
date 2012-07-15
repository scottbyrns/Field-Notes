package com.somegood.notes.entities;

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
 * Created 7/13/12 7:57 AM
 */
public class User implements MappedEntity
{

    private String    id;
    private String    name;
    private JSONArray notes;

    public List<MappedField> getMapping()
    {
        return Arrays.asList(new MappedField[]{ new MappedField("name",
                                                          "name"), new MappedField("id",
                                                                                   "_id")
        });
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public JSONArray getNotes()
    {
        return notes;
    }

    public void setNotes(JSONArray notes)
    {
        this.notes = notes;
    }
}
