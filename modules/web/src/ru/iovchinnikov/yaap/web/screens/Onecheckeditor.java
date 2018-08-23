package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.xml.layout.ComponentsFactory;

import javax.inject.Inject;
import java.util.Date;
import java.util.Map;

public class Onecheckeditor extends AbstractWindow {
    private static final int POS_LABEL = 0;
    private static final int POS_NAME = 1;
    private static final int POS_BTTN = 1;
    private static final int POS_AMNT = 2;
    private static final int POS_VOLM = 3;
    private static final int POS_TOTL = 4;


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

    public void btnCancelClick() {
        this.close("cancelled");
    }

    public void btnOkClick() {
    }

    public void btnAddClick() {
        int rowNumber = gridMain.getRows();
        // prepare next number to insert to a new label
        int inLabelNumber = Integer.parseInt(((Label) gridMain.getComponent(POS_LABEL, rowNumber - 2)).getRawValue()) + 1;
        ButtonsPanel btnAddPanel = (ButtonsPanel) gridMain.getComponent(POS_BTTN, rowNumber - 1);
        gridMain.remove(btnAddPanel);
        // insert a row as last, set previous(empty) label value to number, prepare label dummy for new line
        gridMain.setRows(rowNumber + 1);
        Label emptyLabel = enumLabelsWork(rowNumber, inLabelNumber);
        gridMain.add(emptyLabel, POS_LABEL, rowNumber);
        gridMain.add(btnAddPanel, POS_BTTN, rowNumber);
    }

    private Label enumLabelsWork(int rowNumber, int inLabelNumber) {
        Label enumerator = (Label) gridMain.getComponent(POS_LABEL, rowNumber - 1);
        enumerator.setValue(inLabelNumber);
        enumerator.setWidth("100%");
        enumerator.setId("lbl_" + inLabelNumber);
        return componentsFactory.createComponent(Label.class);
    }
}