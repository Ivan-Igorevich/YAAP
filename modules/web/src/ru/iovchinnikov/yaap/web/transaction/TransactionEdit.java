package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;

public class TransactionEdit extends AbstractEditor<Transaction> {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 0;

    @Inject private TimeSource timeSource;
    @Named("mainGroup.fTotal") private Field fTotal;
    @Named("mainGroup.fAmount") private Field fAmount;
    @Named("mainGroup.fCat") private PickerField fCat;
    @Named("mainGroup.fDate") private Field fDate;

    private int mode;

    @Override
    protected void initNewItem(Transaction item) {
        super.initNewItem(item);
//        fDate.setValue(timeSource.currentTimestamp());
        item.setDate(timeSource.currentTimestamp());
    }

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        fTotal.addValidator(value -> nonZero((double) value));
        fDate.addValidator(value -> isEarlier((Date) value));
        if (((Transaction) params.get("ITEM")).getDirection()) {
            mode = MODE_IN;
            fCat.getLookupAction().setLookupScreen("yaap$CatIncome.browse");
            fAmount.setVisible(false);
        } else {
            mode = MODE_OUT;
            fCat.getLookupAction().setLookupScreen("yaap$CatSpend.browse");
        }
    }

    @Override
    public void ready() {
        super.ready();
        if (mode == MODE_IN) fAmount.setValue(1);
    }

    private void nonZero(double value) throws ValidationException {
        if (value == 0) throw new ValidationException(getMessage("notZero"));
    }

    private void isEarlier(Date date) throws ValidationException {
        if (date.after(timeSource.currentTimestamp())) throw new ValidationException(getMessage("onlyPast"));
    }
}