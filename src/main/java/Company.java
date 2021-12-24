
import entities.Employee;
import entities.Manager;
import entities.Person;

import java.util.ArrayList;
import java.util.HashSet;

import core.Input;

public class Company extends Input{
    
    public Company() {
        this._membersList = new ArrayList<>();
    }

    public Integer count(){
        return this._membersList.size();
    }

    public Person inputMember(){
        Integer tmp = -1;
        System.out.println("\nTHEM NHAN VIEN VAO CONG TY");
        System.out.println("0: Person");
        System.out.println("1: Employee");
        System.out.println("2: Manager");
        System.out.println("3: {Exit}");
        Person person;
        while(true){
            tmp = super.nextInteger("Nhap lua chon cua ban: ");
            if(tmp >= 0 && tmp <= 3){
                break;
            }
        }

        // Init
        switch(tmp){
            case 0 -> {
                person = new Person();
            }
            case 1 -> {
                person = new Employee();
            }
            case 2 -> {
                person = new Manager();
            }
            case 3 -> {
                return null;
            }
            default -> {
                return null;
            }
        }

        // Nhap du lieu tu ban phim
        person = this.inputPerson(person);
        if(tmp > 0) person = this.inputEmployee(person);
        if(tmp > 1) person = this.inputManager(person);

        return person;
    }

    // Thêm từ bàn phím
    public Boolean add(){
        Person person = this.inputMember();
        if(person == null){
            return false;
        }

        return this._membersList.add(person);
    }

    public Boolean add(Person person){
        return this._membersList.add(person);
    }


    public Person get(Integer index){
        if(index >=  this._membersList.size()){
            System.err.println("index >= membersList.size(): index=" + index);
            return null;
        }

        return this.get(index);
    }

    public Person get(String id){
        for(Person person: this._membersList){
            if(id.equals(person.getId())){
                return person;
            }
        }
        return null;
    }

    // Tính xem có bao nhieu người trong công ty làm trợ lý
    public Integer assistantCount(){
        HashSet<Employee> assistantsList = new HashSet<>();

        for(Person person: this._membersList){
            // Bỏ qua person không phải là manager
            if(person.getClass() != Manager.class) continue;

            Manager manager = (Manager)person;
            Employee assistant = manager.getAssistant();

            // Bỏ qua giá trị null
            if(assistant == null) continue;

            // Add
            assistantsList.add(assistant);
        }

        return assistantsList.size();
    }


    // Kiểm tra employee có tồn tại
    public Boolean employeeExists(Employee employee){
        return this._membersList.contains((Person)employee);
    }

    public Boolean employeeExists(String employeeID){
        for (Person person : this._membersList) {
            if(
                person.getClass() == Employee.class
                && person.getId().equals(employeeID)
            ) return true;
        }
        return false;
    }

    public Boolean employeeExists(){
        System.out.println("0: Kiem tra ton tai cua employee theo ID");
        System.out.println("1: Nhap toan bo du lieu roi kiem tra");
        Integer luaChon = -1;

        while(true){
            luaChon = super.nextInteger("Nhap lua chon cua ban: ");
            if(luaChon == 0 || luaChon==1){
                break;
            }
        }

        switch(luaChon){
            case 0 -> {
                return this.employeeExists(super.nextString("Nhap ID employee can tim: "));
            }
            case 1 -> {
                // Khởi tạo và nhập dữ liệu từ bàn phím
                Person person = new Employee();
                person = this.inputPerson(person);
                person = this.inputEmployee(person);
                return this.employeeExists((Employee)person);
            }
        }

        return false;
        
    }


    public void displayTotalSalaryAndHighestSalaryPerson(){
        Integer totalSalary = 0;
        Employee highestSalaryPerson = null;

        // Tìm người có thuộc tính là lương(salary) đầu tiên
        for (Person person : this._membersList) {
            // Nếu là person thì bỏ qua
            if(person.getClass() == Person.class) continue;

            // menber có thể là manager hoặc employee
            Employee member = (Employee)person;
            Integer salary = member.getSalary();

            // debug
            // System.out.println(member.getId() + " = " + salary);

            totalSalary += salary;

            // + Nếu highestSalaryPerson == null hoặc lương của member cao hơn thì
            //      gán highestSalaryPerson =  member
            // + Bởi vì highestSalaryPerson được kiểm tra là khác null trước
            //      => Không xảy ra lỗi highestSalaryPerson == null
            if(
                highestSalaryPerson == null
                || salary > highestSalaryPerson.getSalary())
            {
                highestSalaryPerson = member;
            }
        }


        // + Nếu không có thành viên nào trong công ty là nhân viên (employee)
        //     hoặc quản lý (manager) thông báo và stop.
        // + Chỉ có employee và manager mới có thuộc tính là lương(salary)
        // + Nếu không có lương thì
        //     tổng lương của công ty = 0
        //     người có lương cao nhất = null
        System.out.println("+ Tong so luong cua cong ty la: " + totalSalary + "$");

        if(highestSalaryPerson == null){
            System.out.println("Khong co thanh vien nao trong cong ty la nhan vien(employee) hoac quan ly(manager) !");
        }
        else{
            System.out.println("+ Thong tin cua nguoi co luong cao nhat la: ");
            System.out.println(highestSalaryPerson.getDetail(true));
        }
    }    
    
    public void displayMembersList(Boolean pretty){
        for(Person person: this._membersList){
            System.out.println(person.getDetail(pretty));
        }
    }

    public ArrayList<String> getLinesMembersDetail(){
        ArrayList<String> lines = new ArrayList<>();
        for(Person person: this._membersList){   
            lines.addAll(person.getLinesDetail());
            lines.add("\n");
        }
        return lines;
    }


    // Các phương thức thêm dữ liệu cho person từ bàn phím
    private Person inputPerson(Person person){
        person.setId(super.nextString("ID: "));
        person.setName(super.nextString("Name: "));
        person.setYear(super.nextInteger("Year: "));
        return person;
    }

    private Employee inputEmployee(Person person){
        Employee employee = (Employee)person;
        employee.setSalary(super.nextInteger("Salary: "));
        return employee;
    }

    private Manager inputManager(Person person){
        Manager manager = (Manager)person;

        Employee assistant = null;

        while(true){
            String id = super.nextString("Assistant (Employee ID): ");
            Person p = this.get(id);

            if(p != null && p.getClass()==Employee.class){
                assistant = (Employee)p;
                break;
            }

            System.out.println("Khong tim thay Employee nao trong cong ty co ID=" + id);
            if(!"y".equals(super.nextString("Ban co muon nhap lai ? (y, n): "))){
                break;
            }
        }

        manager.setAssistant(assistant);
        return manager;
    }

    private final ArrayList<Person> _membersList;
    
}
