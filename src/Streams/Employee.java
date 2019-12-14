package Streams;

public class Employee {
    private int id;
    private String name;
    private Double Salary;

    public Employee(int id, String name, Double Salary){
        this.id = id;
        this.name = name;
        this.Salary = Salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Salary=" + Salary +
                '}';
    }

    public Double getSalary() {
        return Salary;
    }

    public void setSalary(Double salary) {
        this.Salary = salary;
    }

    public void salaryIncrement(Double perc){
        if(this.Salary == null) return;
        this.Salary += (this.Salary * (perc / 100.0));
    }

}
