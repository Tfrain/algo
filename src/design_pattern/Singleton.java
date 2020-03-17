package design_pattern;

class Singleton {
    // 懒汉不安全
    private static Singleton uniqueInstance;
    private Singleton() {

    }

    public static Singleton getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
    // 饿汉安全
    private static Singleton uniqueInstance1 = new Singleton();
    // 懒汉安全
    public static synchronized Singleton getUniqueInstance1() {
        if (uniqueInstance1 == null) {
            uniqueInstance1 = new Singleton();
        }
        return uniqueInstance1;
    }

    // 双重校验安全
    private static volatile Singleton Instance;

    public static Singleton getInstance() {
        if (Instance == null) {
            synchronized (Singleton.class) {
                if(Instance == null) {
                    Instance = new Singleton();
                }
            }
        }
        return Instance;
    }

//    静态内部类
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance1() {
        return SingletonHolder.INSTANCE;
    }
//    枚举

}
enum Singleton1 {

    INSTANCE;

    private String objName;


    public String getObjName() {
        return objName;
    }


    public void setObjName(String objName) {
        this.objName = objName;
    }


    public static void main(String[] args) {

        // 单例测试
        Singleton1 firstSingleton = Singleton1.INSTANCE;
        firstSingleton.setObjName("firstName");
        System.out.println(firstSingleton.getObjName());
        Singleton1 secondSingleton = Singleton1.INSTANCE;
        secondSingleton.setObjName("secondName");
        System.out.println(firstSingleton.getObjName());
        System.out.println(secondSingleton.getObjName());

        // 反射获取实例测试
        try {
            Singleton1[] enumConstants = Singleton1.class.getEnumConstants();
            for (Singleton1 enumConstant : enumConstants) {
                System.out.println(enumConstant.getObjName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
