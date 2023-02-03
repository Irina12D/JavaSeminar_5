import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Comparator;

class DescendingOrder implements Comparator<Integer>
{
  //comparing two keys for sorting them in descending order
  public int compare(Integer i1, Integer i2)
  {
    return i2.compareTo(i1);
  }
}

public class HomeWork_5 {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        // Task No 1
        System.out.println("Task No 1");
        task1();
        System.out.println();

        //Task No 2
        System.out.println("Task No 2");
        task2();
        System.out.println();
        
        //Task No 3
        System.out.println("Task No 3");
        task3();
        System.out.println();
    }

    // =====================================================================================    
    static void task1(){
        PhoneBook phonebookImplementation = new PhoneBook();
        System.out.println(" Let's fill out the phone book");
        for (int i = 0; i < 5; i++) {
            System.out.print("  Enter the name of the contact: ");
            String name = scan.nextLine();
            System.out.print("  Enter the contact numbers (separated by a space): ");
            String numbers = scan.nextLine();
            phonebookImplementation.addContact(name.trim(), numbers);
        }
        phonebookImplementation.viewContact("Irina");
        phonebookImplementation.viewContact("Konstantin");
        phonebookImplementation.viewBook();
    }


    // =====================================================================================
    static void task2()
    {
        String namesList =  "Ivan Ivanov, Svetlana Petrova, Kristina Belova, " +  
                            "Anna Musina, Anna Krutova, Ivan Urin, " + 
                            "Petr Lykov, Pavel Chernov, Petr Chernysjov, " +  
                            "Maria Fedorova, Marina Svetlova, Maria Savina, " +  
                            "Maria Рыкова, Marina Lugova, Anna Vladimirova, " +  
                            "Ivan Mechnikov, Petr Petin, Ivan Ejov";
        TreeMap<String, Integer> treeMap = treeName(namesList);
        TreeMap<Integer, List<String>> sortTreeMap = SorttreeName(treeMap);
        System.out.println("  " + sortTreeMap);
    }

    static TreeMap<String, Integer> treeName(String s)
    {
        String[] names = s.split(", ");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (String name: names)
        {
            String[] str = name.split(" ");
            String key = str[0];
            if (treeMap.containsKey(key))
            {
                int value = treeMap.get(key) + 1;
                treeMap.put(key, value);
            }
            else
            {
                treeMap.put(key,1);
            }
        }
        return treeMap;
   }

   static TreeMap<Integer, List<String>> SorttreeName(TreeMap<String, Integer> treeMap)
    {
        /*
        TreeMap<Integer, List<String>> result = new TreeMap<>(new Comparator<Integer>().reversed() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);;
        }});
        */
        TreeMap<Integer, List<String>> result = new TreeMap<>(new DescendingOrder());
        Collection<String> keyVal = treeMap.keySet();
        for(String name: keyVal)
        {
            Integer newKey = treeMap.get(name);
            if(result.containsKey(newKey))
            {
                List<String> list = result.get(newKey);
                list.add(name);
            }
            else
            {
                List<String> list = new ArrayList<>();
                list.add(name);
                result.put(newKey,list);
            }
        }
        return result;
   }


   // =====================================================================================
   static void task3()
    {
        Random rnd = new Random();
        int n = rnd.nextInt(7, 12); 
        int[] mas = new int[n];
        for(int i=0; i<n; i++)
            mas[i] = rnd.nextInt(10, 99);
        System.out.println("  Source array: " + Arrays.toString(mas));
        heapSort(mas);
        System.out.println("  Sorted array: " + Arrays.toString(mas));        
    }

    static void heapSort(int[] sortArr) {
        int n = sortArr.length;
        int i = (n - 2) / 2;
        while (i >= 0) 
        {
            buildHeap(sortArr, i--, n);
        }
        while (n > 0) 
        {
            sortArr[n - 1] = pop(sortArr, n);
            n--;
        }
    }

    static void buildHeap(int[] sortArr, int ind, int size) 
    {
        int left = leftNode(ind);
        int right = rightNode(ind);
        int index = ind;
    
        if (left < size && sortArr[left] > sortArr[index]) index = left;
        if (right < size && sortArr[right] > sortArr[index]) index = right;
    
        if (index != ind) {
            swap(sortArr, ind, index);
            buildHeap(sortArr, index, size);
        }
    }

    static int pop(int[] sortArr, int size) 
    {
        if (size <= 0) 
            return -1;
        int top = sortArr[0];
        sortArr[0] = sortArr[size-1];
        buildHeap(sortArr, 0, size - 1);
        return top;
    }

    static void swap(int[] sortArr, int x, int y)
    {
        int temp = sortArr[x];
        sortArr[x] = sortArr[y];
        sortArr[y] = temp;
    }

    static int leftNode(int x)
    {
        return (2*x + 1);
    }
    
    static int rightNode(int x) 
    {
        return (2*x + 2);
    }

}