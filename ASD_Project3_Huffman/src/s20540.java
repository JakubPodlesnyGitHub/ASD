import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class s20540 {
    public static void main(String[] args) throws FileNotFoundException {
        ReadFile readFile = new ReadFile("input3");
        readFile.readFile1();
        Huffman huffman = new Huffman(readFile.getBigTrees());
        huffman.makeHuffamn();
    }
}

class Tree {
    private Node root;

    public Tree(int count, char character) {
        root = new Node(count, character);
    }

    public Tree(Tree tree1, Tree tree2) {
        root = new Node(tree1.getRoot(), tree2.getRoot());
    }

    public Node getRoot() {
        return root;
    }

    public void createCodesForLetters() {
        if (root != null) {
            root.createCodesForLetters("");
        }
    }
}

class Huffman {
    BigTreesHeap bigTreesHeap;

    public Huffman(BigTreesHeap bigTreesHeap) {
        this.bigTreesHeap = bigTreesHeap;
    }

    public void creteHeap() {
        bigTreesHeap.makeHeap();
    }

    public void makeHuffamn() {
        creteHeap();
        Tree tree1;
        Tree tree2;
        while (bigTreesHeap.getHowManyTrees() > 1) {
            tree1 = bigTreesHeap.extractMinTree();
            tree2 = bigTreesHeap.extractMinTree();
            Tree newTree = new Tree(tree1, tree2);
            bigTreesHeap.insert(newTree);
        }

        bigTreesHeap.getFirst().createCodesForLetters();
    }

}

class Node {
    private int count;
    private char character;
    private Node nodeL;
    private Node nodeR;

    public Node(int count, char character) {
        this.count = count;
        this.character = character;
        nodeL = null;
        nodeR = null;
    }

    public Node(Node nodeL, Node nodeR) {
        this.count = nodeL.count + nodeR.count;
        this.nodeL = nodeL;
        this.nodeR = nodeR;
    }

    public void createCodesForLetters(String path) {
        if (nodeL != null) {
            nodeL.createCodesForLetters(path + '0');
        }
        if (nodeR != null) {
            nodeR.createCodesForLetters(path + '1');
        }

        if (nodeL == null && nodeR == null) {
            System.out.println(character + " " + path);
        }
    }

    public int getCount() {
        return count;
    }
}

class BigTreesHeap {
    private Tree[] treesTab;
    private int howManyTrees;

    public BigTreesHeap() {
        treesTab = new Tree[52];
        howManyTrees = 0;
    }

    public void addTreeToTab(Tree tree) {
        treesTab[howManyTrees] = tree;
        howManyTrees++;
    }

    public void makeHeap() {
        for (int i = howManyTrees / 2; i >= 0; i--) {
            downHeap(i);
        }
    }

    public void downHeap(int parent) {
        int nodeLIndex = 2 * parent + 1;
        int nodeRIndex = 2 * parent + 2;
        int childTree;
        Tree tmpChange;
        if (howManyTrees <= nodeLIndex) {
            return;
        } else if (howManyTrees <= nodeRIndex) {
            childTree = nodeLIndex;
        } else {
            if (treesTab[nodeLIndex].getRoot().getCount() > treesTab[nodeRIndex].getRoot().getCount()) {
                childTree = nodeRIndex;
            } else {
                childTree = nodeLIndex;
            }
        }

        if (treesTab[parent].getRoot().getCount() > treesTab[childTree].getRoot().getCount()) {
            tmpChange = treesTab[parent];
            treesTab[parent] = treesTab[childTree];
            treesTab[childTree] = tmpChange;
            downHeap(childTree);
        }
    }

    public void upHeap(int child) {
        int parent = (child - 1) / 2;
        Tree changeTMP;
        if (treesTab[child].getRoot().getCount() < treesTab[parent].getRoot().getCount()) {
            changeTMP = treesTab[child];
            treesTab[child] = treesTab[parent];
            treesTab[parent] = changeTMP;
            upHeap(parent);
        }
    }

    public Tree extractMinTree() {
        Tree min = treesTab[0];
        Tree lastInTab = treesTab[howManyTrees - 1];
        treesTab[0] = lastInTab;
        howManyTrees--;
        downHeap(0);
        return min;
    }

    public void insert(Tree tree) {
        treesTab[howManyTrees] = tree;
        howManyTrees++;
        upHeap(howManyTrees - 1);
    }

    public Tree getFirst() {
        return treesTab[0];
    }

    public int getHowManyTrees() {
        return howManyTrees;
    }
}

class ReadFile {
    private String fileName;
    private BigTreesHeap heap;

    public ReadFile(String fileName) {
        this.fileName = fileName;
        heap = new BigTreesHeap();
    }

    public void readFile1() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(fileName));
        String[] tmp;
        char characterFromFile;
        int count;
        while (scanner.hasNextLine()) {
            tmp = scanner.nextLine().split("\\s");
            characterFromFile = tmp[0].charAt(0);
            count = Integer.parseInt(tmp[1]);
            heap.addTreeToTab(new Tree(count, characterFromFile));
        }
        scanner.close();
    }

    public BigTreesHeap getBigTrees() {
        return heap;
    }
}
