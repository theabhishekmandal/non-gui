package reflection.modifiers.modifiers_test;

import reflection.modifiers.modifiers_test.auction.Auction;
import reflection.modifiers.modifiers_test.auction.Bid;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        printClassModifiers(Auction.class);
        printClassModifiers(Bid.class);
        printClassModifiers(Bid.Builder.class);
        printClassModifiers(Class.
                forName("reflection.modifiers.modifiers_test.auction.Bid$Builder$BidImpl"));
        printClassModifiers(Serializable.class);
        printMethodModifiers(Auction.class.getDeclaredMethods());
        printFieldModifiers(Auction.class.getDeclaredFields());
    }

    public static void printFieldModifiers(Field[] fields) {
        for (Field field : fields) {
            int modifier = field.getModifiers();
            System.out.printf("%s access modifier is %s%n",
                    field.getName(), getAccessModifierName(modifier));
            if (Modifier.isFinal(modifier)) {
                System.out.println("The field is final");
            }
            if (Modifier.isTransient(modifier)) {
                System.out.println("The field is transient and will not be serialized");
            }
            if (Modifier.isVolatile(modifier)) {
                System.out.println("The field is volatile");
            }
            System.out.println();
        }
    }

    public static void printMethodModifiers(Method[] methods) {
        for (Method method : methods) {
            int modifier = method.getModifiers();
            System.out.printf("%s() access modifier is %s%n",
                    method.getName(), getAccessModifierName(modifier));

            if (Modifier.isSynchronized(modifier)) {
                System.out.println("The method is synchronized");
            } else {
                System.out.println("The method is not synchronized");
            }
            System.out.println();
        }
    }

    public static void printClassModifiers(Class<?> clazz) {
        int modifier = clazz.getModifiers();
        System.out.printf("Class %s access modifiers is %s%n",
                clazz.getSimpleName(), getAccessModifierName(modifier));
        if (Modifier.isStatic(modifier)) {
            System.out.println("The class is static");
        }
        if (Modifier.isAbstract(modifier)) {
            System.out.println("The class is abstract");
        }

        if (Modifier.isInterface(modifier)) {
            System.out.println("The class is an interface");
        }
    }


    public static String getAccessModifierName(int modifier) {
        if (Modifier.isPublic(modifier)) {
            return "public";
        } else if (Modifier.isPrivate(modifier)) {
            return "private";
        } else if (Modifier.isProtected(modifier)) {
            return "protected";
        } else {
            return "package-private";
        }
    }

    private static void runAuction() {
        Auction auction = new Auction();
        auction.startAuction();

        Bid bid1 = Bid.builder()
                .setBidderName("Company1")
                .setPrice(10)
                .build();
        auction.addBid(bid1);

        Bid bid2 = Bid.builder()
                .setBidderName("Company2")
                .setPrice(12)
                .build();

        auction.addBid(bid2);
        auction.stopAuction();

        System.out.println(auction.getAllBids());
        System.out.println("Highest bid" + auction.getHighestBid());
    }
}
