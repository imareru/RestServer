package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class OrderRepository implements IRestRepository<Order>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery1 = "SELECT \"order_id\", \"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\" " +
            "FROM \"new_order\" " +
            "ORDER BY \"order_id\"";

    private static String selectByIdQuery1 = "SELECT \"order_id\", \"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\"  " +
            "FROM \"new_order\" " +
            "WHERE \"order_id\" = ?";

    private static String selectBySourceIdQuery1 = "SELECT \"order_id\", \"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\"  " +
            "FROM \"new_order\" " +
            "WHERE \"cinema_id\" = ?";

    private static String insertQuery1 = "INSERT INTO \"new_order\"(\"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\") " +
            "VALUES (?, ?, ?, ?, ?, ?, ?) " +
            "RETURNING \"order_id\", \"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\"";

    private static String updateQuery1 = "UPDATE \"new_order\" " +
            "SET \"cinema_id\" = ?, \"film_id\" = ?, \"room_id\" = ?, \"quantity\" = ?, \"discount\" = ?, \"payment_method\" = ?, \"client_id\" = ? " +
            "WHERE \"order_id\" = ? " +
            "RETURNING \"order_id\", \"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\"";

    private static String deleteQuery1 = "DELETE FROM \"new_order\" " +
            "WHERE \"order_id\" = ? " +
            "RETURNING \"order_id\", \"cinema_id\", \"film_id\" , \"room_id\", \"quantity\", \"discount\", \"payment_method\", \"client_id\"";

    public OrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Order[] select() {
        ArrayList<Order> values = new ArrayList<Order>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery1);
        while (rowSet.next()) {
            values.add(new Order(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5),
                    rowSet.getDouble(6),
                    rowSet.getString(7),
                    rowSet.getInt(8)
            ));
        }
        Order[] result = new Order[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Order select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getDouble(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    public Order[] selectBySourceId(Integer sourceId) {
        ArrayList<Order> values = new ArrayList<Order>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery1, params, types);
        while (rowSet.next()) {
            values.add(new Order(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getInt(5),
                    rowSet.getDouble(6),
                    rowSet.getString(7),
                    rowSet.getInt(8)
            ));
        }
        Order[] result = new Order[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Order insert(Order entity) {
        Object[] params = new Object[] { entity.getCinemaId(), entity.getFilmId(), entity.getRoomId(), entity.getQuantity(), entity.getDiscount(), entity.getPaymentMethod(), entity.getClientId()};
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DECIMAL, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getDouble(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    @Override
    public Order update(Integer id, Order entity) {
        Object[] params = new Object[] {entity.getCinemaId(), entity.getFilmId(), entity.getRoomId(), entity.getQuantity(), entity.getDiscount(), entity.getPaymentMethod(), entity.getClientId(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.DECIMAL, Types.VARCHAR, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getDouble(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }

    @Override
    public Order delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery1, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Order(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3),
                rowSet.getInt(4),
                rowSet.getInt(5),
                rowSet.getDouble(6),
                rowSet.getString(7),
                rowSet.getInt(8)
        );
    }
}
