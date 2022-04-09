import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class s20540 {
    public static void main(String[] args) {
        ReadFile readFile = new ReadFile(args[0]);
        readFile.readFile();
        ListClass listClass = readFile.getListClass();
        listClass.makeKOperations(readFile.getNumberOfOperations());
        listClass.printListFromPointer();
    }
}

class ReadFile {
    private Scanner scanner;
    private int numberOfOperations;
    private String fileName;
    private ListClass listClass;

    public ReadFile(String fileName) {
        this.fileName = fileName;
        this.listClass = new ListClass();
    }

    public void readFile() {
        try {
            scanner = new Scanner(new File(fileName));
            numberOfOperations = scanner.nextInt();
            while (scanner.hasNextInt()) {
                listClass.addToList(scanner.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumberOfOperations() {
        return numberOfOperations;
    }

    public ListClass getListClass() {
        return listClass;
    }

}

class ListClass {
    private Element firstElement;
    private int listSize;
    private Element pointer;
    private Element lastElementPointer;

    public ListClass() {
        firstElement = null;
        lastElementPointer = null;
        listSize = 0;
        pointer = null;
    }

    public void addToList(int number) {
        if (firstElement == null) {
            lastElementPointer = new Element(number);
            firstElement = lastElementPointer;
            pointer = firstElement;
            listSize++;
        } else {
            Element newElement = new Element(number);
            lastElementPointer.pointerNEXT = newElement;
            lastElementPointer = newElement;
            listSize++;
        }
    }

    public void add() {
        int x = pointer.getNumber();
        Element newElement = new Element(x - 1);
        newElement.pointerNEXT = pointer.pointerNEXT;
        pointer.pointerNEXT = newElement;
        listSize++;
        movePointer(x);
    }

    public void delete() {
        int x;
        if (pointer.pointerNEXT != null) {
            x = pointer.pointerNEXT.getNumber();
        } else {
            x = firstElement.getNumber();
        }

        if (listSize == 1) {
            firstElement = null;
            pointer = null;
            listSize = 0;
        } else {
            if (pointer.pointerNEXT != null) {
                pointer.pointerNEXT = pointer.pointerNEXT.pointerNEXT;
                listSize--;
                movePointer(x);
            } else {
                firstElement = firstElement.pointerNEXT;
                listSize--;
                movePointer(x);
            }
        }
    }

    public void movePointer(int x) {
        int r = 0;
        if (listSize > 0)
            r = x % listSize;

        for (int i = 0; i < r; i++) {
            pointer = pointer.pointerNEXT;
            if (pointer == null) {
                pointer = firstElement;
            }
        }
    }

    public void makeKOperations(int numberOfOperations) {
        for (int i = 0; i < numberOfOperations; i++) {
            if (listSize != 0) {
                if (pointer.getNumber() % 2 == 0) {
                    delete();
                } else {
                    add();
                }
            }
        }
    }

    public void printListFromPointer() {
        Element actualPointer = pointer;
        boolean tmp = false;
        if (firstElement != null) {
            while (pointer != actualPointer.pointerNEXT && !tmp) {
                System.out.print(actualPointer.getNumber() + " ");
                if (actualPointer.pointerNEXT == null) {
                    actualPointer = firstElement;
                    if (pointer == actualPointer) {
                        tmp = true;
                    }
                    if(pointer == actualPointer.pointerNEXT){
                        tmp = true;
                    }
                }
                if (!tmp) {
                    actualPointer = actualPointer.pointerNEXT;
                }
            }
            if (actualPointer != pointer) {
                System.out.print(actualPointer.getNumber());
            }
        }
    }

    class Element {
        private int number;
        public Element pointerNEXT;

        public Element(int number) {
            this.number = number;
            pointerNEXT = null;
        }

        public int getNumber() {
            return number;
        }
    }
}

