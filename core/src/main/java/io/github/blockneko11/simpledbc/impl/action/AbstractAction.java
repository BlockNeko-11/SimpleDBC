package io.github.blockneko11.simpledbc.impl.action;

import io.github.blockneko11.simpledbc.api.Database;
import io.github.blockneko11.simpledbc.api.action.Action;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractAction implements Action {
    private final Database executor;

    protected AbstractAction(@NotNull Database executor) {
        this.executor = executor;
    }

    @NotNull
    protected Database getExecutor() {
        return this.executor;
    }
}
