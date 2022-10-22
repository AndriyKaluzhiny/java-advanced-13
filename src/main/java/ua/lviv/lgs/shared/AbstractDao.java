package ua.lviv.lgs.shared;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao <T> {
    List<T> readAll() throws SQLException;
    T read(int id) throws SQLException;
    T create(T t) throws SQLException;
    T update(T t) throws Exception;
    void delete(int t) throws SQLException;
}
