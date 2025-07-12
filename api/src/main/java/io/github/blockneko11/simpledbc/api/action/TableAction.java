package io.github.blockneko11.simpledbc.api.action;

import org.jetbrains.annotations.NotNull;

public interface TableAction extends Action {
    @NotNull
    String getTable();
}
