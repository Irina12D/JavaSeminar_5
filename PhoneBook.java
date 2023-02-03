import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    
    private Map<String, List<Integer>> phonebook;

    public PhoneBook() {
        phonebook = new HashMap<>();
    }

    private String spacesCompress(String input)
    {
        char[] chars = input.trim().toCharArray();
        StringBuilder s = new StringBuilder();
        int n = chars.length;
        for (int i = 0; i < n-1; i++) {
            char c = chars[i];
            if (c != ' ' || chars[i+1] !=' ')
                s.append(chars[i]);
        }
        s.append(chars[n - 1]);
        return s.toString();
    }

    private List<Integer> contactNumbers(String input){
        List<Integer> phoneNumbers = new ArrayList<>();
        String[] separation = spacesCompress(input).split(" ");
        for (String number: separation)
        {
            try
            {
                phoneNumbers.add(Integer.parseInt(number));                
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("  Invalid number format");
                phoneNumbers.clear();
            }
        }
        return phoneNumbers;
    }

    void addContact(String name, String numbers){
        name = name.trim();
        List<Integer> contacts = contactNumbers(numbers);
        if (contacts.size()==0)
            {
                System.out.println("  Input error: the number is entered incorrectly");
                return;
            }
        if(phonebook.containsKey(name))
        {
            List<Integer> list = phonebook.get(name);
            list.addAll(contacts);
            System.out.println("  Numbers added successfully");
        }
        else
        {
            phonebook.put(name, contacts);
            System.out.println("  Contact added successfully");
        } 
    }

    void viewContact(String nameContact){
        if(! phonebook.containsKey(nameContact))
            {
                System.out.println("  " + nameContact + " - there is no such contact in the phone book");
                return;
            }
        System.out.println("  " + nameContact + ":" + phonebook.get(nameContact));
    }
    
    void viewBook(){
        if(! phonebook.isEmpty())
            {
                System.out.println("  Viewing contacts:");
                for(Map.Entry<String, List<Integer> > entry: phonebook.entrySet()) {
                    String key = entry.getKey();
                    List<Integer> nums = entry.getValue();
                    System.err.println("   " + key + ": " + nums);
                    System.out.println();
                 }
                return;
            }
        System.out.println("  There are no entries in the phone book");
    }    
}
