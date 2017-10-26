/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.aurelien.todoswing.DAO;

import java.util.List;
import java.sql.SQLException;
import java.util.Map;

/**
 * Interface pour la gestion des tâches
 * @author Aurelien Courgeau
 * @param <Object>
 * @param <Data>
 */
public interface DAOInterface <Object , Data >{
    
    /**
     * Suppression d'une tâche
     * @param obj
     * @throws SQLException
     */
    public void deleteTask(Object obj)throws SQLException;

    /**
     * Modification d'une tâche
     * @param obj
     * @throws SQLException
     */
    public void modifyTask(Object obj)throws SQLException;

    /**
     * Ajour d'une tâche
     * @param obj
     * @throws SQLException
     */
    public void addTask(Object obj)throws SQLException;

    /**
     * Récupération par l'id d'une tâche
     * @param id
     * @return
     * @throws SQLException
     */
    public Data getTaskData(int id)throws SQLException;

    /**
     * Récupération des données de toutes les tâches
     * @return
     * @throws SQLException
     */
    public Data getAllTaskData()throws SQLException;

    /**
     * Récupération de l'objet Tâche
     * @return
     * @throws SQLException
     */
    public Object getTaskObject()throws SQLException;

    /**
     * Récupération d'un map ( clé, valeur ) de Tache
     * @return
     * @throws SQLException
     */
    public Map<String,String> getTaskMap()throws SQLException;

    /**
     * Récupération d'un map ( clé, valeur) de la Foreing Key categorie_id
     * @return
     * @throws SQLException
     */
    public Map<String,Integer> getKeyMap()throws SQLException;

    /**
     * Récupération d'une List de Map ( clé, valeur) des taches
     * @return
     * @throws SQLException
     */
    public List<Map<String,String>> getAllTaskListMap()throws SQLException;

    /**
     * Récupération de la list de toutes les tâches
     * @return
     * @throws SQLException
     */
    public List<Object> getAllTaskList() throws SQLException;
    
}
