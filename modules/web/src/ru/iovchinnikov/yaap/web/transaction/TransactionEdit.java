package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import ru.iovchinnikov.yaap.entity.CatIncome;
import ru.iovchinnikov.yaap.entity.CatSpend;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class TransactionEdit extends AbstractEditor<Transaction> {
    @Inject private TimeSource timeSource;
    @Named("mainGroup.fTotal") private Field fTotal;
    @Named("mainGroup.fAmount") private Field fAmount;
    @Named("mainGroup.fCat") private PickerField fCat;
    @Named("mainGroup.fDate") private Field fDate;
    @Named("mainGroup.fName") private Field fName;

    @Override
    protected void initNewItem(Transaction item) {
        super.initNewItem(item);
        item.setDate(timeSource.currentTimestamp());
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        fTotal.addValidator(value -> nonZero((double) value));
        fDate.addValidator(value -> isEarlier((Date) value));
        fCat.getLookupAction().setLookupScreen("yaap$CatIncome.browse");
    }

    @Override
    public void ready() {
        super.ready();
        fAmount.setValue(1);
        fName.setValue(" ");
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