package com.example.demo.repository;

import com.example.demo.entity.Cinema;
import com.example.demo.entity.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class CinemaRepository implements IRestRepository<Cinema>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery1 = "SELECT \"cinema_id\", \"cinema_name\", \"cinema_address\" " +
            "FROM \"cinema\" " +
            "ORDER BY \"cinema_id\"";

    private static String selectByIdQuery1 =  "SELECT \"cinema_id\", \"cinema_name\", \"cinema_address\" " +
            "FROM \"cinema\" " +
            "WHERE \"cinema_id\" = ?";

    private static String selectByAddress =  "SELECT \"cinema_id\", \"cinema_name\", \"cinema_address\" " +
            "FROM \"cinema\" " +
            "WHERE \"cinema_address\" = ?";

    private static String insertQuery1 = "INSERT INTO \"cinema\"(\"cinema_name\", \"cinema_address\") " +
            "VALUES (?, ?) " +
            "RETURNING \"cinema_id\", \"cinema_name\", \"cinema_address\"";

    private static String updateQuery1 = "UPDATE \"cinema\" " +
            "SET \"cinema_name\" = ?, \"cinema_address\" = ? " +
            "WHERE \"cinema_id\" = ? " +
            "RETURNING \"cinema_id\", \"cinema_name\", \"cinema_address\"";

    private static String deleteQuery1 = "DELETE FROM \"cinema\" " +
            "WHERE \"cinema_id\" = ? " +
            "RETURNING \"cinema_id\", \"cinema_name\", \"cinema_address\"";

    public CinemaRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Cinema[] select() {
        ArrayList<Cinema> values = new ArrayList<Cinema>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery1);
        while (rowSet.next()) {
            values.add(new Cinema(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3)
            ));
        }
        Cinema[] result = new Cinema[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Cinema select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Cinema(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    public Cinema[] selectByAddress(String address) {
        ArrayList<Cinema> values = new ArrayList<Cinema>();
        Object[] params = new Object[] { address };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByAddress, params, types);
        while (rowSet.next()) {
            values.add(new Cinema(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3)
            ));
        }
        Cinema[] result = new Cinema[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Cinema insert(Cinema entity) {
        Object[] params = new Object[] { entity.getName(), entity.getAddress() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Cinema(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public Cinema update(Integer id, Cinema entity) {
        Object[] params = new Object[] { entity.getName(), entity.getAddress(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Cinema(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }

    @Override
    public Cinema delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Cinema(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3)
        );
    }
}
