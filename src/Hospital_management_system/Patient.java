package Hospital_management_system;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;

public class Patient {
      private Connection connection;
      private Scanner scanner;
      public Patient(Connection connection, Scanner scanner){
          this.connection=connection;
          this.scanner=scanner;
      }
      public void add_Patient(){
          System.out.println("Enter Patient Name:");
          String name=scanner.next();
          System.out.println("Enter Patient Age:");
          int age=scanner.nextInt();
          System.out.println("Enter Patient Gender:");
          String gender=scanner.next();

          try{
            String query = "INSERT INTO patients(name,age,gender) VALUES(?,?,?)";
              PreparedStatement preparedStatement =connection.prepareStatement(query);
              preparedStatement.setString(1, name);
              preparedStatement.setInt(2, age);
              preparedStatement.setString(3, gender);
              int affectedRows = preparedStatement.executeUpdate();
              if(affectedRows>0){
                  System.out.println("patient add successfully");
              }
              else{
                  System.out.println("Failed to add patients");
              }




          }catch(SQLException e){
              e.printStackTrace();
          }
      }

      public void view_patients(){
          String query="select * from patients";
          try{
              PreparedStatement preparedStatement =connection.prepareStatement(query);
              ResultSet resultSet = preparedStatement.executeQuery();
              System.out.println("Patients:");
              System.out.println("+------------+----------------+---------+------------+");
              System.out.println("| Patient Id | Name           | Age     | Gender     |");
              System.out.println("+------------+----------------+---------+------------+");
              while(resultSet.next()){
                  int id = resultSet.getInt("Id");
                  String name = resultSet.getString("name");
                  int age = resultSet.getInt("age");
                  String gender = resultSet.getString("gender");
                  System.out.printf("|%-12s|%-16s|%-9s|%-12s|\n",id,name,age,gender);
                  System.out.println("+------------+----------------+---------+------------+");
              }
          }catch(SQLException e){
              e.printStackTrace();
          }
      }

      public boolean get_patient_byId(int id) {
          String query = "SELECT * FROM patients WHERE id=?";
          try {
              PreparedStatement preparedStatement = connection.prepareStatement(query);
              preparedStatement.setInt(1, id);
              ResultSet resultSet = preparedStatement.executeQuery();
              if (resultSet.next()) {
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
