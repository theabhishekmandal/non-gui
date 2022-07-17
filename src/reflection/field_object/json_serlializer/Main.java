package reflection.field_object.json_serlializer;

import reflection.field_object.json_serlializer.data.*;

/**
 * This is an example of how a basic object to json converter works.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Company company = new Company("Deutsche Telekom", "Haryana",
                new Address("Building 9b", (short) 15, "10092"));
        Address address = new Address("Street No. 4", (short) 12, "201306");
        Person person = new Person("Abhishek", true, 25, 100, address, company);
//        System.out.println(objectToJson(address, 3));
        System.out.println(JsonConverter.getInstance().objectToJson(person, 0));


        Actor actor1 = new Actor("Elijah Wood", new String[]{"Lord of the Rings", "The Good son"});
        Actor actor2 = new Actor("Ian McKellen", new String[]{"X-men", "Hobbit"});
        Actor actor3 = new Actor("Orlando Bloom", new String[]{"Pirates of the Caribean", "Kingdom of heaven"});

        Movie movie = new Movie("Lord of the Rings", 8.8f, new String[]{"Action", "Adventure", "Drama"}, new Actor[]{actor1, actor2, actor3});
        System.out.println(JsonConverter.getInstance().objectToJson(movie, 0));
    }

}
