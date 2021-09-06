package dat.un.Implementation;

import java.util.concurrent.ThreadLocalRandom;

class Node {
	int key, height;
	Node left, right;

	public Node(int d){
		key = d;
		height = 1;
	}
}

public class AVLTree {

	Node root;

	public boolean find(int key){
        root = find(root, key);
        if (root!= null)
            return true;
        else
            return false;
    }

	private Node find(Node root, int key){
	    // Root es nulo o se encontro la llave
	    if (root==null || root.key==key)
	        return root;

	    // La llave es mayor que la llave de root
	    if (root.key < key)
	       return find(root.right, key);

	    // La llave es menor que la llave de root
	    return find(root.left, key);
	}

	int height(Node N){
		if (N == null)
			return 0;
		return N.height;
	}

	// Funcion para hallar el maximo entre dos int
	int max(int a, int b){
		return (a > b) ? a : b;
	}

	public Node rightRotate(Node y){
		Node x = y.left;
		Node T2 = x.right;

		// Rotacion
		x.right = y;
		y.left = T2;

		// Actualizacion de alturas
		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		// Retorna nueva root
		return x;
	}

	public Node leftRotate(Node x){
		Node y = x.right;
		Node T2 = y.left;

		// Rotacion
		y.left = x;
		x.right = T2;

		// Actualizacion de alturas
		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		// Retorna nueva root
		return y;
	}

	//  Funcion para calcular el balance del nodo N
	int getBalance(Node N){
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	public Node insert(Node node, int key){
		// Insercion normal de BST
		if (node == null)
			return (new Node(key));

		if (key < node.key)
			node.left = insert(node.left, key);
		else if (key > node.key)
			node.right = insert(node.right, key);
		else // Llaves iguales no son insertadas
			return node;

		// Se actualiza la altura
		node.height = 1 + max(height(node.left), height(node.right));

		// Revisar balance del nodo
		int balance = getBalance(node);

		// Left Left Case
		if (balance > 1 && key < node.left.key)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && key > node.right.key)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && key > node.left.key){
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && key < node.right.key){
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		return node;
	}

	// Retorna el nodo con menor llave
	public Node minValueNode(Node node){
		Node current = node;

		// Ciclo hasta encontrar la llave mas izquierda
		while (current.left != null)
		current = current.left;

		return current;
	}

	public Node deleteNode(Node root, int key){
		// Eliminacion normal BST
		if (root == null)
			return root;

		// Navegacion del arbol
		if (key < root.key)
			root.left = deleteNode(root.left, key);
		else if (key > root.key)
			root.right = deleteNode(root.right, key);
		else{
			// Nodo con una rama o ninguna
			if ((root.left == null) || (root.right == null)){
				Node temp = null;
				if (temp == root.left)
					temp = root.right;
				else
					temp = root.left;

				// Sin hijos
				if (temp == null){
					temp = root;
					root = null;
				}
				else // Un hijo
					root = temp;
			}else{
				// Si nodo tiene dos hijos se escoje el siguiente menor
				Node temp = minValueNode(root.right);
				root.key = temp.key;
				// Eliminacion
				root.right = deleteNode(root.right, temp.key);
			}
		}

		// Si el arbol solo tiene un nodo
		if (root == null)
			return root;

		// Actualizar altura
		root.height = max(height(root.left), height(root.right)) + 1;

		// Revisar balance
		int balance = getBalance(root);

		// Left Left Case
		if (balance > 1 && getBalance(root.left) >= 0)
			return rightRotate(root);

		// Left Right Case
		if (balance > 1 && getBalance(root.left) < 0){
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Right Case
		if (balance < -1 && getBalance(root.right) <= 0)
			return leftRotate(root);

		// Right Left Case
		if (balance < -1 && getBalance(root.right) > 0){
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	// Funciones para imprimir pre, pos, in y level order

	public void preOrder(){
        preOrder(root);
    }

	private void preOrder(Node node)
	{
		if (node != null)
		{
			System.out.print(node.key + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	public void posOrder(){
        posOrder(root);
    }

	private void posOrder(Node node)
	{
		if (node != null)
		{
			posOrder(node.left);
			posOrder(node.right);
			System.out.print(node.key + " ");
		}
	}

	public void inOrder(){
        inOrder(root);
    }

	private void inOrder(Node node)
	{
		if (node != null)
		{
			inOrder(node.left);
			System.out.print(node.key + " ");
			inOrder(node.right);
		}
	}

	public void levelOrder()
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printCurrentLevel(root, i);
    }

	private void printCurrentLevel (Node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.key + " ");
        else if (level > 1)
        {
            printCurrentLevel(root.left, level-1);
            printCurrentLevel(root.right, level-1);
        }
    }

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public double countNode(Node root){
	        if(root==null)
	            return 0;
	        return 1 + countNode(root.left) + countNode(root.right);
	}

	 public double addTree(Node root){
	     if (root == null)
	         return 0;
	     return (root.key + addTree(root.left) +addTree(root.right));
	 }

	public double getMean() {
		return addTree(root)/countNode(root);
	}
}
/*
class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 1000;
		System.out.println("Numero de datos: "+ n);

		System.out.println();

		AVLTree tree = new AVLTree();

		long start1 = System.nanoTime();

		for(int i = 0; i < (n/10); i++) {
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
			tree.root = tree.insert(tree.root, ThreadLocalRandom.current().nextInt(0, 10000000 + 1));
		}
		long end1 = System.nanoTime();
		System.out.println("Tiempo de insert(Node,int): "+ (end1-start1));

		System.out.println();

		long start2 = System.nanoTime();
		tree.root = tree.deleteNode(tree.root, 10);
		long end2 = System.nanoTime();
		System.out.println("Tiempo de deleteNode(Node,int): "+ (end2-start2));

		System.out.println();

		long start3 = System.nanoTime();
		System.out.println(tree.find(99));
		long end3 = System.nanoTime();
		System.out.println("Tiempo de find(int): "+ (end3-start3));

		System.out.println();




	}

}
*/
