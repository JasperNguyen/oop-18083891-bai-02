package entities;

import java.util.ArrayList;
import java.util.Objects;

public class Manager extends Employee{

    public Manager(String _id, String _name, Integer _year, Integer _salary, Employee _assistant) {
        super(_id, _name, _year, _salary);
        this._assistant = _assistant;
    }

    public Manager(){
        this._assistant = null;
    }

    @Override
    public ArrayList<String> getLinesDetail() {
        ArrayList<String> lines = super.getLinesDetail();

        if(this._assistant == null){
            lines.add("Assistant: NULL");
            return lines;
        }

        lines.add("Assistant: ");

        ArrayList<String> assistantLinesDetail = this._assistant.getLinesDetail();
        for(String item: assistantLinesDetail){
            lines.add("    + " + item);
        }
        return lines;
    }
    
    @Override
    public String toString() {
        return super.toString() + String.format("Assistant=[%s], ", this._assistant);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this._assistant);
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
        final Manager other = (Manager) obj;
        return Objects.equals(this._assistant, other._assistant);
    }
    
    
    private Employee _assistant;

    public Employee getAssistant() {
        return _assistant;
    }

    public void setAssistant(Employee _assistant) {
        this._assistant = _assistant;
    }
    
}
