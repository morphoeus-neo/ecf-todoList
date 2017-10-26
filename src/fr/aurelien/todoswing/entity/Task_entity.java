/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.aurelien.todoswing.entity;

import java.util.Map;

/**
 *
 * @author Aurelien Courgeau
 */
public class Task_entity {
    
    private Integer todo_id;
    private String todo_task;
    private Integer todo_categorie_id;
    private boolean todo_urgent = false;
    private boolean todo_executed = false;

    public Task_entity() {
    }

    public Task_entity(Integer todo_id, String todo_task, Integer todo_categorie_id) {
        this.todo_id = todo_id;
        this.todo_task = todo_task;
        this.todo_categorie_id = todo_categorie_id;
    }

    public Integer getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(Integer todo_id) {
        this.todo_id = todo_id;
    }

    public String getTodo_task() {
        return todo_task;
    }

    public void setTodo_task(String todo_task) {
        this.todo_task = todo_task;
    }

    public Integer getTodo_categorie_id() {
        return todo_categorie_id;
    }

    public void setTodo_categorie_id(Integer todo_categorie_id) {
        this.todo_categorie_id = todo_categorie_id;
    }

    public boolean isTodo_urgent() {
        return todo_urgent;
    }

    public void setTodo_urgent(boolean todo_urgent) {
        this.todo_urgent = todo_urgent;
    }

    public boolean isTodo_executed() {
        return todo_executed;
    }

    public void setTodo_executed(boolean todo_executed) {
        this.todo_executed = todo_executed;
    }
    
    

    

}