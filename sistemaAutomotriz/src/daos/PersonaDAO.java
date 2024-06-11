package daos;

import funcional.Persona;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {
    private Connection connection;

    public PersonaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarPersona(Persona persona) {
        String sql = "INSERT INTO persona (personaID, nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, fechaFin) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, persona.getPersonaID());
            pstmt.setString(2, persona.getNombre());
            pstmt.setString(3, persona.getApellidoPaterno());
            pstmt.setString(4, persona.getApellidoMaterno());
            pstmt.setDate(5, new java.sql.Date(persona.getFechaNacimiento().getTime()));
            if (persona.getFechaFin() != null) {
                pstmt.setDate(6, new java.sql.Date(persona.getFechaFin().getTime()));
            } else {
                pstmt.setNull(6, java.sql.Types.DATE);
            }
            pstmt.executeUpdate();
            System.out.println("Persona agregada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Persona obtenerPersona(int personaID) {
        String sql = "SELECT * FROM persona WHERE personaID = ?";
        Persona persona = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, personaID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                persona = new Persona(
                        rs.getInt("personaID"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getDate("fechaNacimiento"),
                        rs.getDate("fechaFin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persona;
    }

    public List<Persona> obtenerTodasLasPersonas() {
        String sql = "SELECT * FROM persona";
        List<Persona> personas = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("personaID"),
                        rs.getString("nombre"),
                        rs.getString("apellidoPaterno"),
                        rs.getString("apellidoMaterno"),
                        rs.getDate("fechaNacimiento"),
                        rs.getDate("fechaFin")
                );
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personas;
    }

    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE persona SET nombre = ?, apellidoPaterno = ?, apellidoMaterno = ?, fechaNacimiento = ?, fechaFin = ? WHERE personaID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getApellidoPaterno());
            pstmt.setString(3, persona.getApellidoMaterno());
            pstmt.setDate(4, new java.sql.Date(persona.getFechaNacimiento().getTime()));
            if (persona.getFechaFin() != null) {
                pstmt.setDate(5, new java.sql.Date(persona.getFechaFin().getTime()));
            } else {
                pstmt.setNull(5, java.sql.Types.DATE);
            }
            pstmt.setInt(6, persona.getPersonaID());
            pstmt.executeUpdate();
            System.out.println("Persona actualizada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersona(int personaID) {
        String sql = "DELETE FROM persona WHERE personaID = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, personaID);
            pstmt.executeUpdate();
            System.out.println("Persona eliminada.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
