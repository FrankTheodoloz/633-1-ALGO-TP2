import collections.MyArrayList;
import collections.MyCollection;
import collections.MyLinkedList;
import outils.FileToStr;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.util.NoSuchElementException;

public class Main {

    // paramètre pour afficher le résultat
    private static boolean printResults = true;

    public static void main(String[] args) {

        try {
//            useMyArrayList("insertion", "irishtimes-date-text.csv", 1000);
//            useMyArrayList("fusion", "irishtimes-date-text.csv", 100);
//            useMyLinkedList("insertion", "irishtimes-date-text.csv", 1000);
            useMyLinkedList("fusion", "irishtimes-date-text.csv", 1000);

        } catch (FileNotFoundException e) {
            System.out.println("Erreur : le fichier spécifié est introuvable");
        } catch (ParseException | NoSuchElementException e) {
            System.out.println("Erreur : le format de fichier est erroné");
        } catch (InvalidParameterException e) {
            System.out.println("Erreur: le type de tri est incorrect (insertion ou fusion)");
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: le nombre de lignes doit être supérieur à 0");
        }

    }

    private static void useMyLinkedList(String mode, String filename, int number_lines) throws FileNotFoundException, ParseException, InvalidParameterException {
        FileToStr fileToStr = new FileToStr();
        MyCollection list = new MyLinkedList();

        if (number_lines == -1) {
            list = fileToStr.loadIntoArrayList(filename);

        } else {
            list = fileToStr.loadIntoArrayList(filename, number_lines);
        }

        startSort(mode, list);
    }

    private static void useMyArrayList(String mode, String filename, int number_lines) throws FileNotFoundException, ParseException, InvalidParameterException {
        FileToStr fileToStr = new FileToStr();
        MyCollection list = new MyArrayList();

        if (number_lines == -1) {
            list = fileToStr.loadIntoDoublyLinkedList(filename);

        } else {
            list = fileToStr.loadIntoDoublyLinkedList(filename, number_lines);
        }

        startSort(mode, list);
    }

    private static void startSort(String mode, MyCollection list) throws InvalidParameterException {
        if (mode.equals("insertion")) {
            list.insertionSort();
        } else if (mode.equals("fusion")) {
            list.fusionSort();
        } else {
            throw new InvalidParameterException();
        }

        if (printResults) {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }

}
