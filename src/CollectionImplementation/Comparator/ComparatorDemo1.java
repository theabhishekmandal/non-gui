package CollectionImplementation.Comparator;
import java.util.*;

public class ComparatorDemo1 {

	public static void main(String[] args) {

		// Creating a TreeSet in which elements will be inserted in descending order
		// passing a comparator object and thereby implementing the compare method
		// for customized sorting order.
		TreeSet<Integer> arr = new TreeSet<>(new Comparator<Integer>(){
			@Override
			public int compare(Integer one, Integer two){
				return -one.compareTo(two);
			}
		});
		for(int i = 1; i < 11; i++) arr.add(i);
		System.out.println(arr);


		// implementing the reversed method
		// first creating a comparator for natural sorting order
		// then using reversed method to return a reverse comparator and sorting in descending order
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};

		ArrayList<String> hel = new ArrayList<>();
		for(char ch = 'a'; ch <= 'z'; ch++) hel.add(ch + "");
		Collections.sort(hel, comparator.reversed());
		System.out.println(hel);


		// implementing the reverseOrder method
		ArrayList<City> helobj = new ArrayList<>();
		helobj.add(new City("Delhi"));
		helobj.add(new City("Mumbai"));
		helobj.add(new City("Kolkata"));
		helobj.add(new City("Chennai"));

		// implementing the comparing method of comparator
		/*
		 This Comparator type can be replaced by Comparator.comparing
		 Comparator<City>  cityComparator = (City one, City two) -> {
		    return one.getName().compareTo(two.getName());
		 };
		*/
		//first getting the comparator using Comparator.comparing and then reversing it with reverseOrder method
		Comparator<City> cityComparator = Collections.reverseOrder(Comparator.comparing(City::getName));
		Collections.sort(helobj, cityComparator);
		System.out.println(helobj);

		// implementing the comparing method and also by providing the keyComp
		// the above comparison can also be performed by using the below way
		Comparator<City> cityComparator1 = Comparator.comparing(City::getName, Comparator.reverseOrder());
		helobj.sort(cityComparator1);
		System.out.println(helobj);
	}
}


class City{
	private String name;
	City(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	@Override
	public String toString(){
		return "[" + this.name + "]";
	}
}