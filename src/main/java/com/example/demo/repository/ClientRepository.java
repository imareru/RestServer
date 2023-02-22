package com.example.demo.repository;

import com.example.demo.entity.Client;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class ClientRepository implements IRestRepository<Client>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery1 = "SELECT \"client_id\", \"client_name\", \"phone\", \"email\" " +
            "FROM \"client\" " +
            "ORDER BY \"client_id\"";

    private static String selectByIdQuery1 = "SELECT \"client_id\", \"client_name\", \"phone\", \"email\" " +
            "FROM \"client\" " +
            "WHERE \"client_id\" = ?";

    private static String insertQuery1 = "INSERT INTO \"client\"(\"client_name\", \"phone\", \"email\") " +
            "VALUES (?, ?, ?) " +
            "RETURNING \"client_id\", \"client_name\", \"phone\", \"email\"";

    private static String updateQuery1 = "UPDATE \"client\" " +
            "SET \"client_name\" = ?, \"phone\" = ?, \"email\" = ? " +
            "WHERE \"client_id\" = ? " +
            "RETURNING \"client_id\", \"client_name\", \"phone\", \"email\"";

    private static String deleteQuery1 = "DELETE FROM \"client\" " +
            "WHERE \"client_id\" = ? " +
            "RETURNING \"client_id\", \"client_name\", \"phone\", \"email\"";

    public ClientRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Client[] select() {
        ArrayList<Client> values = new ArrayList<Client>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery1);
        while (rowSet.next()) {
            values.add(new Client(
                    rowSet.getInt(1),
                    rowSet.getString(2),
                    rowSet.getString(3),
                    rowSet.getString(4)
            ));
        }
        Client[] result = new Client[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Client select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Client(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }

    @Override
    public Client insert(Client entity) {
        Object[] params = new Object[] { entity.getName(), entity.getPhone(), entity.getMail() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Client(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }

    @Override
    public Client update(Integer id, Client entity) {
        Object[] params = new Object[] { entity.getName(), entity.getPhone(), entity.getMail(), id };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Client(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }

    @Override
    public Client delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Client(
                rowSet.getInt(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4)
        );
    }
}
