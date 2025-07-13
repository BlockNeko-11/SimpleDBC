package io.github.blockneko11.simpledbc.impl.action;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.TableAction;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractTableAction extends AbstractAction implements TableAction {
    private final String table;

    protected AbstractTableAction(@NotNull Database executor, @NotNull String table) {
        super(executor);
        this.table = table;
    }

    @NotNull
    @Override
    public String getTable() {
        return this.table;
    }
}
