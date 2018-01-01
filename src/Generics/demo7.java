
package Generics;
/**
 *  This is an example of bounded Wild Cards.
 *
 *  We can apply an upper bound and lower bound on the wild cards.
 *
 *  1) Here in this example we have three class: TwoD (having x and y coordinates)
 *  										  ThreeD (having x, y and z coordinates)
 *  										  FourD (having x, y, z and t coordinates)
 *
 *  2) A generic class is present which is having an uppper bound on TwoD class ,it means
 *  only TwoD class and it's subclasses are allowed in this generic class. Also the generic class is
 *  containing object array
 *
 *  3) order of inheritence TwoD -> ThreeD -> FourD
 *
 *
 *  now we want to display the x and y coordinates of all the classes(geng<ThreeD>, geng<TwoD>, geng<FourD>), which is
 *  done through showXY() method
 *
 *  now we want to display the x , y and z coordinates of ThreeD and FourD classes using showXYZ(),
 *  But the problem is that  TwoD classes does not have the third coordinate. So how to prevent the TwoD class object
 *  from using the method of showXYZ()
 *
 *  The solution is that we make an upper bound wildcard of geng<ThreeD> on the showXYZ(). Due to
 *  this if the geng<TwoD> tries to access the showXYZ() then a compile time error will be shown thus
 *  ensuring type safety
 *
*/

//Two Dimensional Coordinates

class TwoD
{
	int x, y;
	TwoD(int a, int b)
	{
		x = a;
		y = b;
	}
}
//Threee Dimenisonal Coordinates
class ThreeD extends TwoD
{
	int z;
	ThreeD(int a, int b, int c)
	{
		super(a,b);
		z = c;
	}
}

//Four Dimensional Coordinates
class FourD extends ThreeD
{
	int t;
	FourD(int a, int b, int c, int d)
	{
		super(a, b, c);
		t = d;
	}
}
class geng<T extends TwoD>
{
	protected T[] coords;
	geng(T[] ob)
	{
		this.coords  = ob;
	}
}

public class demo7 {
    static void showXY(geng<?> c) {
        System.out.println("The x y coordinates are:");
        for (TwoD t : c.coords) {
            System.out.println(t.x + " " + t.y);
        }
    }

    static void showXYZ(geng<? extends ThreeD> c) {
        System.out.println("X Y Z coordinates are:");
        for (ThreeD t : c.coords) {
            System.out.println(t.x + " " + t.y + " " + t.z);
        }
        System.out.println();
    }

    static void showall(geng<? extends FourD> c) {
        System.out.println("X Y Z T coordinates are:");
        for (FourD t : c.coords) {
            System.out.println(t.x + " " + t.y + " " + t.z + " " + t.t);
        }
        System.out.println();
    }

    public static void main(String args[]) {
        TwoD[] two = {
                new TwoD(10, 20),
                new TwoD(30, 40)
        };

        ThreeD[] three = {
                new ThreeD(50, 60, 70),
                new ThreeD(80, 90, 100)
        };

        FourD[] four = {
                new FourD(110, 120, 130, 140),
                new FourD(150, 160, 170, 180)
        };

        geng<TwoD> arr = new geng<>(two);
        geng<ThreeD> arr1 = new geng<>(three);
        geng<FourD> arr2 = new geng<>(four);

        //Displaying x and y coordinates of all TwoD , ThreeD and FourD class object arrays
        showXY(arr);
        showXY(arr1);
        showXY(arr2);

        //Displaying x , y and z coordinates of ThreeD and FourD class object arrays
        //showXYZ(arr); this will show compile time error because there is upper bound on Three D class object
        showXYZ(arr1);
        showXYZ(arr2);

        //Displaying x, y, z and t class object arrays
        //showall(arr);
        //showall(arr1); both will show comile time error
        showall(arr2);
    }
}
