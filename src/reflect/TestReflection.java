package reflect;
/**
 /**
 * @ClassName: TestReflection
 * @Model : (所属模块名称)
 * @Description: 使用反射降低耦合度（通过接口来实现）
 * @author Administrator
 * @date 2017年6月2日 下午5:09:38
 */
public class TestReflection {

    /**
     /**
     * main (这里用一句话描述这个方法的作用)
     * @param args
     * void
     * @ModifiedPerson Administrator
     * @date 2017年6月2日 下午5:09:38
     */
    public static void main(String[] args) {
        //普通写法，使用New 关键字
        ITest iTest = createITest("ITestImpl1");
        iTest.testReflect();
        ITest iTest2 = createITest("ITestImpl2");
        iTest2.testReflect();

        //使用反射机制
        ITest iTest3 = createITest2("ITestImpl1");
        iTest3.testReflect();
        ITest iTest4 = createITest2("ITestImpl2");
        iTest4.testReflect();

    }

    /**
     * createITest 普通写法，使用New关键字，但是假设有1000个不同ITest需要创建,那你打算写1000个 if语句来返回不同的ITest对象?
     * @param name
     * @return
     * ITest
     * @ModifiedPerson Administrator
     * @date 2017年6月2日 下午5:32:21
     */
    public static ITest createITest(String name){

        if (name.equals("ITestImpl1")) {
            return new ITestImpl1();
        } else if(name.equals("ITestImpl2")){
            return new ITestImpl2();
        }

        return null;
    }

    /**
     * createITest2 使用反射机制:当有1000个不同ITest需要创建时，不用针对每个创建ITest对象
     * @param name
     * @return
     * ITest
     * @ModifiedPerson Administrator
     * @date 2017年6月2日 下午5:34:55
     */
    public static ITest createITest2(String name){
        try {
            Class<?> class1 = Class.forName(name);
            //Class<?> cls = iTest5.getClass;
            ITest iTest = (ITest) class1.newInstance();
            return iTest;
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        } catch (InstantiationException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }

        return null;

    }

}


interface ITest{
    public void testReflect();
}

class ITestImpl1 implements ITest{

    /* (non-Javadoc)
     * <p>Title: test</p>
     * <p>Description: </p>
     * @see ITest#test()
     */
    @Override
    public void testReflect() {

        System.out.println("I am ITestImpl1 !");
    }
}

class ITestImpl2 implements ITest{

    /* (non-Javadoc)
     * <p>Title: testReflect</p>
     * <p>Description: </p>
     * @see ITest#testReflect()
     */
    @Override
    public void testReflect() {

        System.out.println("I am ITestImpl2 !");
    }

}


