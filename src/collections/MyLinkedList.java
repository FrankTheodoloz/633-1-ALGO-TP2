package collections;

public class MyLinkedList implements MyCollection {

    private class Element {
        private Element previousElement, nextElement;
        private Comparable element;

        private Element(Element previousElement, Comparable element, Element nextElement) {
            this.previousElement = previousElement;
            this.element = element;
            this.nextElement = nextElement;
        }
    }

    private Element firstElement;
    private Element lastElement;
    private int size;

    public MyLinkedList() {
        this.firstElement = this.lastElement = null;
        size = 0;
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
        // the new last element has for previous element the current last element and no next element
        lastElement = new Element(lastElement, element, null);
        // if there is no first element, the first element is this new element
        if (firstElement == null) {
            firstElement = lastElement;
        } else {
            // update nextElement of the old lastElement
            lastElement.previousElement.nextElement = lastElement;
        }
        size++;

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
        Element afterElement = getElement(index); // element after the newElement inserted
        Element newElement = new Element(null, element, null);

        newElement.nextElement = afterElement;
        newElement.previousElement = afterElement.previousElement;

        if (afterElement.previousElement == null) {
            firstElement = newElement;
        } else {
            afterElement.previousElement.nextElement = newElement;
        }
        afterElement.previousElement = newElement;

        size++;
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
        return getElement(index).element;
    }

    /**
     * Obtenir un élément Element
     *
     * @param index indice de l'élément à récupérer
     * @return l'élément voulu
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    private Element getElement(int index) throws IndexOutOfBoundsException {
        checkIndex(index);
        Element e;

        if (index < size / 2) {
            // start from first
            e = firstElement;
            while (index-- > 0) {
                e = e.nextElement;
            }
        } else {
            // start from end
            e = lastElement;
            while (++index < size) {
                e = e.previousElement;
            }
        }
        return e;
    }

    /**
     * Remplacer un élément par un autre
     *
     * @param index   indice de l'élément à remplacer
     * @param element élément à stocker
     * @throws IndexOutOfBoundsException lance une exception si l'indice est négatif ou supérieur à la taille de la liste
     */
    @Override
    public void set(int index, Comparable element) throws IndexOutOfBoundsException {
        Element e = getElement(index);
        e.element = element;
    }

    /**
     * Supprimer un élément
     *
     * @param element élément à supprimer de la liste
     */
    @Override
    public void remove(Comparable element) {

        // decrement size to know if the list will be empty
        if (size - 1 == 0) {
            firstElement = lastElement = null; // list is empty
        } else if (element.equals(firstElement.element)) {
            firstElement = firstElement.nextElement;
            firstElement.previousElement = null;
        } else if (element.equals(lastElement.element)) {
            lastElement = lastElement.previousElement;
            lastElement.nextElement = null;
        } else {

            Element e = firstElement.nextElement; // starting from 2nd element
            while (e != null) {
                if (element.equals(e.element)) {
                    //update previous element
                    e.previousElement.nextElement = e.nextElement;
                    // update next element
                    e.nextElement.previousElement = e.previousElement;
                }
                e = e.nextElement;
            }
        }
        size--;
    }

    /**
     * Supprimer un élément
     *
     * @param index indice de l'élément à supprimer
     */
    @Override
    public void remove(int index) {
        if (size - 1 == 0) {
            firstElement = lastElement = null; // list is empty
        } else if (index == 0) { // first element
            firstElement = firstElement.nextElement;
            firstElement.previousElement = null;
        } else if (index == size - 1) { // last element
            lastElement = lastElement.previousElement;
            lastElement.nextElement = null;
        } else {
            Element e = getElement(index);

            //update previous element
            e.previousElement.nextElement = e.nextElement;

            // update next element
            e.nextElement.previousElement = e.previousElement;
        }
        size--;
    }

    /**
     * Vider la liste
     */
    @Override
    public void clear() {
        this.firstElement = this.lastElement = null;
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
        Element e = firstElement;
        while (e != null) {
            if (element.equals(e.element)) {
                return true;
            }
            e = e.nextElement;
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
        int index = 0;

        Element e = firstElement;
        while (e != null) {
            if (element.equals(e.element)) {
                return index;
            }
            index++;
            e = e.nextElement;
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

        triFusion(this, 0, size() - 1);

    }

    private void triFusion(MyLinkedList list, int premier, int dernier) {
        if (premier < dernier) { // il n'y a plus qu'un seul élément
            int milieu = premier + (dernier - premier) / 2; // on cherche le milieu du tableau (division entière ex: 7/2 = 3)
            triFusion(list, premier, milieu); // on subdivise (récursion) le tableau depuis le premier élément jusqu'au milieu (ex: de 1 à 3)
            triFusion(list, milieu + 1, dernier); // on subdivise (récursion) le tableau depuis le premier élément  de la deuxième partie jusqu'à la fin(ex: de [3+1]=4 à 7)
            fusionner(list, premier, milieu, dernier); // on fusionne les tableaux lorsque les récursions sont terminées
        }
    }

    private void fusionner(MyLinkedList list, int premier, int milieu, int dernier) {

        // l'indice du début de la deuxième partie du tableau
        int premier2 = milieu + 1;

        // traitement de l'indice du milieu
//        if (list.get(milieu).compareTo(list.get(premier2)) <= 0) {
//            return;
//        }

        while (premier <= milieu && premier2 <= dernier) {

            // si le premier élément est déjà classé
            if (list.get(premier).compareTo(list.get(premier2)) <= 0) {
                premier++;
            } else {
                // sinon classement de l'élément premier2 avant le premier
                list.add(premier, list.get(premier2));
                list.remove(premier2);

                // mise à jour des index
                premier++;
                milieu++;
                premier2++;
            }
        }

    }

}
