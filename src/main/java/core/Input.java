package core;

import java.util.Scanner;

public class Input {

    public Input() {
        this.scan = new Scanner(System.in);
    }
    
    /**
     * @param val gia tri kieu string can kiem tra
     *  => Tra ve true khi cac phan tu nam trong khoang 0->9
     *  => Cac truong hop con lai tra ve false
     */
    private boolean isInteger(String val){
        if(val.length() == 0) return false;
        
        int startIndex = 0;
        char[] tmp =  val.toCharArray();

        if(tmp[0] == '-') startIndex = 1;
        
        for(int i = startIndex; i < tmp.length; i++){
            char item = tmp[i];
            
            if(item < '0' || item > '9'){
                return false;
            }
        }
        return true;
    }
    
    /**
     * @param val gia tri kieu string can kiem tra
     * => Tra ve true khi cac phan tu nam trong khoang 0->9 va xuat hien duy nhat 1 lan dau '.'
     * => Cac truong hop con lai tra ve false
     */
    private boolean isDouble(String val){
        if(val.length() == 0) return false;
        
        int startIndex = 0;
        boolean daXuatHienDauCham = false;
        char[] tmp =  val.toCharArray();

        if(tmp[0] == '-') startIndex = 1;
        
        for(int i = startIndex; i < tmp.length; i++){
            char item = tmp[i];

            if(item < '0' || item > '9'){
                if(daXuatHienDauCham || item != '.') return false;
                daXuatHienDauCham = true;
            }
        }
        return true;
    }
    
    
    public String nextString(String mess){
        System.out.print("[INPUT] " + mess);
        return this.scan.nextLine();
    }
    
    /**
     * @param mess Dong thong bao se duoc in ra man hinh
     * Neu nguoi dung nhap sai (khong phai Integer thi yeu cau nhap lai)
     * Tra ve gia tri Integer duoc nguoi dung nhap vao tu ban phim
     */
    public Integer nextInteger(String mess){
        while(true){
            String tmp = this.nextString(mess);
            
            if(this.isInteger(tmp)){
                return Integer.parseInt(tmp);
            }
            System.out.println("[ERROR] Gia tri ban vua nhap vao khong phai la so nguyen !!!");
        }
    }
    
    
    /**
     * @param mess Dong thong bao se duoc in ra man hinh
     * Neu nguoi dung nhap sai (khong phai Double thi yeu cau nhap lai)
     * Tra ve gia tri Double duoc nguoi dung nhap vao tu ban phim
     */
    public Double nextDouble(String mess){
        while(true){
            String tmp = this.nextString(mess);
            
            if(this.isDouble(tmp)){
                return Double.parseDouble(tmp);
            }
            System.out.println("[ERROR] Gia tri ban vua nhap vao khong phai la so thuc !!!");
        }
    }
    
    private final Scanner scan;
    
    public Scanner getScan(){
        return this.scan;
    }
}
