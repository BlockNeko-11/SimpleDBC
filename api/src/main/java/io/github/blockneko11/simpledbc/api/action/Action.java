package io.github.blockneko11.simpledbc.api.action;

import java.sql.SQLException;

public interface Action {
    int execute() throws SQLException;
}
