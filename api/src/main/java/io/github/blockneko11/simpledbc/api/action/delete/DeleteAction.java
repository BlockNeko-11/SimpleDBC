package io.github.blockneko11.simpledbc.api.action.delete;

import io.github.blockneko11.simpledbc.api.action.TableAction;
import org.jetbrains.annotations.Nullable;

public interface DeleteAction extends TableAction {
    DeleteAction condition(@Nullable String condition);
}
