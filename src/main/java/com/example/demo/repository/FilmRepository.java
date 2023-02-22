package com.example.demo.repository;

import com.example.demo.entity.Film;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository

public class FilmRepository implements IRestRepository<Film>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"film_id\",\"film_name\", \"genre\", \"price\", \"age_limit\", \"description\" " +
            "FROM \"film\" " +
            "ORDER BY \"film_id\"";

    private static String selectByIdQuery = "SELECT \"film_id\", \"film_name\", \"genre\", \"price\", \"age_limit\", \"description\" " +
            "FROM \"film\" " +
            "WHERE \"film_id\" = ?";

    private static String insertQuery = "INSERT INTO \"film\"(\"film_name\", \"genre\", \"price\", \"age_limit\", \"description\" )" +
            "VALUES (?, ?, ?, ?, ?) " +
            "RETURNING \"film_id\", \"film_name\", \"genre\", \"price\", \"age_limit\", \"description\" ";

    private static String updateQuery = "UPDATE \"film\" " +
            "SET \"film_name\" = ?, \"film_id\" = ?, \"film_name\" = ?, \"genre\" = ?, \"price\" = ?, \"age_limit\" = ?, \"description\" = ? " +
            "WHERE \"film_id\" = ? " +
            "RETURNING \"film_id\", \"film_name\", \"genre\", \"price\", \"age_limit\", \"description\" ";

    private static String deleteQuery = "DELETE FROM \"film\" " +
            "WHERE \"film_id\" = ? " +
            "RETURNING \"film_id\", \"film_name\", \"genre\", \"price\", \"age_limit\", \"description\" ";

    public FilmRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Film[] select() {
        ArrayList<Film> values = new ArrayList<Film>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Film(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getDouble(4),
                    rowSet.getString(5),
                    rowSet.getString(6)));
        }
        Film[] result = new Film[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Film select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Film(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getDouble(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }

    @Override
    public Film insert(Film entity) {
        Object[] params = new Object[] {entity.getFilm_name(), entity.getGenre(), entity.getPrice(), entity.getAgeLimit(), entity.getDescription()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Film(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getDouble(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }

    @Override
    public Film update(Integer id, Film entity) {
        Object[] params = new Object[] {entity.getFilm_name(), entity.getGenre(), entity.getPrice(), entity.getAgeLimit(), entity.getDescription(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.DOUBLE, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Film(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getDouble(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }

    @Override
    public Film delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Film(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getDouble(4),
                rowSet.getString(5),
                rowSet.getString(6)
        );
    }
}
