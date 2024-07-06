package stream;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams3 {
    // Advance stream
    public static void main(String[] args) {
        // int[] to arraylist
        int[] nums = {1,2,2,1,8,5,4,3,4,5};
        IntStream intStream = Arrays.stream(nums);
        Stream<Integer> stream = intStream.boxed();
        List<Integer> list = stream.collect(Collectors.toList());
        System.out.println(Arrays.toString(nums));
        System.out.println(list);
        // arraylit to int[]
        int[] nums1 = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(nums1));

        Map<Integer,Long> map = Arrays.stream(nums1).boxed().collect(Collectors.groupingBy(e ->e, Collectors.counting())); // Collectors.counting return Long
        // groupingBy will let the value become key , eg e->e is key, counting is value
        // groupingBy(key) , groupingBy(key,second key) ,groupingBy(key, value) , key can be customize , eg if String (e.getName()+ "_" + e.getBrand()) 零食_月饼
        // so that the grouping will be two Map Map<String, Map<String,List<Product>>>
        // Therefore groupingBy can 1 parameter or 2 parameter, while the 2 parameter can be a value OR second grouping key

        // if inner class
        /*
        Streams3 streams3 = new Streams3();
        Streams3.Product product = streams3.new Product(2,"Candy", "Kinder");
        OR
        Product p1 = new Streams3().new Product(2,"Candy", "Kinder");
        */
        Product p1 = new Product(2,"Candy", "Kinder");
        Product p2 = new Product(4,"Chocolate", "Kinder");
        Product p3 = new Product(4,"Chocolate", "JP");
        Product p4 = new Product(4,"Candy", "JP");
        Product p5 = new Product(4,"Popsicle", "JP");
        Product p6 = new Product(4,"Popsicle", "Angry");
        List<Product> prodList = Arrays.asList(p1,p2,p3,p4,p5,p6);
        Map<String, List<Product>> prodMap= prodList.stream().collect(Collectors.groupingBy(item -> {
            if(item.getNum() < 3) {
                return "3";
            }else {
                return "other";
            }
        }));
        // Then we can get String that store a list of Product
        // Product with num < 3
        System.out.println(prodMap);
        List<Product> productWithLessThan3 = prodMap.get("3");
        Product product = productWithLessThan3.get(0);
        System.out.println(product.getNum() + " " + product.getBrand() + " " + product.getName()); // 2 Kinder Candy

        // 2 grouping
        Map<String, Map<String, List<Product>>> prodMap2= prodList.stream().collect(Collectors.groupingBy(Product::getBrand, Collectors.groupingBy(item -> {
            if(item.getNum() < 3) {
                return "3";
            }else {
                return "other";
            }
        })));
        System.out.println(prodMap2);

        // use Couting
        Map<String, Long> foodType = prodList.stream().collect(Collectors.groupingBy(Product::getName, Collectors.counting()));
        System.out.println(foodType);
        // summingInt, summingLong etc
        Map<String, Integer> foodSum = prodList.stream().collect(Collectors.groupingBy(Product::getName, Collectors.summingInt(Product::getNum)));
        System.out.println(foodSum);
        // use maxBy and collectingAndThen , which turn the result of List<Product> to Product by collectingAndThen(maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)
        Map<String, Product> foodMax = prodList.stream().collect(Collectors.groupingBy(Product::getName, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Product::getNum)), Optional::get)));
        System.out.println(foodMax);
        Map<String, Long> foodMin = prodList.stream().collect(Collectors.groupingBy(Product::getName, Collectors.counting()));
        System.out.println(foodMin);

        // Partiotion by
        Map<Boolean, List<Product>> partitionResult = prodList.stream().collect(Collectors.partitioningBy(e -> e.getNum() > 3));
        partitionResult.forEach((k,v) -> System.out.println("key " + k + " value " +v));

        // Grouping by (get a Object
        Map<Object, List<Product>> groupingResult = prodList.stream().collect(Collectors.groupingBy(Product::getBrand));
        groupingResult.forEach((k,v) -> System.out.println("key " + k + " value " + v));
    }
}

class Product {
    int num;
    String name;
    String brand;

    public Product(int num, String name, String brand) {
        this.num = num;
        this.name = name;
        this.brand = brand;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
