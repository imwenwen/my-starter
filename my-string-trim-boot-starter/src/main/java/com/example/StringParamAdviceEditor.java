//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example;

import java.beans.PropertyEditorSupport;
import org.springframework.lang.Nullable;

public class StringParamAdviceEditor extends PropertyEditorSupport {

    public StringParamAdviceEditor() {
        super();
    }

    public void setAsText(@Nullable String text) {
        this.setValue(WebAdviceStringUtil.processStringValue(text));
    }

    public String getAsText() {
        Object value = this.getValue();
        return value != null ? value.toString() : "";
    }
}
