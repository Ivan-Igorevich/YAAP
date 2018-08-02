package ru.iovchinnikov.yaap.web.category;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.TreeTable;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import com.haulmont.cuba.gui.components.actions.RemoveAction;
import ru.iovchinnikov.yaap.entity.Category;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.Set;

public class CategoryBrowse extends AbstractLookup {
    @Inject private TreeTable<Category> categoriesTable;
    @Named("categoriesTable.create") private CreateAction categoriesTableCreate;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        categoriesTable.addAction(new RemoveAction(categoriesTable) {
            @Override
            protected void remove(Set<Entity> selected) {
                super.remove(selected);
                categoriesTable.getDatasource().refresh();
            }
        });

        categoriesTableCreate.setInitialValuesSupplier(() -> ParamsMap.of("parent", categoriesTable.getSingleSelected()));
    }
}