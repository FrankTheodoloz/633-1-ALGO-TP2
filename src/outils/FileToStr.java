package outils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import collections.MyCollection;
import collections.MyArrayList;
import collections.MyLinkedList;

import domaine.Headline;

public class FileToStr {

    /**
     * Charger un fichier et ajouter les éléments dans une DoublyLinkedList
     *
     * @param filename Nom du fichier à charger
     * @return Une instance de MyLinkedList
     * @throws FileNotFoundException Lance une exception si le fichier n'est pas trouvé
     * @throws ParseException        Lance une exception si le format du fichier n'est pas valide
     */
    public MyLinkedList loadIntoDoublyLinkedList(String filename) throws FileNotFoundException, ParseException {
        MyCollection list = new MyLinkedList();
        return (MyLinkedList) scanFile(filename, list);
    }

    /**
     * Charger un fichier et ajouter les éléments dans une DoublyLinkedList
     *
     * @param filename     Nom du fichier à charger
     * @param number_lines Nombre de lignes à charger
     * @return Une instance de MyLinkedList
     * @throws FileNotFoundException    Lance une exception si le fichier n'est pas trouvé
     * @throws ParseException           Lance une exception si le format du fichier n'est pas valide
     * @throws IllegalArgumentException Si le nombre de lignes n'est pas supérieur à 0
     */
    public MyLinkedList loadIntoDoublyLinkedList(String filename, int number_lines) throws FileNotFoundException, ParseException, IllegalArgumentException {
        checkNumberLines(number_lines);
        MyCollection list = new MyLinkedList();
        return (MyLinkedList) scanFile(filename, list, number_lines);
    }

    /**
     * Charger un fichier et ajouter les éléments dans une MyArrayList
     *
     * @param filename Nom du fichier à charger
     * @return Une instance de MyArrayList
     * @throws FileNotFoundException Lance une exception si le fichier n'est pas trouvé
     * @throws ParseException        Lance une exception si le format du fichier n'est pas valide
     */
    public MyArrayList loadIntoArrayList(String filename) throws FileNotFoundException, ParseException {
        MyCollection list = new MyArrayList();
        return (MyArrayList) scanFile(filename, list);
    }

    /**
     * Charger un fichier et ajouter les éléments dans une MyArrayList
     *
     * @param filename     Nom du fichier à charger
     * @param number_lines Nombre de lignes à charger
     * @return Une instance de MyArrayList
     * @throws FileNotFoundException    Lance une exception si le fichier n'est pas trouvé
     * @throws ParseException           Lance une exception si le format du fichier n'est pas valide
     * @throws NoSuchElementException   Lance une exception si le format du fichier n'est pas valide
     * @throws IllegalArgumentException Si le nombre de lignes n'est pas supérieur à 0
     */
    public MyArrayList loadIntoArrayList(String filename, int number_lines) throws FileNotFoundException, ParseException, NoSuchElementException, IllegalArgumentException {
        checkNumberLines(number_lines);
        MyCollection list = new MyArrayList();
        return (MyArrayList) scanFile(filename, list, number_lines);
    }

    /**
     * Scanne le fichier
     *
     * @param filename Nom du fichier à charger
     * @throws ParseException        Lance une exception si le format du fichier n'est pas valide
     * @throws FileNotFoundException Lance une exception si le fichier n'est pas trouvé
     */
    private MyCollection scanFile(String filename, MyCollection list) throws ParseException, FileNotFoundException {

        Scanner f = new Scanner(new File(filename));

        // Saute le header
        if (f.hasNextLine()) {
            f.nextLine();
        }

        // Lit toutes les lignes du fichier
        while (f.hasNextLine()) {
            list.add(scanLine(f.nextLine()));
        }
        return list;
    }

    /**
     * Scanne n lignes du fichier
     *
     * @param filename Nom du fichier à charger
     * @throws FileNotFoundException  Lance une exception si le fichier n'est pas trouvé
     * @throws ParseException         Lance une exception si le format du fichier n'est pas valide
     * @throws NoSuchElementException Lance une exception si le format du fichier n'est pas valide
     */
    private MyCollection scanFile(String filename, MyCollection list, int number_lines) throws FileNotFoundException, ParseException, NoSuchElementException {

        Scanner f = new Scanner(new File(filename));

        // Saute le header
        if (f.hasNextLine()) {
            f.nextLine();
        }

        // Lit les n premières lignes du fichier
        while (f.hasNextLine() && number_lines-- > 0) {
            list.add(scanLine(f.nextLine()));
        }
        return list;
    }

    /**
     * Scanne une ligne et retourne un Headline
     *
     * @param line Une ligne du fichier
     * @return Une instance de Headline
     * @throws ParseException         Lance une exception si le format du fichier n'est pas valide
     * @throws NoSuchElementException Lance une exception si le format du fichier n'est pas valide
     */
    private Headline scanLine(String line) throws ParseException, NoSuchElementException {
        Scanner scannerLine = new Scanner(line).useDelimiter(",");
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = dateFormat.parse(scannerLine.next());
        String type = scannerLine.next();
        String headline = scannerLine.next();
        headline = headline.substring(1, headline.length() - 1);

        // Date date, String type, String headline
        return new Headline(date, type, headline);
    }

    /**
     * Vérifie le nombre de lignes
     *
     * @param number_lines Le nombre de lignes
     * @throws IllegalArgumentException Lance une exception si le nombre de lignes n'est pas supérieur à 0
     */
    private void checkNumberLines(int number_lines) throws IllegalArgumentException {
        if (number_lines <= 0) {
            throw new IllegalArgumentException();
        }
    }

}
