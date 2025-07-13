package io.github.blockneko11.simpledbc.api.action.replace;

import io.github.blockneko11.simpledbc.api.action.TableAction;

public interface ReplaceAction extends TableAction {
    ReplaceAction value(String key, Object value);
}
