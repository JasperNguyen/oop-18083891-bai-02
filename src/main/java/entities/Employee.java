
package entities;

import java.util.ArrayList;
import java.util.Objects;

public class Employee extends Person{

    public Employee(String _id, String _name, Integer _year, Integer _salary) {
        super(_id, _name, _year);
        this._salary = _salary;
    }

    public Employee(Integer _salary) {
        super();
        this._salary = _salary;
    }

    public Employee(){
        super();
        this._salary = 0;
    }

    @Override
    public ArrayList<String> getLinesDetail() {
        ArrayList<String> lines = super.getLinesDetail();
        lines.add("Salary: " + this._salary);
        return lines;
    }
      
    @Override
    public String toString() {
        return super.toString() + String.format("salary=%s, ", this._salary);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this._salary);
        return hash + super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        return Objects.equals(this._salary, other._salary);
    }

    
    
    private Integer _salary;

    public Integer getSalary() {
        return _salary;
    }

    public void setSalary(Integer _salary) {
        this._salary = _salary;
    }
}
