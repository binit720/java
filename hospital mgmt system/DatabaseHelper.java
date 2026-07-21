package server;

import java.sql.*;

/**
 * Handles all database (SQLite) operations for the Hospital Management System.
 * Uses the sqlite-jdbc driver -> jdbc:sqlite:hospital.db
 */
public class DatabaseHelper {
    private Connection conn;

    public DatabaseHelper(String dbPath) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            createTable();
            System.out.println("Connected to database: " + dbPath);
        } catch (Exception e) {
            System.out.println("Database connection failed. Did you add sqlite-jdbc.jar to the classpath?");
            e.printStackTrace();
        }
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "age INTEGER," +
                "gender TEXT," +
                "disease TEXT," +
                "doctor TEXT," +
                "phone TEXT," +
                "address TEXT)";
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public synchronized String addPatient(String name, int age, String gender, String disease,
                                           String doctor, String phone, String address) {
        String sql = "INSERT INTO patients(name, age, gender, disease, doctor, phone, address) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);
            ps.setString(5, doctor);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.executeUpdate();
            return "OK~Patient added successfully";
        } catch (SQLException e) {
            return "ERROR~" + e.getMessage();
        }
    }

    public synchronized String updatePatient(int id, String name, int age, String gender, String disease,
                                              String doctor, String phone, String address) {
        String sql = "UPDATE patients SET name=?, age=?, gender=?, disease=?, doctor=?, phone=?, address=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);
            ps.setString(5, doctor);
            ps.setString(6, phone);
            ps.setString(7, address);
            ps.setInt(8, id);
            int rows = ps.executeUpdate();
            return rows > 0 ? "OK~Patient updated successfully" : "ERROR~No patient found with id " + id;
        } catch (SQLException e) {
            return "ERROR~" + e.getMessage();
        }
    }

    public synchronized String deletePatient(int id) {
        String sql = "DELETE FROM patients WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0 ? "OK~Patient deleted successfully" : "ERROR~No patient found with id " + id;
        } catch (SQLException e) {
            return "ERROR~" + e.getMessage();
        }
    }

    public synchronized String getAllPatients() {
        StringBuilder sb = new StringBuilder("OK~");
        String sql = "SELECT * FROM patients ORDER BY id";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                sb.append(rs.getInt("id")).append(",")
                  .append(rs.getString("name")).append(",")
                  .append(rs.getInt("age")).append(",")
                  .append(rs.getString("gender")).append(",")
                  .append(rs.getString("disease")).append(",")
                  .append(rs.getString("doctor")).append(",")
                  .append(rs.getString("phone")).append(",")
                  .append(rs.getString("address")).append(";");
            }
            return sb.toString();
        } catch (SQLException e) {
            return "ERROR~" + e.getMessage();
        }
    }
}
