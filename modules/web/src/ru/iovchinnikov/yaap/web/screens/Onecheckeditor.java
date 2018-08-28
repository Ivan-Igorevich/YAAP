package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Onecheckeditor extends AbstractWindow {
    private static final int POS_LINE = 0;
    private static final int POS_BTTN = 1;


    @Inject private TimeSource timeSource;
    @Inject private ComponentsFactory componentsFactory;
    @Inject private DatePicker dateBuy;
    @Inject private GridLayout gridMain;

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
        int rowNumber = gridMain.getRows();

        gridMain.setRows(rowNumber + 1);
        ButtonsPanel btnAddPanel = getButtonAdd(rowNumber);
        Frmcheckline oneLine = (Frmcheckline) gridMain.getComponent(POS_LINE, rowNumber - 2);
        Frmcheckline newLine;
        oneLine.setId(oneLine.getId() + "1");
        gridMain.add(oneLine, POS_LINE, rowNumber - 1);
        gridMain.add(componentsFactory.createComponent(Label.class), POS_LINE, rowNumber);
        gridMain.add(btnAddPanel, POS_BTTN, rowNumber);

    }

    public void btnCancelClick() {
        this.close("cancelled");
    }

    public void btnOkClick() {
    }

    public void btnAddClick() {
    }

    private ButtonsPanel getButtonAdd(int rowNumber) {
        ButtonsPanel btnAddPanel = (ButtonsPanel) gridMain.getComponent(POS_BTTN, rowNumber - 1);
        gridMain.remove(btnAddPanel);
        return btnAddPanel;
    }
}