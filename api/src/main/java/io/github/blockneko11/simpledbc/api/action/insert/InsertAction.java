package io.github.blockneko11.simpledbc.api.action.insert;

import io.github.blockneko11.simpledbc.api.action.TableAction;

public interface InsertAction extends TableAction {
    InsertAction ignore(boolean ignore);

    InsertAction value(String key, Object value);
}
