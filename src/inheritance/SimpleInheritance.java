package inheritance;
/**
 * this program shows how to call overridden method of superclass
 */
class Hello {
    protected void show() {
        System.out.println("this is the super class");
    }
}

class Hi extends Hello {
    @Override
    public void show() {
        super.show();
        System.out.println("this is the sub class");
    }
}

class SimpleInheritance {
    public static void main(String[] args) {
        Hello obj2 = new Hi(); // can assign parent object to child object but not viceversa
        obj2.show();
    }
}