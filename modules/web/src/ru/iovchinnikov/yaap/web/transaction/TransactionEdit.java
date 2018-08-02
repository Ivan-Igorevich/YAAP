package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.Field;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.components.ValidationException;
import ru.iovchinnikov.yaap.entity.Transaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class TransactionEdit extends AbstractEditor<Transaction> {
    @Named("mainGroup.fTotal") private Field fTotal;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        fTotal.addValidator(value -> nonZero((double) value));
    }

    private void nonZero(double value) throws ValidationException {
        if (value == 0) {
            throw new ValidationException(getMessage("notZero"));
        }
    }
}