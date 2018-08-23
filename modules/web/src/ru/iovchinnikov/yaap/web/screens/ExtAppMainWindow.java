package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.gui.components.AbstractMainWindow;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;

import javax.inject.Inject;
import java.util.Map;

public class ExtAppMainWindow extends AbstractMainWindow {
    @Inject
    private FtsField ftsField;

    @Inject private SideMenu sideMenu;
    @Inject
    private Embedded logoImage;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initLayoutAnalyzerContextMenu(logoImage);
        initLogoImage(logoImage);
        initFtsField(ftsField);

        int count = 5;
        SideMenu.MenuItem item = sideMenu.createMenuItem("total");
        item.setCaption(getMessage("balance"));
        item.setBadgeText(count + " RUR");
        item.setIcon("font-icon:FILE");
        sideMenu.addMenuItem(item,0);
    }
}