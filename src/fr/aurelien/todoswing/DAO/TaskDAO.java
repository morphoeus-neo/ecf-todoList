/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.aurelien.todoswing.DAO;


import fr.aurelien.todoswing.entity.Task_entity;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
 *
 * @author Aurelien Courgeau
 */
public class TaskDAO implements DAOInterface<Task_entity , TaskDAO >{
    
    private Connection cn;
    private PreparedStatement pstm;
    private ResultSet resultSet;
    
    public TaskDAO(Connection cn)throws SQLException {
        this.cn = cn;
    }

    public ResultSet getResultSet(){
        return this.resultSet;
    }
    
    @Override
    public void addTask(Task_entity obj) throws SQLException{
        String sql ="INSERT INTO todo_list (todo_task,todo_id_categorie,todo_urgent,todo_executed) VALUES(?,?,?,?)";
        
        pstm = cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        
        pstm.setString(1,obj.getTodo_task());
        pstm.setInt(2,obj.getTodo_categorie_id());
        pstm.setBoolean(3,obj.isTodo_urgent());
        pstm.setBoolean(4,obj.isTodo_executed());
        
        
        pstm.executeUpdate();
    }

    @Override
    public void deleteTask(Task_entity Object) throws SQLException {
        
            String sql = "DELETE FROM todo_list WHERE todo_id =?";

            pstm = cn.prepareStatement(sql);
            pstm.setInt(1,(int)Object.getTodo_id());
            
            pstm.executeUpdate();
        
        
    }
  
    @Override
    public void modifyTask(Task_entity Object) throws SQLException {
        String sql ="UPDATE todo_list SET todo_task = ? , todo_id_categorie = ? , todo_urgent = ? , todo_executed = ? WHERE todo_id = ?";
        pstm = cn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);//pour recuperer l'id du dernier insert
        
        pstm.setString(1,Object.getTodo_task());
        pstm.setInt(2,Object.getTodo_categorie_id());
        pstm.setBoolean(3,Object.isTodo_urgent());
        pstm.setBoolean(4,Object.isTodo_executed());
        pstm.setInt(5,Object.getTodo_id());
        
        ResultSet key = pstm.getGeneratedKeys();
        if(key.next()){
            Object.setTodo_id(key.getInt("id"));
        }
        
        pstm.executeUpdate(); 
    }

   
    public void deleteAllTaskData() throws SQLException {
        String sql ="TRUNCATE TABLE `todo_list`" ;
            
            pstm = cn.prepareStatement(sql);
                       
            pstm.executeUpdate(); 
    }

    @Override
    public TaskDAO getTaskData(int id) throws SQLException {
        String sql = "SELECT * FROM todo_list WHERE todo_id = ?";
        pstm = cn.prepareStatement(sql);
        pstm.setInt(1,id);
            
        resultSet = pstm.executeQuery();
        return this;
    }

    @Override
    public TaskDAO getAllTaskData() throws SQLException {
        String sql = "SELECT todo_id,todo_task,todo_id_categorie,todo_urgent,todo_executed "
                + "FROM `todo_list` "
                + "INNER JOIN categorie "
                + "ON todo_list.todo_id_categorie = categorie.categorie_id";
        pstm = cn.prepareStatement(sql);
        resultSet = pstm.executeQuery();
        return this;
    }

    @Override
    public Task_entity getTaskObject()throws SQLException {
        Task_entity entity = new Task_entity();
        
        if (resultSet.next()){
            entity.setTodo_task(resultSet.getString("todo_task"));
            entity.setTodo_categorie_id(resultSet.getInt("categorie_name"));
            entity.setTodo_urgent(resultSet.getBoolean("todo_urgent"));
            entity.setTodo_executed(resultSet.getBoolean("todo_executed"));
        }
        
        return entity; 
    }

    @Override
    public Map<String, String> getTaskMap() throws SQLException {
        Map<String,String> mapData = new HashMap<>();
        
        if (resultSet.next()){
            mapData.put("todo_task",resultSet.getString("todo_task"));
            mapData.put("categorie_name",String.valueOf(resultSet.getInt("id_categorie")));
            mapData.put("todo_urgent",String.valueOf(resultSet.getBoolean("urgent")));
            mapData.put("todo_executed",String.valueOf(resultSet.getBoolean("todo_executed")));
        }
        
        return mapData;
    }

    @Override
    public List<Map<String, String>> getAllTaskListMap() throws SQLException {
        List<Map<String,String>> listMap = new ArrayList<>();
        
        if (resultSet.isBeforeFirst()){
            while(!resultSet.isLast()){
            listMap.add(this.getTaskMap());
            }
        }
        return listMap;
    }

    @Override
    public List<Task_entity> getAllTaskList() throws SQLException {
        List<Task_entity> listTast = new ArrayList<>();
        
        if (resultSet.isBeforeFirst()){
            while(!resultSet.isLast()){
                listTast.add(this.getTaskObject());
            }
        }
        return listTast;
    }
    
    @Override
    public Map<String,Integer> getKeyMap()throws SQLException{
        String sql = "SELECT * FROM categorie ORDER BY categorie_name";
        pstm = cn.prepareStatement(sql);
        resultSet = pstm.executeQuery();
        
        Map<String,Integer> map = new HashMap<>();
        
        while(resultSet.next()){
            map.put(resultSet.getString(2),resultSet.getInt(1));
          
        }
        return map;
    }
}

    