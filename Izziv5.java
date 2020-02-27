import java.util.Scanner;

// Testing class
public class Izziv5 {
    
    // Main
    public static void main(String[] args){
        
        // Initialize new scanner
        Scanner sc = new Scanner(System.in);

        // Print help
        System.out.println("Za urejanje po: \n\t* letnici rojstva vnesi 0\n\t* imenu vnesi 1\n\t* priimku: vnesi 2\nZa naraščajoče urejanje vnesi 0, za padajoče vnesi 1\n==========\n");
        
        // Get size
        System.out.println("Vnesi velikost");
        int n = Integer.parseInt(sc.next());  

        // Make an array of Oseba class
        Oseba[] osebe = new Oseba[n];
       
        
        try {
            // Fill the osebe array
            for (int i=0;i<n;i++){
                osebe[i] = new Oseba();
                osebe[i].setAtr(3);
                osebe[i].setDirection(0);
            }

            // Do tests
            while (true) {
                
                // Copy original array
                Oseba[] temp = new Oseba[n];
                for (int i=0;i<n;i++){
                    temp[i] = osebe[i];
                }
                
                // Get attribute to be sorted 
                System.out.println(arrToString(temp, -1));
                System.out.println("Urejanje po: ");
                int arg = Integer.parseInt(sc.next());       
                
                // Set attribute
                setAtr(arg, temp);
                
                // Get direction of sort
                System.out.println("Smer urejanja: ");
                int d = Integer.parseInt(sc.next());
                
                // Set direction
                setDirection(d, temp);                       
                
                // Sort the list
                bubbleSort(temp);

                // Set attribute to 3 so
                // It outputs all Oseba attributes
                setAtr(3, temp);

                // Exit check
                System.out.println("Za konec vpisi q, za ponovitev karkol drugega.");
                if (sc.next().equals("q")) {
                    break;
                }
            }
        
        // Catch any incorrectly entered values
        } catch (SizeError e){
            System.out.println(e);
        }
    }

    // Sets attribute for every Object in Oseba array
    public static void setAtr(int i, Oseba[] arr) throws SizeError{
        for (Oseba o: arr){
            o.setAtr(i);
        }
    }

    // Sets direction attribute for every Object in Oseba array
    public static void setDirection (int i, Oseba[] arr) throws SizeError{
        for (Oseba o: arr){
            o.setDirection(i);
        }
    }

    // Implements directional bubble sort
    public static Oseba[] bubbleSort(Oseba arr[]) throws SizeError{ 
        
        // Get array length
        int n = arr.length;

        // For each element in array
        for (int i = 0; i < n-1; i++) {
            
            // Variable to test if array is already sorted
            boolean sw = false;
            
            // For each other element in non-sorted part
            // n - i - 1 elements are unsorted
            for (int j = 0; j < n-i-1; j++) { 
                
                // Whether the sorting is ASC or DESC
                // A bit hacky way, but instructions are unclear
                // On how to implement this. 
                switch(arr[j].getDirection()){
                    case 0: 
                        
                        // ASC sorting
                        if (arr[j].compareTo(arr[j+1]) > 0){ 
                        
                            // Swap arr[j+1] and arr[i] 
                            Oseba temp = arr[j]; 
                            arr[j] = arr[j+1]; 
                            arr[j+1] = temp; 
                        
                            // Swap has occured
                            sw = true;
                        } 
                        break;
                    case 1: 
                        
                        // DESC sorting
                        if (arr[j].compareTo(arr[j+1]) < 0){ 
                            // Swap arr[j+1] and arr[i] 
                            Oseba temp = arr[j]; 
                            arr[j] = arr[j+1]; 
                            arr[j+1] = temp; 
                        
                            // Swap has occured
                            sw = true;
                        } 
                        break;
                    default: 
                        
                        // Direction error, should probably implement an Exception
                        System.out.println("Direction not found");
                }  
            }

            // Prints array
            System.out.println(arrToString(arr, arr.length-i-1));
            
            // If no changes occured
            // The array is already sorted
            // Return it
            if (!sw){
                return arr;
            }
        }
        
        // Return a sorted array
        return arr;
    }


