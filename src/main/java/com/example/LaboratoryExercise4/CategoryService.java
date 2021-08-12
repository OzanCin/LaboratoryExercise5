package com.example.LaboratoryExercise4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CategoryService {
    private static List<Category> todos = new ArrayList<Category>();
    //private static int todoCount = 2;

    static {
        todos.add(new Category("SS", "School Supplies"));
        todos.add(new Category("Dr", "Drinks"));
    }

    public void initialadd(){
        todos.add(new Category("SS", "School Supplies"));
        todos.add(new Category("Dr", "Drinks"));
    }

    public List<Category> retrieveTodos() {
        List<Category> filteredTodos = new ArrayList<Category>();
        for (Category todo : todos) {
            filteredTodos.add(todo);
        }
        return filteredTodos;    }

    public void addTodo(String catcode, String catdesc) {
        todos.add(new Category(catcode, catdesc));
    }

    public void deleteTodo(String id) {

        for (int i = 0; i < todos.size(); i++) {

            if(id.equals(todos.get(i).getCatcode())) {

                todos.remove(i);
                break;
            }
        }
    }
    public Category retrieve(String id){

        for(Category todo: todos){
            if(todo.getCatcode().equals(id)){
                return todo;
            }
        }
        return null;
    }
    public void update(Category todo){
        todos.remove(todo);
        todos.add(todo);
    }
}
