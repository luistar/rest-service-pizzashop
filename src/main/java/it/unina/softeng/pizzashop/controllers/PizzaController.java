package it.unina.softeng.pizzashop.controllers;

import it.unina.softeng.pizzashop.data.Pizza;
import it.unina.softeng.pizzashop.data.PizzaDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

    private PizzaDAO pizzaDAO = new PizzaDAO();

    @GetMapping("/")
    List<Pizza> all(){
        return pizzaDAO.getAll();
    }

    /**
     * Return a pizza by ID
     * @param id ID of the pizza to return
     * @return the Pizza with the given ID
     */
    @GetMapping("/{id}")
    Pizza getById(@PathVariable int id){
        // return pizzaDAO.getById(id);
        //properly return a 404 when not found
        try {
            return pizzaDAO.getById(id);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    void addPizza(@RequestBody Pizza pizza){
        System.out.println(pizza.toString());
        pizzaDAO.persist(pizza);
    }

    @PutMapping("/{id}")
    void createOrUpdatePizza(@RequestBody Pizza pizza, @PathVariable int id){
        pizzaDAO.createOrUpdate(pizza, id);
    }

    @DeleteMapping("/{id}")
    void deletePizza(@PathVariable int id){
        pizzaDAO.deleteById(id);
    }
}
