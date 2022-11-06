package it.unina.softeng.pizzashop.data;

import java.util.*;
import java.util.stream.Collectors;

public class PizzaDAO {

    private List<Pizza> pizzas =  new ArrayList<Pizza>(Arrays.asList(
            new Pizza(1, "Margherita","Mozzarella, pomodoro, basilico.",4.50f),
            new Pizza(2, "Marinara","Pomodoro, origano, acciughe.",4.00f),
            new Pizza(3, "Capricciosa","Carciofi, prosciutto, funghi, mozzarella, pomodoro.",5.50f)
    ));

    public void persist(Pizza pizza) {
        pizzas.add(pizza);
    }

    public List<Pizza> getAll() {
        // return pizzas; //return all pizzas
        return pizzas.stream() //returns pizzas, sorted by id
                .sorted((Pizza p1, Pizza p2) -> p1.getId() - p2.getId())
                .collect(Collectors.toList());
    }

    public Pizza getById(int id) {
        return pizzas.stream().filter(p->p.getId()==id).findFirst().get();
    }

    public void createOrUpdate(Pizza pizza, int id){
        pizza.setId(id);
        Optional<Pizza> old = pizzas.stream().filter(p -> p.getId() == id).findFirst();
        if(old.isPresent()){
            pizzas.remove(old.get());
        }
        pizzas.add(pizza);
    }

    public void deleteById(int id){
        Pizza pizza = pizzas.stream().filter(p->p.getId()==id).findFirst().get();
        pizzas.remove(pizza);
    }
}
