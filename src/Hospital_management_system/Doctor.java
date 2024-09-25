package Hospital_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor{
    private Connection connection;
    public Doctor(Connection connection){
        this.connection=connection;
    }

    public void view_doctors(){
        String query="select * from doctors";
        try{
            PreparedStatement preparedStatement =connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors:");
            System.out.println("+------------+----------------+----------------------+");
            System.out.println("| Doctor Id  | Name           | Specialization       |");
            System.out.println("+------------+----------------+----------------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("|%-12s|%-16s|%-22s|\n",id,name,specialization);
                System.out.println("+------------+----------------+----------------------+");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getdoctorbyId(int id) {
        String query = "SELECT * FROM doctors WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
