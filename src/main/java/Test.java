import entities.Employee;
import entities.Manager;
import entities.Person;

import java.util.ArrayList;
import java.util.Random;

import core.Input;

public class Test extends Input{
    public Test(){
        this._random = new Random();
        this.initialize();
    }

    private void initialize(){
        this._company = new Company();
    }

    public Integer getOption(){
        System.out.println("\n\n\n0: Random mot mang gom: 20 Person, 10 Employee, 5 Manager");
        System.out.println("1: Tinh xem co bao nhieu nguoi lam tro ly");
        System.out.println("2: Nhap vao mot employee va kiem tra employee nay co ton tai");
        System.out.println("3: Tinh tong luong cua toan cong ty, in ra nguoi co luong cao nhat");
        System.out.println("4: Them mot nhan vien vao cong ty tu ban phim");
        System.out.println("5: In danh sach nhan vien cua cong ty hien tai");
        System.out.println("6: Thoat");
        System.out.println("7: Reset");
        
        Integer result = -1;
        while(true){
            result = nextInteger("=> Nhap vao lua chon: ");
            if(result >= 0 && result <= 6){
                break;
            }
        }

        return result;
    }

    public void runTest(){
        while(true){
            switch(this.getOption()){
                // 0: Random mot mang gom: 20 Person, 10 Employee, 5 Manager
                case 0->{ 
                    this.randomDuLieu();
                    break;
                }
                
                // 1: Tinh xem co bao nhieu nguoi lam tro ly
                case 1->{ 
                    System.out.println("So nguoi lam tro ly la: " + this._company.assistantCount());
                    break;
                }

                // 2: Nhap vao mot employee va kiem tra employee nay co ton tai
                case 2->{ 
                    this.nhapVaoMotEmployeeVaKiemTraTonTai();
                    break;
                }

                // 3: Tinh tong luong cua toan cong ty, in ra nguoi co luong cao nhat
                case 3->{ 
                    this._company.displayTotalSalaryAndHighestSalaryPerson();
                    break;
                }

                // 4: Them nhan vien vao cong ty tu ban phim
                case 4->{
                    this.nhapTuBanPhim();
                    break;
                }

                // 5: In danh sach nhan vien cua cong ty hien tai
                case 5 ->{
                    this._company.displayMembersList(true);
                    break;
                }

                // 6: Thoat
                case 6 ->{
                    return;
                }

                // 7: Reset
                case 7 ->{
                    initialize();
                }

            }
        }
        
    }
    
    private String randomName(){
        String lastName = this._lastName[this._random.nextInt(this._lastName.length)];
        String firstName = this._firstName[this._random.nextInt(this._firstName.length)];
        return lastName + " " + firstName;
    }

    // Random dữ liệu cho 20 person, 10 employee, 5 manager
    public void randomDuLieu(){
        System.out.println("\n\nTEST CAU A, CAU B ================================");
        // Thêm 20 person
        for(int i = 0; i < 20; i++){
            this._company.add(new Person(
                "Person_" + Integer.toString(i), // ID
                this.randomName(), // Name
                20 + this._random.nextInt(50) // Year
            ));
        }

        // Thêm 10 Employee
        ArrayList<Employee> employeesList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            Employee newEmployee = new Employee(
                "Employee_" + Integer.toString(i), // ID
                this.randomName(), // Name
                20 + this._random.nextInt(50), // Year
                100 + this._random.nextInt(1000) // Salary
            );

            employeesList.add(newEmployee);
            this._company.add(newEmployee);
        }

        // Thêm 5 Manager
        for(int i = 0; i < 5; i++){

            // Cần lưu lại size của employeesList vì size sẽ 
            // được gọi lại nhiều lần gây ảnh hưởng đến hiệu suất

            int employeesListCount = employeesList.size();

            this._company.add(new Manager(
                "Manager_" + Integer.toString(i), // ID
                this.randomName(), // Name
                20 + this._random.nextInt(50), // Year
                100 + this._random.nextInt(1000), // Salary
                employeesList.get(this._random.nextInt(employeesListCount))
            ));
        }

        System.out.println("Tong so member trong cong ty hien tai: " + this._company.count());
    }

    // Nhập dữ liệu từ bàn phím
    public void nhapTuBanPhim(){
        while (true) {
            this._company.add();
            // break
            String option = super.nextString("Ban co muon tiep tuc nhap them ? (y, n): ");
            if(!option.equals("y")){
                break;
            }
        }
        
    }

    public void nhapVaoMotEmployeeVaKiemTraTonTai(){
        if(this._company.employeeExists()){
            System.out.println("=> Employee nay co trong danh sach !");
        }
        else{
            System.out.println("=> Khong ton tai employee nay trong danh sach !");
        }
    }

    private Company _company;
    private final Random _random;

    private final String[] _lastName = {
        "Nguyen", "Tran", "Le", "Ly", "Ngo",
        "Pham", "Phan", "Dang"
    };

    
    private final String[] _firstName = {
        "Minh Anh", "Nguyet Anh", "Kim Chi", "My Duyen", "My Dung", "Anh Duong",
        "Tuyet Nhung", "Nha Phuong", "Cat Tien", "Anh Thu", "Hoang Son", "Son Dang",
        "Van Vinh", "Hai Minh", "The Vy", "Khac Tu", "Tuan Tu", "Ba Thong", "Huu Thanh",
    };
}
