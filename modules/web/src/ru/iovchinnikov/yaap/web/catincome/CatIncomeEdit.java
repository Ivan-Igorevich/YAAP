package ru.iovchinnikov.yaap.web.catincome;

import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.components.PickerField;
import ru.iovchinnikov.yaap.web.category.CategoryEdit;

import javax.inject.Named;
import java.util.Map;

public class CatIncomeEdit extends CategoryEdit {
    @Named("fieldGroup.fParent") private Field fParent;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        PickerField parent = (PickerField) fParent;
        parent.getLookupAction().setLookupScreen("yaap$CatIncome.browse");
    }
}