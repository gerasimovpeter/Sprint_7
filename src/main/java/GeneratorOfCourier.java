public class GeneratorOfCourier {
    public static Courier getCourierWithExistingLogin() {
        return new Courier("testSashaUser", "12345", "Sasha");
    }
    public static Courier getNewValidCourier() {
        return new Courier("testMaximUser", "12345", "Maxim");
    }
    public static Courier getCourierWithEmptyLogin(){
        return new Courier(null, "123456", "oleg");
    }
    public static Courier getCourierWithEmptyName(){
        return new Courier("zahar", "zahar577", null);
    }
    public static Courier getCourierWithEmptyPassword(){
        return new Courier("ivanov", null, "ivan");
    }


}
