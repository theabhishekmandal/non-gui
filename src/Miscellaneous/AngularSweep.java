package Miscellaneous;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


/**
 *  Test cases:
 *  3
 *  6.47634 7.69628
 *  5.16828 4.79915
 *  6.69533 6.20378
 *
 */

public class AngularSweep {
    static class Point{
        Double x;
        Double y;
        Point(Double x, Double y){
            this.x = x;
            this.y = y;
        }
        static double getMagnitude(Point point, Point point2){
            return Math.sqrt(Math.pow(point2.x - point.x, 2) + Math.pow(point2.y - point.y, 2));
        }
        static double getAngle(Point point1, Point point2){
            return Math.atan((point1.y - point2.y) / (point1.x - point2.x));
        }
        Point subtract(Point point){
            double x = this.x - point.x;
            double y = this.y - point.y;
            return new Point(x, y);
        }
        @Override
        public String toString(){
            return "[" + this.x + " " + this.y + "]";
        }
    }
    static public class ComplexNumber{
        private double x, y;
        public ComplexNumber(double real, double imaginary){
            this.x = real;
            this.y = imaginary;
        }
        public double real(){
            return x;
        }
        public double imaginary(){
            return y;
        }
        public double magnitude(){
            return Math.sqrt(x * x + y * y);
        }
        public static double getMagnitude(ComplexNumber one, ComplexNumber two){
            return Math.sqrt(Math.pow(one.x - two.x, 2) + Math.pow(one.y - two.y, 2));
        }
        public String toString(){
            return "{" + x + "," + y + "}";
        }
        public static ComplexNumber add(ComplexNumber a, ComplexNumber b){
            return new ComplexNumber(a.x + b.x, a.y + b.y);
        }
        public static Double getAngle(ComplexNumber one, ComplexNumber two){
            return Math.atan((one.y - two.y) / (one.x - two.x));
        }
        public ComplexNumber add(ComplexNumber a){
            return new ComplexNumber(this.x + a.x , this.y + a.y);
        }
        public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b){
            return new ComplexNumber(a.x * b.x - a.y * b.y, a.x * b.y + a.y + b.x );
        }
        public ComplexNumber multiply(ComplexNumber a){
            return new ComplexNumber(this.x * a.x - this.y * a.y, this.y * a.x + this.x * a.y);
        }
    }
    static class pair<First extends Comparable<? super First>, Second extends Comparable<? super Second>> implements Comparable<pair<First, Second>>{
        public final First first;
        public final Second second;
        pair(First first, Second second){
            this.first = first;
            this.second = second;
        }
        @Override
        public String toString(){
            return this.first + " " + this.second;
        }
        @Override
        public boolean equals(Object o){
            if(this == o) return true;
            if(o == null || this.getClass() != o.getClass()) return false;
            pair<?, ?> pair = (pair<?, ?>) o;
            return this.first.equals(pair.first) && this.second.equals(pair.second);
        }
        @Override
        public int compareTo(pair<First, Second> o){
            int result = first.compareTo(o.first);
            return result == 0 ? second.compareTo(o.second) : result;
        }
        @Override
        public int hashCode(){
            return 31 * first.hashCode() * second.hashCode();
        }
    }
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        ComplexNumber[] pointsArray = new ComplexNumber[n];
        double diameter = 2.0;
        for(int i = 0; i < pointsArray.length; i++) pointsArray[i] = new ComplexNumber(s.nextDouble(), s.nextDouble());
        double[][] distance = new double[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(distance[i][j] == 0.0  && distance[j][i] == 0.0)
                    distance[i][j] = distance[j][i] = ComplexNumber.getMagnitude(pointsArray[i], pointsArray[j]);
            }
        }
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans = Math.max(ans, getAnswer(i, distance, pointsArray, diameter));
        }
        System.out.println(ans);

    }
    private static int getAnswer(int i, double[][] distance, ComplexNumber[] pointsArray, double diameter){
        List<pair<Double, Boolean>> list = new ArrayList<>();
        for(int j = 0; j < pointsArray.length; j++){
            if(i != j && distance[i][j] <= diameter){
                double A = Math.acos(distance[i][j] / diameter);
                double B = ComplexNumber.getAngle(pointsArray[i], pointsArray[j]);
                double alpha = A - B;
                double beta = A + B;
                list.add(new pair<>(alpha, true));
                list.add(new pair<>(beta, false));
            }
        }
        Collections.sort(list);
        int count = 1, res = 1;
        for(pair<Double, Boolean> pair: list){
            if(pair.second) count++;
            else count--;
            res = Math.max(res, count);
            System.out.println("Printing angles " + pair);
        }
        return res;
    }
}
  