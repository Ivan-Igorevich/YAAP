package ru.iovchinnikov.yaap.web.transaction;

import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.yaap.entity.*;
import ru.iovchinnikov.yaap.service.AccountService;
import ru.iovchinnikov.yaap.service.PeriodService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.Map;

public class TransactionEdit extends AbstractEditor<Transaction> {
    @Inject private AccountService accountService;
    @Inject private UserSession userSession;
    @Inject private TimeSource timeSource;
    @Inject private PeriodService periodService;
    @Inject private LookupPickerField lpfAcct;
    @Named("mainGroup.fTtl") private Field fTtl;
    @Named("mainGroup.fAmnt") private Field fAmnt;
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
        fTtl.addValidator(value -> nonZero((double) value));
        fDate.addValidator(value -> isEarlier((Date) value));
        fCat.getLookupAction().setLookupScreen("yaap$CatIncome.browse");
    }

    @Override
    public void ready() {
        super.ready();
        lpfAcct.setValue(accountService.getDefault(userSession.getUser()));
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

    @Override
    public void commitAndClose() {
        periodService.setPeriod(getItem(), lpfAcct.getValue());
        close(AbstractEditor.COMMIT_ACTION_ID, true);
    }
}