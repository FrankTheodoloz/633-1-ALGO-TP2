package collections;

public class MyArrayList implements MyCollection {

    private static int TAB_SIZE = 10;
    private Comparable[] list;
    private int size;

    public MyArrayList() {
        list = new Comparable[TAB_SIZE];
        size = 0;
    }

    /**
     * Recopie le tableau dans un tableau 50% plus grand
     */
    private void resize() {
        // creates a new list
        Comparable[] newList = new Comparable[list.length * 150 / 100];

        // runs over all elements and copies in new list
        for (int i = 0; i < size; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    /**
     * vérifie si l'index est hors limite
     *
     * @param index index demandé
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    private void checkIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Ajoute un élément à la fin de la liste
     *
     * @param element élément à ajouter
     */
    @Override
    public void add(Comparable element) {
        // checks whether the list is big enough
        if (size >= list.length) {
            resize();
        }
        list[size++] = element;
    }

    /**
     * Ajoute un élément à la liste dans un indice donné
     *
     * @param index   indice d'insertion de l'élément
     * @param element élément à insérer
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    @Override
    public void add(int index, Comparable element) throws IndexOutOfBoundsException {
        checkIndex(index);
        Comparable[] newList;

        // checks whether the list is big enough
        if (size >= list.length) {
            newList = new Comparable[list.length * 150 / 100];
        } else {
            newList = new Comparable[list.length];
        }

        for (int i = 0; i < size; i++) {

            if (i < index) {
                newList[i] = list[i];
            } else if (index == i) {
                newList[i] = element;
            } else {
                newList[i] = list[i - 1];
            }
        }
        list = newList;
    }

    /**
     * Obtenir un élément
     *
     * @param index indice de l'élément à récupérer
     * @return l'élément voulu
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    @Override
    public Comparable get(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        return list[index];
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
        checkIndex(index);
        list[index] = element;
    }

    /**
     * Supprimer un élément
     *
     * @param element élément à supprimer de la liste
     */
    @Override
    public void remove(Comparable element) {
        int index = indexOf(element);
        remove(index);
    }

    /**
     * Supprimer un élément
     *
     * @param index indice de l'élément à supprimer
     */
    @Override
    public void remove(int index) {
        // starting index, shifts elements to left
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        // clear last element in double
        list[size - 1] = null;
        size--;
    }

    /**
     * Vider la liste
     */
    @Override
    public void clear() {
        list = new Comparable[TAB_SIZE];
        size = 0;
    }

    /**
     * Obtenir la taille de la liste
     *
     * @return taille de la liste
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Savoir si la liste est vide
     *
     * @return true si size == 0, si non, retourne false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Déterminer si la liste contient un élément
     *
     * @param element élément à chercher
     * @return true si la liste contient l'élément
     */
    @Override
    public boolean contains(Comparable element) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                return true;
            }
        }
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
        for (int i = 0; i < size; i++) {
            if (list[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Trier la liste en utilisant l'algorithme de tri "insertion sort"
     */
    @Override
    public void insertionSort() {
        for (int i = 1; i < size; i++) { // parcours du tableau depuis le 2è element
            Comparable key = get(i); // on prend l'élément à comparer
            int j = i - 1;  // l'indice précédent l'élément
            while (j >= 0 && get(j).compareTo(key) > 0) { // tant que l'élément à déplacer vers la droite est plus grand que l'élément à comparer && que l'indice >= 0
                set(j + 1, get(j)); // on déplace l'élément vers la droite
                j--; // on passe à l'élément précédent (pour la première place j devient -1 = fin boucle)
            }
            set(j + 1, key); // (pour la première place j[-1] +1 = 0)
        }
    }

    /**
     * Trier la liste en utilisant l'algorithme de tri "tri par fusion"
     */
    @Override
    public void fusionSort() {
        // TODO
    }

}
