/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2017 Serge Rider (serge@jkiss.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ui.editors;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.jkiss.dbeaver.ui.UIUtils;
import org.jkiss.utils.CommonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * TextEditorUtils
 */
public class TextEditorUtils {


    private static Map<String, Integer> ACTION_TRANSLATE_MAP;

    public static IPreferenceStore getEditorsPreferenceStore() {
        return new ScopedPreferenceStore(InstanceScope.INSTANCE, "org.eclipse.ui.editors");
    }

    public static Color getDefaultTextBackground() {
        IPreferenceStore preferenceStore = getEditorsPreferenceStore();
        String bgRGB = preferenceStore.getString(AbstractTextEditor.PREFERENCE_COLOR_BACKGROUND);
        return CommonUtils.isEmpty(bgRGB) ? Display.getDefault().getSystemColor(SWT.COLOR_LIST_BACKGROUND) : UIUtils.getSharedColor(bgRGB);
    }

    public static Color getDefaultTextForeground() {
        IPreferenceStore preferenceStore = getEditorsPreferenceStore();
        String fgRGB = preferenceStore.getString(AbstractTextEditor.PREFERENCE_COLOR_FOREGROUND);
        return CommonUtils.isEmpty(fgRGB) ? Display.getDefault().getSystemColor(SWT.COLOR_LIST_FOREGROUND) : UIUtils.getSharedColor(fgRGB);
    }

    public static Map<String, Integer> getTextEditorActionMap()
    {
        if (ACTION_TRANSLATE_MAP == null) {
            ACTION_TRANSLATE_MAP = new HashMap<>();
            FakeTextEditor.fillActionMap(ACTION_TRANSLATE_MAP );
        }
        return ACTION_TRANSLATE_MAP;
    }

    private static class FakeTextEditor extends AbstractTextEditor {
        static void fillActionMap(Map<String, Integer> map) {
            for (AbstractTextEditor.IdMapEntry entry : AbstractTextEditor.ACTION_MAP) {
                map.put(entry.getActionId(), entry.getAction());
            }
        }
    }

}
