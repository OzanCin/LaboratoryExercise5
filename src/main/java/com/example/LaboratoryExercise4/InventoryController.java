package com.example.LaboratoryExercise4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes({"id","desc","errMsg","errorMessage"})
@RequestMapping
@Controller
public class InventoryController {

    DatabaseService service1;

    @Autowired
    CategoryService service;

    @Autowired
    Connection123 connect;

//    @Autowired
//    Connection1 con;


    //a mapping when someone enters file
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategorypage(ModelMap model,@RequestParam(defaultValue = "") String id) throws ClassNotFoundException, SQLException{

        service1 = new DatabaseService(connect.connect());

        model.addAttribute("todos", service1.display());

        List<Category> filteredTodos = new ArrayList<Category>();

        filteredTodos = (List) model.get("todos");

        model.put("id",filteredTodos.get(0).getCatcode());

        model.put("desc",filteredTodos.get(0).getCatdesc());

        return "category";

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCategoryPage2(ModelMap model) throws ClassNotFoundException, SQLException {

        service1 = new DatabaseService(connect.connect());

        model.addAttribute("todos", service1.display());

        List<Category> filteredTodos = new ArrayList<Category>();

        filteredTodos = (List) model.get("todos");

        model.put("id",filteredTodos.get(0).getCatcode());

        model.put("desc",filteredTodos.get(0).getCatdesc());

        return "category";

    }

    @RequestMapping(value ="/add-todo", method = RequestMethod.GET)
    public String showtodopage(){
        return "catser";
    }


    @RequestMapping(value ="/add-todo", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @RequestParam String catcode, @RequestParam String catdesc)
            throws SQLException, ClassNotFoundException{

        if (!((service1.search(catcode)) ==null)){
            model.put("errorMessage","Record Existing");
            return "redirect:/category";
        }

        Category cc = new Category(catcode,catdesc);

        service1.add(cc);
        model.clear();
        return "redirect:/category";
    }


    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model,  @RequestParam(defaultValue = "") String id)
            throws SQLException, ClassNotFoundException{

        model.put("id", id);

        // model.addAttribute("category",service.retrieve(id));

        Category cc = service1.search(id);

        model.put("id",cc.getCatcode());
        model.put("desc", cc.getCatdesc());

        return "catedit";
    }



    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String showUpdate(ModelMap model,  @RequestParam String catcode, @RequestParam String catdesc)
            throws SQLException, ClassNotFoundException{

        String iid = (String) model.get("id");

        Category cc = new Category(catcode, catdesc);

        service1.edit(cc,iid);

        return "redirect:/";

    }



    @RequestMapping(value ="/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(ModelMap model, @RequestParam String id)
            throws SQLException, ClassNotFoundException {

        service1.delete(id);

        model.clear();
        return "redirect:/";
    }

    @RequestMapping(value ="/see-todo", method = RequestMethod.GET)
    public String seetodo(ModelMap model, @RequestParam(defaultValue = "") String id)
            throws SQLException, ClassNotFoundException { model.put("id", id);
        service1 = new DatabaseService(connect.connect());
        String iid = (String) model.get("id");
        List<Items> Itemlist = new ArrayList<>();
        Itemlist = service1.display2(iid);
        if(Itemlist.size()==0){
            model.put("errorMessage","There are not items for this category ");
            return "redirect:/";
        }
        model.put("desc",iid);
        System.out.println(iid);
        model.addAttribute("todos1", service1.display2(iid));
        return "items";
    }

    @RequestMapping(value ="/see-todo", method = RequestMethod.POST)
    public String seetodo2(ModelMap model)
            throws SQLException, ClassNotFoundException {
        return "redirect:/";
    }
}

