package data_structure;

import java.util.Arrays;

import static java.lang.System.*;
public class SegmentTree{
    static int[] SegmentTree;
    SegmentTree(int[] arr){
        int x = (int)Math.ceil(Math.log(arr.length - 1) / Math.log(2));
        int maxsize = 2 * (int)Math.pow(2, x) - 1;
        SegmentTree = new int[maxsize];
        createtree(arr, 0, arr.length - 1, 0);
    }
    private  int getmid(int one, int two){
        return one + (two - one) / 2;
    }
    private int createtree(int[] arr, int start, int end, int index) {
        if(start == end){
            SegmentTree[index] = arr[start];
            return SegmentTree[index];
        }
        int mid = getmid(start, end);
        SegmentTree[index] = createtree(arr, start, mid, 2 * index + 1) +
                    createtree(arr, mid + 1, end, 2 * index + 2);
        return SegmentTree[index];
    }
    private int getsumcheck(int segmentstart, int segmentend, int arraystart, int arrayend, int index){
        if(arraystart <= segmentstart && arrayend >= segmentend){
            return SegmentTree[index];
        }
        if(arraystart > segmentend || segmentstart > arrayend)
            return 0;
        int mid = getmid(segmentstart, segmentend);
        return getsumcheck(segmentstart, mid, arraystart, arrayend, 2 * index + 1) +
                getsumcheck(mid + 1, segmentend, arraystart, arrayend, 2 * index + 2);
    }
    private int getsum(int[] arr, int arraystart, int arrayend){
        if(arraystart < 0 ||  arrayend > arr.length - 1 ||  arrayend < arraystart){
            return -1;
        }
        return getsumcheck( 0, arr.length - 1, arraystart, arrayend, 0);
    }
    private void printArray(){
        out.println(Arrays.toString(SegmentTree));
    }
    private void updateutil(int segementstart, int segementend, int arrayindex, int diff, int segindex){
        if(segementend < arrayindex || segementstart > arrayindex)
            return ;
        SegmentTree[segindex] += diff;
        if(segementstart != segementend){
            int mid = getmid(segementstart, segementend);
            updateutil(segementstart, mid, arrayindex, diff, 2 * segindex + 1);
            updateutil(mid + 1, segementend, arrayindex, diff, 2 * segementend + 2);
        }
    }
    private void update(int[] arr, int index, int val){
        if(index < 0 || index > arr.length - 1){
            err.println("Invalid index");
            return;
        }
        int diff = val - arr[index];
        arr[index] = val;
        updateutil( 0, arr.length - 1, index, diff, 0);
    }
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7 , 9, 11};
        SegmentTree ob = new SegmentTree(arr);
        ob.printArray();
        out.println(ob.getsum(arr, 0 , 1));
        ob.update(arr, 0, 14);
        out.println(Arrays.toString(arr));
        ob.printArray();

    }
}


