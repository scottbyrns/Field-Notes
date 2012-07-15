package com.somegood.mva.view;

import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.ViewLayers;
import com.somegood.notes.view.layers.*;

/**
 * Produce view layers.
 *
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
 * Created 7/13/12 7:58 PM
 */
public class ViewFactory
{
    /**
     * Produce a view layer of the specified type.
     *
     * @param viewLayers The type of view layer to create.
     * @return The specified view layer.
     */
    public static ViewLayer produce(ViewLayers viewLayers) {
        switch (viewLayers) {
            case LOGIN:
                return new LoginView();
            case FIELD_NOTES_LIST:
                return new FieldNoteListView();
            case FIELD_NOTE_DETAILS:
                return new FieldNoteDetailView();
            case ADD_FIELD_NOTE:
                return new AddFieldNoteView();
            case EDIT_FIELD_NOTE:
                return new EditFieldNoteView();
        }
        return null;
    }
}
