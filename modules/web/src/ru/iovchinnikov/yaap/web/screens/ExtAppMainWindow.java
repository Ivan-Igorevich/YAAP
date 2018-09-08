package ru.iovchinnikov.yaap.web.screens;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractEditor;
import com.haulmont.cuba.gui.components.AbstractMainWindow;
import com.haulmont.cuba.gui.components.Embedded;
import com.haulmont.cuba.gui.components.mainwindow.FtsField;
import com.haulmont.cuba.gui.components.mainwindow.SideMenu;
import com.haulmont.cuba.security.global.UserSession;
import ru.iovchinnikov.yaap.entity.Account;
import ru.iovchinnikov.yaap.service.BalanceService;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class ExtAppMainWindow extends AbstractMainWindow {
    @Inject private Metadata metadata;
    @Inject private UserSession userSession;
    @Inject private DataManager dataManager;
    @Inject private BalanceService balanceService;
    @Inject private FtsField ftsField;
    @Inject private SideMenu sideMenu;
    @Inject private Embedded logoImage;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        initLayoutAnalyzerContextMenu(logoImage);
        initLogoImage(logoImage);
        initFtsField(ftsField);
        addAllAccounts();
        sideMenu.addMenuItem(addAccBtn(), sideMenu.getMenuItems().size() - 2);
    }

    private void addAllAccounts() {
        LoadContext<Account> ctx = LoadContext.create(Account.class);
        ctx.setQuery(LoadContext.createQuery("select e from yaap$Account e where e.owner.id = :userid")
                .setParameter("userid", userSession.getUser().getId()));
        List<Account> accts = dataManager.loadList(ctx);
        int aCount = 0;
        for (Account a : accts) {
            SideMenu.MenuItem oneAcc = sideMenu.createMenuItem("account" + aCount);
            oneAcc.setCaption(a.getName());
            //item.setIcon("font-icon:FILE");
            String currency = a.getCurrency() == null ? "" : a.getCurrency();
            oneAcc.setBadgeText(balanceService.getCurrentBalance(a) + " " + currency);
            sideMenu.addMenuItem(oneAcc, aCount++);
        }
    }

    private SideMenu.MenuItem addAccBtn() {
        SideMenu.MenuItem item = sideMenu.createMenuItem("addAcc");
        item.setCaption(getMessage("addAcc"));
        item.setCommand(menuItem -> {
            Account acc = metadata.create(Account.class);
            AbstractEditor e = openEditor(acc, WindowManager.OpenType.DIALOG);
            e.addCloseListener((action) -> {
                if (action.equals(AbstractEditor.COMMIT_ACTION_ID))
                    showNotification(getMessage("reLogin"));
            });
        });
        return item;
    }
}