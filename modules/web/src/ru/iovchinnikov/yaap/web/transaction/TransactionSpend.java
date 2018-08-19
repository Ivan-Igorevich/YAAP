package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;

import java.util.Map;

public class TransactionSpend extends AbstractLookup {

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
    }

    public void onBtnAddCheckClick() {
        openWindow("oneCheckEditor", WindowManager.OpenType.DIALOG);
    }
}