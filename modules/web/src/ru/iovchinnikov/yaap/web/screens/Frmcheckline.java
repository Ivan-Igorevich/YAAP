package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.components.TextField;
import ru.iovchinnikov.yaap.entity.Category;
import ru.iovchinnikov.yaap.entity.Company;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class Frmcheckline extends AbstractFrame {
    @Inject private Metadata metadata;
    @Inject private DataManager dataManager;
    @Inject private TextField amnt;
    @Inject private TextField cost;
    @Inject private LookupPickerField names;
    @Inject private TextField vol;



    public void saveTx(Date date, Category category, Company company, String desc) {
        Transaction tx = metadata.create(Transaction.class);
        tx.setAmount(Double.parseDouble(amnt.getRawValue()));
        tx.setVolume(Double.parseDouble(vol.getRawValue()));
        tx.setTotal(Double.parseDouble(cost.getRawValue()));
        tx.setCategory(category);
        tx.setDate(date);
        tx.setDescription(desc);
        tx.setDirection(false);
        tx.setName(names.getValue());
        tx.setSource(company);
        dataManager.commit(tx);
    }
}