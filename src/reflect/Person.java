package reflect;

import java.lang.reflect.Field;

public class Person {
    private String name = "zhangsan";
    private String age;
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Person person = new Person();
        //打印没有改变属性之前的name值
        System.out.println("before：" + getPrivateValue(person, "name"));
        person.setName("lisi");
        //打印修改之后的name值
        System.out.println("after：" + getPrivateValue(person, "name"));
    }

    private static Object getPrivateValue(Person person, String fieldName) {

        try {
            // 根据变量名，返回一个成员变量（不分public和非public属性）
            Field field = person.getClass().getDeclaredField(fieldName);
            // 参数值为true，打开禁用访问控制检查
            //setAccessible(true) 并不是将方法的访问权限改成了public，而是取消java的权限控制检查。
            //所以即使是public方法，其accessible 属相默认也是false
            field.setAccessible(true);
            System.out.println(field.getType());
            return field.get(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
