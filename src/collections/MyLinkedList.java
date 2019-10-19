package collections;

public class MyLinkedList implements MyCollection {

    /**
     * Ajoute un élément à la fin de la liste
     *
     * @param element élément à ajouter
     */
    @Override
    public void add(Comparable element) {

    }

    /**
     * Ajoute un élément à la liste dans un indice donné
     *
     * @param index   indice d'insertion de l'élément
     * @param element élément à insérer
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    @Override
    public void add(int index, Comparable element) {

    }

    /**
     * Obtenir un élément
     *
     * @param index indice de l'élément à récupérer
     * @return l'élément voulu
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    @Override
    public Comparable get(int index) {
        return null;
    }

    /**
     * Remplacer un élément par un autre
     *
     * @param index   indice de l'élément à remplacer
     * @param element élément à stocker
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    @Override
    public void set(int index, Comparable element) {

    }

    /**
     * Supprimer un élément
     *
     * @param element élément à supprimer de la liste
     */
    @Override
    public void remove(Comparable element) {

    }

    /**
     * Supprimer un élément
     *
     * @param index indice de l'élément à supprimer
     */
    @Override
    public void remove(int index) {

    }

    /**
     * Vider la liste
     */
    @Override
    public void clear() {

    }

    /**
     * Obtenir la taille de la liste
     *
     * @return taille de la liste
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Savoir si la liste est vide
     *
     * @return true si size == 0, si non, retourne false
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Déterminer si la liste contient un élément
     *
     * @param element élément à chercher
     * @return true si la liste contient l'élément
     */
    @Override
    public boolean contains(Comparable element) {
        return false;
    }

    /**
     * Trouver un élément dans la liste.
     * Si plusieurs fois le même élément, l'indice retourné est celui de la première instance trouvée
     *
     * @param element élément à trouver dans la liste
     * @return indice de l'élément trouvé. Retourne -1 si l'élément n'est pas trouvé.
     */
    @Override
    public int indexOf(Comparable element) {
        return 0;
    }

    /**
     * Trier la liste en utilisant l'algorithme de tri "insertion sort"
     */
    @Override
    public void insertionSort() {

    }

    /**
     * Trier la liste en utilisant l'algorithme de tri "tri par fusion"
     */
    @Override
    public void fusionSort() {

    }

}
