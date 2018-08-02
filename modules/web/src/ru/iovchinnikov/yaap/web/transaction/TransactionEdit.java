package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.gui.components.*;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class TransactionEdit extends AbstractEditor<Transaction> {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 0;

    @Named("mainGroup.fTotal") private Field fTotal;
    @Named("mainGroup.fAmount") private Field fAmount;
    @Named("mainGroup.fCat") private PickerField fCat;

    private int mode;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        fTotal.addValidator(value -> nonZero((double) value));
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
        if (mode == MODE_IN)
            fAmount.setValue(1);
    }

    private void nonZero(double value) throws ValidationException {
        if (value == 0) {
            throw new ValidationException(getMessage("notZero"));
        }
    }


}