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
        int count = 0;
        Element e = firstElement;
        Element newElement = new Element(null, element, null);

        // runs over all elements and count them until it matches the index
        while (e != null && count != index) {
            e = e.nextElement;
            count++;
        }
        //update with previous element
        e.previousElement.nextElement = newElement; // 1
        newElement.previousElement = e.previousElement; // 2

        // update with next element
        e.previousElement = newElement; // 3
        newElement.nextElement = e; // 4
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
        return e.element;

//        if (index == 0) {
//            remove(0);
//            e = firstElement;
//        } else if (index == size - 1) {
//            remove(size - 1);
//            e = lastElement;
//        } else {
//            // starting from 2nd element
//            int count = 1;
//            e = firstElement.nextElement;
//
//            while (e != null && count != index) {
//                if (count == index) {
//                    return e.element;
//                }
//
//                count++;
//                e = e.nextElement;
//            }
//            remove(index);
//        }
//        return e.element;
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
        checkIndex(index);
        // starting from 1st element
        int count = -1;
        Element e = firstElement;
        while (e != null && count != index) {
            count++;
            if (index == count) {
                e.element = element;
            }
            e = e.nextElement;
        }
    }

    /**
     * Supprimer un élément
     *
     * @param element élément à supprimer de la liste
     */
    @Override
    public void remove(Comparable element) {

        if (element.equals(firstElement.element)) {
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
        if (index == 0) {
            firstElement = firstElement.nextElement;
        } else if (index == size - 1) {
            lastElement = lastElement.previousElement;
            lastElement.nextElement = null;
        } else {
            // starting from 2nd element
            int count = 1;
            Element e = firstElement.nextElement;

            while (e != null && count != index) {
                count++;
                if (count == index) {
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
        // TODO
    }

    /**
     * Trier la liste en utilisant l'algorithme de tri "tri par fusion"
     */
    @Override
    public void fusionSort() {
        // TODO
    }

}
