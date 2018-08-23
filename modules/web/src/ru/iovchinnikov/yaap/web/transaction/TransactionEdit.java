package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.iovchinnikov.yaap.entity.*;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class TransactionEdit extends AbstractEditor<Transaction> {
    @Inject private TimeSource timeSource;
    @Inject private Metadata metadata;
    @Inject private DataManager dataManager;
    @Named("mainGroup.fTtl") private Field fTtl;
    @Named("mainGroup.fAmnt") private Field fAmnt;
    @Named("mainGroup.fCat") private PickerField fCat;
    @Named("mainGroup.fDate") private Field fDate;
    @Named("mainGroup.fName") private Field fName;
    @Named("mainGroup.fSrc") private Field fSrc;

    @Override
    protected void initNewItem(Transaction item) {
        super.initNewItem(item);
        item.setDate(timeSource.currentTimestamp());
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        fTtl.addValidator(value -> nonZero((double) value));
        fDate.addValidator(value -> isEarlier((Date) value));
        fCat.getLookupAction().setLookupScreen("yaap$CatIncome.browse");
    }

    @Override
    public void ready() {
        super.ready();
        fAmnt.setValue(1);
        fName.setValue(null);
    }

    private void nonZero(double value) throws ValidationException {
        if (value == 0)
            throw new ValidationException(getMessage("notZero"));
    }

    private void isEarlier(Date date) throws ValidationException {
        if (date.after(timeSource.currentTimestamp()))
            throw new ValidationException(getMessage("onlyPast"));
    }
}