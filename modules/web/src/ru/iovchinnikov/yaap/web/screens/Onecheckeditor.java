package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.yaap.entity.Account;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Onecheckeditor extends AbstractWindow {
    private static final int POS_LINE = 0;
    private static final int POS_BTTN = 1;

    @Inject private UserSession userSession;
    @Inject private DataManager dataManager;
    @Inject private LookupPickerField lpfCmp;
    @Inject private PickerField pfCat;
    @Inject private ResizableTextArea taDesc;
    @Inject private TimeSource timeSource;
    @Inject private ComponentsFactory componentsFactory;
    @Inject private DatePicker dateBuy;
    @Inject private GridLayout gridMain;
    @Inject private PickerField pfAcc;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        dateBuy.addValidator(value -> isEarlier((Date) value));
    }
    private void isEarlier(Date date) throws ValidationException {
        if (date.after(timeSource.currentTimestamp()))
            throw new ValidationException(getMessage("onlyPast"));
    }

    @Override
    public void ready() {
        super.ready();

        LoadContext<Account> ctx = LoadContext.create(Account.class);
        ctx.setQuery(LoadContext.createQuery("select e from yaap$Account e where e.owner.id = :userid and e.isDefault = true")
                .setParameter("userid", userSession.getUser().getId()));
        Account acct = dataManager.load(ctx);
        pfAcc.setValue(acct);

        int rowNumber = gridMain.getRows();
        moveButtonAdd(rowNumber);
        FrmchecklineHeaders head = (FrmchecklineHeaders) openFrame(null, "yaap$frmCheckLine.headers");
        gridMain.add(head, POS_BTTN, rowNumber - 1);
        addEmptyRow();
    }

    private void addEmptyRow() {
        int rowNumber = gridMain.getRows();
        int enumer = rowNumber - 5;
        moveButtonAdd(rowNumber);
        Label lblEnum = componentsFactory.createComponent(Label.class);     // create enum label
        lblEnum.setId("lblEnum" + enumer);
        lblEnum.setValue(enumer);
        Frmcheckline newLine = (Frmcheckline) openFrame(null, "yaap$frmCheckLine");     // create frame
        newLine.setId("frmLine" + enumer);
        gridMain.add(lblEnum, POS_LINE, rowNumber - 1);                     // put enum
        gridMain.add(newLine, POS_BTTN, rowNumber - 1);                     // put frame
    }

    private void moveButtonAdd(int rowNumber) {
        gridMain.setRows(rowNumber + 1);                                    // add a row
        ButtonsPanel btnAddPanel = getButtonAdd(rowNumber);                 // get and remove add button
        gridMain.remove(getComponent("lblSpacing"));                        // remove spacing label
        Label lblSpacing = componentsFactory.createComponent(Label.class);  // create new spacing label
        lblSpacing.setId("lblSpacing");
        gridMain.add(lblSpacing, POS_LINE, rowNumber);                      // put spacing label
        gridMain.add(btnAddPanel, POS_BTTN, rowNumber);                     // put add button
    }

    public void btnCancelClick() {
        this.close("cancelled");
    }

    public void btnOkClick() {
        Date d = dateBuy.getValue() == null ? new Date(System.currentTimeMillis()) : dateBuy.getValue();
        for (int i = 5; i < gridMain.getRows() - 1; i++) {
            Frmcheckline line = (Frmcheckline) gridMain.getComponent(POS_BTTN, i);
            if (line == null) continue;
            line.saveTx(d, pfCat.getValue(), lpfCmp.getValue(), taDesc.getValue());
        }
        this.close("saved");
    }

    public void btnAddClick() {
        addEmptyRow();
    }

    private ButtonsPanel getButtonAdd(int rowNumber) {
        ButtonsPanel btnAddPanel = (ButtonsPanel) gridMain.getComponent(POS_BTTN, rowNumber - 1);
        gridMain.remove(btnAddPanel);
        return btnAddPanel;
    }
}