    // Array to string
    public static String arrToString(Oseba[] arr, int line) { 
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<arr.length;i++){
            if (line == i){
                sb.append(" | ");
            } else if (i != 0) {
                sb.append(", ");
            }
            sb.append(arr[i].toString());
        }
        return sb.toString();
    } 
}


// Class Oseba
class Oseba{

    // Variables
    int letoR;
    String ime;
    String priimek;

    // Helpers
    static int atr;
    static int direction; 

    // Constructor
    public Oseba(){
        
        // Pool of names to choose from
        String[] imena = {"Winona", "Yuk", "Lorette", "Ying", "Iona", "Araceli", "Earlene", "Tiesha", "Sonya", "Tony", "Marcell", "Neomi", "Eddy", "Tom", "Malvina", "Cristobal", "Carola", "Kimberlee", "Elza", "Dirk", "Rasheeda", "Jed", "Luann", "Ardath", "Soraya", "Kourtney", "Lory", "Cherri", "Jim", "Petronila", "Corie", "Sueann", "Isabel", "Verdell", "Donetta", "Drucilla", "Temeka", "Glynda", "Lelah", "Ludivina", "Rafaela", "Genny", "Brandie", "Tangela", "Kerstin", "Gertha", "Ilse", "Heriberto", "Danuta", "Nigel"};
        
        // Pool of last names to choose from
        String[] priimki = {"Mendoza", "Parks", "Marquez", "Ingram", "Oconnor", "Perkins", "Mckinney", "Ray", "Ayers", "Owens", "Cherry", "Cantrell", "Escobar", "Duncan", "Kramer", "Mccarthy", "Novak", "Rodriguez", "Fernandez", "Phillips", "Sharp", "Buck", "Adams", "Sherman", "Butler", "Mcbride", "Benjamin", "Phelps", "Melton", "Huynh", "Daniels", "Stuart", "Gomez", "Wagner", "Nixon", "Cooley", "Green", "Zavala", "Morrow", "Kaufman", "Gentry", "Romero", "Schultz", "Downs", "Ryan", "Morris", "Petersen", "Barrera", "Villanueva", "Compton"};

        // Random year between 1950 and 2018
        letoR = 1950 + (int)(Math.random() * ((2018 - 1950) + 1));

        // Random first name and surname from imena or priimku respectively
        ime = imena[0 + (int)(Math.random() * ((imena.length-1) - 0) + 1)];
        priimek = priimki[0 + (int)(Math.random() * ((priimki.length-1) - 0) + 1)];
    }

    // toString method
    public String toString(){
        // Returns string value from Variables
        // dependant on atr attribute
        switch (atr){
            case 0: 
                return String.valueOf(letoR);
            case 1: 
                return ime;
            case 2: 
                return priimek;
            default: 
                // Pretty print
                return ime + " " + priimek + " " + String.valueOf(letoR);
        }
    }
    
    // compareTo method
    public int compareTo(Oseba o) throws SizeError {
        // Variable to compare by is dependent on atr attribute
        switch (this.atr){
            case 0: 
                return new Integer(letoR).compareTo(o.getLeto());
            case 1: 
                return ime.compareTo(o.getIme());
            case 2: 
                return priimek.compareTo(o.getPriimek());
            default: 
                // Throws exception in case of incorrect value
                throw new SizeError("int atr not defined or 3");
        }
    }

    // Setter for atr
    public void setAtr(int i) throws SizeError {
        if (0 > i || i > 3) {
            // if not 0 <= i <= 3 throw new exception
            throw new SizeError("incorrect i");
        } 
        this.atr = i;
    }
    
    // Getter for atr
    public int getAtr(){
        return atr;
    }

    // Setter for direction
    public void setDirection(int i) throws  SizeError{
        if (i < 0 || i > 1) {
            // if not 0 <= i =< 1 throw new Exception
            throw new SizeError("incorrect i");
        } 
        this.direction = i;
    }
    
    // Getter for direction
    public int getDirection(){
        return direction;
    }

    // Getter for letoR
    public int getLeto(){
        return letoR;
    }

    // Getter for ime
    public String getIme(){
        return ime;
    }

    // Getter for priimek
    public String getPriimek(){
        return priimek;
    }
    
}


// Comparable
interface Comparable {
    public int compareTo(Object o);
}

// Exception
class SizeError extends Exception{
    public SizeError(String message){
        super(message);
    }
}