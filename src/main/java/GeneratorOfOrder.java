import java.util.List;

public class GeneratorOfOrder {

    public static Order getNewOrder(List<String> color){
        return new Order(
                "Peter",
                "Gerasimov",
                "Moscow",
                4,
                "+7 964 745 85 11",
                5,
                "2023-02-02",
                "No Comments",
                color);
    }
}
