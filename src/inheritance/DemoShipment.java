package inheritance;
/**
 * this program shows how to call overridden method of superclass
 */
class Hello {
    void show() {
        System.out.println("this is the super class");
    }
}

class Hi extends Hello {
    @Override
    void show() {
        super.show();
        System.out.println("this is the sub class");
    }
}

class DemoShipment {
    public static void main(String[] args) {
        Hi object = new Hi();
        Hello obj2 = object; // can assign parent object to child object but not viceversa
        object.show();
    }
}