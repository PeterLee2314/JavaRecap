// IVIT (Java 10)
public class IVIT {
    public static void main(String[] args) {
        // Local Variable Type Inference
        var nums = new int[10]; // LVTI
        int nums2[] = new int[10];
        nums2[0] = 123;
        nums = nums2;
        System.out.println(nums[0]);
        // allow object too

    }
}
