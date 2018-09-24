package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractFrame;
import com.haulmont.cuba.gui.components.LookupPickerField;
import com.haulmont.cuba.gui.components.TextField;
import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.entity.Category;
import ru.iovchinnikov.yaap.entity.Company;
import ru.iovchinnikov.yaap.entity.Transaction;
import ru.iovchinnikov.yaap.service.PeriodService;

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
    @Inject private PeriodService periodService;

    public void saveTx(Date date, Category category, Company company, String desc, Account account) {
        Transaction tx = metadata.create(Transaction.class);
        String amount = amnt.getRawValue();
        if (amount != null && !"".equals(amount))
            tx.setAmount(Double.parseDouble(amount));
        String volume = vol.getRawValue();
        if (volume != null && !"".equals(volume))
            tx.setVolume(Double.parseDouble(vol.getRawValue()));
        String cst = cost.getRawValue();
        if(cst != null && !"".equals(cst))
            tx.setTotal(Double.parseDouble(cst));

        tx.setCategory(category);
        tx.setDate(date);
        tx.setDescription(desc);
        tx.setDirection(false);
        tx.setName(names.getValue());
        tx.setSource(company);
        periodService.setPeriod(tx, account);
        //dataManager.commit(tx);
    }
}