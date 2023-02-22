package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.ArrayList;

@Repository
public class RoomRepository implements IRestRepository<Room>{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"room_id\", \"number_of_seats\", \"cinema_id\"" +
            "FROM \"room\" " +
            "ORDER BY \"room_id\"";

    private static String selectBySourceIdQuery1 = "SELECT \"room_id\", \"number_of_seats\", \"cinema_id\" " +
            "FROM \"room\" " +
            "WHERE \"room_id\" = ?";

    private static String selectByIdQuery = "SELECT \"room_id\", \"number_of_seats\", \"cinema_id\" " +
            "FROM \"room\" " +
            "WHERE \"room_id\" = ?";

    private static String insertQuery = "INSERT INTO \"room\"(\"number_of_seats\", \"cinema_id\") " +
            "VALUES (?, ?) " +
            "RETURNING \"room_id\", \"number_of_seats\", \"cinema_id\"";

    private static String updateQuery = "UPDATE \"room\" " +
            "SET \"number_of_seats\" = ?, \"cinema_id\" = ? " +
            "WHERE \"room_id\" = ? " +
            "RETURNING \"room_id\", \"number_of_seats\", \"cinema_id\"";

    private static String deleteQuery = "DELETE FROM \"room\" " +
            "WHERE \"room_id\" = ? " +
            "RETURNING \"room_id\", \"number_of_seats\", \"cinema_id\"";


    public RoomRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Room[] select() {
        ArrayList<Room> values = new ArrayList<Room>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while (rowSet.next()) {
            values.add(new Room(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            )
            );
        }
        Room[] result = new Room[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Room select(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Room(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Room insert(Room entity) {
        Object[] params = new Object[] {entity.getNumOfSeats(), entity.getCinemaId()};
        int[] types = new int[] { Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Room(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Room update(Integer id, Room entity) {
        Object[] params = new Object[] {entity.getNumOfSeats(), entity.getCinemaId(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Room(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    @Override
    public Room delete(Integer id) {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Room(
                rowSet.getInt(1),
                rowSet.getInt(2),
                rowSet.getInt(3)
        );
    }

    public Room[] selectBySourceId(Integer sourceId) {
        ArrayList<Room> values = new ArrayList<Room>();
        Object[] params = new Object[] { sourceId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySourceIdQuery1, params, types);
        while (rowSet.next()) {
            values.add(new Room(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
            ));
        }
        Room[] result = new Room[values.size()];
        result = values.toArray(result);
        return result;
    }
}

