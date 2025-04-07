/**
 * Print out the names for which 100 or fewer babies were born in a chosen CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyBirths {
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        
        int sum = 0;
        int rank = getRank(year,name,gender);
        //System.out.println(rank);
        if (rank == 1) {
            return -1;
        } else {
            while (rank != 1) { 
                //System.out.println(rank-1);
                sum += getNumberPerRank(rank-1,gender);
                --rank;
            }
        }        
        return sum;
        
    }
    
    public int getNumberPerRank (int rank, String gender) {
        
        FileResource fr = new FileResource();
        int contador = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                if (rank == contador) {
                    return Integer.parseInt(rec.get(2));
                } else {
                    contador++;
                    
                }
            }
        }            
        return -1;
    } 
    
    public int getNumber (String name, String gender) {
        FileResource fr = new FileResource();
        int num = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender) && rec.get(0).equals(name) ) {
                return Integer.parseInt(rec.get(2));
            }
        }    //System.out.println(num);
        return -1;
    }    
    
    public double getAverageRank(String name, String gender) {
    
        DirectoryResource dr = new DirectoryResource();
        double contador = 0;
        double sum = 0;
        String nameF = "";
        for (File f : dr.selectedFiles() ) {
            
            ++contador;
            FileResource fr = new FileResource(f); 
            int current = getRankFiles(name, gender,fr);
            System.out.println(current);
            if (current == -1) {
                continue;
            } else {
                sum += current;
            }
        }
        
        return sum/contador;
    }
    
    
    public int yearOfHighestRank(String name, String gender) {
        
        DirectoryResource dr = new DirectoryResource();
        int min = 0;
        String nameF = "";
        for (File f : dr.selectedFiles() ) {
            
            FileResource fr = new FileResource(f); 
            int current = getRankFiles(name, gender,fr);
            //System.out.println(current);
            if (current == -1) {
                continue;
            } else if (min == 0) {
                min = current;
                nameF = f.getName();
            } else {
                if (current < min) {
                    min = current;
                    nameF = f.getName();                                       
                }
            }
        }
        
        String cadena = nameF;

        // Encontrar el índice del primer dígito
        int indiceInicio = 0;
        while (indiceInicio < cadena.length() && !Character.isDigit(cadena.charAt(indiceInicio))) {
            indiceInicio++;
        }
        
        // Encontrar el índice del primer carácter no numérico después del número
        int indiceFin = indiceInicio;
        while (indiceFin < cadena.length() && Character.isDigit(cadena.charAt(indiceFin))) {
            indiceFin++;
        }
        
        // Extraer la subcadena que representa el número
        String numero = cadena.substring(indiceInicio, indiceFin);
        //System.out.println("Número encontrado: " + numero);
        //System.out.println(nameF);
        return Integer.parseInt(numero);
    }
    
    public int getRankFiles (String name, String gender, FileResource fr) {
        int contador = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    return contador;
                } else {
                    contador++;
                }
            }
        }
        return -1;
    }
    
    public String getNameFiles (int rank, String gender, FileResource fr) {
        int contador = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                if (contador == rank) {
                    return rec.get(0);
                } else contador++;
            }
        }
        return "NO NAME";
    }
    
    public void testGetNumberPerRank() {
        String gender = "M";
        int rank = 4;
        int total = getNumberPerRank(rank,gender);
        System.out.println(total);
    }
    
    public void testGetNumber() {
        String name = "Ethan";
        String gender = "M";
        int total = getNumber(name,gender);
        System.out.println(total);
    }
    
    public void testGetTotalBirthsRankedHigher() {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int sum = getTotalBirthsRankedHigher(year,name,gender);
        System.out.println(sum);
    }
    
    public void testGetAverageRank() {
        String name = "Robert";
        String gender = "M";
        double avg = getAverageRank(name,gender);
        System.out.println(avg);
    }
    
    public void testYearOfHighestRank () {
        String name = "Mich";
        String gender = "M";
        int year = yearOfHighestRank(name,gender);
        System.out.println(year);
    }
    
    public void whatIsNameInYear(String name, int year,int newYear, String gender) {
        
        int rank = getRank(year,name,gender);
        String newName = getName(newYear,rank,gender);
        System.out.println(name + " born in " + year + " would be " +
                            newName + " if she was born in " + newYear);
        
    }
    
    public void testWhatIsNameInYear() {
        int year = 1974;
        int newYear = 2014;
        String name = "Owen";
        String gender = "M";
        whatIsNameInYear(name,year,newYear,gender);
        
    }
    
    public String getName (int year, int rank, String gender) {
        FileResource fr = new FileResource();
        int contador = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                if (contador == rank) {
                    return rec.get(0);
                } else contador++;
            }
        }
        return "NO NAME";
    }
        
    public void testGetName () {
        int year = 2014;
        int rank = 430;
        String gender = "M";
        String name = getName(year,rank,gender);
        System.out.println(name);
    }
    
    public int getRank (int year, String name, String gender) {
        FileResource fr = new FileResource();
        int contador = 1;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            
            if (rec.get(1).equals(gender)) {
                if (rec.get(0).equals(name)) {
                    return contador;
                } else {
                    contador++;
                }
            }
        }
        return -1;
    }
        
    public void testGetRank () {
        int year = 1974;
        String name = "Owen";
        String gender = "M";
        int rank = getRank(year,name,gender);
        System.out.println(rank);
    }
    
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths (FileResource fr) {
        
        int totalBoys = 0;
        int totalGirls = 0;
        int totalBirths = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            } else {
                totalGirls += numBorn;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls = " + totalGirls);
        System.out.println("total boys = " + totalBoys);
            
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
