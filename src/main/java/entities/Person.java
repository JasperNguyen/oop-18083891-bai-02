
package entities;

import java.util.ArrayList;
import java.util.Objects;


public class Person {
    
    public Person(String _id, String _name, Integer _year) {
        this._id = _id;
        this._name = _name;
        this._year = _year;
    }
    
    public Person(){
        this._id = "";
        this._name = "";
        this._year = 0;
    }
    
    public String getDetail(){
        return getDetail(false);
    }

    public String getDetail(Boolean pretty){
        String result = "";
        if(pretty){
            ArrayList<String> lines = this.getLinesDetail();
            for(String line: lines){
                result += line + "\n";
            }
        }
        else{
            result = this.toString();
        }
        return result;
    }

    public ArrayList<String> getLinesDetail(){
        ArrayList<String> lines = new ArrayList<>();
        lines.add("ID: " + this._id);
        lines.add("Name: " + this._name);
        lines.add("Year: " + this._year);
        return lines;
    }
    
    @Override
    public String toString() {
        return String.format("ID=%s, Name=%s, Year=%s,", this._id, this._name, this._year);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this._name);
        hash = 71 * hash + Objects.hashCode(this._year);
        hash = 71 * hash + Objects.hashCode(this._id);
        return hash;
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
        final Person other = (Person) obj;
        if (!Objects.equals(this._name, other._name)) {
            return false;
        }
        if (!Objects.equals(this._id, other._id)) {
            return false;
        }
        return Objects.equals(this._year, other._year);
    }
    
    
    private String _name;
    private Integer _year;
    protected String _id;

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public Integer getYear() {
        return _year;
    }

    public void setYear(Integer _year) {
        this._year = _year;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }
}
