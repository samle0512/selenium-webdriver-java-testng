package javaTester;

public class Topic_14_Constructor {
    //1 class nếu ko define constructor cụ thể thì nó sẽ có 1 constructor rỗng (default)

    public Topic_14_Constructor(String name) {
        //Constructor là 1 hàm, cùng tên với class, ko có kiểu dữ liệu trả về
        //Trong 1 class có thể có nhiều constructors vẫn được. Nếu ko define -> sẽ có 1 constructor rỗng (default)
        //Nếu mình define thì khi khởi tạo nó bắt buộc phải gọi tới constructor mà mình define
        //Nó đại diện cho 1 cái gọi là tính đa hình thái (Polymorphism)

        System.out.println(name);
    }
    public Topic_14_Constructor(int numberStrudent) {
        System.out.println(numberStrudent);
    }

    public static void main(String [] args){
        Topic_14_Constructor topic01 = new Topic_14_Constructor("Testing");
        Topic_14_Constructor topic02 = new Topic_14_Constructor(15);
    }
}
